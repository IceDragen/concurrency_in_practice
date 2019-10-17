package jmm;

import java.util.concurrent.TimeUnit;

/**
 * Description: 演示可见性带来的问题
 */
public class FieldVisibility {
    private int a = 1;
    private int b = 2;

    public static void main(String[] args) {
        /*
            一般打印结果：
               1. a=1,b=2(先执行读线程)
               2. a=3;b=3(先执行写线程)
               3. a=3;b=2(两线程交替执行的结果)
            因为可见性带来的结果：
               1. a=1;b=3
         */
        while (true){
            FieldVisibility test = new FieldVisibility();
            Thread thread1 = new Thread(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test.change();
            });

            Thread thread2 = new Thread(test::print);

            thread1.start();
            thread2.start();
        }
    }

    private void print() {
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("a=" + a +";b=" + b);
    }

    private void change() {
        a = 3;
        b = a;
    }
}
