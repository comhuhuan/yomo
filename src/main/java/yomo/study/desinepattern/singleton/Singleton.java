package yomo.study.desinepattern.singleton;

/**
 * <p>Title:SingletonModel
 * <p>Description:单例模式
 * <p>Modified History:
 *
 * @author HH
 * @date 2019/8/2 12:16
 */
public class Singleton {


    /**
     * 单例模式
     * 1.恶汉模式
     * 2.懒汉模式
     *  a.线程安全
     *  b.线程不安全
     * 3.双重校验
     * 4.静态内部类
     * 5.枚举
     */
    private static Singleton instance = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return instance;
    }





}