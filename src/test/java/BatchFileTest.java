import calculator.Calculator;
import functionality.SolutionFormatter;
import model.CalculationType;
import model.CalculatorModel;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

/**
 * This Test file will be used to test
 * BatchFile class
 */
@RunWith(MockitoJUnitRunner.class)
@Ignore("The PowerMock are slow , so instead we're gonna use the Integration Test ")
public class BatchFileTest {
    // index of fixtures by type
    private static final int MULTIPLICATION_INDEX = 0;
    private static final int ADDITION_INDEX = 1;

    @Mock
    BatchCalculatorFileReader batchCalculatorFileReader;


    @Mock
    Calculator calculator;

    @Mock
    SolutionFormatter formatter;
    // The class we're testing
    BatchFile classUnderTest;

    @Before
    public void setup() throws IOException {
        // This is our fake file content
        Stream<String> calculations = Arrays.asList("3 x 2", "2 + 2").stream();

        // Let's make sure that the batch calculation service returns this
        when(batchCalculatorFileReader.read(Mockito.any(String.class))).thenReturn(calculations);

        // Set up the calculator
        when(calculator.add(2, 2)).thenReturn(4);
        when(calculator.multiply(3, 2)).thenReturn(6);

        classUnderTest = new BatchFile(batchCalculatorFileReader, calculator, formatter);
    }

    @Test
    public void calculateFromFile_shouldOpenTheRightFile_whenGivenAPath() throws IOException {
        // ACT
        List<CalculatorModel> actual = classUnderTest.calculateFromFile("/path/to/fake/file");

        // ASSERT we get back usable models
        verify(batchCalculatorFileReader).read("/path/to/fake/file");
    }

    @Test
    public void calculateFromFile_shouldReturnTwoSolutions_forTwoCalculations() throws IOException {
        // ACT
        List<CalculatorModel> actual = classUnderTest.calculateFromFile("/path/to/fake/fail");

        // ASSERT we get back usable models
        assertThat(actual, hasSize(2));
    }

    @Test
    public void calculateFromFile_shouldReturnTheCorrectAnswer_forAdditions() throws IOException {
        // ACT
        List<CalculatorModel> solutions = classUnderTest.calculateFromFile("/path/to/fake/fail");
        Integer answer = solutions.get(ADDITION_INDEX).getSolution();

        // ASSERT we get back usable models
        assertThat(answer, is(equalTo(4)));
    }

    @Test
    public void calculateFromFile_shouldCorrectlyAddWithTheCalculator_forAdditions() throws IOException {
        // ACT
        List<CalculatorModel> solutions = classUnderTest.calculateFromFile("/path/to/fake/fail");
        Integer answer = solutions.get(ADDITION_INDEX).getSolution();

        // ASSERT we get back usable models
        verify(calculator, times(1)).add(2, 2);
    }

    @Test
    public void calculateFromFile_shouldReturnTheCorrectAnswer_forMultiplication() throws IOException {
        // ACT
        List<CalculatorModel> solutions = classUnderTest.calculateFromFile("/path/to/fake/fail");
        Integer answer = solutions.get(MULTIPLICATION_INDEX).getSolution();

        // ASSERT we get back usable models
        assertThat(answer, is(equalTo(6)));
    }

    @Test
    public void calculateFromFile_shouldCorrectlyMultiplyWithTheCalculator_forProducts() throws IOException {
        // ACT
        List<CalculatorModel> solutions = classUnderTest.calculateFromFile("/path/to/fake/fail");
        Integer answer = solutions.get(ADDITION_INDEX).getSolution();

        // ASSERT we get back usable models
        verify(calculator, times(1)).multiply(3, 2);
    }

    @Test
    public void calculateFromFile_shouldPassbookTheCorrectModel_forCalculations() throws IOException {
        // ACT
        List<CalculatorModel> solutions = classUnderTest.calculateFromFile("/path/to/fake/fail");
        CalculatorModel answer = solutions.get(ADDITION_INDEX);

        // ASSERT we get back usable models
        assertThat(answer, allOf(
                hasProperty("leftArgument", is(2)),
                hasProperty("rightArgument", is(2)),
                hasProperty("type", is(equalTo(CalculationType.ADDITION))),
                hasProperty("solution", is(4))));
    }


}

