package class04;

// 给定一棵二叉树，你需要计算它的直径长度
// 一棵二叉树的直径长度是任意两个结点路径长度中的最大值
// 这条路径可能穿过也可能不穿过根结点
// 测试链接 : https://leetcode.cn/problems/diameter-of-binary-tree/
public class Code02_DiameterOfBinaryTree {

	// 提交时不要提交这个类
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;

		public TreeNode(int value) {
			val = value;
		}
	}

	// 提交以下的方法
	public static int diameterOfBinaryTree(TreeNode root) {
		return process(root).maxDistance;
	}

	public static class Info {
		public int maxDistance;
		public int height;

		public Info(int m, int h) {
			maxDistance = m;
			height = h;
		}

	}

	public static Info process(TreeNode x) {
		if (x == null) {
			return new Info(0, 0);
		}
		Info leftInfo = process(x.left);
		Info rightInfo = process(x.right);
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		// 可能性1&2 : 左树上的最大距离 右树上的最大距离 取最大值
		int maxDistance = Math.max(leftInfo.maxDistance, rightInfo.maxDistance);
		// 可能性3 : 左高 + 右高
		maxDistance = Math.max(maxDistance, leftInfo.height + rightInfo.height);
		return new Info(maxDistance, height);
	}

}
