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

    private int hashFun2(int index, int counter) {
        int step = (this.step % size == 0) ? this.step + 1 : this.step;
        return (index + (step * counter)) % size;
    }

    public int seekSlot(String value)
    {
        // находит индекс пустого слота для значения, или -1
        int index = hashFun(value);
        int start = index;
        int counter = 1;
        do {
            if (slots[index] == null) {
                return index;
            } else {
                index = hashFun2(index, counter);
                if (index >= size) {
                    index = 0;
                    counter += 1;
                }
            }
        } while (slots[index] != null && index != start);

        return index == start ? -1 : index;
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
        int index = hashFun(value);
        int start = index;
        int counter = 1;
        do {
            if (slots[index] == null) {
                return -1;
            } else {
                if (slots[index].equals(value)) {
                    break;
                } else {
                    index = hashFun2(index, counter);
                    if (index >= size) {
                        index = 0;
                        counter += 1;
                    }
                }
            }
        } while (slots[index] != null && !slots[index].equals(value) && index != start);

        if (slots[index] != null && slots[index].equals(value))
            return index;

        return -1;
    }

    public int getSize() {
        return currentSize;
    }
}