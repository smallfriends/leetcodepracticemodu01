
package zhiming.practice.leetcodemain;

import java.util.Comparator;
import java.util.PriorityQueue;

public class practice1024 {
    public int[] getLeastNumbers(int[] arr, int k) {        //最小的k个数
        int[] vec = new int[k];
        if(k == 0) {
            return vec;
        }
        PriorityQueue<Integer> queuek = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for(int i = 0; i < k; i++) {
            queuek.add(arr[i]);
        }
        for(int i = k; i < arr.length; i++) {
            if(arr[i] < queuek.peek()) {
                queuek.poll();
                queuek.add(arr[i]);
            }
        }
        for(int i = 0; i < k; i++) {
            vec[i] = queuek.peek();
            queuek.poll();
        }
        return vec;
    }
    public int maxSubArray(int[] nums) {    //连续子数组的最大和
        int maxSum = nums[0];
        int[] maxNums = new int[nums.length];
        maxNums[0] = nums[0];
        for(int i = 1; i < nums.length; i++) {
            maxNums[i] = Math.max(maxNums[i - 1] + nums[i], nums[i]);
            if(maxSum < maxNums[i]) {
                maxSum = maxNums[i];
            }
        }
        return maxSum;
    }
    public int[] twoSum(int[] nums, int target) {       //和为s的两个数字
        int i = 0, j = nums.length - 1;
        int[] res = new int[2];
        while(i < j) {
            if(nums[i] + nums[j] < target) {
                i++;
            } else if(nums[i] + nums[j] > target) {
                j--;
            } else {
                res[0] = nums[i];
                res[1] = nums[j];
                return res;
            }
        }
        return res;
    }

    public boolean exist(char[][] board, String word) {     //剑指 Offer 12. 矩阵中的路径
        char[] words = word.toCharArray();
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(dfs(board, words, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean dfs(char[][] board, char[] words, int i, int j, int k) {
        if(i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != words[k]) {
            return false;
        }
        if(k == words.length - 1) {
            return true;
        }
        board[i][j] = '\0';
        boolean res = dfs(board, words, i + 1, j, k + 1) || dfs(board, words, i - 1, j, k + 1) ||
                dfs(board, words, i, j + 1, k + 1) || dfs(board, words, i, j - 1, k + 1);
        board[i][j] = words[k];
        return res;
    }
}
