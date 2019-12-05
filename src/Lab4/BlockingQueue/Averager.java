package Lab4.BlockingQueue;

import java.util.concurrent.BlockingQueue;

class Averager implements Runnable {
    private BlockingQueue<Integer> queue;
    int countToTake;
    double sum = 0;
    int num = 0;

    public Averager(BlockingQueue<Integer> queue, int countToTake) {
        this.queue = queue;
        this.countToTake = countToTake;
    }

    public void run() {
        // Вилучаємо числа:
        try {
            for (int i = 1; i <= countToTake; i++) {
//                System.out.printf("Taken by customer: %d%n", queue.take());
                sum += queue.take();
                num++;
                System.out.println("Average: " + sum/num);
            }
        }
        catch (InterruptedException e) {
            System.out.println("Consumer interrupted");
        }
    }
}