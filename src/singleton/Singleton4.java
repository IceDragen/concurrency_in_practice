package singleton;

/**
 * Description: 懒汉式（线程安全）[不推荐用]
 */
public class Singleton4 {
    private static Singleton4 INSTANCE;

    private Singleton4(){}

    public synchronized Singleton4 getInstance(){
        if (INSTANCE == null){
            INSTANCE = new Singleton4();
        }
        return INSTANCE;
    }
}
