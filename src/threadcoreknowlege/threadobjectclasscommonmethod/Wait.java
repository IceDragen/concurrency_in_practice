package threadcoreknowlege.threadobjectclasscommonmethod;

/**
 * Desc: 展示wait和notify的基本用法
 * 我们的目标：
 *  1.研究代码的执行顺序
 *  2.证明wait方法会释放锁
 */
public class Wait {
    static Object object = new Object();
    static final Object lock = new Object();

    static class Thread1 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                System.out.println(Thread.currentThread().getName() + "开始执行");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "重新获取到了锁");
            }
        }
    }

    static class Thread2 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                lock.notify();
                System.out.println(Thread.currentThread().getName() + "调用了notify()");
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();

        thread1.start();

        //确保thread1已经进入waiting状态
        Thread.sleep(200);
        thread2.start();
    }
}
