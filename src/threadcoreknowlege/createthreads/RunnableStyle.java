package threadcoreknowlege.createthreads;

/**
 * 使用Runnable方式实现线程
 */
public class RunnableStyle implements Runnable {

    public static void main(String[] args) {
        RunnableStyle runnableStyle = new RunnableStyle();
        new Thread(runnableStyle).start();
    }

    @Override
    public void run() {
        System.out.println("用Runnable方式创建线程");
    }
}
