package yomo.study.leetcode;

import java.util.Arrays;

/**
 * <p>Title:FindUnsortedSubarray
 * <p>Description:连续子数组
 * <p>Modified History:
 *
 * @author HH
 * @date 2019/9/6 9:33
 */
public class FindUnsortedSubarray {

    public int findUnsortedSubarray(int[] nums) {
        int[] snums = nums.clone();
        Arrays.sort(snums);
        int start = snums.length, end = 0;
        for (int i = 0; i < snums.length; i++) {
            if (snums[i] != nums[i]) {
                start = Math.min(start, i);
                end = Math.max(end, i);
            }
        }
        return (end - start >= 0 ? end - start + 1 : 0);

    }
}