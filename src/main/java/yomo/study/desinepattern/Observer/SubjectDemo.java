package yomo.study.desinepattern.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * <p>Title:SubjectDemo
 * <p>Description:
 * <p>Modified History:
 *
 * @author HH
 * @date 2018/7/30 21:40
 */
public class SubjectDemo {
    private List<ObserverDemo> observers = new ArrayList<>();


    public void setMsg() {
        noticeAllObservers();
    }

    public void attach(ObserverDemo observerDemo) {
        observers.add(observerDemo);
    }

    private void noticeAllObservers() {
        for (ObserverDemo observer : observers) {
            observer.update();
        }
    }

}