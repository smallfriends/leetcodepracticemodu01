package zhiming.practice.leetcodemain;

import java.util.ArrayList;
import java.util.List;

public class Practice1011 {
    public int maxDepth(TreeNode root) {    //求数的深度
        if(root == null) {
            return 0;
        }
        List<TreeNode> queue = new ArrayList<TreeNode>();
        queue.add(root);
        int res = 0;
        while(queue.isEmpty()) {
            List<TreeNode> tmp = new ArrayList<TreeNode>();
            for(TreeNode node : queue) {
                if(node.left != null) {
                    tmp.add(node.left);
                }
                if(node.right != null) {
                    tmp.add(node.right);
                }
            }
            res++;
            queue = tmp;
        }
        return res;
    }
    /*public*/

}
