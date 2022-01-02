package zhiming.practice.leetcodemain;

import java.util.Stack;

public class Test917 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        int[] res = reversePrint(head);
        System.out.println(res.toString());
    }
    public static int[] reversePrint(ListNode head) {
        if(head == null) {
            return null;
        }
        Stack<Integer> stack = new Stack<Integer>();
        ListNode tmp = head;
        while(tmp != null) {
            stack.push(tmp.val);
            tmp = tmp.next;
        }
        int length = stack.size();
        int[] result = new int[length];
        for(int i = 0; i < length; i++) {
            result[i] = stack.pop();
        }
        return result;
    }
}
