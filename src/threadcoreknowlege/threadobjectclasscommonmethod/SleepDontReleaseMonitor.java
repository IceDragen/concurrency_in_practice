package threadcoreknowlege.threadobjectclasscommonmethod;

/**
 * Description: 展示sleep方法不会释放monitor锁，要等sleep时间到了线程正常执行结束才会释放锁
 */
public class SleepDontReleaseMonitor implements Runnable{
    @Override
    public void run() {
        syn();
    }

    private synchronized void syn() {
        System.out.println(Thread.currentThread().getName() + "拿到了monitor锁");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "退出了程序");
    }

    public static void main(String[] args) {
        Runnable runnable = new SleepDontReleaseMonitor();
        new Thread(runnable, "线程1").start();
        new Thread(runnable, "线程2").start();
    }
}
