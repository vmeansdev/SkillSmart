public class BracketBalancer {

    public static boolean isStringBalanced(String string) {
        Stack<Character> stack = new Stack<>();
        for (char c : string.toCharArray()) {
            stack.push(c);
            if (stack.peek() == '(')
                continue;
            if (stack.peek() == ')') {
                stack.pop();
                stack.pop();
            }
        }

        return stack.size() == 0;
    }
}
