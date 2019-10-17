package threadcoreknowlege.uncaughtexception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Description: 自己定义的异常处理器
 */
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.WARNING,"线程异常，终止啦!" + t.getName(), e);
    }
}
