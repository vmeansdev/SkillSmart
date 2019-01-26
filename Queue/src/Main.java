public class Main {

    public static void main(String[] args) {
        testQueueInitSizeIsZero();
        testQueueSizeIsChangedAfterEnqueue();
        testQueueReturnsNullOnDequeueWhenEmpty();
        testQueueReturnsFirstlyAddedObjectOnDequeue();
        testQueueSizeIsChangedAfterDequeue();
        testQueueSpinnerSpinQueue();
    }

    private static void testQueueInitSizeIsZero() {
        Queue<Integer> queue = new Queue<>();
        test(queue.size() == 0, "Queue size should be 0");
    }

    private static void testQueueSizeIsChangedAfterEnqueue() {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(1);
        test(queue.size() == 1, "Queue size should be 1");
    }

    private static void testQueueReturnsNullOnDequeueWhenEmpty() {
        Queue<Integer> queue = new Queue<>();
        Integer element = queue.dequeue();
        test(element == null, "Element should be null");
    }

    private static void testQueueReturnsFirstlyAddedObjectOnDequeue() {
        Queue<Integer> queue = new Queue<>();
        Integer elementOne = 1;
        Integer elementTwo = 2;
        Integer elementThree = 3;
        queue.enqueue(elementOne);
        queue.enqueue(elementTwo);
        queue.enqueue(elementThree);
        test(queue.dequeue().equals(elementOne), "Dequeue should return firstly added object");
        test(queue.dequeue().equals(elementTwo), "Dequeue should return next to firstly added object");
        test(queue.dequeue().equals(elementThree), "Dequeue should return next object");
        test(queue.dequeue() == null, "Dequeue should return null when nothing left");
    }

    private static void testQueueSizeIsChangedAfterDequeue() {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        test(queue.size() == 3, "Queue size should be 3");
        queue.dequeue();
        test(queue.size() == 2, "Queue size should be 2");
    }

    private static void testQueueSpinnerSpinQueue() {
        Queue<Integer> queue = prepareQueue();
        QueueSpinner.spinQueue(queue, 3);

        queue = prepareQueue();
        QueueSpinner.spinQueue(queue, 4);

        queue = prepareQueue();
        QueueSpinner.spinQueue(queue, 5);

        queue = prepareQueue();
        QueueSpinner.spinQueue(queue, 6);
    }

    private static Queue<Integer> prepareQueue() {
        Queue<Integer> queue = new Queue<>();
        for (int j = 0; j < 5; j++) {
            queue.enqueue(j);
        }

        return queue;
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
