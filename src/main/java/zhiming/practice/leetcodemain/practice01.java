package zhiming.practice.leetcodemain;

import java.util.*;

public class practice01 {
    public static void main(String[] args) {

    }

    /*public static int[] reversePrint(ListNode head) {     finger06
        Stack<ListNode> s = new Stack<ListNode>();
        while(head != null) {
            s.push(head);
            head = head.next;
        }
        int length = s.size();
        int[] nums = new int[length];
        for(int i = 0; i < length; i++) {
            nums[i] = s.pop().val;
        }
        return nums;
    }*/
    /*public static String replaceSpace(String s) {       //finger05
        char[] tempStr = new char[s.length() * 3];
        int j = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == ' ') {
                tempStr[j++] = '%';
                tempStr[j++] = '2';
                tempStr[j++] = '0';
            } else {
                tempStr[j++] = s.charAt(i);
            }
        }
        return new String(tempStr, 0, j);
    }*/
    /*public static int findRepeatNumber(int[] nums) {    //finger03
        int result = 0;
        Set<Integer> numsSet = new HashSet<Integer>();
        for(int i : nums) {
            if(numsSet.contains(i)) {
                result = i;
                break;
            }
            numsSet.add(i);
        }
        return result;
    }*/
    /*public static int lengthOfLongestSubstring(String s) {    //hot03
        if(s.length() <= 1) {
            return s.length();
        }
        int left = 0, right = 0;
        int result = 0;
        Set<Character> subStrSet = new HashSet<Character>();
        for(; left < s.length() - 1; left++) {
            if(left != 0) {
                subStrSet.remove(s.charAt(left - 1));
            }
            while(right < s.length() && !subStrSet.contains(s.charAt(right))) {
                subStrSet.add(s.charAt(right));
                if(right <= s.length() - 1) {
                    right++;
                }
            }
            result = Math.max(result, right - left);
        }
        return result;
    }*/
    /*public static int[] twoSum(int[] nums, int target) {      //hot01
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; ++i) {
            if(hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }*/
    /*int[] result = new int[2];
        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;*/
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}
/*class MinStack {        finger30
    private Stack<Integer> A, B;
    public MinStack() {
        A = new Stack<Integer>();
        B = new Stack<Integer>();
    }

    public void push(int x) {
        A.push(x);
        if(B.empty() || B.peek() >= x) {
            B.push(x);
        } else {
            B.push(B.peek());
        }
    }

    public void pop() {
        A.pop();
        B.pop();
    }

    public int top() {
        return A.peek();
    }

    public int min() {
        return B.peek();
    }
}*/

