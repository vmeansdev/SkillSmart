public class Main {

    private static String[] words = {"hello", "olleh", "aaa", "aab", "aba", "bba",
                                     "bab", "abb", "asd", "dsa", "sda", "fds",
                                     "qwe", "ewq", "wqe", "poi", "iop"};

    public static void main(String[] args) {
        testHashTableInit();
        testSeekSlot();
        testFind();
    }

    private static void testHashTableInit() {
        HashTable ht = new HashTable(17, 1);
        test(ht.slots[0] == null, "Should be null");
    }

    private static void testSeekSlot() {
        HashTable ht = new HashTable(19, 3);
        for (String word : words) {
            int slotIndex = ht.seekSlot(word);
            ht.put(word);
            test(ht.slots[slotIndex].equals(word), "Should be equal");
        }
        test(ht.getSize() == 19, "Should be 19");
        int slotIndex = ht.seekSlot("baa");
        test(slotIndex == -1, "Should be -1 as ht is full");
        int aaaSlotIndex = ht.find("aaa");
        test(aaaSlotIndex == ht.seekSlot("aaa"), "Should be equal");
        int noValueIndex = ht.find("lol");
        test(noValueIndex == -1, "Should be -1");
    }

    private static void testFind() {
        HashTable ht = new HashTable(17, 1);
        for (String word : words) {
            int slotIndex = ht.find(word);
            test(slotIndex == -1, "Should be -1");
        }
        test(ht.getSize() == 17, "Should be 17");
        ht.put("lol");
        int lolIndex = ht.find("lol");
        test(lolIndex != -1, "Should not be -1");
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
