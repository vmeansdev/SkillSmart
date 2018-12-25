public class LinkedListUtils {

    public static LinkedList add(LinkedList lhs, LinkedList rhs) {
        LinkedList list = new LinkedList();
        if (lhs.count() == 0 || rhs.count() == 0)
            return list;

        if (lhs.count() == rhs.count()) {
            Node lhsHead = lhs.head;
            Node rhsHead = rhs.head;
            while (lhsHead != null && rhsHead != null) {
                Node newNode = new Node(lhsHead.value + rhsHead.value);
                list.addInTail(newNode);

                lhsHead = lhsHead.next;
                rhsHead = rhsHead.next;
            }
        }

        return list;
    }

}
