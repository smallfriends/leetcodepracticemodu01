package zhiming.leetcode;

import java.util.*;

public class lc0302 {
    public static int cuttingRope(int n) {
        //贪心算法Java解法：（应该是对的把。）
        if(n <= 3) return n - 1;
        long res=1L;
        int p=(int)1e9+7;
        //贪心算法，优先切三，其次切二
        while(n>4){
            res=res*3%p;
            n-=3;
        }
        //出来循环只有三种情况，分别是n=2、3、4
        return (int)(res*n%p);
    }
    //两数之和,哈希表
    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if(hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target-nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }
    //两数想加,处理进位符
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head= new ListNode(0);
        ListNode cur = head;
        int flag = 0;
        while (l1 != null && l2 != null) {
            ListNode currentNode = new ListNode((l1.val + l2.val + flag) % 10);
            flag = (l1.val + l2.val + flag) / 10;
            cur.next = currentNode;
            l1 = l1.next;
            l2 = l2.next;
            cur = cur.next;
        }
        while(l1 != null) {
            ListNode currentNode = new ListNode((l1.val + flag) % 10);
            flag = (l1.val + flag) / 10;
            cur.next = currentNode;
            l1 = l1.next;
            cur = cur.next;
        }
        while(l2 != null) {
            ListNode currentNode = new ListNode((l2.val + flag) % 10);
            flag = (l2.val + flag) / 10;
            cur.next = currentNode;
            l2 = l2.next;
            cur = cur.next;
        }
        if(flag == 1) {
            ListNode currentNode = new ListNode(1);
            cur.next = currentNode;
            cur = cur.next;
        }
        return head.next;
    }
    //无重复字符的最长子串
    public static int lengthOfLongestSubstring(String s) {
        if(s.length() <= 0) {
            return 0;
        }
        //使用Set集合保存当前不重复子串的字符
        Set<Character> numSet = new HashSet<Character>();
        //使用双端队列保存当前子串
        Deque<Character> numdeque = new ArrayDeque<>();
        numSet.add(s.charAt(0));
        numdeque.addLast(s.charAt(0));
        int maxSub = numSet.size();
        for (int i = 1; i < s.length(); i++) {
            boolean flag = numSet.add(s.charAt(i));
            if(flag) {
                maxSub = Math.max(maxSub, numSet.size());
                numdeque.addLast(s.charAt(i));
            } else {
                Character firstNum = numdeque.removeFirst();
                while( firstNum != s.charAt(i)) {
                    numSet.remove(firstNum);
                    firstNum = numdeque.removeFirst();

                }

                numSet.add(s.charAt(i));
                numdeque.addLast(s.charAt(i));
                maxSub = Math.max(maxSub, numSet.size());
            }
        }
        return maxSub;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("aabaab!bb"));
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}
