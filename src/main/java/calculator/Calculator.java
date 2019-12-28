package calculator;

import org.springframework.stereotype.Component;

@Component
public class Calculator {

    /**
     * @param i  The first number
     * @param i1 The second number
     * @return The sum of two numbers
     */
    public int add(int i, int i1) {
        return i + i1;
    }

    /**
     * @param i  The first number
     * @param i1 The second number
     * @return The subtraction of two numbers
     */
    public int subtract(int i, int i1) {
        return i - i1;
    }

    /**
     * @param divider
     * @param divisor
     * @return the Quotient of two numbers
     */
    public int divide(int divider, int divisor) {

        if (divisor == 0) {
            throw new IllegalArgumentException();
        }
        return divider / divisor;

    }

    /**
     * @param i  The first number
     * @param i1 The second number
     * @return The multiplication of two numbers
     */
    public int multiply(int i, int i1) {
        return i * i1;
    }
}
