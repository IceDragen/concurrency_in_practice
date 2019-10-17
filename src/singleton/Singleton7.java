package singleton;

/**
 * Description: 静态内部类，推荐用
 */
public class Singleton7 {

    private Singleton7(){}

    private static class Singleton7Instance{
        private final static Singleton7 INSTANCE = new Singleton7();
    }

    public Singleton7 getInstance(){
        return Singleton7Instance.INSTANCE;
    }
}
