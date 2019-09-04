package yomo.study.leetcode;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

/**
 * <p>Title:ClibStaires
 * <p>Description:
 * <p>Modified History:
 * 爬楼梯问题解法：
 * 1.暴力破解 --需处理最后一次走两步但是只有一个台阶的情况  即i>n的情况,这种情况直接废弃
 * 2.优化暴力破解，可以添加缓存
 * 3.动态规划
 * 4.斐波那契 Fibonacci    [fěi bō nà qì] 数解法 f(n)=f(n-1)+f(n-2)
 *
 * @author HH
 * @date 2019/9/4 15:12
 */

public class ClibStaires {

    public static int climbStairs(int n) {
        return climb_Stairs(0, n);
    }

    // 递归
    public static int climb_Stairs(int i, int n) {

        if (i > n) {
            return 0;
        }
        // 最后刚刚好上第五个台阶的时候
        if (i == n) {
            return 1;
        }
        return climb_Stairs(i + 1, n) + climb_Stairs(i + 2, n);
    }

    public static int climbStairs2(int n) {
        int memo[] = new int[n + 1];
        return climb_Stairs(0, n, memo);
    }

    public static int climb_Stairs(int i, int n, int memo[]) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        if (memo[i] > 0) {
            return memo[i];
        }
        memo[i] = climb_Stairs(i + 1, n, memo) + climb_Stairs(i + 2, n, memo);
        return memo[i];
    }

    /**
     * 动态规划求解   --斐波那契数
     *
     * @param n
     * @return
     */
    public static int climbStairs3(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static int climbStairs4(int n) {
        if (n == 1) {
            return 1;
        }
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }


    public static void main(String[] args) {
        Stopwatch started = Stopwatch.createStarted();
        // 不加缓存要10s
        // System.out.println(climbStairs(45));
        System.out.println(started.elapsed(TimeUnit.SECONDS));
        // 加缓存是0s
        System.out.println(climbStairs2(45));
        System.out.println("时间" + started.elapsed(TimeUnit.SECONDS));
        System.out.println(climbStairs3(45));
        System.out.println(climbStairs4(45));
        System.out.println("时间" + started.elapsed(TimeUnit.SECONDS));


    }


}