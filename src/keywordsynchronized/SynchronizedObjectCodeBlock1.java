package keywordsynchronized;

/**
 * Desc: 这个例子展示只用一把锁锁住一段代码块的情况，这种情况下只有当其中一个线程执行完代码块所有内容后另一个线程才会获得代码块的执行权力
 */
public class SynchronizedObjectCodeBlock1 implements Runnable{

    @Override
    public void run() {
        synchronized (this){
            try {
                System.out.println(Thread.currentThread().getName() + "进入了同步代码块");
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + "结束了同步代码块");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedObjectCodeBlock1 lock = new SynchronizedObjectCodeBlock1();
        Thread thread1 = new Thread(lock);
        Thread thread2 = new Thread(lock);
        thread1.start();
        thread2.start();

        //只要有一个线程存活就继续等待
        while (thread1.isAlive() || thread2.isAlive()){}

        System.out.println("finished");
    }
}
