import java.util.*;

public class Queue<T>
{
    private ArrayList<T> store;

    public Queue()
    {
        // инициализация внутреннего хранилища очереди
        store = new ArrayList<>();
    }

    public void enqueue(T item)
    {
        // вставка в хвост
        store.add(0, item);
    }

    public T dequeue()
    {
        // выдача из головы
        if (size() > 0) {
            T element = store.get(size() - 1);
            store.remove(size() - 1);
            return element;
        }
        return null; // null если очередь пустая
    }

    public int size()
    {
        return store.size(); // размер очереди
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = size() - 1; i >= 0; i--) {
            sb.append(store.get(i));
            sb.append(i == 0 ? "" : ", ");
        }
        sb.append(']');
        return sb.toString();
    }
}