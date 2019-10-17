package threadcoreknowlege.uncaughtexception;

/**
 * Description: 主线程中出现异常后，程序会停止运行并且控制台会打印输出异常信息，
 * 但是如果子线程发生异常，控制台中的信息很有可能被主线程中的输出信息覆盖过去导致无法发现。
 *
 */
public class ExceptionInChildThread implements Runnable{
    @Override
    public void run() {
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        new Thread(new ExceptionInChildThread()).start();
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }
}
