package threadcoreknowlege.threadobjectclasscommonmethod;

/**
 * Description: 用synchronized关键字实现两个线程交替打印0～100中的奇偶数
 */
public class WaitNotifyPrintOddEvenSyn {

    private static final Object lock = new Object();
    private static int count = 1;


    //为什么最后打印出了101
    public static void main(String[] args) {
        new Thread(() -> {
            while (count <= 100){
                synchronized (lock){
                    if ((count & 1) == 0){
                        System.out.println(Thread.currentThread().getName() + ": " + count++);
                    }
                }
            }
        }).start();

        new Thread(() -> {
            while (count <= 100){
                synchronized (lock){
                    if ((count & 1) == 1){
                        System.out.println(Thread.currentThread().getName() + ": " + count++);
                    }
                }
            }
        }).start();

//        new Thread(() -> {
//            synchronized (lock){
//                while (count <= 100){
//                    if ((count & 1) == 0){
//                        System.out.println(Thread.currentThread().getName() + ": " + count++);
//                    }
//                }
//            }
//        }).start();
//
//        new Thread(() -> {
//            synchronized (lock){
//                while (count <= 100){
//                    if ((count & 1) == 1){
//                        System.out.println(Thread.currentThread().getName() + ": " + count++);
//                    }
//                }
//            }
//        }).start();

    }
}
