package yomo.study.leetcode;

/**
 * <p>Title:Sqrt2
 * <p>Description:求根号二的值 二分查找法的灵活运用
 * <p>Modified History:
 *
 * @author HH
 * @date 2019/8/2 11:30
 */
public class Sqrt2 {
    public static double EPSILON = 0.0000000001;

    double squr2() {
        double low = 1.4;
        double high = 1.5;
        double mid = (high + low) / 2;

        while (high - low > EPSILON) {
            if (mid * mid > 2) {
                high = mid;
            }
            mid = (high + low) / 2;
        }
        return mid;
    }
}