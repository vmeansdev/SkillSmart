public class Main {

    public static void main(String[] args) {
        testStackInit();

        testPopReturnsNullWhenStackIsEmpty();
        testPopReturnsLastPushedElement();
        testPopRemoveLastElementFromTheTop();

        testPushChangesStackSize();
        testPeekReturnsNullForEmptyStack();
        testPeekReturnsObjectFromTheTop();

        testBracketBalancerWorksProperly();
        testPostfixCalculatorWorksWithoutEqOperation();
    }

    private static void testStackInit() {
        Stack<Object> stack = new Stack<>();
        test(stack.size() == 0, "Stack size should be 0");
    }

    private static void testPopReturnsNullWhenStackIsEmpty() {
        Stack<Object> stack = new Stack<>();
        test(stack.pop() == null, "Stack pop should return null");
    }

    private static void testPopReturnsLastPushedElement() {
        Stack<String> stack = new Stack<>();
        stack.push("Hello");
        stack.push("World");

        test(stack.pop().equalsIgnoreCase("World"), "Last element should be world");
        test(stack.pop().equalsIgnoreCase("Hello"), "Last element should be hello");
    }

    private static void testPopRemoveLastElementFromTheTop() {
        Stack<String> stack = new Stack<>();
        stack.push("Hello");
        stack.push("World");
        int stackSize = stack.size();

        stack.pop();
        test(stack.size() == (stackSize - 1), "Stack size should decrease by 1");
        stack.pop();
        stackSize -= 1;
        test(stack.size() == (stackSize - 1), "Stack size should decrease by 1");
        stack.pop();
        test(stack.size() == 0, "Stack should not be less than 0");
    }

    private static void testPushChangesStackSize() {
        Stack<Object> stack = new Stack<>();
        stack.push("Hello");
        test(stack.size() == 1, "Stack size should be 1");
        stack.push(1);
        test(stack.size() == 2, "Stack size should be 2");
        stack.push(3.14);
        test(stack.size() == 3, "Stack size should be 3");
    }

    private static void testPeekReturnsNullForEmptyStack() {
        Stack<Object> stack = new Stack<>();
        test(stack.peek() == null, "Stack peek should return null");
    }

    private static void testPeekReturnsObjectFromTheTop() {
        Stack<Object> stack = new Stack<>();
        String hello = "hello";
        stack.push(hello);
        test(stack.peek() == hello, "Last object should be the latest we added");
    }

    private static void testBracketBalancerWorksProperly() {
        test(
                BracketBalancer.isStringBalanced("(()((())()))"),
                "String should be balanced"
        );
        test(
                !BracketBalancer.isStringBalanced("(()()(()"),
                "String should not be balanced"
        );
        test(
                !BracketBalancer.isStringBalanced("())("),
                "String should not be balanced"
        );
        test(
                !BracketBalancer.isStringBalanced("))(("),
                "String should not be balanced"
        );
        test(
                !BracketBalancer.isStringBalanced("((())"),
                "String should not be balanced"
        );
        test(
                !BracketBalancer.isStringBalanced("())"),
                "String should not be balanced"
        );
    }

    private static void testPostfixCalculatorWorksWithoutEqOperation() {
        PostfixCalculator calc = new PostfixCalculator();
        calc.calculate("1 2 + 3 * =");
        calc.calculate("8 2 + 5 * 9 + =");
    }

    /*
        TEST helper methods
     */

    private static void test(boolean condition, String description) {
        try {
            check(condition, description);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void check(boolean condition, String description) throws Exception {
        if (!condition) {
            throw new Exception(description);
        }
    }

}
