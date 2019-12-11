package Lab4.BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

        Thread adderThread = new Thread(new Adder(queue, 5));
        Thread averagerThread = new Thread(new Averager(queue, 5));
        adderThread.start();
        averagerThread.start();

        Thread.sleep(500);
        adderThread.interrupt();
        averagerThread.interrupt();
    }
}
