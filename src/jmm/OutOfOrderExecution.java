package jmm;

import java.util.concurrent.CountDownLatch;

/**
 * Description: 演示重排序现象
 * 由于重排序不是每次都能发生，所以我们要用个小技巧，即让程序达到某个条件才停止，从而测试小概率事件
 */
public class OutOfOrderExecution {
    private static int a = 0, b = 0;
    private static int x = 0, y = 0;

    /*
        这个程序一共会有三种情况：
        1. x=0, y=1
        2. x=1, y=0
        3. x=1, y=1
    */
    public static void main(String[] args) throws InterruptedException {
        //以下这么设计都是为了测出x=1, y=1的情况，也就是开头说的小概率事件
        int i = 1;
        do {
            a = 0;
            b = 0;
            x = 0;
            y = 0;

            CountDownLatch latch = new CountDownLatch(3);

            Thread one = new Thread(() -> {
                try {
                    latch.countDown();
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a = 1;
                x = b;
            });

            Thread two = new Thread(() -> {
                try {
                    latch.countDown();
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                b = 1;
                y = a;
            });

            one.start();
            two.start();
            latch.countDown();
            one.join();
            two.join();

            String result = "第" + i++ + "次(x=" + x + ", y=" + y + ")";
            System.out.println(result);
        } while (x != 0 || y != 0);
    }

}
