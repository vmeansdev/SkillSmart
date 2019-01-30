import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

class Node<T>
{
    public T value;
    public Node<T> next, prev;

    public Node(T _value)
    {
        value = _value;
        next = null;
        prev = null;
    }
}

public class OrderedList<T>
{
    public Node<T> head, tail;
    private boolean _ascending;

    public OrderedList(boolean asc)
    {
        head = null;
        tail = null;
        _ascending = asc;
    }

    public int compare(T v1, T v2)
    {
        if (v1 instanceof String && v2 instanceof String) {
            String s1 = ((String) v1).trim();
            String s2 = ((String) v2).trim();
            return s1.compareTo(s2);
        }

        if (v1 instanceof Number && v2 instanceof Number) {
            return compareNumbers((Number)v1, (Number)v2);
        }

        return 0;
    }

    public void add(T value)
    {
        // автоматическая вставка value 
        // в нужную позицию
        Node<T> newNode = new Node<>(value);

        if (head == null) {
            head = new Node<>(value);
            head.next = null;
            head.prev = null;
            return;
        }

        int cmp_result = compare(value, head.value);
        boolean condition = _ascending ? cmp_result < 0 : cmp_result > 0;
        if (condition) {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        } else {
            Node<T> after = head.next;
            Node<T> before = head;
            while (after != null) {
                cmp_result = compare(value, after.value);
                condition = _ascending ? cmp_result < 0 : cmp_result > 0;
                if (condition)
                    break;
                before = after;
                after = after.next;
            }
            newNode.next = before.next;
            newNode.prev = before;
            before.next = newNode;
        }
    }

    public Node<T> find(T val)
    {
        if (head == null)
            return null;

        int cmp_result = compare(val, head.value);
        boolean condition = _ascending ? cmp_result < 0 : cmp_result > 0;
        if (condition)
            return null;

        Node<T> node = head;
        while (node != null) {
            if (node.value == val) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    public void delete(T val)
    {
        Node node = head;
        if (node == null) {
            return;
        }

        while (node != null) {
            if (node.value == val) {
                if (node.prev != null) {
                    node.prev.next = node.next;
                } else {
                    head = node.next;
                }
                if (node.next != null) {
                    node.next.prev = node.prev;
                } else {
                    tail = node.prev;
                }
            }
            node = node.next;
        }
    }

    public void clear(boolean asc)
    {
        _ascending = asc;
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

    ArrayList<Node<T>> getAll() // выдать все элементы упорядоченного 
    // списка в виде стандартного списка
    {
        ArrayList<Node<T>> r = new ArrayList<Node<T>>();
        Node<T> node = head;
        while(node != null)
        {
            r.add(node);
            node = node.next;
        }
        return r;
    }

    ArrayList<T> getAllValues(ArrayList<Node<T>> nodes) {
        ArrayList<T> result = new ArrayList<>();
        for (Node<T> node : nodes) {
            result.add(node.value);
        }
        return result;
    }

    private int compareNumbers(Number x, Number y) {
        if(isSpecial(x) || isSpecial(y))
            return Double.compare(x.doubleValue(), y.doubleValue());
        else
            return toBigDecimal(x).compareTo(toBigDecimal(y));
    }

    private static boolean isSpecial(Number x) {
        boolean specialDouble = x instanceof Double
                && (Double.isNaN((Double) x) || Double.isInfinite((Double) x));
        boolean specialFloat = x instanceof Float
                && (Float.isNaN((Float) x) || Float.isInfinite((Float) x));
        return specialDouble || specialFloat;
    }

    private static BigDecimal toBigDecimal(Number number) {
        if(number instanceof BigDecimal)
            return (BigDecimal) number;
        if(number instanceof BigInteger)
            return new BigDecimal((BigInteger) number);
        if(number instanceof Byte || number instanceof Short
                || number instanceof Integer || number instanceof Long)
            return new BigDecimal(number.longValue());
        if(number instanceof Float || number instanceof Double)
            return new BigDecimal(number.doubleValue());

        try {
            return new BigDecimal(number.toString());
        } catch(final NumberFormatException e) {
            throw new RuntimeException("The given number (\"" + number + "\" of class " + number.getClass().getName() + ") does not have a parsable string representation", e);
        }
    }
}