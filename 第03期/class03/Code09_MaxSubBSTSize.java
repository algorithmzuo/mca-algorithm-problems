package class03;

// 给定一个二叉树，找到其中最大的二叉搜索树（BST）子树，并返回该子树的大小
// 其中，最大指的是子树节点数最多的
// 二叉搜索树（BST）中的所有节点都具备以下属性：
// 左子树的值小于其父（根）节点的值
// 右子树的值大于其父（根）节点的值
// 注意：子树必须包含其所有后代
// 测试链接 : https://leetcode.cn/problems/largest-bst-subtree
public class Code09_MaxSubBSTSize {

	// 提交时不要提交这个类
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;

		public TreeNode(int value) {
			val = value;
		}
	}

	// 提交如下方法
	public static int largestBSTSubtree(TreeNode head) {
		if (head == null) {
			return 0;
		}
		return process(head).maxBSTSubtreeSize;
	}

	public static class Info {
		public int maxBSTSubtreeSize;
		public int allSize;
		public int max;
		public int min;

		public Info(int m, int a, int ma, int mi) {
			maxBSTSubtreeSize = m;
			allSize = a;
			max = ma;
			min = mi;
		}
	}

	public static Info process(TreeNode x) {
		if (x == null) {
			return null;
		}
		Info leftInfo = process(x.left);
		Info rightInfo = process(x.right);
		int max = x.val;
		int min = x.val;
		int allSize = 1;
		if (leftInfo != null) {
			max = Math.max(leftInfo.max, max);
			min = Math.min(leftInfo.min, min);
			allSize += leftInfo.allSize;
		}
		if (rightInfo != null) {
			max = Math.max(rightInfo.max, max);
			min = Math.min(rightInfo.min, min);
			allSize += rightInfo.allSize;
		}
		int p1 = -1;
		if (leftInfo != null) {
			p1 = leftInfo.maxBSTSubtreeSize;
		}
		int p2 = -1;
		if (rightInfo != null) {
			p2 = rightInfo.maxBSTSubtreeSize;
		}
		int p3 = -1;
		boolean leftBST = leftInfo == null ? true : (leftInfo.maxBSTSubtreeSize == leftInfo.allSize);
		boolean rightBST = rightInfo == null ? true : (rightInfo.maxBSTSubtreeSize == rightInfo.allSize);
		if (leftBST && rightBST) {
			boolean leftMaxLessX = leftInfo == null ? true : (leftInfo.max < x.val);
			boolean rightMinMoreX = rightInfo == null ? true : (x.val < rightInfo.min);
			if (leftMaxLessX && rightMinMoreX) {
				int leftSize = leftInfo == null ? 0 : leftInfo.allSize;
				int rightSize = rightInfo == null ? 0 : rightInfo.allSize;
				p3 = leftSize + rightSize + 1;
			}
		}
		return new Info(Math.max(p1, Math.max(p2, p3)), allSize, max, min);
	}

}
