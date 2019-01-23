import java.util.*;

public class LinkedList2
{
    public Node head;
    public Node tail;

    public LinkedList2()
    {
        head = null;
        tail = null;
    }

    public void addInTail(Node _item)
    {
        if (head == null) {
            this.head = _item;
            this.head.next = null;
            this.head.prev = null;
        } else {
            this.tail.next = _item;
            _item.prev = tail;
        }
        this.tail = _item;
    }

    public Node find(int _value)
    {
        Node node = head;
        while (node != null) {
            if (node.value == _value) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    public ArrayList<Node> findAll(int _value)
    {
        ArrayList<Node> nodes = new ArrayList<Node>();
        Node node = this.head;
        while (node != null) {
            if (node.value == _value)
                nodes.add(node);
            node = node.next;
        }
        return nodes;
    }

    public boolean remove(int _value)
    {
        Node node = head;
        if (node == null) {
            return false;
        }

        while (node.value != _value && node.next != null) {
            node = node.next;
        }

        if (node.value == _value) {
            if (node.prev != null) {
                node.prev.next = node.next;
                if (node.prev.next == null) {
                    this.tail = node.prev;
                }
            } else {
                this.head = node.next;
                if (node.next == null) {
                    this.tail = null;
                }
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
        Node lastDiffNode = null;

        while (node != null) {
            if (node.value == _value) {
                if (node.next == null) {
                    if (head == node) {
                        clear();
                        return;
                    } else {
                        if (lastDiffNode != null)
                            lastDiffNode.next = null;
                        tail = lastDiffNode;
                    }
                } else {
                    node.next.prev = node.prev;
                }

                if (node.prev == null) {
                    head = node.next;
                } else {
                    node.prev.next = node.next;
                }
            } else {
                lastDiffNode = node;
            }
            node = node.next;
        }
    }

    public void clear()
    {
        head = null;
        tail = null;
    }

    public int count()
    {
        int count = 0;
        Node node = head;
        while (node != null) {
            count += 1;
            node = node.next;
        }
        return count; 
    }

    public void insertAfter(Node _nodeAfter, Node _nodeToInsert)
    {
        if (_nodeToInsert == null) {
            return;
        }

        if (count() == 0) {
            addInTail(_nodeToInsert);
            return;
        }

        if (_nodeAfter == null) {
            head.prev = _nodeToInsert;
            _nodeToInsert.next = head;
            head = _nodeToInsert;
        }

        Node node = this.head;
        while (node != null) {
            if (node == _nodeAfter) {
                _nodeToInsert.prev = node.prev;
                _nodeToInsert.next = node.next;
                _nodeAfter.next = _nodeToInsert;

                if (_nodeToInsert.next == null) {
                    this.tail = _nodeToInsert;
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

class Node
{
    public int value;
    public Node next;
    public Node prev;

    public Node(int _value)
    {
        value = _value;
        next = null;
        prev = null;
    }
}