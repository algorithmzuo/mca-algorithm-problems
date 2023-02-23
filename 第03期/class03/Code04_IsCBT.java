package class03;

import java.util.LinkedList;

// 给定一个二叉树的 root ，确定它是否是一个 完全二叉树
// 在一个 完全二叉树 中，除了最后一个关卡外，所有关卡都是完全被填满的
// 并且最后一个关卡中的所有节点都是尽可能靠左的
// 它可以包含 1 到 2h 节点之间的最后一级 h
// 测试链接 : https://leetcode.cn/problems/check-completeness-of-a-binary-tree/
public class Code04_IsCBT {

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
	public static boolean isCompleteTree(TreeNode head) {
		if (head == null) {
			return true;
		}
		LinkedList<TreeNode> queue = new LinkedList<>();
		boolean leaf = false;
		TreeNode l = null;
		TreeNode r = null;
		queue.add(head);
		while (!queue.isEmpty()) {
			head = queue.poll();
			l = head.left;
			r = head.right;
			if ((leaf && (l != null || r != null)) || (l == null && r != null)) {
				return false;
			}
			if (l != null) {
				queue.add(l);
			}
			if (r != null) {
				queue.add(r);
			}
			if (l == null || r == null) {
				leaf = true;
			}
		}
		return true;
	}

}
