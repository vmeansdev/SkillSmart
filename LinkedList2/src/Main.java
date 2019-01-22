import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        testLinkedListCreatedEmpty();
        testLinkedListAddInTailWorksProperly();

        testFindMethodWorksProperlyForNoNodes();
        testFindMethodWorksProperlyForOneNode();
        testFindMethodWorksProperlyForMultipleNodes();
        testFindAllMethodWorksProperly();

        testRemoveMethodForSingleElementWorksProperly();
        testRemoveMethodForTwoElementsWorksProperly();
        testRemoveMethodForThreeElementsWorksProperly();
        testRemoveMethodWorksProperly();
        testRemoveAllMethodWorksProperly();
        testRemoveAllMethodWorksProperlyForSameValues();
        testRemoveAllMethodWorksProperlyForMostlySameValues();

        testClearMethodWorksProperly();
        testInsertAfterMethodWorksProperly();
        testInsertAfterWorksForEmptyList();
        testInsertAfterInsertsNothingWhenNullsPassed();
    }

    private static void testLinkedListCreatedEmpty() {
        LinkedList2 list = new LinkedList2();
        test(list.count() == 0, "List should be empty");
    }

    private static void testLinkedListAddInTailWorksProperly() {
        LinkedList2 list = new LinkedList2();
        Node node = new Node(0);
        list.addInTail(node);
        test(
                list.count() == 1,
                "List should have at least one element after adding one node to it"
        );
        test(
                list.head == node,
                "List head should be equal to node"
        );
        test(
                list.tail == node,
                "List tail should be equal to node"
        );

        Node node1 = new Node(1);
        list.addInTail(node1);
        test(
                list.count() == 2,
                "List should have two elements after adding two nodes to it"
        );
        test(
                list.head == node,
                "List head still should be equal to node"
        );
        test(
                list.tail == node1,
                "List tail should become node1"
        );
    }

    private static void testFindMethodWorksProperlyForNoNodes() {
        LinkedList2 list = new LinkedList2();

        Node foundNode = list.find(0);
        test(foundNode == null, "Found node should be null");
    }

    private static void testFindMethodWorksProperlyForOneNode() {
        LinkedList2 list = new LinkedList2();
        Node node = new Node(0);
        list.addInTail(node);

        Node foundNode = list.find(0);
        test(foundNode != null, "Found node should not be null");
        test(foundNode == node, "Found node should be equal to added node");
    }

    private static void testFindMethodWorksProperlyForMultipleNodes() {
        LinkedList2 list = new LinkedList2();
        Node node = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(0);
        Node node4 = new Node(4);

        list.addInTail(node);
        list.addInTail(node1);
        list.addInTail(node2);
        list.addInTail(node3);
        list.addInTail(node4);

        Node foundNode = list.find(0);
        test(foundNode == node, "Found node should be equal to first added node");
        test(
                foundNode != node3,
                "Found node should not be equal to node3 despite the same value because of order"
        );

        Node newFoundNode = list.find(4);
        test(newFoundNode == node4, "New found node should be equal to node4");
    }

    private static void testFindAllMethodWorksProperly() {
        LinkedList2 list = new LinkedList2();
        test(list.findAll(1).size() == 0, "List should be empty");

        Node node = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(0);
        Node node4 = new Node(4);

        list.addInTail(node);
        list.addInTail(node1);
        list.addInTail(node2);
        list.addInTail(node3);
        list.addInTail(node4);

        List<Node> zeroes = new ArrayList<>();
        zeroes.add(node);
        zeroes.add(node3);

        List<Node> foundZeroes = list.findAll(0);
        test(foundZeroes.size() == zeroes.size(), "Lists should be equal");

        List<Node> foundOnes = list.findAll(1);
        List<Node> ones = new ArrayList<>();
        ones.add(node1);
        test(foundOnes.size() == ones.size(), "Lists should be equal");
    }

    private static void testRemoveMethodForSingleElementWorksProperly() {
        LinkedList2 list = new LinkedList2();
        Node node = new Node(0);
        list.addInTail(node);
        list.remove(0);

        test(list.count() == 0, "List size should be 0");
        test(list.head == null, "List head should be null");
        test(list.tail == null, "List tail should be null");
    }

    private static void testRemoveMethodForTwoElementsWorksProperly() {
        LinkedList2 list = new LinkedList2();
        Node node = new Node(0);
        Node node1 = new Node(1);
        list.addInTail(node);
        list.addInTail(node1);

        list.remove(0);
        test(list.count() == 1, "List size should be 1");
        test(list.head == node1, "New list head should be node1");
        test(list.tail == node1, "New list tail should be node1");
    }

    private static void testRemoveMethodForThreeElementsWorksProperly() {
        LinkedList2 list = new LinkedList2();
        Node node = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);

        list.addInTail(node);
        list.addInTail(node1);
        list.addInTail(node2);

        list.remove(0);
        test(list.count() == 2, "List size should be 2");
        test(list.head == node1, "List head should be node1");
        test(list.tail == node2, "List tail should be node2");
    }

    private static void testRemoveMethodWorksProperly() {
        LinkedList2 list = new LinkedList2();
        boolean emptyRemoved = list.remove(0);
        test(!emptyRemoved, "Remove should return false on empty list");

        Node node = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(0);
        Node node4 = new Node(4);

        list.addInTail(node);
        list.addInTail(node1);
        list.addInTail(node2);
        list.addInTail(node3);
        list.addInTail(node4);

        int sizeBeforeRemove = list.count();
        boolean removed = list.remove(2);
        test(removed, "Element should be removed");
        test(
                sizeBeforeRemove == list.count() + 1,
                "Element count should be equal to list count + 1"
        );
        boolean tailRemoved = list.remove(4);
        test(tailRemoved, "Tail node should be removed");
        test(list.tail == node3, "Node3 should become a new list tail");

        boolean headRemoved = list.remove(0);
        test(headRemoved, "Head node should be removed");
        test(list.head == node1, "Node1 should become a new list head");
    }

    private static void testRemoveAllMethodWorksProperly() {
        LinkedList2 list = new LinkedList2();

        Node node = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        Node node3 = new Node(1);
        Node node4 = new Node(5);
        Node node5 = new Node(1);

        list.addInTail(node);
        list.addInTail(node1);
        list.addInTail(node2);
        list.addInTail(node3);
        list.addInTail(node4);
        list.addInTail(node5);


        int sizeBeforeRemove = list.count();
        list.removeAll(1);
        test(
                list.count() == 3,
                "List contains "
                        + list.count()
                        + " elements, but should contain 3 "
                        + list.toString()
        );
        test(
                list.head.value == 2,
                "List head value should be equal to the new first node value"
        );
    }

    private static void testRemoveAllMethodWorksProperlyForSameValues() {
        LinkedList2 list = new LinkedList2();

        Node node = new Node(1);
        Node node1 = new Node(1);
        Node node2 = new Node(1);
        Node node3 = new Node(1);
        Node node4 = new Node(1);
        Node node5 = new Node(1);

        list.addInTail(node);
        list.addInTail(node1);
        list.addInTail(node2);
        list.addInTail(node3);
        list.addInTail(node4);
        list.addInTail(node5);

        list.removeAll(1);
        test(
                list.count() == 0,
                "List contains "
                        + list.count()
                        + " elements, but should contain 0 "
                        + list.toString()
        );
        test(
                list.head == null,
                "List head should be null"
        );
        test(
                list.tail == null,
                "List tail should be null"
        );
    }

    private static void testRemoveAllMethodWorksProperlyForMostlySameValues() {
        LinkedList2 list = new LinkedList2();

        Node node = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(1);
        Node node3 = new Node(4);
        Node node4 = new Node(1);
        Node node5 = new Node(1);

        list.addInTail(node);
        list.addInTail(node1);
        list.addInTail(node2);
        list.addInTail(node3);
        list.addInTail(node4);
        list.addInTail(node5);

        list.removeAll(1);
        test(
                list.count() == 2,
                "List contains "
                        + list.count()
                        + " elements, but should contain 2 "
                        + list.toString()
        );
        test(
                list.head == node1,
                "List head should be node1"
        );
        test(
                list.tail == node3,
                "List tail should be node3"
        );
    }

    private static void testClearMethodWorksProperly() {
        LinkedList2 list = new LinkedList2();

        Node node = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(0);
        Node node4 = new Node(4);

        list.addInTail(node);
        list.addInTail(node1);
        list.addInTail(node2);
        list.addInTail(node3);
        list.addInTail(node4);

        test(list.count() == 5, "List should not be empty before clear");
        list.clear();
        test(list.count() == 0, "List should be empty after clear");
    }

    private static void testInsertAfterMethodWorksProperly() {
        LinkedList2 list = new LinkedList2();

        Node node = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(0);
        Node node4 = new Node(4);

        list.addInTail(node);
        list.addInTail(node1);
        list.addInTail(node2);
        list.addInTail(node3);
        list.addInTail(node4);

        int sizeBeforeInsert = list.count();
        Node nodeWithThree = new Node(3);
        list.insertAfter(node1, nodeWithThree);
        test(
                list.count() == sizeBeforeInsert + 1,
                "List size should be increased by one"
        );

        Node lastNode = new Node(10);
        list.insertAfter(node4, lastNode);
        test(
                list.tail == lastNode,
                "Node inserted after tail should become tail"
        );
    }

    private static void testInsertAfterWorksForEmptyList() {
        LinkedList2 list = new LinkedList2();
        Node node = new Node(0);
        list.insertAfter(null, node);

        test(
                list.count() == 1,
                "List size should be 1"
        );
        test(
                list.head == node,
                "List head should be node"
        );
        test(
                list.tail == node,
                "List tail should be node"
        );
    }

    private static void testInsertAfterInsertsNothingWhenNullsPassed() {
        LinkedList2 list = new LinkedList2();
        list.insertAfter(null, null);

        test(
                list.count() == 0,
                "List size should be 0"
        );
        test(
                list.head == null,
                "List head should be null"
        );
        test(
                list.tail == null,
                "List tail should be null"
        );
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
