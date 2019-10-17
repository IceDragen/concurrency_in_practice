package keywordsynchronized;

/**
 * Desc: 这个例子展示只用一把锁锁住两段代码块的情况，这种情况下大概率其中一个线程会依次执行完两段代码块，
 * 但也有小概率情况下当某一个线程执行完第一段代码块释放掉lock以后另一个线程会获取到这个lock从而开始执行代码逻辑
 * 小概率结果：
 * Thread-0进入了第一段同步代码块
 * Thread-0结束了第一段同步代码块
 * Thread-1进入了第一段同步代码块
 * Thread-1结束了第一段同步代码块
 * Thread-1进入了第二段同步代码块
 * Thread-1结束了第二段同步代码块
 * Thread-0进入了第二段同步代码块
 * Thread-0结束了第二段同步代码块
 * finished
 *
 * 大概率结果：
 * Thread-0进入了第一段同步代码块
 * Thread-0结束了第一段同步代码块
 * Thread-0进入了第二段同步代码块
 * Thread-0结束了第二段同步代码块
 * Thread-1进入了第一段同步代码块
 * Thread-1结束了第一段同步代码块
 * Thread-1进入了第二段同步代码块
 * Thread-1结束了第二段同步代码块
 * finished
 */
public class SynchronizedObjectCodeBlock2 implements Runnable{

    private final Object lock= new Object();
    @Override
    public void run() {
        synchronized (lock){
            try {
                System.out.println(Thread.currentThread().getName() + "进入了第一段同步代码块");
                Thread.sleep(10);
                System.out.println(Thread.currentThread().getName() + "结束了第一段同步代码块");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        synchronized (lock){
            try {
                System.out.println(Thread.currentThread().getName() + "进入了第二段同步代码块");
                Thread.sleep(10);
                System.out.println(Thread.currentThread().getName() + "结束了第二段同步代码块");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedObjectCodeBlock2 lock = new SynchronizedObjectCodeBlock2();
        Thread thread1 = new Thread(lock);
        Thread thread2 = new Thread(lock);
        thread1.start();
        thread2.start();

        //只要有一个线程存活就继续等待
        while (thread1.isAlive() || thread2.isAlive()){}

        System.out.println("finished");
    }
}
