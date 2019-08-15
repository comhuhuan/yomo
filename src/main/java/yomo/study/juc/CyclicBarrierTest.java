package yomo.study.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * <p>Title:CyclicBarrierTest
 * <p>Description:
 * <p>Modified History:
 *
 * @author HH
 * @date 2019/8/14 17:37
 */
public class CyclicBarrierTest {

    public static void main(String[] args) throws InterruptedException {

        int N = 5;  // 运动员数
        CyclicBarrier cb = new CyclicBarrier(N, new Runnable() {
            @Override
            public void run() {
                System.out.println("****** 所有运动员已准备完毕，发令枪：跑！******");
            }
        });

        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Thread t = new Thread(new PrepareWork(cb), "运动员[" + i + "]");
            list.add(t);
            t.start();
            // if (i == 3) {
            //     t.interrupt();  // 运动员[3]置中断标志位
            // }
        }

        Thread.sleep(2000);
        System.out.println("Barrier是否损坏：" + cb.isBroken());
    }


    private static class PrepareWork implements Runnable {
        private CyclicBarrier cb;

        PrepareWork(CyclicBarrier cb) {
            this.cb = cb;
        }

        @Override
        public void run() {
            try {
                int numberWaiting = cb.getNumberWaiting();
                int i = 0;
                //注意：使用CyclicBarrier时，对异常的处理一定要小心，比如线程在到达栅栏前就抛出异常，此时如果没有重试机制，其它已经到达栅栏的线程会一直等待（因为没有还没有满足总数），最终导致程序无法继续向下执行。
                if (numberWaiting == 3)
                   i= 1 / 0;


                System.out.println(Thread.currentThread().getName() + ": 准备完成");
                cb.await();

            } catch (
                    InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + ": 被中断");
            } catch (
                    BrokenBarrierException e) {
                System.out.println(Thread.currentThread().getName() + ": 抛出BrokenBarrierException");
            }
        }
    }
}