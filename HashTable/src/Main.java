import java.nio.charset.Charset;
import java.util.Random;

public class Main {

    private static String[] words = {"hello", "olleh", "aaa", "aab", "aba", "bba",
                                     "bab", "abb", "asd", "dsa", "sda", "fds",
                                     "qwe", "ewq", "wqe", "poi", "iop"};

    public static void main(String[] args) {
        testHashTableInit();
        testSeekSlot();
//        stressTestSeekSlot();
        testFind();
        stressTestFind();
        testHashFun();
    }

    private static String randomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 32;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    private static void testHashTableInit() {
        HashTable ht = new HashTable(17, 1);
        test(ht.slots[0] == null, "Should be null");
    }

    private static void testHashFun() {
        HashTable ht = new HashTable(19, 3);
        for (int i = 0; i < 1000; i++) {
            String random = randomString();
            int hash = ht.hashFun(random);
            test(hash == ht.hashFun(random), "Should be equal");
        }
    }

    private static void seekCase(int size, int step) {
        HashTable ht = new HashTable(size, step);
        for (int i = 0; i < size; i++) {
            String generatedString = randomString();
            int slotIndex = ht.seekSlot(generatedString);
            ht.put(generatedString);
            if (slotIndex != -1) {
                test(ht.slots[slotIndex].equals(generatedString), "Should be equal");
            } else {
                int foundIndex = ht.find(generatedString);
                System.out.println(
                    "slotIndex = " + slotIndex + " ht.size = " + size + " step = " + step + " value = " + generatedString
                    + " foundIndex = " + foundIndex
                );
            }
        }
        test(ht.getSize() == size, "Should be as given size");
    }

    private static void testSeekSlot() {
        seekCase(19, 1);
    }

    private static void stressTestSeekSlot() {
        for(int size = 1; size <= 1000; size++) {
            for (int step = 1; step <= 1000; step++) {
                seekCase(size, step);
            }
        }
    }

    private static void findCase(int size, int step) {
        HashTable ht = new HashTable(size, step);
        for (int i = 0; i < size; i++) {
            String random = randomString();
            int index = ht.find(random);
            test(index == -1, "Should be -1");
            ht.put(random);
            int newIndex = ht.find(random);
            test(newIndex != -1, "Should NOT be -1");
        }
        test(ht.getSize() == size, "Should be " + size);
    }

    private static void testFind() {
        findCase(17, 3);
    }

    private static void stressTestFind() {
        for (int step = 1; step <= 1000; step++) {
            findCase(17, step);
        }
    }

    /*
        TEST helper methods
     */

    private static void test(boolean condition, String description) {
        try {
            check(condition, description);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void check(boolean condition, String description) throws Exception {
        if (!condition) {
            throw new Exception(description);
        }
    }
}
