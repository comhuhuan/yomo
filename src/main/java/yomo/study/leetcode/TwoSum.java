package yomo.study.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>Title:TwoSum
 * <p>Description:两数之和
 * <p>Modified History:
 *
 * @author HH
 * @date 2019/7/31 18:21
 */
public class TwoSum {

    public static void main(String[] args) throws IllegalAccessException {
        int[] ints = {1, 2, 3, 5, 6, 87, 4, 5};

        int target = 8;
        int[] sum = twoSum(ints, target);
        System.out.println(Arrays.toString(sum));


        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(3);
        listNode.next = new ListNode(5);


        ListNode listNode2 = new ListNode(1);
        listNode2.next = new ListNode(3);
        listNode2.next = new ListNode(5);

        ListNode listNode1 = addTwoNumbers(listNode, listNode2);
        System.out.println(listNode1);
    }


    /**
     * 暴力破解
     * <p>
     * 复杂度分析：
     * <p>
     * 时间复杂度：O(n^2)O(n
     * 2
     * )， 对于每个元素，我们试图通过遍历数组的其余部分来寻找它所对应的目标元素，这将耗费 O(n)O(n) 的时间。因此时间复杂度为 O(n^2)O(n
     * 2
     * )。
     * <p>
     * 空间复杂度：O(1)O(1)。
     *
     * @param nums
     * @param target
     * @return
     * @throws IllegalAccessException
     */
    public static int[] twoSum(int[] nums, int target) throws IllegalAccessException {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (target == nums[i] + nums[j]) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalAccessException("没有参数");
    }


    /**
     * Definition for singly-linked list.
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    /**
     * https://leetcode-cn.com/problems/add-two-numbers/solution/liang-shu-xiang-jia-by-leetcode/
     * 两数相加  链表的遍历
     *
     * @param node1
     * @param node2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode node1, ListNode node2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = node1, q = node2, cur = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = p != null ? p.val : 0;
            int y = q != null ? q.val : 0;

            int sum = carry + x + y;
            carry = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            if (p != null) {
                p = p.next;
            }
            if (q != null) {
                q = q.next;
            }
            //额外进位
            if (carry > 0) {
                cur.next = new ListNode(carry);
            }

        }
        return dummyHead.next;
    }

    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。 暴力破解
     * 算法的本质是什么？ 思维的方式？
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (unique(s, i, j)) {
                    ans = Math.max(ans, j - i);
                }
            }
        }
        return ans;
    }

    private boolean unique(String s, int i, int j) {
        Set<Character> set = new HashSet<>();
        for (; i < j; i++) {
            char c = s.charAt(i);
            if (set.contains(c)) {
                return false;
            }
            set.add(c);
        }
        return true;
    }


}