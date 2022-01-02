package zhiming.practice.leetcodemain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Practice108 {
    public static void main(String[] args) {

    }

    public static int search(int[] nums, int target) {
        int index = searchRecursion(nums, target);
        if(index == -1) {
            return 0;
        }
        int result = 1;
        int pre = index - 1, next = index + 1;
        while(pre >= 0 && nums[pre] == target) {
            pre--;
            result++;
        }
        while(next < nums.length && nums[next]== target) {
            next++;
            result++;
        }
        return result;
    }

    /**
     * 二分查找非递归式
     * */
    public int binarySearch(int[] nums, int target) {
        if(nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(nums[mid] == target) {
                return mid;
            } else if(target < nums[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 二分查找，递归式
     * */
    public static int searchRecursion(int[] nums, int target) {
        if(nums.length != 0) {
            return searchRecursion(nums, target, 0, nums.length - 1);
        }
        return -1;
    }
    public static int searchRecursion(int[] nums, int target, int start, int end) {
        if(start > end) {
            return -1;
        }
        int mid = start + (end - start) / 2;
        if(nums[mid] == target) {
            return mid;
        } else if(target < nums[mid]) {
            return searchRecursion(nums, target, start, mid - 1);
        } else {
            return searchRecursion(nums, target, start + 1, end);
        }
    }

    /**
     * 字符串中找到第一个只出现一次的字符
     * */
    public char firstUniqChar(String s) {
        Map<Character, Integer> frequency = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
        }
        for(int i = 0; i < s.length(); i++) {
            if(frequency.get(s.charAt(i)) == 1) {
                return s.charAt(i);
            }
        }
        return ' ';
    }

    /**
     * 寻找两个链表的第一个公共节点
     * */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> visited = new HashSet<>();
        while(headA != null) {
            visited.add(headA);
            headA = headA.next;
        }
        while(!visited.contains(headB) && headB != null) {
            headB = headB.next;
        }
        return headB;
    }
}
