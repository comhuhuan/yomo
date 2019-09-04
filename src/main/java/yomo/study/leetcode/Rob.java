package yomo.study.leetcode;

import lombok.extern.log4j.Log4j;

/**
 * <p>Title:Rob
 * <p>Description:
 * <p>Modified History:
 * [2,7,9,3,1,8]
 * 前k个元素的最大值为前面两个最大和当前数的值  或者前一个的最大
 * f(k)=max(f(k-2)+Ak,f(k-1))
 * 选择初始条件
 *
 * @author HH
 * @date 2019/9/4 17:28
 */

public class Rob {
    public static int rob(int[] nums) {
        int prevMax = 0;
        int currMax = 0;
        for (int num : nums) {
            int temp = currMax;
            currMax =Math.max(prevMax + num, currMax);
            prevMax = temp;
        }
        return currMax;
    }

    public static void main(String[] args) {
        int[] ints = {2,7,9,3,1,8};
        System.out.println( rob(ints));
    }
}