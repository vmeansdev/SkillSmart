public class Main {

    public static void main(String[] args) {
        testDequeInit();
        testDequeAddFrontChangeSize();
        testDequeAddTailChangeSize();
        testDequeRemoveFrontChangeSize();
        testDequeRemoveTailChangeSize();
        testDequeRemoveFrontReturnsNullWhenDequeIsEmpty();
        testDequeRemoveTailReturnsNullWhenDequeIsEmpty();
        testPalindromeCheckerWorks();
    }

    private static void testDequeInit() {
        Deque<Integer> deque = new Deque<>();
        test(deque.size() == 0, "Size should be 0");
    }

    private static void testDequeAddFrontChangeSize() {
        Deque<Integer> deque = new Deque<>();
        deque.addFront(0);
        deque.addFront(1);
        deque.addFront(2);
        test(deque.size() == 3, "Size should be 3");
    }

    private static void testDequeAddTailChangeSize() {
        Deque<Integer> deque = new Deque<>();
        deque.addTail(2);
        deque.addTail(1);
        deque.addTail(0);
        test(deque.size() == 3, "Size should be 3");
    }

    private static void testDequeRemoveFrontReturnsNullWhenDequeIsEmpty() {
        Deque<Integer> deque = new Deque<>();
        test(deque.removeFront() == null, "Element should be null");
    }

    private static void testDequeRemoveTailReturnsNullWhenDequeIsEmpty() {
        Deque<Integer> deque = new Deque<>();
        test(deque.removeTail() == null, "Element should be null");
    }

    private static void testDequeRemoveFrontChangeSize() {
        Deque<Integer> deque = new Deque<>();
        deque.addFront(0);
        deque.addFront(1);
        deque.addFront(2);

        test(deque.removeFront() == 2, "Removed element should be 2");
        test(deque.size() == 2, "Size should be 2");
    }

    private static void testDequeRemoveTailChangeSize() {
        Deque<Integer> deque = new Deque<>();
        deque.addTail(2);
        deque.addTail(1);
        deque.addTail(0);
        test(deque.removeTail() == 0, "Removed element should be 0");
        test(deque.size() == 2, "Size should be 2");
    }

    private static void testPalindromeCheckerWorks() {
        test(PalindromeChecker.isPalindrome("radar"), "Should be true");
        test(PalindromeChecker.isPalindrome("madam"), "Should be true");
        test(PalindromeChecker.isPalindrome("toot"), "Should be true");
        test(PalindromeChecker.isPalindrome("lol"), "Should be true");
        test(!PalindromeChecker.isPalindrome("omfg"), "Should be false");
        test(!PalindromeChecker.isPalindrome("somthing"), "Should be false");

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
