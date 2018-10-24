package yomo.study.desinepattern.Observer;

/**
 * <p>Title:Test
 * <p>Description:
 * <p>Modified History:
 *
 * @author HH
 * @date 2018/7/30 21:47
 */
public class Test {
    /**
     * 在抽象类里有一个 ArrayList 存放观察者们。
     *
     * @param args
     */
    public static void main(String[] args) {
        SubjectDemo subjectDemo = new SubjectDemo();
        subjectDemo.attach(new Test1Observer());
        subjectDemo.attach(new Test2Observer());
        subjectDemo.setMsg();
    }
}