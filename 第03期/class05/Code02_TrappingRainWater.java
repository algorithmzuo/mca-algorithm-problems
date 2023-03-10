package class05;

// 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图
// 计算按此排列的柱子，下雨之后能接多少雨水
// 本题测试链接 : https://leetcode.cn/problems/trapping-rain-water/
public class Code02_TrappingRainWater {

	// 辅助数组的
	public static int zuo(int[] arr) {
		int n = arr.length;
		if (n < 3) {
			return 0;
		}
		int[] leftMax = new int[n];
		leftMax[0] = arr[0];
		for (int i = 1; i < n; i++) {
			leftMax[i] = Math.max(leftMax[i - 1], arr[i]);
		}
		int[] rightMax = new int[n];
		rightMax[n - 1] = arr[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			rightMax[i] = Math.max(rightMax[i + 1], arr[i]);
		}
		int ans = 0;
		for (int i = 1; i < n - 1; i++) {
			ans += Math.max(Math.min(leftMax[i - 1], rightMax[i + 1]) - arr[i], 0);
		}
		return ans;
	}

	// 首尾双指针
	public static int trap(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int N = arr.length;
		int L = 1;
		int leftMax = arr[0];
		int R = N - 2;
		int rightMax = arr[N - 1];
		int water = 0;
		while (L <= R) {
			if (leftMax <= rightMax) {
				water += Math.max(0, leftMax - arr[L]);
				leftMax = Math.max(leftMax, arr[L++]);
			} else {
				water += Math.max(0, rightMax - arr[R]);
				rightMax = Math.max(rightMax, arr[R--]);
			}
		}
		return water;
	}

}
