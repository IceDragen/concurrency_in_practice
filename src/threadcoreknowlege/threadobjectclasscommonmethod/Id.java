package threadcoreknowlege.threadobjectclasscommonmethod;

/**
 * Description: 线程Id从1开始，然而当JVM运行起来之后，我们自己创建的线程的Id不会是2。
 * 因为JVM内部已经有多个线程了帮我们去做其他事了。
 */
public class Id {
    public static void main(String[] args) {
        Thread thread = new Thread();
        System.out.println(Thread.currentThread().getId());

        System.out.println(thread.getId());
    }
}
