package class03;

// 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树
// 有效 二叉搜索树定义如下：
// 节点的左子树只包含 小于 当前节点的数
// 节点的右子树只包含 大于 当前节点的数
// 所有左子树和右子树自身必须也是二叉搜索树
// 测试链接 : https://leetcode.cn/problems/validate-binary-search-tree/
public class Code05_IsBST {

	// 提交时不提交这个类
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;

		public TreeNode(int v) {
			val = v;
		}
	}

	// 提交以下的方法
	public static class Info {
		public boolean isBST;
		public int min;
		public int max;

		public Info(boolean a, int b, int c) {
			isBST = a;
			min = b;
			max = c;
		}
	}

	public boolean isValidBST(TreeNode root) {
		if (root == null) {
			return true;
		}
		return process(root).isBST;
	}

	public static Info process(TreeNode x) {
		if (x == null) {
			return null;
		}
		Info leftInfo = process(x.left);
		Info rightInfo = process(x.right);
		int min = x.val;
		int max = x.val;
		if (leftInfo != null) {
			min = Math.min(leftInfo.min, min);
			max = Math.max(leftInfo.max, max);
		}
		if (rightInfo != null) {
			min = Math.min(rightInfo.min, min);
			max = Math.max(rightInfo.max, max);
		}
		boolean isBST = true;
		if (leftInfo != null && !leftInfo.isBST) {
			isBST = false;
		}
		if (rightInfo != null && !rightInfo.isBST) {
			isBST = false;
		}
		if (leftInfo != null && leftInfo.max >= x.val) {
			isBST = false;
		}
		if (rightInfo != null && rightInfo.min <= x.val) {
			isBST = false;
		}
		return new Info(isBST, min, max);
	}

}
