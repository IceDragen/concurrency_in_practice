package threadcoreknowlege.threadobjectclasscommonmethod;

/**
 * Desc: 3个线程，线程1和线程2首先被阻塞，线程3唤醒它们。
 *       notify，notifyAll。
 *       start先执行不代表线程先启动
 */
public class WaitNotifyAll implements Runnable{

    private static final Object resourceA = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new WaitNotifyAll());
        Thread thread2 = new Thread(new WaitNotifyAll());
        Thread thread3 = new Thread(() -> {
           synchronized (resourceA){
               resourceA.notifyAll();
               System.out.println(Thread.currentThread().getName() + "调用了resourceA的notifyAll()方法");
           }
        });

        thread1.start();
        thread2.start();
        //确保thread1和thread2已经进入waiting状态
        Thread.sleep(200);
        thread3.start();
    }

    @Override
    public void run() {
        synchronized (resourceA){
            System.out.println(Thread.currentThread().getName() + "获得了锁resourceA");

            try {
                System.out.println(Thread.currentThread().getName() + "释放了锁resourceA，即将进入等待状态");
                resourceA.wait();
                System.out.println(Thread.currentThread().getName() + "再次获得了锁resourceA，重新进入运行状态");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
