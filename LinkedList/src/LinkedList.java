import java.util.*;

public class LinkedList
{
    public Node head;
    public Node tail;

    public LinkedList()
    {
        head = null;
        tail = null;
    }

    public void addInTail(Node item) {
        if (this.head == null)
            this.head = item;
        else
            this.tail.next = item;
        this.tail = item;
    }

    public Node find(int value) {
        Node node = this.head;
        while (node != null) {
            if (node.value == value)
                return node;
            node = node.next;
        }
        return null;
    }

    public ArrayList<Node> findAll(int value) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        Node node = this.head;
        while (node != null) {
            if (node.value == value)
                nodes.add(node);
            node = node.next;
        }

        return nodes;
    }

    public boolean remove(int value)
    {
        if (this.head == null)
            return false;

        Node node = this.head;
        Node prevNode = null;

        while (node.value != value && node.next != null) {
            prevNode = node;
            node = node.next;
        }

        if (node.value == value) {
            if (prevNode != null) {
                prevNode.next = node.next;
                if (prevNode.next == null) {
                    this.tail = prevNode;
                }
            } else {
                this.head = node.next;
            }
            return true;
        }

        return false;
    }

    public void removeAll(int _value)
    {
        if (this.head == null)
            return;

        Node node = this.head;
        Node prevNode = null;

        if (node.next == null && node.value == _value) {
            clear();
        }

        while (node != null) {
            if (prevNode == null && node.value == _value) {
                this.head = node.next;
                node = node.next;
                continue;
            }

            prevNode = node;
            node = node.next;

            if (node != null && node.value == _value) {
                prevNode.next = node.next;
                node = node.next;
            }

            if (node == null) {
                this.tail = prevNode;
            }
        }
    }

    public void clear()
    {
        this.head = null;
        this.tail = null;
    }

    public int count()
    {
        int count = 0;
        Node node = this.head;
        while (node != null) {
            count += 1;
            node = node.next;
        }
        return count;
    }

    public void insertAfter(Node nodeAfter, Node nodeToInsert)
    {
        if (nodeAfter == null)
            return;

        if (nodeToInsert == null)
            return;

        if (this.count() == 0) {
            this.addInTail(nodeToInsert);
        }

        Node node = this.head;
        while (node != null) {
            if (node == nodeAfter) {
                nodeToInsert.next = node.next;
                nodeAfter.next = nodeToInsert;

                if (nodeToInsert.next == null) {
                    this.tail = nodeToInsert;
                }

                return;
            }
            node = node.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        Node node = this.head;
        while (node != null) {
            String stringVal = this.tail == node ? String.valueOf(node.value) : node.value + ", ";
            builder.append(stringVal);
            node = node.next;
        }
        builder.append("]");

        return builder.toString();
    }
}

