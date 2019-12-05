package Lab4.BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Створюємо чергу з 10 елементів:
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

        // Створюємо і запускаємо два потоки - для запису і читання:
        Thread adderThread = new Thread(new Adder(queue, 100));
        Thread averagerThread = new Thread(new Averager(queue, 100));
        adderThread.start();
        averagerThread.start();

        // Чекаємо 10 секунд і перериваємо перший потік:
        Thread.sleep(20000);
        adderThread.interrupt();
        averagerThread.interrupt();
    }
}
