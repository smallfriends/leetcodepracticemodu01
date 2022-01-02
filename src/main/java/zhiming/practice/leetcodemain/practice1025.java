package zhiming.practice.leetcodemain;

import java.util.HashMap;
import java.util.Map;

public class practice1025 {

    public boolean isNumber(String s) {

        return true;
    }

    public int nthUglyNumber(int n) {       //第n个丑数
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        int p2 = 1, p3 = 1, p5 = 1;
        for(int i = 2; i < n + 1; i++) {
            dp[i] = Math.min(Math.min(dp[p2] * 2, dp[p3] * 3), dp[p5] * 5);
            if(dp[i] == dp[p2] * 2) {
                p2++;
            }
            if(dp[i] == dp[p3] * 3) {
                p3++;
            }
            if(dp[i] == dp[p5] * 5) {
                p5++;
            }
        }
        return dp[n];
    }

    public boolean verifyPostorder(int[] postorder) {       //判断是否为二叉搜索数的后序遍历序列
        return recurVerify(postorder, 0, postorder.length - 1);
    }
    private boolean recurVerify(int[] postorder, int i, int j) {
        if(i >= j) {
            return true;
        }
        int m = i;
        while(postorder[m] < postorder[j]) {
            m++;
        }
        int p = m;
        while(postorder[m] > postorder[j]) {
            m++;
        }
        return (m == j) && recurVerify(postorder, i, p - 1) && recurVerify(postorder, p, j - 1);
    }

    public int maxArea(int[] height) {      //盛水最多的容器
        int left = 0, right = height.length - 1;
        int ans = 0;
        while(left < right) {
            int tmp = Math.min(height[left], height[right]) * (right - left);
            ans = Math.max(tmp, ans);
            if(height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return ans;
    }

    public int maxValue(int[][] grid) {     //47. 礼物的最大价值
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for(int i = 1; i < grid.length; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for(int j = 1; j < grid[0].length; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for(int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]) + grid[i][j];
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abba"));
    }
    public static int lengthOfLongestSubstring(String s) {     //48. 最长不含重复字符的子字符串
        Map<Character, Integer> hashSet = new HashMap<>();
        int res = 0, curTmp = 0;
        for(int i = 0; i < s.length(); i++) {
            if(hashSet.getOrDefault(s.charAt(i), -1) == -1) {
                hashSet.put(s.charAt(i), i);
                curTmp += 1;
            } else {
                res = Math.max(res, curTmp);
                if(curTmp < i - hashSet.get(s.charAt(i))) {     //这个ifelse有点不清楚
                    curTmp = curTmp + 1;
                } else {
                    curTmp = i - hashSet.get(s.charAt(i));
                }
                hashSet.replace(s.charAt(i), i);
            }
        }
        res = Math.max(res, curTmp);
        return res;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {      //滑动窗口的最大值
        if(nums.length == 0) {
            return new int[0];
        }
        int resLength = nums.length - k + 1;
        int[] res = new int[resLength];
        res[0] = nums[0];
        for(int i = 0; i < k; i++) {
            if(nums[i] > res[0]) {
                res[0] = nums[i];
            }
        }
        int curMax = res[0];
        for(int i = k; i < nums.length; i++) {
            if(nums[i - k] == curMax) {
                curMax = nums[i - k + 1];
                for(int j = i - k + 1; j <= i; j++) {
                    if(nums[j] > curMax) {
                        curMax = nums[j];
                    }
                }
            }
            if(nums[i] > curMax) {
                curMax = nums[i];
            }
            res[i - k + 1] = curMax;
        }
        return res;
    }

}







