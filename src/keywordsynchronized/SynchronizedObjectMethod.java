package keywordsynchronized;

/**
 * Desc: 方法锁实例，默认锁对象为this，即当前实例对象
 */
public class SynchronizedObjectMethod implements Runnable{
    @Override
    public void run() {
        try {
            test();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void test() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "正在同步方法中");
        Thread.sleep(500);
        System.out.println(Thread.currentThread().getName() + "离开了同步方法");
    }

    public static void main(String[] args) {
        SynchronizedObjectMethod objectMethod = new SynchronizedObjectMethod();
        Thread thread1 = new Thread(objectMethod);
        Thread thread2 = new Thread(objectMethod);
        thread1.start();
        thread2.start();

        //只要有一个线程存活就继续等待
        while (thread1.isAlive() || thread2.isAlive()){}

        System.out.println("finished");
    }


}
