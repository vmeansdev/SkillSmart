public class QueueSpinner {

    public static void spinQueue(Queue queue, int elementsCount) {
        System.out.println("Spin queue for " + elementsCount + " elements");
        System.out.println(queue);
        for (int i = 0; i < elementsCount; i++) {
            queue.enqueue(queue.dequeue());
            System.out.println(queue);
        }
        System.out.println("===============================================");
    }

}
