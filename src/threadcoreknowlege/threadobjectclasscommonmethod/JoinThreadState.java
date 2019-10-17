package threadcoreknowlege.threadobjectclasscommonmethod;

import java.util.concurrent.TimeUnit;

/**
 * Description: 主要证明当某个线程在等待join方法时其处于Waiting状态同时介绍获取线程状态的两种方法
 *  1. 通过thread.getState()方法获取
 *  2. 通过控制台Debugger查看
 */
public class JoinThreadState {

    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(() -> {
            try {
                System.out.println("子线程开始运行");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("主线程状态：" + mainThread.getState());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        System.out.println("等待子线程执行");
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("子线程运行完毕");
    }
}
