import java.nio.charset.Charset;

public class HashTable
{
    public int size;
    public int step;
    public String [] slots;

    private int currentSize = 0;

    public HashTable(int sz, int stp)
    {
        size = sz;
        step = stp;
        slots = new String[size];
        for(int i=0; i<size; i++) slots[i] = null;
    }

    public int hashFun(String value)
    {
        // всегда возвращает корректный индекс слота
        int sum = 0;
        byte[] bs = value.getBytes(Charset.forName("UTF-8"));
        for (byte b : bs) {
            sum += (int)b;
        }
        return sum % size;
    }

    public int seekSlot(String value)
    {
        // находит индекс пустого слота для значения, или -1
        int index = hashFun(value);



        return slots[index] != null ? -1 : index;
    }

    public int put(String value)
    {
        // записываем значение по хэш-функции
        int slotIndex = seekSlot(value);
        if (slotIndex != -1) {
            slots[slotIndex] = value;
            currentSize += 1;
            return slotIndex;
        }
        // возвращается индекс слота или -1
        // если из-за коллизий элемент не удаётся разместить
        return -1;
    }

    public int find(String value)
    {
        // находит индекс слота со значением, или -1
        int slotIndex = seekSlot(value);
        if (slotIndex != -1 && slots[slotIndex] != null) {
            return slotIndex;
        }
        return -1;
    }

    public int getSize() {
        return currentSize;
    }
}