package singleton;

/**
 * Description: 双重检查模式（面试考察首选）
 */
public class Singleton6 {
    private volatile static Singleton6 INSTANCE;

    private Singleton6(){}

    public Singleton6 getInstance(){
        if (INSTANCE == null){
            synchronized (Singleton6.class){
                if (INSTANCE == null){
                    INSTANCE = new Singleton6();
                }
            }
        }
        return INSTANCE;
    }
}
