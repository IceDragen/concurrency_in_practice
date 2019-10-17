package background;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 演示方法返回一个private对象导致逸出
 */
public class MultiThreadsError03 {
    //定义成private的目的是不希望这个类被外部使用
    private Map<String, String> config;

    public MultiThreadsError03() {
        config = new HashMap<>();
        config.put("1", "北京");
        config.put("2", "上海");
    }

    //不小心返回了config
    public Map<String, String> getConfig() {
        return config;
    }

    public static void main(String[] args) {
        MultiThreadsError03 m = new MultiThreadsError03();
        Map<String, String> r = m.getConfig();
        System.out.println(r.get("1"));


    }
}
