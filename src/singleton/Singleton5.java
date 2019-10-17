package singleton;

/**
 * Description: 懒汉式（线程不安全）[不推荐用]
 */
public class Singleton5 {
    private static Singleton5 INSTANCE;

    private Singleton5(){}

    public synchronized Singleton5 getInstance(){
        //这个if判断还是会出现线程安全问题，两个线程可能同时进入了if中
        if (INSTANCE == null){
            synchronized (Singleton5.class) {
                INSTANCE = new Singleton5();
            }
        }
        return INSTANCE;
    }
}
