package yomo.study.leetcode;

import java.util.HashSet;

/**
 * <p>Title:HasCycle
 * <p>Description: 链表是否有环问题
 * <p>Modified History:
 *
 * @author HH
 * @date 2019/9/4 16:35
 */
public class HasCycle {
    public boolean hasCycle(ListNode head) {
        HashSet set = new HashSet();
        while (head != null) {
            if (set.contains(head)) {
                return true;
            }
            set.add(head);
            head = head.next;
        }
        return false;
    }

    /**
     * 双指针方法
     * @param head
     * @return
     */
    public boolean hasCycle2(ListNode head) {
        if (head == null && head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}