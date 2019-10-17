package threadcoreknowlege.startthread;

/**
 *  比较启动线程的run方法和start方法，其实正确启动线程的方法就是用start方法，
 *  使用run方法打印出的线程名字是main，这显然不符合我们启动线程的本意
 */
public class StartAndRunMethod {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName());
        };

        runnable.run();
        new Thread(runnable).start();

    }
}
