package class03;

import java.util.HashMap;

//测试链接：https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
public class Code03_ConstructBinaryTreeFromPreorderAndInorderTraversal {

	// 提交时不提交这个类
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}
	}

	// 提交如下的方法
	public static TreeNode buildTree(int[] pre, int[] in) {
		HashMap<Integer, Integer> valueIndexMap = new HashMap<>();
		for (int i = 0; i < in.length; i++) {
			valueIndexMap.put(in[i], i);
		}
		return g(pre, 0, pre.length - 1, in, 0, in.length - 1, valueIndexMap);
	}

	public static TreeNode g(int[] pre, int L1, int R1, int[] in, int L2, int R2,
			HashMap<Integer, Integer> valueIndexMap) {
		if (L1 > R1) {
			return null;
		}
		TreeNode head = new TreeNode(pre[L1]);
		if (L1 == R1) {
			return head;
		}
		int find = valueIndexMap.get(pre[L1]);
		head.left = g(pre, L1 + 1, L1 + find - L2, in, L2, find - 1, valueIndexMap);
		head.right = g(pre, L1 + find - L2 + 1, R1, in, find + 1, R2, valueIndexMap);
		return head;
	}

}
