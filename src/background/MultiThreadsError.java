package background;

/**
 * Description: 线程不安全的第一种情况：运行结果出错
 * 计数不准确
 */
public class MultiThreadsError implements Runnable{

    private int index;

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            index++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MultiThreadsError m  = new MultiThreadsError();
        Thread thread1 = new Thread(m);
        Thread thread2 = new Thread(m);
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(m.index);
    }
}
