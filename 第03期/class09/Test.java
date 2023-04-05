package class09;

public class Test {

	public static int f(int n) {
		if (n == 1) {
			return 1;
		}
		if (n == 2) {
			return 1;
		}
		// n > 2
		return f(n - 1) + f(n - 2);
	}

	public static int f2(int n) {
		int[] dp = new int[n + 1];
		return p2(n, dp);
	}

	public static int p2(int i, int[] dp) {
		if (i == 1) {
			return 1;
		}
		if (i == 2) {
			return 1;
		}
		// i > 2
		if (dp[i] != 0) {
			return dp[i];
		}
		int ans = p2(i - 1, dp) + p2(i - 2, dp);
		dp[i] = ans;
		return ans;
	}

	public static void main(String[] args) {
		int n = 7;
		// 1 1 2 3 5 8 13
		System.out.println("菲数列第 " + n + " 项，为" + f(n));
	}

	// 彻底暴力
	public static int knapsack1(int[] w, int[] v, int bag) {
		// 0...... bag
		// 返回最大价值
		return process(w, v, 0, bag);
	}

	// i....... 货自由选择，背包的剩余载重是j
	// 返回最大价值
	public static int process(int[] w, int[] v, int i, int j) {
		if (j < 0) {
			// 剩余载重是负数！？？？
			// 说明之前的选择有错，返回-1表示无效
			return -1;
		}
		// j >= 0;
		if (i == w.length) {
			// 没货了！
			return 0;
		}
		// 背包j >= 0
		// 有货！
		// 可能性1，不要当前的货
		int p1 = process(w, v, i + 1, j);
		// 可能性2，要当前的货
		int p2 = 0;
		int next2 = process(w, v, i + 1, j - w[i]);
		if (next2 != -1) {
			p2 = next2 + v[i];
		}
		return Math.max(p1, p2);
	}

	// 记忆化搜索
	// 货物数量n <= 1000
	// 背包容量bag <= 1000
	public static int knapsack2(int[] w, int[] v, int bag) {
		int n = w.length;
		int[][] dp = new int[n][bag + 1];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= bag; j++) {
				dp[i][j] = -2;
			}
		}
		return process2(w, v, 0, bag, dp);
	}

	// i....... 货自由选择，背包的剩余载重是j
	// 返回最大价值
	public static int process2(int[] w, int[] v, int i, int j, int[][] dp) {
		if (j < 0) {
			return -1;
		}
		if (i == w.length) {
			return 0;
		}
		if (dp[i][j] != -2) {
			return dp[i][j];
		}
		int p1 = process2(w, v, i + 1, j, dp);
		int p2 = 0;
		int next2 = process2(w, v, i + 1, j - w[i], dp);
		if (next2 != -1) {
			p2 = next2 + v[i];
		}
		int ans = Math.max(p1, p2);
		dp[i][j] = ans;
		return ans;
	}

	public static int knapsack3(int[] w, int[] v, int bag) {
		int n = w.length;
		int[][] dp = new int[n + 1][bag + 1];
		// dp[n][...] = 0
		for (int i = n - 1; i >= 0; i--) {
			// 每个格子依赖下一行的，两个位置的值
			for (int j = 0; j <= bag; j++) {
				// dp[i][j]
				int p1 = dp[i + 1][j];
				int p2 = 0;
				if (j - w[i] >= 0) {
					p2 = v[i] + dp[i + 1][j - w[i]];
				}
				dp[i][j] = Math.max(p1, p2);
			}
		}
		return dp[0][bag];
	}

	public static int knapsack4(int[] w, int[] v, int bag) {
		int n = w.length;
		int[] dp = new int[bag + 1];
		// 脑中[n][...] = 0
		for (int i = n - 1; i >= 0; i--) {
			// 每个格子依赖下一行的，两个位置的值
			for (int j = bag; j >= 0; j--) {
				// 0 1 2 3 4 5 6
				// <-
				int p1 = dp[j];
				int p2 = 0;
				if (j - w[i] >= 0) {
					p2 = v[i] + dp[j - w[i]];
				}
				dp[j] = Math.max(p1, p2);
			}
		}
		// 第0行的值
		return dp[bag];
	}

}
