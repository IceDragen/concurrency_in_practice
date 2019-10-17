package keywordsynchronized;

/**
 * Desc: 这个例子展示用两把不同的锁住两段代码块的情况，这种情况下要注意在当thread0在结束第一段代码块时候会释放掉lock1，
 * 此时thread1会获取到lock1从而开始执行第一段代码块，也就是说thread0执行第二段代码块和thread1执行第一段代码块的时间几乎是一样的。
 */
public class SynchronizedObjectCodeBlock3 implements Runnable {

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    @Override
    public void run() {
        synchronized (lock1) {
            try {
                System.out.println("我是lock1，" + Thread.currentThread().getName() + "进入了第一段同步代码块");
                Thread.sleep(10);
                System.out.println("我是lock1，" + Thread.currentThread().getName() + "结束了第一段同步代码块");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        synchronized (lock2) {
            try {
                System.out.println("我是lock2，" + Thread.currentThread().getName() + "进入了第二段同步代码块");
                Thread.sleep(10);
                System.out.println("我是lock2，" + Thread.currentThread().getName() + "结束了第二段同步代码块");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedObjectCodeBlock3 lock = new SynchronizedObjectCodeBlock3();
        Thread thread1 = new Thread(lock);
        Thread thread2 = new Thread(lock);
        thread1.start();
        thread2.start();

        //只要有一个线程存活就继续等待
        while (thread1.isAlive() || thread2.isAlive()) {
        }

        System.out.println("finished");
    }
}
