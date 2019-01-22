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

        if (node.next == null && node.value == _value) {
            clear();
        }

        while (node != null) {
            if (node.prev == null && node.value == _value) {
                this.head = node.next;
                node = node.next;
                if (node == null) {
                    this.tail = null;
                }
                continue;
            }

            node.prev = node;
            node = node.next;

            if (node != null && node.value == _value) {
                while (node.next != null && node.next.value == _value) {
                    node = node.next;
                }
                node.prev.next = node.next;
                node = node.next;
            }

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