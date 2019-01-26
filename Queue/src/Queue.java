import java.util.*;

public class Queue<T>
{

    private Stack<T> stack1;
    private Stack<T> stack2;

    public Queue()
    {
        // инициализация внутреннего хранилища очереди
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void enqueue(T item)
    {
        // вставка в хвост
        stack1.push(item);
    }

    public T dequeue()
    {
        // выдача из головы
        while (stack1.size() > 0) {
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }

    public int size()
    {
        return stack1.size() + stack2.size(); // размер очереди
    }
}