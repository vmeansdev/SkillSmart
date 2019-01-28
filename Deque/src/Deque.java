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
        T element = size() == 0 ? null : store.get(size() - 1);
        store.remove(element);
        return element;
    }

    public T removeTail()
    {
        // удаление из хвоста
        T element = size() == 0 ? null : store.get(0);
        store.remove(element);
        return element;
    }

    public int size()
    {
        return store.size(); // размер очереди
    }
}