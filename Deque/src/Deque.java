import java.util.*;

public class Deque<T>
{
    private ArrayList<T> store;

    public Deque()
    {
        // инициализация внутреннего хранилища
        store = new ArrayList<>();
    }

    public void addFront(T item)
    {
        // добавление в голову
        store.add(item);
    }

    public void addTail(T item)
    {
        // добавление в хвост
        store.add(0, item);
    }

    public T removeFront()
    {
        // удаление из головы
        if (size() == 0)
            return null;

        int lastIndex = size() - 1;
        T element = store.get(lastIndex);
        store.remove(lastIndex);
        return element;
    }

    public T removeTail()
    {
        // удаление из хвоста
        if (size() == 0)
            return null;

        T element =  store.get(0);
        store.remove(0);
        return element;
    }

    public int size()
    {
        return store.size(); // размер очереди
    }
}