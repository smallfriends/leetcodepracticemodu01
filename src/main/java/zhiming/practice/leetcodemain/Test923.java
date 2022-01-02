package zhiming.practice.leetcodemain;

import sun.reflect.generics.tree.Tree;

import java.util.*;

public class Test923 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(levelOrder03(root));
    }
    public static List<List<Integer>> levelOrder03(TreeNode root) {
        if(root == null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<List<Integer>> res = new ArrayList<>();
        boolean flag = true;
        while(!queue.isEmpty()) {
            flag = !flag;
            int curSize = queue.size();
            Deque<TreeNode> tmpStack = new LinkedList<>();
            for(int i = curSize; i > 0; i--) {
                TreeNode curNode = queue.poll();
                tmpStack.push(curNode);
                if(curNode.left != null) {
                    queue.add(curNode.left);
                }
                if(curNode.right != null) {
                    queue.add(curNode.right);
                }

            }
            List<Integer> tmpRes = new ArrayList<>();
            while(!tmpStack.isEmpty()) {
                TreeNode tmpNode = null;
                if(flag) {
                    tmpNode = tmpStack.pollFirst();
                } else {
                    tmpNode = tmpStack.pollLast();
                }
                tmpRes.add(tmpNode.val);
            }
            res.add(tmpRes);
        }
        return res;
    }
    public int[] levelOrder01(TreeNode root) {        //按层打印二叉树
        if(root == null) {
            return new int[0];
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        ArrayList<Integer> ans = new ArrayList<>();
        while(!queue.isEmpty()) {
            TreeNode tmp = queue.poll();
            ans.add(tmp.val);
            if(tmp.left != null) {
                queue.add(tmp.left);
            }
            if(tmp.right != null) {
                queue.add(tmp.right);
            }
        }
        int[] result = new int[ans.size()];
        for(int i = 0; i < ans.size(); i++) {
            result[i] = ans.get(i);
        }
        return result;
    }
    public List<List<Integer>> levelOrder02(TreeNode root) {        //按层打印二叉树，保存每一层的节点数量
        if(root == null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<List<Integer>> res = new ArrayList<>();
        while(!queue.isEmpty()) {
            List<Integer> tmpList = new ArrayList<>();
            int curSize = queue.size();
            for(int i = curSize; i > 0; i--) {
                TreeNode tmp = queue.poll();
                tmpList.add(tmp.val);
                if(tmp.left != null) {
                    queue.add(tmp.left);
                }
                if(tmp.right != null) {
                    queue.add(tmp.right);
                }
            }
            res.add(tmpList);
        }
        return res;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}