package yomo.study.desinepattern.others;

import yomo.study.netty.InSocksHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * <p>Title:ReflectionTest
 * <p>Description:反射
 * <p>Modified History:
 *
 * @author HH
 * @date 2018/7/30 17:29
 */
public class ReflectionTest {
    public static void main(String[] args) {
        Class<InSocksHandler> inSocksHandlerClass = InSocksHandler.class;
        inSocksHandlerClass.getName();
        inSocksHandlerClass.getSimpleName();
        int modifiers = inSocksHandlerClass.getModifiers();
        Modifier.isAbstract(modifiers);
        Package aPackage = inSocksHandlerClass.getPackage();
        inSocksHandlerClass.getConstructors();
        Annotation[] annotations = inSocksHandlerClass.getAnnotations();
        inSocksHandlerClass.isAnnotation();
        inSocksHandlerClass.isInterface();
        inSocksHandlerClass.isPrimitive();
        Method[] methods = InSocksHandler.class.getMethods();

    }
}