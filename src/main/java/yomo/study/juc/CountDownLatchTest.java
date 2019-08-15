package yomo.study.juc;


import java.util.concurrent.CountDownLatch;

/**
 * <p>Title:CountDownLatchTest
 * <p>Description:
 * <p>Modified History:
 *
 * @author HH
 * @date 2019/8/14 16:59
 */
public class CountDownLatchTest {
    private static final int N = 10;

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(N);
        for (int i = 0; i < N; i++) {
            Thread thread = new Thread(new Workers(countDownLatch, "abc" + i), "abc" + i);
            thread.start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("大家开始吧");
        // for (int i = 0; i < 6; i++) {
        //     countDownLatch.countDown();
        // }

        System.out.println("游戏结束了");
    }


    private static class Workers implements Runnable {
        private final CountDownLatch countDownLatch;
        private final String name;

        public Workers(CountDownLatch countDownLatch,String name) {
            this.countDownLatch = countDownLatch;
            this.name = name;
        }

        @Override
        public void run() {

                countDownLatch.countDown();
                System.out.println(name+countDownLatch.getCount());


        }
    }
}