public class PostfixCalculator {

    private Stack<Integer> results = new Stack<>();
    private Stack<Character> operandsAndOperators = new Stack<>();

    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char DIV = '/';
    private static final char MUL = '*';
    private static final char EQ = '=';

    private final char[] allowedOperators = {PLUS, MINUS, DIV, MUL, EQ};
    private char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public void calculate(String expression) {
        populateOperandsAndOperators(expression);
        Character term = operandsAndOperators.pop();
        while (term != null) {
            if (isDigit(term)) {
                int digit = Character.getNumericValue(term);
                results.push(digit);
            }
            if (isOperator(term)) {
                if (isOpEqual(term)) {
                    printResult();
                    return;
                }
                if (results.size() == 2) {
                    operateOnResults(term);
                }
            }
            term = operandsAndOperators.pop();
        }
    }

    public void printResult() {
        Integer result = results.pop();
        System.out.println(result);
    }

    private void operateOnResults(char term) {
        Integer op1 = results.pop();
        Integer op2 = results.pop();
        Integer result = operate(term, op1, op2);
        if (result != null) {
            results.push(result);
        }
    }

    private Integer operate(char operator, Integer op1, Integer op2) {
        switch (operator) {
            case PLUS:
                return op1 + op2;
            case MINUS:
                return op1 - op2;
            case DIV:
                return op1 / op2;
            case MUL:
                return op1 * op2;
        }

        return null;
    }

    private void populateOperandsAndOperators(String expression) {
        char[] exChars = expression.toCharArray();
        for (int i = exChars.length - 1; i >= 0; i--) {
            char c = exChars[i];
            if (c == ' ')
                continue;
            operandsAndOperators.push(c);
        }
    }

    private boolean isOpEqual(char operator) {
        return operator == EQ;
    }

    private boolean isOperator(char c) {
        return isTermOf(allowedOperators, c);
    }

    private boolean isDigit(char c) {
        return isTermOf(digits, c);
    }

    private boolean isTermOf(char[] arr, char c) {
        for (char _c : arr) {
            if (_c == c) {
                return true;
            }
        }
        return false;
    }

}
