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
        try {
            for (int i = 1; i <= countToTake; i++) {
                sum += queue.take();
                num++;
                System.out.println("Average: " + sum/num);
                Thread.sleep(100);
            }
        }
        catch (InterruptedException e) {
            System.out.println("Averager interrupted");
        }
    }
}