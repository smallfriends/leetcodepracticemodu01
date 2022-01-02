package zhiming.practice.leetcodemain;

import static java.lang.Math.pow;

public class Test918 {
    public static void main(String[] args) {
        //System.out.println(myPow(2,10));
        //System.out.println(pow(10, 3));
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        ListNode newHead = reverseList(head);
        while(newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
    }
    public static ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode L1 = head;
        ListNode L2 = head.next;
        L1.next = null;
        ListNode L3 = null;
        while(L2 != null) {
            L3 = L2.next;
            L2.next = L1;
            L1 = L2;
            L2 = L3;
        }
        return L1;
    }
    public static double myPow(double x, int n) {
        if(x == 0) {
            return 0;
        }
        double result = 1;
        if(n < 0) {
            x = 1 / x;
            n = -n;
        }
        while(n > 0) {
            if((n & 1) == 1) {
                result *= x;
            }
            x *= x;
            n = n >> 1;
        }
        return result;
    }

}
