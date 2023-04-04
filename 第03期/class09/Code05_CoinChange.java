package class09;

// 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额
// 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0
// 假设每一种面额的硬币有无限个
// 题目数据保证结果符合 32 位带符号整数
// 测试链接 : https://leetcode.cn/problems/coin-change-ii/
public class Code05_CoinChange {

	public static int change(int aim, int[] arr) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		int N = arr.length;
		int[][] dp = new int[N + 1][aim + 1];
		dp[N][0] = 1;
		for (int index = N - 1; index >= 0; index--) {
			for (int rest = 0; rest <= aim; rest++) {
				dp[index][rest] = dp[index + 1][rest];
				if (rest - arr[index] >= 0) {
					dp[index][rest] += dp[index][rest - arr[index]];
				}
			}
		}
		return dp[0][aim];
	}

}
