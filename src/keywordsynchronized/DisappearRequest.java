package keywordsynchronized;

/**
 * Desc: 描述一种不加并发控制手段，最后结果出现错误的情况
 * 理论上我们希望最终输出的i是200000，但是实际是几乎不可能出现这种情况的。
 */
public class DisappearRequest implements Runnable {

    private static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 100000; j++) {
            i++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new DisappearRequest());
        Thread thread2 = new Thread(new DisappearRequest());

        thread1.start();
        thread2.start();

        //为了让主线程在两个子线程全部结束之后再执行
        thread1.join();
        thread2.join();

        System.out.println(i);
    }
}
