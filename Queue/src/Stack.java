import java.util.*;

public class Stack<T>
{

    private LinkedList<T> store;

    public Stack()
    {
        // инициализация внутреннего хранилища стека
        store = new LinkedList<>();
    }

    public int size()
    {
        // размер текущего стека
        return store.size();
    }

    public T pop()
    {
        // ваш код
        if (size() > 0) {
            T element = store.getFirst();
            store.removeFirst();
            return element;
        }

        return null;  // если стек пустой
    }

    public void push(T val)
    {
        store.addFirst(val);
    }

    public T peek()
    {
        // ваш код
        if (size() > 0) {
            return store.getFirst();
        }
        return null; // если стек пустой
    }
}