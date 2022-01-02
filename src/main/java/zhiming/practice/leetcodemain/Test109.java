package zhiming.practice.leetcodemain;

public class Test109 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);
        int res = kthLargest(root, 1);
        System.out.println(res);
    }


    public static int kthLargest(TreeNode root, int k) {
        int[] kRes = new int[2];
        kRes[0] = k;
        kRes[1] = 0;
        dfs(root, kRes);
        return kRes[1];
    }
    public static void dfs(TreeNode root, int[] kRes) {
        if(root == null) {
            return;
        }
        dfs(root.right, kRes);
        if(kRes[0] == 0) {
            return;
        } else {
            kRes[1] = root.val;
            kRes[0]--;
        }
        dfs(root.left, kRes);
    }

    public int missingNumber(int[] nums) {
        int i = 0, j = nums.length;
        while(i <= j) {
            int mid = (i + j) / 2;
            if(nums[mid] == mid) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        return i;
    }



}
