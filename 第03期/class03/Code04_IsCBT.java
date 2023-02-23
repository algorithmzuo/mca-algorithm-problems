package class03;

import java.util.LinkedList;

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
