package threadcoreknowlege.threadobjectclasscommonmethod;

import java.sql.Time;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Description: 演示sleep方法被中断的情况
 *      每隔一秒钟打印当前时间，过一段时间被中断，观察
 * Thread.sleep
 * TimeUnit.SECONDS.sleep()
 */
public class SleepInterrupted implements Runnable{

    @Override
    public void run() {
        for (;;){
            System.out.println(new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("我被中断了");
                break;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new SleepInterrupted());
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}
