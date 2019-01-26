public class BracketBalancer {

    public static boolean isStringBalanced(String string) {
        Stack<Character> stack = new Stack<>();
        for (char c : string.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                Character top = stack.peek();
                if (top == null || !isPair(top, c)) {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }

        return stack.size() == 0;
    }

    private static boolean isPair(char open, char close) {
        return open == '(' && close == ')';
    }
}
