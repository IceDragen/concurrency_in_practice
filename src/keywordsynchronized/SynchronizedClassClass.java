package keywordsynchronized;

/**
 * 展示类锁的第二种形式：synchronized(*.class){代码逻辑}
 */
public class SynchronizedClassClass implements Runnable{
    @Override
    public void run() {
        try {
            test();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void test() throws InterruptedException {
        synchronized (Object.class){
            System.out.println(Thread.currentThread().getName() + "进入了同步代码块");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "完成了同步代码块内容");
        }
    }

    public static void main(String[] args) {
        SynchronizedClassClass instance1 = new SynchronizedClassClass();
        SynchronizedClassClass instance2 = new SynchronizedClassClass();
        Thread thread1 = new Thread(instance1);
        Thread thread2 = new Thread(instance2);

        thread1.start();
        thread2.start();
    }



}
