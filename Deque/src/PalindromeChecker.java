public class PalindromeChecker {

    public static boolean isPalindrome(String string) {
        Deque<Character> word = new Deque<>();
        for (Character ch : string.toCharArray()) {
            word.addTail(ch);
        }

        boolean isEqual = true;
        while (word.size() > 1 && isEqual) {
            char first = word.removeFront();
            char last = word.removeTail();
            if (first != last) {
                isEqual = false;
            }
        }

        return isEqual;
    }

}
