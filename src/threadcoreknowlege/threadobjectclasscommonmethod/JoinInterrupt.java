package threadcoreknowlege.threadobjectclasscommonmethod;

import java.util.concurrent.TimeUnit;

/**
 * Description: 演示join被中断的效果
 */
public class JoinInterrupt {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "开始执行");
        Thread mainThread = Thread.currentThread();
        Thread thread1 = new Thread(() -> {
            try {
                mainThread.interrupt();
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "执行完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();

        try {
            thread1.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "被中断了");
//            收到中断信息后在这里执行响应中断的逻辑
//            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "执行完毕");
    }
}
