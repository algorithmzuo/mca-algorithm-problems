package class03;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 测试链接 : https://leetcode.cn/problems/binary-tree-level-order-traversal/
public class Code02_LevelTraversalBT {

	// 不提交这个类
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;

		public TreeNode(int v) {
			val = v;
		}
	}

	// 提交以下的方法
	public static List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> ans = new ArrayList<>();
		if (root != null) {
			Queue<TreeNode> queue = new LinkedList<>();
			queue.add(root);
			while (!queue.isEmpty()) {
				int size = queue.size();
				List<Integer> curLevel = new ArrayList<>();
				for (int i = 0; i < size; i++) {
					TreeNode cur = queue.poll();
					if (cur.left != null) {
						queue.add(cur.left);
					}
					if (cur.right != null) {
						queue.add(cur.right);
					}
					curLevel.add(cur.val);
				}
				ans.add(curLevel);
			}
		}
		return ans;
	}

}
