package deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * Description: 用ThreadMXBean检测死锁
 */
public class ThreadMXBeanDetection {
    private final static Object lock1 = new Object();
    private final static Object lock2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (lock1){
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2){
                    System.out.println(Thread.currentThread().getName() + "获得了两把锁");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock2){
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1){
                    System.out.println(Thread.currentThread().getName() + "获得了两把锁");
                }
            }
        });

        t1.start();
        t2.start();

       TimeUnit.SECONDS.sleep(1);

       ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();
       //拿到的是发生所有发生死锁的线程id
       long[] deadlockedThreads = mxBean.findDeadlockedThreads();
       Arrays.stream(deadlockedThreads).forEach(value -> {
           ThreadInfo info = mxBean.getThreadInfo(value);
           System.out.println(info.getThreadName() + "发生死锁啦");
       });

    }
}
