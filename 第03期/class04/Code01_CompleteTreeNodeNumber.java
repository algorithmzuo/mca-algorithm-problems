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
	// head为头的树一定是完全二叉树，保证这一点！
	public static int countNodes(TreeNode head) {
		if (head == null) {
			return 0;
		}
		return bs(head, 1, mostLeftLevel(head, 1));
	}

	// node : 当前来到的树的头部，当前这棵树一定是完全二叉树！
	// Level : 在整棵大树中，node在第几层
	// h : 在整棵大树中，一共有几层
	// 返回 : 以node为头的完全二叉树有几个节点
	// 时间复杂度O((logN)的平方) ，远好于，都遍历一遍所有的节点
	public static int bs(TreeNode node, int Level, int h) {
		if (Level == h) {
			return 1;
		}
		if (mostLeftLevel(node.right, Level + 1) == h) {
			// (1 << (h - Level)) 就代表 : 2的(h-level)次方
			return (1 << (h - Level)) + bs(node.right, Level + 1, h);
		} else {
			return (1 << (h - Level - 1)) + bs(node.left, Level + 1, h);
		}
	}

	// node，此时在level层
	// 请顺着node的left指针，往下扎
	// 返回最终的深度
	public static int mostLeftLevel(TreeNode node, int level) {
		while (node != null) {
			level++;
			node = node.left;
		}
		return level - 1;
	}

}
