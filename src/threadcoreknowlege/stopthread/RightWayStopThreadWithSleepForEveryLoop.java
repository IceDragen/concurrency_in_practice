package threadcoreknowlege.stopthread;

/**
 * DESC：每次迭代后线程都被阻塞的情况
 * 这种情况下不需要再加Thread.currentThread().isInterrupted()这个判断，
 * 因为线程中每次sleep占了每次迭代的绝大部分时间，所有线程收到中断信号时肯定处于sleep状态中，
 * 所以此时的中断处理由系统帮我们去做。
 *
 */
public class RightWayStopThreadWithSleepForEveryLoop {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            try {
                int num = 0;
                while(num < 300){
                    if (num % 10 == 0){
                        System.out.println(num + "是10的倍数");
                    }
                    num++;
                    Thread.sleep(10);
                }
                System.out.println("程序运行结束");

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
