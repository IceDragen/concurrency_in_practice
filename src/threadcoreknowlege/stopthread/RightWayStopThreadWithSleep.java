package threadcoreknowlege.stopthread;

/**
 * DESC：线程可能被阻塞的情况
 * 新开的线程中有sleep方法调用，这种情况下线程在收到中断信号时如果处于sleep状态，
 * 那么会抛出异常：java.lang.InterruptedException: sleep interrupted，
 * 这是当线程处于sleep状态时被中断的默认做法。
 */
public class RightWayStopThreadWithSleep {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            try {
                int num = 0;
                while(!Thread.currentThread().isInterrupted() && num < 300){
                    if (num % 100 == 0){
                        System.out.println(num + "是100的倍数");
                    }
                    num++;
                }
                System.out.println("程序运行结束");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }
}
