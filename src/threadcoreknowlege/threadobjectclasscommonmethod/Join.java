package threadcoreknowlege.threadobjectclasscommonmethod;

import java.util.concurrent.TimeUnit;

/**
 * Description: 演示join的普通用法，注意输出语句顺序会变化
 */
public class Join {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName());
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName());
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName());
                }
            }
        });


        thread1.start();
        thread2.start();
        System.out.println("启动子线程");

        thread1.join();
        thread2.join();
        System.out.println("主线程执行完毕");

    }
}
