package yomo.study.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>Title:AtomicInteger
 * <p>Description:
 * <p>Modified History:
 *
 * @author HH
 * @date 2019/8/13 17:01
 */
public class AtomicIntegerTest {

    public static void main(String[] args) throws InterruptedException {
         AtomicInteger ai = new AtomicInteger();
        int integer = 0;

        List<Thread> list = new ArrayList<>();
        List<Thread> list2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Accumlator(ai), "thread-" + i);
            list.add(t);
            t.start();
        }
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new AccumlatorInter(integer), "thread-" + i);
            list2.add(t);
            t.start();
        }

        for (Thread t : list) {
            t.join();
        }
        for (Thread t : list2) {
            t.join();
        }
        System.out.println(ai.get());
        System.out.println(integer);
        Thread thread1 = new Thread(new AccumlatorInter(integer));
        Thread thread2 = new Thread(new AccumlatorInter(integer));
        thread1.join();
        thread2.join();




    }

    static class Accumlator implements Runnable {
        private AtomicInteger ai;

        Accumlator(AtomicInteger ai) {
            this.ai = ai;
        }

        @Override
        public void run() {
            for (int i = 0, len = 1000; i < len; i++) {
                ai.incrementAndGet();
            }
        }
    }


    static class AccumlatorInter implements Runnable {


        private  Integer ai;

        AccumlatorInter(Integer ai) {
            this.ai = ai;
        }

        @Override
        public void run() {
            for (int i = 0, len = 1000; i < len; i++) {
                ai++;
            }
        }
    }
}