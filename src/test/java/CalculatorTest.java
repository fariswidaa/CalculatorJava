import calculator.Calculator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

// Test for Addition

// Test for Multiplication

// Test for Subtraction

// Test for Division
public class CalculatorTest {


    private Calculator classUnderTest;


    @Before
    public void setUp() {
        classUnderTest = new Calculator();
    }

    @After
    public void tearDown() {
        classUnderTest = null;
    }

    // ADDITION TESTS
    @Test
    public void add_shouldReturnTheSumOfTwoPositiveNumbers_whenGivenTwoNumbers() {
        // Arrange
        int expected = 3;
        // Act
        int result = classUnderTest.add(1, 2);
        // Assert
        assert (expected == result);
    }

    @Test
    public void add_shouldReturnTheSumOfTwoNegativeNumbers_whenGivenTwoNumbers() {
        // Arrange
        int expected = -3;
        // Act
        int result = classUnderTest.add(-1, -2);
        // Assert
        assert (expected == result);
    }

    @Test
    public void add_shouldReturnTheSumOfTwoNegativeAndPositiveNumbers_whenGivenTwoNumbers() {
        // Arrange
        int expected = 1;
        // Act
        int result = classUnderTest.add(-1, 2);
        // Assert
        assert (expected == result);
    }

    // Subtraction
    @Test
    public void subtract_shouldReturnTheSubtractionOfTwoPositiveNumbers_whenGivenTwoNumbers() {
        // Arrange
        int expected = 3;

        // Act
        int result = classUnderTest.subtract(6, 3);

        // Assert
        assert (expected == result);
    }

    @Test
    public void subtract_shouldReturnTheSubtractionOfTwoNegativeNumbers_whenGivenTwoNumbers() {
        // Arrange
        int expected = -3;

        // Act
        int result = classUnderTest.subtract(-6, -3);

        // Assert
        assert (expected == result);
    }

    @Test
    public void subtract_shouldReturnTheSubtractionOfTwoDifferentSignalsNumbers_whenGivenTwoNumbers() {
        // Arrange
        int expected = -9;

        // Act
        int result = classUnderTest.subtract(-6, 3);

        // Assert
        assert (expected == result);
    }

    // Division
    @Test
    public void divide_shouldReturnTheDivisionOfTwoPositiveNumbers_whenGivenTwoNumbers() {
        // Arrange
        int expected = 2;

        // Act
        int result = classUnderTest.divide(4, 2);

        // Assert
        assert (expected == result);
    }

    @Test
    public void divide_shouldReturnTheDivisionOfTwoNegativeNumbers_whenGivenTwoNumbers() {
        // Arrange
        int expected = 2;

        // Act
        int result = classUnderTest.divide(-4, -2);

        // Assert
        assert (expected == result);
    }

    @Test
    public void divide_shouldReturnTheDivisionOfTwoDifferentSignalsNumbers_whenGivenTwoNumbers() {
        // Arrange
        int expected = -2;

        // Act
        int result = classUnderTest.divide(-4, 2);

        // Assert
        assert (expected == result);
    }


    @Test(expected = IllegalArgumentException.class)
    public void divide_shouldReturnAnException_whenDividingByZero() {
        classUnderTest.divide(4, 0);
    }

    // Multiplication
    @Test
    public void multiply_shouldReturnTheProductOfTwoPositiveNumbers_WhenGivenTwoNumbers() {
        // Arrange
        int expected = 6;

        // Act
        int result = classUnderTest.multiply(3, 2);

        // Assert
        assert (expected == result);
    }

    @Test
    public void multiply_shouldReturnTheProductOfTwoNegativeNumbers_WhenGivenTwoNumbers() {
        // Arrange
        int expected = 6;

        // Act
        int result = classUnderTest.multiply(-3, -2);

        // Assert
        assert (expected == result);
    }

    @Test
    public void multiply_shouldReturnTheProductOfTwoDifferentSignalsNumbers_WhenGivenTwoNumbers() {
        // Arrange
        int expected = -6;

        // Act
        int result = classUnderTest.multiply(-3, 2);

        // Assert
        assert (expected == result);
    }

}
