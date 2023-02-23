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
	public boolean isValidBST(TreeNode root) {
		if (root == null) {
			return true;
		}
		return process(root).isBST;
	}

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

	public static Info process(TreeNode root) {
		if (root == null) {
			return null;
		}
		Info left = process(root.left);
		Info right = process(root.right);
		int min = root.val;
		int max = root.val;
		boolean isBST = true;
		if (left != null) {
			min = Math.min(min, left.min);
			max = Math.max(max, left.max);
			if (!left.isBST || left.max >= root.val) {
				isBST = false;
			}
		}
		if (right != null) {
			min = Math.min(min, right.min);
			max = Math.max(max, right.max);
			if (!right.isBST || root.val >= right.min) {
				isBST = false;
			}
		}
		return new Info(isBST, min, max);
	}

}
