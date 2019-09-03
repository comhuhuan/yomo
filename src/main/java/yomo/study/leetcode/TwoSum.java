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


    /**
     * 镜像二叉树
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    public boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val)
                && isMirror(t1.right, t2.left)
                && isMirror(t1.left, t2.right);
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
     *
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

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    /**
     * 把二叉搜索树转化为累加树
     *
     * @param root
     * @return
     */

    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            dfs(root, 0);
        }
        return root;
    }

    private int dfs(TreeNode node, int sum) {
        if (node == null) {
            return sum;
        }
        // 获取右边节点的和
        sum = dfs(node.right, sum);
        // 暂存当前节点
        int nodeValue = node.val;
        //更新当前节点的值
        node.val += sum;
        //暂存的值加上右边节点的值带到左边节点
        sum += nodeValue;

        sum = dfs(node.left, sum);

        return sum;
    }

    /**
     * 指针跟随法  把0元素移到最后
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                swap(nums, i, k++);
            }
        }
    }

    private void swap(int[] nums, int first, int second) {
        if (first == second) {
            return;
        }
        nums[second] = nums[first];
        nums[first] = 0;
    }


    /**
     * 将数组元素对应为索引的位置加n todo
     * 遍历加n后的数组，若数组元素值小于等于n，则说明数组下标值不存在，即消失的数字
     *
     * @param nums
     * @return
     */
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> objects = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int index = (nums[i] - 1) % nums.length;
            nums[index] += nums.length;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= nums.length)
                objects.add(i + 1);
        }
        System.out.println(Arrays.toString(objects.toArray()));
        return objects;

    }

    int pathnumber;

    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        Sum(root, sum);
        pathSum(root.left, sum);
        pathSum(root.right, sum);
        return pathnumber;
    }


    public void Sum(TreeNode root, int sum) {
        if (root == null) return;
        sum -= root.val;
        if (sum == 0) {
            pathnumber++;
        }
        Sum(root.left, sum);
        Sum(root.right, sum);
    }

    public static int maxProfit(int[] prices) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice) {
                minprice = prices[i];
            } else if (prices[i] - minprice > maxprofit) {
                maxprofit = prices[i] - minprice;
            }
        }
        return maxprofit;
    }


    /**
     * 这道题的思想很简单：“以空间换时间”，使用辅助栈是常见的做法。
     * 思路 1：数据栈和辅助栈在任何时候都同步不需要额外的条件判断;(可能导致内存占用过多)
     */
    class MinStack {
        // 数据栈
        private Stack<Integer> data;
        // 辅助栈
        private Stack<Integer> helper;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            data = new Stack<>();
            helper = new Stack<>();
        }

        public void push(int x) {
            // 数据栈和辅助栈一定会增加元素
            data.add(x);
            // 和栈顶的元素比较,如果比栈顶元素小,直接放 ,如果大的话,就重新再入一遍栈顶元素
            if (helper.isEmpty() || helper.peek() >= x) {
                helper.add(x);
            } else {
                helper.add(helper.peek());
            }
        }

        public void pop() {
            // 两个栈都得 pop
            if (!data.isEmpty()) {
                helper.pop();
                data.pop();
            }
        }

        public int top() {
            if (!data.isEmpty()) {
                return data.peek();
            }
            throw new RuntimeException("栈中元素为空，此操作非法");
        }

        public int getMin() {
            if (!helper.isEmpty()) {
                return helper.peek();
            }
            throw new RuntimeException("栈中元素为空，此操作非法");
        }

    }


    /**
     * 思路二 : 辅助栈和数据栈不同步
     */
    public class MinStack2 {

        // 数据栈
        private Stack<Integer> data;
        // 辅助栈
        private Stack<Integer> helper;

        /**
         * initialize your data structure here.
         */
        public MinStack2() {
            data = new Stack<>();
            helper = new Stack<>();
        }

        // 思路 2：辅助栈和数据栈不同步
        // 关键 1：辅助栈的元素空的时候，必须放入新进来的数
        // 关键 2：新来的数小于或者等于辅助栈栈顶元素的时候，才放入（特别注意这里等于要考虑进去）
        // 关键 3：出栈的时候，辅助栈的栈顶元素等于数据栈的栈顶元素，才出栈，即"出栈保持同步"就可以了

        public void push(int x) {
            // 辅助栈在必要的时候才增加
            data.add(x);
            // 关键 1 和 关键 2
            if (helper.isEmpty() || helper.peek() >= x) {
                helper.add(x);
            }
        }

        public void pop() {
            // 关键 3：data 一定得 pop()
            if (!data.isEmpty()) {
                // 注意：声明成 int 类型，这里完成了自动拆箱，从 Integer 转成了 int，因此下面的比较可以使用 "==" 运算符
                // 参考资料：https://www.cnblogs.com/GuoYaxiang/p/6931264.html
                // 如果把 top 变量声明成 Integer 类型，下面的比较就得使用 equals 方法
                int top = data.pop();
                if (top == helper.peek()) {
                    helper.pop();
                }
            }
        }

        public int top() {
            if (!data.isEmpty()) {
                return data.peek();
            }
            throw new RuntimeException("栈中元素为空，此操作非法");
        }

        public int getMin() {
            if (!helper.isEmpty()) {
                return helper.peek();
            }
            throw new RuntimeException("栈中元素为空，此操作非法");
        }

    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    // public int maxSubArray(int[] nums) {
    //     int k = 0;
    //     int sum = 0;
    //     for (int i = 0; i < nums.length; i++) {
    //         if (nums[k] > nums[i]) {
    //
    //             k++;
    //         }
    //
    //     }
    // }


    private int max = 0;

    /**
     * 二叉树的直径
     * 前序递归，记录左右节点的深度，
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return max;
    }

    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depth = depth(root.left);
        int depth1 = depth(root.right);
        //max记录当前的最大直径
        max = Math.max(depth + depth1, max);
        return Math.max(depth, depth1) + 1;
    }

    public static void main(String[] args) throws IllegalAccessException {
        int[] ints = {3, 2, 2, 5, 6, 2, 4, 5};
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

        System.out.println(maxProfit(ints));
        findDisappearedNumbers(ints);


    }
}