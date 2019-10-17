package threadcoreknowlege.threadobjectclasscommonmethod;

import java.util.concurrent.TimeUnit;

/**
 * Description: 了解了join方法底层原理之后，尝试用wait方法写出其等价用法
 */
public class JoinPrinciple {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("子线程开始运行");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println("子线程运行完毕");
            }
        });
        thread.start();
        System.out.println("等待子线程执行");
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //这个synchronized代码块的作用和join方法是一样的
        synchronized (thread){
            thread.wait();
        }
        System.out.println("主线程运行完毕");

    }

}
