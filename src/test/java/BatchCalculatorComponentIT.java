import calculator.Calculator;
import functionality.SolutionFormatter;
import model.CalculationType;
import model.CalculatorModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class BatchCalculatorComponentIT {

    BatchFile classUnderTest;
    String FIXTURE_FILE;

    @Spy
    private BatchCalculatorFileReader batchCalculatorFileReader;

    @Spy
    private Calculator calculator;

    @Spy
    private SolutionFormatter solutionFormatter;

    @Before
    public void setUp() throws Exception {
        classUnderTest = new BatchFile(batchCalculatorFileReader, calculator, solutionFormatter);
        FIXTURE_FILE =
                Paths.get(getClass().getClassLoader().getResource("data/calculations").toURI()).toString();
    }

    @After
    public void tearDown() throws Exception {
        classUnderTest = null;
    }

    @Test
    public void calculate_shouldOpenTheRightFile_whenGivenAPath() throws IOException {
        List<CalculatorModel> calculatorModelList = classUnderTest.calculateFromFile(FIXTURE_FILE);
        // ASSERT we get back usable models
        verify(batchCalculatorFileReader).read(FIXTURE_FILE);
    }


    @Test
    public void calculateFromFile_shouldHaveTheCorrectSizeOfOperations_whenGivenAPath() throws IOException {
        // Act
        List<CalculatorModel> calculatorModelList = classUnderTest.calculateFromFile(FIXTURE_FILE);
        // Assert
        assertThat(calculatorModelList, hasSize(2));
        // verify
    }

    @Test
    public void calculateFromFile_shouldReturnTheCorrectAnswer_forDivision() throws IOException {
        // Act
        List<CalculatorModel> calculatorModelList = classUnderTest.calculateFromFile(FIXTURE_FILE);
        // Assert
        Integer solution = calculatorModelList.get(0).getSolution();
        // verify
        assertThat(solution, is(equalTo(3)));
    }

    @Test
    public void calculateFromFile_shouldCorrectlyAddWithTheCalculator_forDivision() throws IOException {
        // Act
        List<CalculatorModel> calculatorModelList = classUnderTest.calculateFromFile(FIXTURE_FILE);
        // Assert
        Integer solution = calculatorModelList.get(0).getSolution();
        // verify
        Mockito.verify(calculator, times(1)).divide(6, 2);
    }


    @Test
    public void calculateFromFile_shouldReturnTheCorrectAnswer_forAddition() throws IOException {
        // Act
        List<CalculatorModel> calculatorModelList = classUnderTest.calculateFromFile(FIXTURE_FILE);
        // Assert
        Integer solution = calculatorModelList.get(1).getSolution();
        // verify
        assertThat(solution, is(equalTo(7)));
    }

    @Test
    public void calculateFromFile_shouldCorrectlyAddWithTheCalculator_forAddition() throws IOException {
        // Act
        List<CalculatorModel> calculatorModelList = classUnderTest.calculateFromFile(FIXTURE_FILE);
        // Assert
        Integer solution = calculatorModelList.get(0).getSolution();
        // verify
        Mockito.verify(calculator, times(1)).add(3, 4);
    }

    @Test
    public void calculateFromFile_shouldPassBackTheCorrectModel_forCalculations() throws IOException {
        // ACT
        List<CalculatorModel> solutions = classUnderTest.calculateFromFile(FIXTURE_FILE);
        CalculatorModel answer = solutions.get(0);

        // ASSERT we get back usable models
        assertThat(answer, allOf(
                hasProperty("leftArgument", is(6)),
                hasProperty("rightArgument", is(2)),
                hasProperty("type", is(equalTo(CalculationType.DIVISION))),
                hasProperty("solution", is(3))));
    }
}
