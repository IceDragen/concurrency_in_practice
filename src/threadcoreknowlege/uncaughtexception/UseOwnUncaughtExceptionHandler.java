package threadcoreknowlege.uncaughtexception;

import java.util.concurrent.TimeUnit;

/**
 * Description: 如类名所示
 */
public class UseOwnUncaughtExceptionHandler {
    public static void main(String[] args) throws InterruptedException {

        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());

        new Thread(new CantCatchException(), "Thread-1").start();
        TimeUnit.MILLISECONDS.sleep(300);

        new Thread(new CantCatchException(), "Thread-2").start();
        TimeUnit.MILLISECONDS.sleep(300);

        new Thread(new CantCatchException(), "Thread-3").start();
        TimeUnit.MILLISECONDS.sleep(300);

        new Thread(new CantCatchException(), "Thread-4").start();
        TimeUnit.MILLISECONDS.sleep(300);
    }
}
