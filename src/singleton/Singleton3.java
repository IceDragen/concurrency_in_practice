package singleton;

/**
 * Description: 懒汉式（线程不安全）[不可用]
 */
public class Singleton3 {
    private static Singleton3 INSTANCE;

    private Singleton3(){}

    public Singleton3 getInstance(){
        //这个判断会出现线程不安全的情况
        if (INSTANCE == null){
            INSTANCE = new Singleton3();
        }
        return INSTANCE;
    }
}
