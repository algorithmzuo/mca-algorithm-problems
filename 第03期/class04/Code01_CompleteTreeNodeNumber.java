package class04;

// 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数
// 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外
// 其余每层节点数都达到最大值
// 并且最下面一层的节点都集中在该层最左边的若干位置
// 若最底层为第 h 层，则该层包含 1~ 2h 个节点。
// 测试链接 : https://leetcode.cn/problems/count-complete-tree-nodes/
public class Code01_CompleteTreeNodeNumber {

	// 提交时不要提交这个类
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
	}

	// 提交如下的方法
	public static int countNodes(TreeNode head) {
		if (head == null) {
			return 0;
		}
		return bs(head, 1, mostLeftLevel(head, 1));
	}

	// 当前来到node节点，node节点在level层，总层数是h
	// 返回node为头的子树(必是完全二叉树)，有多少个节点
	public static int bs(TreeNode node, int Level, int h) {
		if (Level == h) {
			return 1;
		}
		if (mostLeftLevel(node.right, Level + 1) == h) {
			return (1 << (h - Level)) + bs(node.right, Level + 1, h);
		} else {
			return (1 << (h - Level - 1)) + bs(node.left, Level + 1, h);
		}
	}

	// 如果node在第level层，
	// 求以node为头的子树，最大深度是多少
	// node为头的子树，一定是完全二叉树
	public static int mostLeftLevel(TreeNode node, int level) {
		while (node != null) {
			level++;
			node = node.left;
		}
		return level - 1;
	}

}
