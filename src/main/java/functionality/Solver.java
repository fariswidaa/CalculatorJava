package functionality;

import calculator.Calculator;
import model.CalculationType;
import model.CalculatorModel;
import org.springframework.stereotype.Component;

@Component
public interface Solver {
    /**
     * Takes a definition of a calculation and solves it
     *
     * @param calculatorModel
     * @return
     */

    default CalculatorModel solve(CalculatorModel calculatorModel, Calculator calculator, SolutionFormatter formatter) {
        CalculationType type = calculatorModel.getType();

        Integer response = null;
        switch (type) {
            case ADDITION:
                response = calculator.add(
                        calculatorModel.getLeftArgument(),
                        calculatorModel.getRightArgument());
                break;

            case DIVISION:
                response = calculator.divide(
                        calculatorModel.getLeftArgument(),
                        calculatorModel.getRightArgument());
                break;
            case SUBTRACTION:
                response = calculator.subtract(
                        calculatorModel.getLeftArgument(),
                        calculatorModel.getRightArgument());
                break;

            case MULTIPLICATION:
                response = calculator.multiply(
                        calculatorModel.getLeftArgument(),
                        calculatorModel.getRightArgument());
                break;

            default:
                throw new UnsupportedOperationException("Unsupported calculations");
        }


        calculatorModel.setSolution(response);
        calculatorModel.setFormattedSolution(formatter.format(response));
        return calculatorModel;
    }


}