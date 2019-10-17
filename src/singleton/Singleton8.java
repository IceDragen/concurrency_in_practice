package singleton;

/**
 * Description: 枚举方法实现单例
 */
public enum Singleton8 {
    INSTANCE;

    public void whatever(){

    }
}

/**
 * 演示如何使用枚举单例
 */
class UseEnumSingleton{
    public static void main(String[] args) {
        Singleton8.INSTANCE.whatever();
    }
}
