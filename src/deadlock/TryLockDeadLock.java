package deadlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description: 使用TryLock方法解决死锁问题
 */
public class TryLockDeadLock {
    private final static Lock LOCK1 = new ReentrantLock();
    private final static Lock LOCK2 = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
                    if (LOCK1.tryLock(300, TimeUnit.MILLISECONDS)){
                        System.out.println(Thread.currentThread().getName() + "获取了第一把锁");
                        if (LOCK2.tryLock(300, TimeUnit.MILLISECONDS)){
                            System.out.println(Thread.currentThread().getName() + "成功获取了两把锁");
                            LOCK1.unlock();
                            LOCK2.unlock();
                            break;
                        }else {
                            System.out.println(Thread.currentThread().getName() + "获取第二把锁失败");
                            LOCK1.unlock();
                            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
                        }
                    }else {
                        System.out.println(Thread.currentThread().getName() + "获取第一把锁失败");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
                    if (LOCK2.tryLock(3000, TimeUnit.MILLISECONDS)){
                        System.out.println(Thread.currentThread().getName() + "获取了第二把锁");
                        if (LOCK1.tryLock(3000, TimeUnit.MILLISECONDS)){
                            System.out.println(Thread.currentThread().getName() + "成功获取了两把锁");
                            LOCK2.unlock();
                            LOCK1.unlock();
                            break;
                        }else {
                            System.out.println(Thread.currentThread().getName() + "获取第一把锁失败");
                            LOCK2.unlock();
                            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
                        }
                    }else {
                        System.out.println(Thread.currentThread().getName() + "获取第二把锁失败");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
