package yomo.study.leetcode;

import yomo.study.datastructure.list.Node;

import java.util.*;

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
        lengthOfLonges3tSubstring("sadsfdsfadsfasdfsadfklkjijlkjskadfljlkasjdfhdgadsjfkdsaf");
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
     *
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


    public boolean Find(int target, int[][] array) {
        int length = array.length - 1;
        int i = 0;
        while (length >= 0 && i < array[0].length) {
            if (array[length][i] > target) {
                length--;
            } else if (array[length][i] < target) {
                i++;
            } else {
                return true;
            }
        }
        return false;

    }


    /**
     * 滑动窗口 set 解最长无重复字符串   时间复杂度为 O(2n) 空间复杂度为O(min(m,n))
     *
     * @param s
     * @return
     */
    public int lengthOfLonges2tSubstring(String s) {
        int i = 0, j = 0, n = s.length(), ans = 0;

        Set<Object> set = new HashSet<>();
        while (i < n && j < n) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - 1);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    /**
     * 滑动窗口map实现
     *
     * @param s
     * @return
     */
    public static int lengthOfLonges3tSubstring(String s) {
        int i = 0, j = 0, n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    public Node mergeTrees(Node t1, Node t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        Node node = new Node(t1.data + t2.data);
        node.next = mergeTrees(t1.next, t2.next);
        node.Pre = mergeTrees(t1.Pre, t2.Pre);

        return node;

    }


    public int hammingDistance(int x, int y) {
        //bitCount 数出整数二进制下 1 的个数
        //1^0 = 1 ,0^1 =1 ,0^0 = 0 ,1^1 = 0
        return Integer.bitCount(x ^ y);
    }


    /**
     * 树的最大深度
     * @param root
     * @return
     */
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(maxDepth(root.next), maxDepth(root.Pre)) + 1;
        }


    }


}