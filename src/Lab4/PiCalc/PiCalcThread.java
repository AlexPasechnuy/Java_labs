package Lab4.PiCalc;

import javafx.application.Platform;

public class PiCalcThread implements Runnable {
    private Thread primesThread;
    private int num;
    private double eps;
    private double lastFound;
    private Runnable displayFunc;
    private Runnable percentageFunc;
    private Runnable finishFunc;
    private double percentage;
    private boolean suspended;
    private boolean stopped;
    private int count;
    public PiCalcThread(Runnable addFunc, Runnable percentageFunc, Runnable finishFunc) {
        this.displayFunc = addFunc;
        this.percentageFunc = percentageFunc;
        this.finishFunc = finishFunc;
    }

    public int getNum() {
        return num;
    }
    public int getCount(){return count;}
    public void setCount(int count){this.count=count;}
    public void setNum(int to) {
        this.num = to;
    }
    public void setEps(double eps){this.eps=eps;}

    public synchronized double getLastFound() {
        return lastFound;
    }

    private synchronized void setLastFound(double lastFound) {
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

    @Override
    public void run() {
     double result=0;
     boolean sign=true;
     int k=1;

        for (int n = 1; n <= num; n++)
        {
            setCount(n-1);
            try {


                setPercentage(n * 1.0 / num);
                // Оновлюємо кількість відсотків:
                if (percentageFunc != null) {
                    Platform.runLater(percentageFunc);
                }
                if(sign){
                    result+=(4/(double)k);
                    sign=false;
                }
                else{
                    result-=(4/(double)k);
                    sign=true;
                }
                k+=2;

                Thread.sleep(200);
                setLastFound((double)Math.round(result*1/eps)/(1/eps));


                if (displayFunc != null) {
                        displayFunc.run();
                }

            }
            catch (InterruptedException e) {
                n--;
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
