import java.lang.reflect.Array;
import java.util.Arrays;

public class DynArray<T>
{
    public T [] array;
    public int count;
    public int capacity;
    private Class<T> clazz;

    private static final int DEFAULT_CAPACITY = 16;
    private static final int CAPACITY_MULTIPLIER = 2;
    private static final double CAPACITY_DENOMINATOR = 1.5;
    private static final int OCCUPANCY_EDGE_PERCENTAGE = 50;

    public DynArray(Class<T> clazz)
    {
        this.clazz = clazz;
        this.count = 0;
        this.capacity = DEFAULT_CAPACITY;
        makeArray(capacity);
    }

    public void makeArray(int new_capacity)
    {
        // ваш код
        if (count != 0) {
            this.capacity = new_capacity;
            array = Arrays.copyOf(array, new_capacity);
            return;
        }
        array = _makeArray(new_capacity);
    }

    public T getItem(int index)
    {
        // ваш код
        if (isOutOfBounds(index)) {
            throw new IndexOutOfBoundsException();
        }

        return array[index];
    }

    public void append(T itm)
    {
        // ваш код
        if (count == capacity) {
            // increase capacity
            increaseBuffer();
            array = Arrays.copyOf(array, capacity);
        }
        array[count] = itm;
        increaseCount();
    }

    public void insert(T itm, int index)
    {
        // ваш код
        if (index <= -1 || (count > 0 && index > count)) {
            throw new IndexOutOfBoundsException();
        }

        if (index == count) {
            append(itm);
            return;
        }

        if (count == capacity) {
            increaseBuffer();
        }

        T[] newArr = _makeArray(capacity);
        System.arraycopy(array, 0, newArr, 0, index);
        newArr[index] = itm;
        int elementsCount = (count - index);
        System.arraycopy(array, index, newArr, index + 1, elementsCount);
        array = newArr;
        increaseCount();
    }

    public void remove(int index)
    {
        // ваш код
        if (isOutOfBounds(index)) {
            throw new IndexOutOfBoundsException();
        }

        if (occupancyPercentage(count - 1, capacity) < OCCUPANCY_EDGE_PERCENTAGE) {
            decreaseBuffer();
        }
        T[] newArr = _makeArray(capacity);
        System.arraycopy(array, 0, newArr, 0, index);
        System.arraycopy(array, index + 1,newArr, index, capacity - index - 1);
        decreaseCount();
        array = newArr;
    }

    @SuppressWarnings("unchecked")
    private T[] _makeArray(int capacity)
    {
        return (T[]) Array.newInstance(this.clazz, capacity);
    }

    private boolean isOutOfBounds(int index)
    {
        return index >= count || index <= -1;
    }

    private void increaseBuffer()
    {
        this.capacity = this.capacity * CAPACITY_MULTIPLIER;
    }

    private void increaseCount() {
        count += 1;
    }

    private void decreaseBuffer()
    {
        if (capacity <= DEFAULT_CAPACITY) {
            return;
        }

        int newCapacity = (int)(this.capacity / CAPACITY_DENOMINATOR);
        if (newCapacity < DEFAULT_CAPACITY) {
            newCapacity = DEFAULT_CAPACITY;
        }
        this.capacity = newCapacity;
    }

    private void decreaseCount() {
        count -= 1;
    }

    private int occupancyPercentage(int count, int capacity)
    {
        return (100 * count) / capacity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < count; i++) {
            sb.append(array[i]);
            sb.append((i == count -1) ? "" : ", ");
        }
        sb.append("]");
        return sb.toString();
    }

}