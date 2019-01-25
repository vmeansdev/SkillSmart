import java.util.*;

public class Stack<T>
{

    private ArrayList<T> store;

    public Stack()
    {
        // инициализация внутреннего хранилища стека
        store = new ArrayList<>();
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
            int lastIndex = store.size() - 1;
            T element = store.get(lastIndex);
            store.remove(lastIndex);
            return element;
        }

        return null;  // если стек пустой
    }

    public void push(T val)
    {
        store.add(val);
    }

    public T peek()
    {
        // ваш код
        if (size() > 0) {
            return store.get(store.size() - 1);
        }
        return null; // если стек пустой
    }
}