package keywordsynchronized;

/**
 * Desc：类锁的第一种形式：static形式
 * 需求：在使用了不同实例的锁后要起锁住同一个静态方法
 */
public class SynchronizedClassStatic implements Runnable{
    @Override
    public void run() {
        try {
            test();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static synchronized void test() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "正在静态同步方法中");
        Thread.sleep(500);
        System.out.println(Thread.currentThread().getName() + "离开了静态同步方法");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        SynchronizedClassStatic classStatic1 = new SynchronizedClassStatic();
        SynchronizedClassStatic classStatic2 = new SynchronizedClassStatic();
        Thread thread1 = new Thread(classStatic1);
        Thread thread2 = new Thread(classStatic2);
        thread1.start();
        thread2.start();

        //只要有一个线程存活就继续等待
        while (thread1.isAlive() || thread2.isAlive()){}

        System.out.println("finished");

    }
}
