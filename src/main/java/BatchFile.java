import calculator.Calculator;
import functionality.SolutionFormatter;
import functionality.Solver;
import model.CalculatorModel;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Supports solving calculations provided in a batch file.
 */

@Service
public class BatchFile implements Solver {

    // TODO Although I'm sticking to vanilla Java, the curious student,
    // can check out Project Lombok which saves you having to
    // write customer getters and setters: https://projectlombok.org/
    private BatchCalculatorFileReader batchCalculatorFileReader;
    private Calculator calculator;
    private SolutionFormatter formatter;
    /**
     * Constructor
     *
     * @param batchCalculatorFileReader instance used to read the batch file
     * @param calculator                instance used to solve problems
     */
    public BatchFile(BatchCalculatorFileReader batchCalculatorFileReader, Calculator calculator
            , SolutionFormatter formatter) {
        this.batchCalculatorFileReader = batchCalculatorFileReader;
        this.calculator = calculator;
        this.formatter = formatter;
    }

    public List<CalculatorModel> calculateFromFile(String pathToFile) throws IOException {
        Stream<String> calculations = batchCalculatorFileReader.read(pathToFile);
        ArrayList<CalculatorModel> solutions = new ArrayList<>();
        calculations.forEach(calculation -> {
            CalculatorModel calculationModel = CalculatorModel.fromText(calculation);
            CalculatorModel solutionModel = solve(calculationModel, calculator, formatter);
            solutions.add(solutionModel);
        });
        return solutions;
    }


}


