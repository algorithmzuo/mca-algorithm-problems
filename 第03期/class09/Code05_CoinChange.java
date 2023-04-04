package class09;

// 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额
// 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0
// 假设每一种面额的硬币有无限个
// 题目数据保证结果符合 32 位带符号整数
// 测试链接 : https://leetcode.cn/problems/coin-change-ii/
public class Code05_CoinChange {

	public static int change1(int aim, int[] arr) {
		return process1(arr, 0, aim);
	}

	public static int process1(int[] arr, int i, int j) {
		if (j < 0) {
			return 0;
		}
		if (i == arr.length) {
			return j == 0 ? 1 : 0;
		}
		return process1(arr, i + 1, j) + process1(arr, i, j - arr[i]);
	}

	public static int change2(int aim, int[] arr) {
		int n = arr.length;
		int[][] dp = new int[n][aim + 1];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= aim; j++) {
				dp[i][j] = -1;
			}
		}
		return process2(arr, 0, aim, dp);
	}

	public static int process2(int[] arr, int i, int j, int[][] dp) {
		if (j < 0) {
			return 0;
		}
		if (i == arr.length) {
			return j == 0 ? 1 : 0;
		}
		if (dp[i][j] != -1) {
			return dp[i][j];
		}
		int ans = process2(arr, i + 1, j, dp) + process2(arr, i, j - arr[i], dp);
		dp[i][j] = ans;
		return ans;
	}

	public static int change3(int aim, int[] arr) {
		int n = arr.length;
		int[][] dp = new int[n + 1][aim + 1];
		dp[n][0] = 1;
		for (int i = n - 1; i >= 0; i--) {
			for (int j = 0; j <= aim; j++) {
				dp[i][j] = dp[i + 1][j];
				if (j - arr[i] >= 0) {
					dp[i][j] += dp[i][j - arr[i]];
				}
			}
		}
		return dp[0][aim];
	}

	public static int change4(int aim, int[] arr) {
		int n = arr.length;
		int[] dp = new int[aim + 1];
		dp[0] = 1;
		for (int i = n - 1; i >= 0; i--) {
			for (int j = 0; j <= aim; j++) {
				if (j - arr[i] >= 0) {
					dp[j] += dp[j - arr[i]];
				}
			}
		}
		return dp[aim];
	}

	public static int change5(int aim, int[] arr) {
		int n = arr.length;
		int[] dp = new int[aim + 1];
		dp[0] = 1;
		for (int i = n - 1; i >= 0; i--) {
			for (int j = arr[i]; j <= aim; j++) {
				dp[j] += dp[j - arr[i]];
			}
		}
		return dp[aim];
	}

}
