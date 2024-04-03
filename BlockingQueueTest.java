import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue {
    private final Queue<Object> items = new LinkedList<>();
    private final int maxSize;

    public BlockingQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public synchronized void enqueue(Object o) throws InterruptedException {
        while (items.size() == maxSize || items.contains(o)) {
            wait();
        }
        items.add(o);
        notify();
    }

    public synchronized void dequeue(Object o) throws InterruptedException {
        while (items.isEmpty() || !items.contains(o)) {
            wait();
        }
        items.remove(o);
        notify();
    }

    public int size() {
        return items.size();
    }
}
