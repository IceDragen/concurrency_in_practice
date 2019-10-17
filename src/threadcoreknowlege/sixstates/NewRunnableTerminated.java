package threadcoreknowlege.sixstates;

/**
 * Desc: 展示线程New,Runnable,Terminated三种,即使是正在运行，也是Runnable状态，而不是Running。
 */
public class NewRunnableTerminated implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new NewRunnableTerminated());
        //将会打印出NEW状态
        System.out.println(thread.getState());

        thread.start();
        //将会打印出Runnable状态
        System.out.println(thread.getState());

        Thread.sleep(10);
        //将会打印出Runnable状态,即使是正在运行，也是Runnable状态，而不是Running。
        System.out.println(thread.getState());

        Thread.sleep(1000);
        //将会打印出Terminated状态
        System.out.println(thread.getState());
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }


}
