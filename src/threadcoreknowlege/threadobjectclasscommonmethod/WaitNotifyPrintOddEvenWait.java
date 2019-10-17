package threadcoreknowlege.threadobjectclasscommonmethod;

import com.sun.xml.internal.bind.v2.model.annotation.RuntimeAnnotationReader;

/**
 * Description: 使用wait和notify方法分别打印0～100中的奇偶数
 */
public class WaitNotifyPrintOddEvenWait {

    private static final Object lock = new Object();
    private static int count = 1;

    /**
     * 整体思路：
     *  1.有一个线程拿到锁就打印数字，不用再去判断奇偶数
     *  2.打印完就休眠自己，唤醒另一个线程
     * @param args
     */
    public static void main(String[] args) {

        new Thread(new TurningRunnable()).start();
        new Thread(new TurningRunnable()).start();
    }

    static class TurningRunnable implements Runnable{

        @Override
        public void run() {
            synchronized (lock){
                while (count <= 100) {
                    System.out.println(Thread.currentThread() + ": " + count++);
                    lock.notify();
                    if (count <= 100){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }
    }
}
