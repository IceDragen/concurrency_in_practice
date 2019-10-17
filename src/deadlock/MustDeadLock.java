package deadlock;

import java.util.concurrent.TimeUnit;

/**
 * Description: 演示两个线程一定发生死锁的情况
 */
public class MustDeadLock {
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

        t1.join();
        t2.join();

        System.out.println("主线程运行结束");
    }
}
