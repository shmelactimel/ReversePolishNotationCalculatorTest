import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.jupiter.api.Test;


public class ReversePolishNotationCalculatorTest {

    @Test
    public void shouldCalculateAddition() {
        ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();
        String expression = "4 3 +";
        int result = calculator.calculatePolishNotation(expression);
        Assertions.assertEquals(7, result);
    }

    @Test
    public void shouldCalculateSubtraction() {
        ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();
        String expression = "1 2 -";
        int result = calculator.calculatePolishNotation(expression);
        Assertions.assertEquals(-1, result);
    }

    @Test
    public void shouldCalculateMultiplication() {
        ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();
        String expression = "3 4 *";
        int result = calculator.calculatePolishNotation(expression);
        Assertions.assertEquals(12, result);
    }

    @Test
    public void shouldHandleNegativeNumbers() {
        ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();
        String expression = "5 -3 +";
        int result = calculator.calculatePolishNotation(expression);
        Assertions.assertEquals(2, result);
    }

    @Test
    public void shouldHandleMultipleOperations() {
        ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();
        String expression = "1 2 3 - +";
        int result = calculator.calculatePolishNotation(expression);
        Assertions.assertEquals(0, result);
    }

    @Test
    public void shouldHandleSpacesBetweenOperandsAndOperators() {
        ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();
        String expression = "  2   3  *  ";
        int result = calculator.calculatePolishNotation(expression);
        Assertions.assertEquals(6, result);
    }
}

class ReversePolishNotationCalculator {

    public int calculatePolishNotation(String str) {
        String[] parts = str.split(" ");
        Deque<Integer> numbers = new ArrayDeque<>();
        int index = 0;

        while (index != parts.length) {

            if (parts[index].isBlank()) {
                index++;
                continue;
            }

            if (isOperation(parts[index])) {
                int operandOne = numbers.pop();
                int operandTwo = numbers.pop();

                if (parts[index].equals("+")) {
                    numbers.push(operandOne + operandTwo);
                } else if (parts[index].equals("-")) {
                    numbers.push(operandTwo - operandOne);
                } else if (parts[index].equals("*")) {
                    numbers.push(operandOne * operandTwo);
                }

            } else {
                numbers.push(Integer.parseInt(parts[index]));
            }

            index++;
        }

        return numbers.pop();
    }

    private boolean isOperation(String part) {
        if (part.equals("+")
                || part.equals("-")
                || part.equals("*")) {
            return true;
        }

        return false;
    }
}
