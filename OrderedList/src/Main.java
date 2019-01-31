import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        testOrderedListInit();
        testOrderedListClear();

        testAddWorksOnIntegersAscSimple();
        testAddWorksOnIntegersAscRandomInsert();
        testAddWorksOnIntegersAscSame();
        testAddWorksOnIntegersAscEmptyHeadTail();

        testAddWorksOnIntegersDescSimple();
        testAddWorksOnIntegersDescRandomInsert();
        testAddWorksOnIntegersDescSame();

        testAddWorksOnStringsAscSimple();

        testFindWorksElementAdded();
        testFindWorksElementNotAddedAsc();
        testFindWorksElementNotAddedDesc();

        testDeleteWorks();
    }

    private static void testOrderedListInit() {
        OrderedList<Integer> ol = new OrderedList<>(true);
        test(ol.count() == 0, "Size should be 0");
        test(ol.getAll().size() == 0, "Size should be 0");
    }

    private static void testOrderedListClear() {
        OrderedList<Integer> ol = new OrderedList<>(true);
        ol.add(1);
        ol.add(2);
        ArrayList<Integer> al = new ArrayList<>();
        ol.clear(true);
        test(ol.count() == 0, "Size should be 0");
        test(ol.getAllValues(ol.getAll()).equals(al), "Should be equal");
    }

    private static void testAddWorksOnIntegersAscSimple() {
        OrderedList<Integer> ol = new OrderedList<>(true);
        ol.add(1);
        ol.add(2);
        ArrayList<Integer> al = new ArrayList<>();
        al.add(1);
        al.add(2);
        test(ol.getAllValues(ol.getAll()).equals(al), "Should be equal");
    }

    private static void testAddWorksOnIntegersAscEmptyHeadTail() {
        OrderedList<Integer> ol = new OrderedList<>(true);
        ol.add(1);
        test(ol.head.value == 1, "Should be 1");
        test(ol.tail.value == 1, "Should be 1");
        ol.add(2);
        test(ol.head.value == 1, "Should be 1");
        test(ol.tail.value == 2, "Should be 2");
        ol.add(3);
        test(ol.tail.value == 3, "Should be 3");
        ArrayList<Integer> al = new ArrayList<>();
        al.add(1);
        al.add(2);
        al.add(3);
        test(ol.getAllValues(ol.getAll()).equals(al), "Should be equal");
    }

    private static void testAddWorksOnIntegersAscRandomInsert() {
        OrderedList<Integer> ol = new OrderedList<>(true);
        ol.add(1);
        ol.add(3);
        ol.add(2);
        ol.add(0);
        ol.add(9);
        ol.add(10);
        ArrayList<Integer> al = new ArrayList<>();
        al.add(0);
        al.add(1);
        al.add(2);
        al.add(3);
        al.add(9);
        al.add(10);
        test(ol.getAllValues(ol.getAll()).equals(al), "Should be equal");
    }

    private static void testAddWorksOnIntegersAscSame() {
        OrderedList<Integer> ol = new OrderedList<>(true);
        ol.add(1);
        ol.add(1);
        ArrayList<Integer> al = new ArrayList<>();
        al.add(1);
        al.add(1);
        test(ol.getAllValues(ol.getAll()).equals(al), "Should be equal");
    }

    private static void testAddWorksOnIntegersDescSimple() {
        OrderedList<Integer> ol = new OrderedList<>(false);
        ol.add(1);
        ol.add(2);
        ArrayList<Integer> al = new ArrayList<>();
        al.add(2);
        al.add(1);
        test(ol.getAllValues(ol.getAll()).equals(al), "Should be equal");
    }

    private static void testAddWorksOnIntegersDescRandomInsert() {
        OrderedList<Integer> ol = new OrderedList<>(false);
        ol.add(1);
        ol.add(3);
        ol.add(2);
        ol.add(0);
        ol.add(9);
        ol.add(10);
        ArrayList<Integer> al = new ArrayList<>();
        al.add(10);
        al.add(9);
        al.add(3);
        al.add(2);
        al.add(1);
        al.add(0);
        test(ol.getAllValues(ol.getAll()).equals(al), "Should be equal");
    }

    private static void testAddWorksOnIntegersDescSame() {
        OrderedList<Integer> ol = new OrderedList<>(false);
        ol.add(1);
        ol.add(1);
        ArrayList<Integer> al = new ArrayList<>();
        al.add(1);
        al.add(1);
        test(ol.getAllValues(ol.getAll()).equals(al), "Should be equal");
    }

    private static void testAddWorksOnStringsAscSimple() {
        OrderedList<String> ol = new OrderedList<>(true);
        ol.add("a");
        ol.add("aa");
        ArrayList<String> al = new ArrayList<>();
        al.add("a");
        al.add("aa");
        test(ol.getAllValues(ol.getAll()).equals(al), "Should be equal");
    }

    private static void testFindWorksElementAdded() {
        OrderedList<Integer> ol = new OrderedList<>(true);
        ol.add(1);
        ol.add(2);
        ol.add(3);
        Node<Integer> node = ol.find(2);
        test(node != null, "Should not be null");
        test( node.value == 2, "Should be 2");
    }

    private static void testFindWorksElementNotAddedAsc() {
        OrderedList<Integer> ol = new OrderedList<>(true);
        ol.add(1);
        ol.add(2);
        ol.add(3);
        Node<Integer> node = ol.find(5);
        test(node == null, "Should be null");
        Node<Integer> node0 = ol.find(0);
        test(node0 == null, "Should be null");
    }

    private static void testFindWorksElementNotAddedDesc() {
        OrderedList<Integer> ol = new OrderedList<>(false);
        ol.add(1);
        ol.add(2);
        ol.add(3);
        Node<Integer> node = ol.find(5);
        test(node == null, "Should be null");
        Node<Integer> node0 = ol.find(0);
        test(node0 == null, "Should be null");
    }

    private static void testDeleteWorks() {
        OrderedList<Integer> ol = new OrderedList<>(true);
        ol.delete(1);
        ol.add(1);
        ol.add(2);
        ol.add(3);
        ol.add(2);
        test(ol.tail.value == 3, "Should be 3");

        ArrayList<Integer> al = new ArrayList<>();
        al.add(1);
        al.add(3);
        ol.delete(2);
        test(ol.count() == 2, "Size should be 2");
        test(ol.getAllValues(ol.getAll()).equals(al), "Should be equal");
        test(ol.tail.value == 3, "Should be 3");
        ol.delete(3);
        ol.delete(1);
        test(ol.head == null, "Should be null");
        test(ol.tail == null, "Should be null");
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
