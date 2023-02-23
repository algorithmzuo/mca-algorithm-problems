package class03;

// 测试链接 : https://leetcode.cn/problems/balanced-binary-tree/
public class Code06_IsBalanced {

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
	public static boolean isBalanced(TreeNode root) {
		return process(root).isBalanced;
	}

	public static class Info {
		public boolean isBalanced;
		public int height;

		public Info(boolean i, int h) {
			isBalanced = i;
			height = h;
		}
	}

	public static Info process(TreeNode root) {
		if (root == null) {
			return new Info(true, 0);
		}
		Info leftInfo = process(root.left);
		Info rightInfo = process(root.right);
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		boolean isBalanced = leftInfo.isBalanced && rightInfo.isBalanced
				&& Math.abs(leftInfo.height - rightInfo.height) < 2;
		return new Info(isBalanced, height);
	}

}
