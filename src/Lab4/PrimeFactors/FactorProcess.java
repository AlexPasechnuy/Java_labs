package Lab4.PrimeFactors;

import javafx.application.Platform;

import java.util.ArrayList;

public class FactorProcess implements Runnable {
    private Thread primesThread;
    private int to;
    private int from;
    private ArrayList<Integer> lastFound;
    private Runnable displayFunc;
    private Runnable percentageFunc;
    private Runnable finishFunc;
    private double percentage;
    private boolean suspended;
    private boolean stopped;

    public FactorProcess(Runnable addFunc, Runnable percentageFunc, Runnable finishFunc) {
        this.displayFunc = addFunc;
        this.percentageFunc = percentageFunc;
        this.finishFunc = finishFunc;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        if(to < from){throw new IllegalArgumentException();}
        this.to = to;
    }
    public void setFrom(int from) {
        if(from <= 0){throw new IllegalArgumentException();}
        this.from = from;
    }

    public synchronized ArrayList<Integer> getLastFound() {
        return lastFound;
    }

    private synchronized void setLastFound(ArrayList<Integer> lastFound) {
        this.lastFound = lastFound;
    }

    public synchronized double getPercentage() {
        return percentage;
    }

    private synchronized void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public synchronized boolean isSuspended() {
        return suspended;
    }

    private synchronized void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    public synchronized boolean isStopped() {
        return stopped;
    }

    private synchronized void setStopped(boolean stopped) {
        this.stopped = stopped;
    }
    private boolean isPrime(int number){
        for(int i=2;i<=number/2;i++){
            if(number%i==0){
                return false;
            }
        }
        return true;
    }

    @Override
    public void run() {

        for (int n = from; n <= to; n++)
        {
            try {
                ArrayList<Integer> res=new ArrayList<>();
                res.add(n);
                setPercentage(n * 1.0 / to);

                if (percentageFunc != null) {
                    Platform.runLater(percentageFunc);
                }
                int u=n;
                while(u!=1) {
                    for (int i = 2; i <= u; i++) {
                        if (u % i == 0 && isPrime(i)) {
                            res.add(i);
                            u/=i;
                        }
                    }
                }

                Thread.sleep(200);

                setLastFound(res);

                if (displayFunc != null) {
                    displayFunc.run();
                }

            }
            catch (InterruptedException e) {

                while (isSuspended()) {
                    try {
                        Thread.sleep(100);
                    }
                    catch (InterruptedException e1) {

                        if (isStopped()) {
                            break;
                        }
                    }
                }
                if (isStopped()) {
                    break;
                }
            }
        }
        if (finishFunc != null) {
            Platform.runLater(finishFunc);
        }
    }

    public void start() {
        primesThread = new Thread(this);
        setSuspended(false);
        setStopped(false);
        primesThread.start();
    }

    public void suspend() {
        setSuspended(true);
        primesThread.interrupt();
    }

    public void resume() {
        setSuspended(false);
        primesThread.interrupt();
    }

    public void stop() {
        setStopped(true);
        primesThread.interrupt();
    }
}
