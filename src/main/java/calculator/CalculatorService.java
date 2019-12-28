package calculator;

import functionality.SolutionFormatter;
import functionality.Solver;
import model.CalculatorModel;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService implements Solver {
    Calculator calculator;
    private SolutionFormatter formatter;

    public CalculatorService(Calculator calculator, SolutionFormatter formatter) {
        this.calculator = calculator;
        this.formatter = formatter;
    }

    public CalculatorModel calculate(CalculatorModel calculatorModel) {
        // FIXME : This really needs a custom exception
        if (null == calculatorModel) {
            return null;
        }

        return solve(calculatorModel, calculator, formatter);
    }
}

