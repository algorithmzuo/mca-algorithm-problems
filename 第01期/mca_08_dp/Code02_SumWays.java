package mca_08_dp;

public class Code02_SumWays {

	// arr中都是正数，sum也是正数
	// 求组成sum的方法数量
	public static int sumWays(int[] arr, int sum) {
		int n = arr.length;
		if (n == 0) {
			return sum == 0 ? 1 : 0;
		}
		int[][] dp = new int[n][sum + 1];
		for (int i = 0; i < n; i++) {
			dp[i][0] = 1;
		}
		if (arr[0] <= sum) {
			dp[0][arr[0]] = 1;
		}
		for (int i = 1; i < n; i++) {
			for (int j = 1; j <= sum; j++) {
				dp[i][j] = dp[i - 1][j] + (j - arr[i] >= 0 ? dp[i - 1][j - arr[i]] : 0);
			}
		}
		return dp[n - 1][sum];
	}

}
