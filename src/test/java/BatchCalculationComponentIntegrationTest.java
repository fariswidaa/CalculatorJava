import calculator.Calculator;
import functionality.SolutionFormatter;
import model.CalculationType;
import model.CalculatorModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class BatchCalculationComponentIntegrationTest {

    BatchFile cut;
    @Spy
    private Calculator calculator;
    @Spy
    private BatchCalculatorFileReader batchCalculatorFileReader;
    private String FIXTURE_FILE;

    @Mock
    private SolutionFormatter formatter;
    @Before
    public void setup() throws IOException, URISyntaxException {
        cut = new BatchFile(batchCalculatorFileReader, calculator, formatter);
        // Munged for windows
        FIXTURE_FILE =
                Paths.get(getClass().getClassLoader().getResource("data/calculations").toURI()).toString();
    }


    @Test
    public void calculateFromFile_shouldOpenTheRightFile_whenGivenAPath() throws IOException {
        // Act
        List<CalculatorModel> calculatorModelList = cut.calculateFromFile(FIXTURE_FILE);
        // Assert
        // verify
    }

    @Test
    public void calculateFromFile_shouldHaveTheCorrectSizeOfOperations_whenGivenAPath() throws IOException {
        // Act
        List<CalculatorModel> calculatorModelList = cut.calculateFromFile(FIXTURE_FILE);
        // Assert
        assertThat(calculatorModelList, hasSize(2));
        // verify
    }

    @Test
    public void calculateFromFile_shouldReturnTheCorrectAnswer_forDivision() throws IOException {
        // Act
        List<CalculatorModel> calculatorModelList = cut.calculateFromFile(FIXTURE_FILE);
        // Assert
        Integer solution = calculatorModelList.get(0).getSolution();
        // verify
        assertThat(solution, is(equalTo(3)));
    }

    @Test
    public void calculateFromFile_shouldCorrectlyAddWithTheCalculator_forDivision() throws IOException {
        // Act
        List<CalculatorModel> calculatorModelList = cut.calculateFromFile(FIXTURE_FILE);
        // Assert
        Integer solution = calculatorModelList.get(0).getSolution();
        // verify
        Mockito.verify(calculator, times(1)).divide(6, 2);
    }


    @Test
    public void calculateFromFile_shouldReturnTheCorrectAnswer_forAddition() throws IOException {
        // Act
        List<CalculatorModel> calculatorModelList = cut.calculateFromFile(FIXTURE_FILE);
        // Assert
        Integer solution = calculatorModelList.get(1).getSolution();
        // verify
        assertThat(solution, is(equalTo(7)));
    }

    @Test
    public void calculateFromFile_shouldCorrectlyAddWithTheCalculator_forAddition() throws IOException {
        // Act
        List<CalculatorModel> calculatorModelList = cut.calculateFromFile(FIXTURE_FILE);
        // Assert
        Integer solution = calculatorModelList.get(0).getSolution();
        // verify
        Mockito.verify(calculator, times(1)).add(3, 4);
    }

    @Test
    public void calculateFromFile_shouldPassBackTheCorrectModel_whenGivenACalculation() throws IOException {
        // Act
        List<CalculatorModel> calculatorModelList = cut.calculateFromFile(FIXTURE_FILE);
        // Assert
        Integer solution = calculatorModelList.get(1).getSolution();
        // verify
        assertThat(solution,
                allOf(
                        hasProperty(" leftArgument ", is(3)),
                        hasProperty(" rightArgument ", is(4)),
                        hasProperty(" solution ", is(7)),
                        hasProperty(" type ", is(CalculationType.ADDITION))
                ));
    }
}
