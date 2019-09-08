package yomo.study.leetcode;

public class BFS {
    int maxDepth(TreeNode root) {
        // 分治的方法
        return root == null ? 0 : 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    int minDepths(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 如果右节点为null 则计算左节点
        if (root.right == null) {
            return 1 + minDepths(root.left);
        }
        // 右节点为null则计算左节点
        if (root.left == null) {
            return 1 + minDepths(root.right);
        }
        //如果都不为null 则分治左右节点
        int maxLeftLevel = minDepths(root.left);
        int maxRightLevel = minDepths(root.right);
        // 返回语句要加上操作  加上这一层的层级
        return 1 + Math.min(maxLeftLevel, maxRightLevel);
    }


    int minDepths2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //如果都不为null 则分治左右节点
        int maxLeftLevel = minDepths2(root.left);
        int maxRightLevel = minDepths2(root.right);
        return maxLeftLevel == 0 || maxRightLevel == 0 ? maxLeftLevel + maxRightLevel + 1 : 1 + Math.min(maxLeftLevel, maxRightLevel);


    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
