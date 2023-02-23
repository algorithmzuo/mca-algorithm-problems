package class03;

import java.util.LinkedList;
import java.util.Queue;

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
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(head);
		// 一旦遇到不双全的节点，接下来遇到的所有节点都必须是叶！
		boolean mustLeafStage = false;
		while (!queue.isEmpty()) {
			TreeNode cur = queue.poll();
			if ((cur.left == null && cur.right != null) 
					|| (mustLeafStage && (cur.left != null || cur.right != null))) {
				return false;
			}
			if(cur.left == null || cur.right == null) {
				mustLeafStage = true;
			}
			if (cur.left != null) {
				queue.add(cur.left);
			}
			if (cur.right != null) {
				queue.add(cur.right);
			}
		}
		return true;
	}

}
