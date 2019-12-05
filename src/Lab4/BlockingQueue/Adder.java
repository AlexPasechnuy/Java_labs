package Lab4.BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Adder implements Runnable {
    private BlockingQueue<Integer> queue;
    int countToAdd;

    public Adder(BlockingQueue<Integer> queue, int countToAdd) {
        this.queue = queue;
        this.countToAdd = countToAdd;
    }

    public void run() {
        // Намагаємося додавати числа:
        try {
            for (int i = 1; i <= countToAdd; i++) {
                queue.put(i);
                System.out.printf("Added: %d%n", i);
                Thread.sleep(100);
            }
        }
        catch (InterruptedException e) {
            System.out.println("Producer interrupted");
        }
    }
}