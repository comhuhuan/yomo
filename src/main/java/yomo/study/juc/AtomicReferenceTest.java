package yomo.study.juc;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * <p>Title:AtomicReferenceTest
 * <p>Description:
 * <p>Modified History:
 *
 * @author HH
 * @date 2019/8/13 21:52
 */
public class AtomicReferenceTest {

    public static void main(String[] args) {

        atomicReference();
    }

    public static void atomicReference() {

        AtomicStampedReference<Object> objectAtomicStampedReference = new AtomicStampedReference<>(null, 1);
        int stamp = objectAtomicStampedReference.getStamp();
        int[] ints = new int[1];
        Object o = objectAtomicStampedReference.get(ints);
        int oldStamp=ints[0];
        System.out.println(stamp);
        boolean b = objectAtomicStampedReference.compareAndSet(o, null, oldStamp, oldStamp + 1);
        System.out.println(b);
    }


}