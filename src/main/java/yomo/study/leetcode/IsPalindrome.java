package yomo.study.leetcode;


/**
 * <p>Title:IsPalindrome
 * <p>Description:回文链表
 * <p>Modified History:
 *
 * @author HH
 * @date 2019/9/5 9:43
 */
public class IsPalindrome {

    public boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        // 快慢指针找出中间节点 判断条件是快指针的前两个节点不为null
        ListNode fast = head.next.next;
        ListNode slow = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        //反转链表前半部分
        ListNode pre = null;
        ListNode next = null;
        while (head != slow) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        //    如果是奇数个节点去掉后半部分第一个节点
        if (fast != null) {
            slow = slow.next;
        }
        while (pre != null) {
            if (pre.val != slow.val) {
                return false;
            }
            pre = pre.next;
            slow = slow.next;
        }
        return true;

    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}