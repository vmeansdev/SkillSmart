public class Main {

    private static String[] words = {"hello", "olleh", "aaa", "aab", "aba", "bba",
                                     "bab", "abb", "asd", "dsa", "sda", "fds",
                                     "qwe", "ewq", "wqe", "poi", "iop"};

    public static void main(String[] args) {
        testHashTableInit();
        testSeekSlot();
        stressTestSeekSlot();
        testFind();
        stressTestFind();
    }

    private static void testHashTableInit() {
        HashTable ht = new HashTable(17, 1);
        test(ht.slots[0] == null, "Should be null");
    }

    private static void seekCase(int size, int step) {
        HashTable ht = new HashTable(size, step);
        for (String word : words) {
            int slotIndex = ht.seekSlot(word);
            ht.put(word);
            test(ht.slots[slotIndex].equals(word), "Should be equal");
        }
        ht.put("more");
        ht.put("words");
        test(ht.getSize() == 19, "Should be 19");
        int slotIndex = ht.seekSlot("baa");
        test(slotIndex == -1, "Should be -1 as ht is full");
        int aaaSlotIndex = ht.find("aaa");
        test(aaaSlotIndex != ht.seekSlot("aaa"), "Should NOT be equal");
        int noValueIndex = ht.find("lol");
        test(noValueIndex == -1, "Should be -1");
    }

    private static void testSeekSlot() {
        seekCase(19, 1);
    }

    private static void stressTestSeekSlot() {
        for (int step = 1; step <= 1000; step++) {
            seekCase(19, step);
        }
    }

    private static void findCase(int size, int step) {
        HashTable ht = new HashTable(size, step);
        for (String word : words) {
            int slotIndex = ht.find(word);
            test(slotIndex == -1, "Should be -1");
            ht.put(word);
        }
        test(ht.getSize() == 17, "Should be 17");
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
