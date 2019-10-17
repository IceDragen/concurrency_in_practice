package threadcoreknowlege.uncaughtexception;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 *         1. 不加try catch, 抛出4个异常，都带线程名字
 *         2. 加了try catch, 期望捕获第1个线程的异常，然后其他三个线程不应该再继续运行，希望看到打印出caught exception。
 *         然而执行时发现根本没有caught exception，其他三个线程依然运行并且抛出异常
 *
 *         说明线程的异常不能用传统方法捕获
 */
public class CantCatchException implements Runnable{
    @Override
    public void run() {
        throw new RuntimeException();
    }

    public static void main(String[] args) throws InterruptedException {
//        CantCatchException.dontTryCatch();
        CantCatchException.useTryCatch();
    }

    public static void dontTryCatch() throws InterruptedException {
        new Thread(new CantCatchException(), "Thread-1").start();
        TimeUnit.MILLISECONDS.sleep(300);

        new Thread(new CantCatchException(), "Thread-2").start();
        TimeUnit.MILLISECONDS.sleep(300);

        new Thread(new CantCatchException(), "Thread-3").start();
        TimeUnit.MILLISECONDS.sleep(300);

        new Thread(new CantCatchException(), "Thread-4").start();
        TimeUnit.MILLISECONDS.sleep(300);

    }

    public static void useTryCatch() throws InterruptedException {
        try {
            new Thread(new CantCatchException(), "Thread-1").start();
            TimeUnit.MILLISECONDS.sleep(300);

            new Thread(new CantCatchException(), "Thread-2").start();
            TimeUnit.MILLISECONDS.sleep(300);

            new Thread(new CantCatchException(), "Thread-3").start();
            TimeUnit.MILLISECONDS.sleep(300);

            new Thread(new CantCatchException(), "Thread-4").start();
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (RuntimeException e) {
            System.out.println("caught exception");
        }
    }
}
