public class Main {

    public static void main(String[] args) {
        testArrayCreatedWithProperType();

        testArrayIsEmptyAfterCreation();
        testMakeArrayCopiesArrayValues();
        testAppendWorksProperly();

        testInsertWorksProperly();
        testInsertThrowsExceptionWhenIndexIsNegative();
        testInsertThrowsExceptionWhenIndexIsMoreThanCount();
        testInsertIncreasesBufferSizeWhenLimitReached();

        testRemoveLeaveBufferSizeUnchanged();
        testRemoveDecreaseBufferSize();
        testRemoveThrowsExceptionWhenIndexIsNegative();
        testRemoveThrowsExceptionWhenIndexIsMoreThanCount();
    }

    public static void testArrayCreatedWithProperType() {
        DynArray<String> strings = new DynArray<>(String.class);
        DynArray<Integer> ints = new DynArray<>(Integer.class);
        DynArray<Double> doubles = new DynArray<>(Double.class);

        Class cls = strings.array.getClass();
        String stringsTypeName = cls.getSimpleName();
        test(
                stringsTypeName.equalsIgnoreCase("String[]"),
                "Type of strings array should be String, but it's " + stringsTypeName
        );

        String intsTypeName = ints.array.getClass().getSimpleName();
        test(
                intsTypeName.equalsIgnoreCase("Integer[]"),
                "Type of ints array should be Integer, but it's " + intsTypeName
        );

        String doublesTypeName = doubles.array.getClass().getSimpleName();
        test(
                doublesTypeName.equalsIgnoreCase("Double[]"),
                "Type of doubles array should be Double, but it's " + doublesTypeName
        );
    }

    private static void testArrayIsEmptyAfterCreation() {
        DynArray<Integer> arr = new DynArray<>(Integer.class);
        test(
                arr.count == 0,
                "Array should be empty"
        );
    }

    private static void testMakeArrayCopiesArrayValues() {
        DynArray<Integer> arr = new DynArray<>(Integer.class);
        test(arr.count == 0, "Arr size should be 0");
        test(arr.capacity == 16, "Arr capacity should be 16");

        for (int i = 0; i < 4; i++) {
            arr.append(i);
        }
        test(arr.count == 4, "Arr size should become 4");
        test(arr.getItem(0) == 0, "First element should be 0");
        test(arr.getItem(3) == 3, "Last element should be 4");
        arr.makeArray(32);
        test(arr.count == 4, "Arr size should remain 4");
        test(arr.capacity == 32, "Arr capacity should become 32");
    }

    private static void testAppendWorksProperly() {
        DynArray<Integer> arr = new DynArray<>(Integer.class);
        for (int i = 0; i < 15; i++) {
            arr.append(i * i);
        }
        int oldCapacity = arr.capacity;
        test(arr.count == 15, "Array size should be 15");
        test(arr.capacity == 16, "Capacity should not be changed until limit reached");

        arr.append(1);
        arr.append(2);
        int newCapacity = oldCapacity * 2;
        test(arr.count == 17, "Array size should become 17");
        test(arr.capacity == newCapacity, "Capacity should become twice as big as previous value");
    }

    private static void testInsertWorksProperly() {
        DynArray<Integer> arr = new DynArray<>(Integer.class);
        arr.insert(2, 0);
        test(arr.count == 1, "Arr size should become 1");
        test(arr.getItem(0) == 2, "Item at 0 should be 2");
        arr = new DynArray<>(Integer.class);

        for (int i = 0; i < 15; i++) {
            arr.append(2);
        }
        arr.insert(5, 1);
        test(arr.count == 16, "Arr size should become 16");
        test(arr.getItem(1) == 5, "Item should be inserted");

        arr.insert(3, 1);
        test(arr.count == 17, "Arr size should be 17");
        test(arr.getItem(1) == 3, "Item should be inserted");
        test(arr.getItem(2) == 5, "Items should be shifted");
    }

    private static void testInsertThrowsExceptionWhenIndexIsNegative() {
        DynArray<Integer> arr = new DynArray<>(Integer.class);
        try {
            arr.insert(0, -1);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Attempt to insert at negative index throws exception");
        }
    }

    private static void testInsertThrowsExceptionWhenIndexIsMoreThanCount() {
        DynArray<Integer> arr = new DynArray<>(Integer.class);
        try {
            arr.insert(0, 1);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Attempt to insert at index that more than count throws exception");
        }
    }

    private static void testInsertIncreasesBufferSizeWhenLimitReached() {
        DynArray<Integer> arr = new DynArray<>(Integer.class);
        int oldCapacity = arr.capacity;
        int newCapacity = oldCapacity * 2;
        for (int i = 0; i < 16; i++) {
            arr.append(2);
        }
        test(arr.count == 16, "Arr size should be 16");
        test(arr.capacity == oldCapacity, "Capacity should be the same");

        arr.append(2);
        test(arr.capacity == newCapacity, "Capacity should be increased");
    }

    private static void testRemoveLeaveBufferSizeUnchanged() {
        DynArray<Integer> arr = new DynArray<>(Integer.class);
        for (int i = 0; i < 25; i++) {
            arr.append(i);
        }
        int oldCapacity = arr.capacity;
        test(arr.count == 25, "Arr size should be 25");
        arr.remove(0);
        test(arr.count == 24, "Arr size should become 24");
        test(arr.getItem(0) == 1, "First item should become 1");
        arr.remove(5);
        test(arr.getItem(5) == 7, "Fifth item shoud become 6");
        test(arr.capacity == oldCapacity, "Capacity should be the same");
    }

    private static void testRemoveDecreaseBufferSize() {
        DynArray<Integer> arr = new DynArray<>(Integer.class);
        for (int i = 0; i < 33; i++) {
            arr.append(i);
        }

        int oldCapacity = arr.capacity;
        test(arr.count == 33, "Arr size should be 5");
        arr.remove(0);
        test(arr.count == 32, "Arr size should become 4");
        test(arr.getItem(0) == 1, "First item should become 1");
        arr.remove(5);
        test(arr.getItem(5) == 7, "Fifth item shoud become 6");
        test(arr.capacity < oldCapacity, "Capacity should be decreased");
    }

    private static void testRemoveThrowsExceptionWhenIndexIsNegative() {
        DynArray<Integer> arr = new DynArray<>(Integer.class);
        try {
            arr.remove(-1);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Attempt to remove item at negative index throws an exception");
        }
    }

    private static void testRemoveThrowsExceptionWhenIndexIsMoreThanCount() {
        DynArray<Integer> arr = new DynArray<>(Integer.class);
        try {
            arr.remove(80);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Attempt to remove item at index bigger than array count throws an exception");
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
