package class10;

import java.util.ArrayList;

// 本题测试链接 : https://leetcode.com/problems/burst-balloons/
public class Code03_BurstBalloons {

	public static int maxCoins(int[] nums) {
		int n = nums.length;
		// 3 5 2
		// 1 3 5 2 1
		int[] arr = new int[n + 2];
		arr[0] = 1;
		arr[n + 1] = 1;
		// 1 3 5 2 1
		for (int i = 0; i < n; i++) {
			arr[i + 1] = nums[i];
		}
		// 3 5 2 3
		// 1 3 5 2 1
		// 0 1 2 3 4
		return hit(arr, 1, n);
	}

	// arr[l...r]范围上，选择最优的打爆顺序，返回最大的得分!
	// 想要调用递归，必须满足如下条件 :
	// arr[l-1]的气球，一定没爆!
	// arr[r+1]的气球，一定没爆!
	public static int hit(int[] arr, int l, int r) {
		if (l == r) {
			// l...r 只有一个气球了!
			return arr[l - 1] * arr[l] * arr[r + 1];
		}
		// l < r 不只一个气球
		// 可能性1 : 最后打爆l位置的气球
		int p1 = hit(arr, l + 1, r) + arr[l - 1] * arr[l] * arr[r + 1];
		// 可能性2 : 最后打爆r位置的气球
		int p2 = hit(arr, l, r - 1) + arr[l - 1] * arr[r] * arr[r + 1];
		int ans = Math.max(p1, p2);
		for (int m = l + 1; m < r; m++) {
			// 尝试中间每一个气球，都最后打爆
			int left = hit(arr, l, m - 1);
			int right = hit(arr, m + 1, r);
			int mid = arr[l - 1] * arr[m] * arr[r + 1];
			int curAns = left + right + mid;
			ans = Math.max(ans, curAns);
		}
		return ans;
	}

	public static void main(String[] args) {
		int[] arr = { 3, 3, 4, 3, 3 };
		System.out.println(maxCoins(arr));
		ArrayList<ArrayList<Integer>> paths = maxCoins2(arr);
		for (ArrayList<Integer> path : paths) {
			for (int num : path) {
				System.out.print(num + " ");
			}
			System.out.println();
		}
	}

	public static ArrayList<ArrayList<Integer>> maxCoins2(int[] arr) {
		int N = arr.length;
		int[] help = new int[N + 2];
		help[0] = 1;
		help[N + 1] = 1;
		for (int i = 0; i < N; i++) {
			help[i + 1] = arr[i];
		}
		int[][] dp = new int[N + 2][N + 2];
		for (int i = 1; i <= N; i++) {
			dp[i][i] = help[i - 1] * help[i] * help[i + 1];
		}
		for (int L = N; L >= 1; L--) {
			for (int R = L + 1; R <= N; R++) {
				int ans = help[L - 1] * help[L] * help[R + 1] + dp[L + 1][R];
				ans = Math.max(ans, help[L - 1] * help[R] * help[R + 1] + dp[L][R - 1]);
				for (int i = L + 1; i < R; i++) {
					ans = Math.max(ans, help[L - 1] * help[i] * help[R + 1] + dp[L][i - 1] + dp[i + 1][R]);
				}
				dp[L][R] = ans;
			}
		}
		System.out.println("结果 : " + dp[1][N]);
		ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
		ArrayList<Integer> path = new ArrayList<>();
		generatePath2(dp, help, 1, N, N, path, ans);
		return ans;

	}

	public static int pi;

	// 回溯！倒着填路径
	public static void generatePath1(int[][] dp, int[] arr, int l, int r, int[] path) {
		if (l == r) {
			path[pi--] = l;
		} else {
			if (dp[l + 1][r] + arr[l - 1] * arr[l] * arr[r + 1] == dp[l][r]) {
				path[pi--] = l;
				generatePath1(dp, arr, l + 1, r, path);
			} else if (dp[l][r - 1] + arr[l - 1] * arr[r] * arr[r + 1] == dp[l][r]) {
				path[pi--] = r;
				generatePath1(dp, arr, l, r - 1, path);
			} else {
				for (int m = l + 1; m < r; m++) {
					int left = dp[l][m - 1];
					int right = dp[m + 1][r];
					int mid = arr[l - 1] * arr[m] * arr[r + 1];
					if (left + right + mid == dp[l][r]) {
						path[pi--] = m;
						generatePath1(dp, arr, l, m - 1, path);
						generatePath1(dp, arr, m + 1, r, path);
						break;
					}
				}
			}
		}
	}

	// 回溯！倒着填路径
	public static void generatePath2(int[][] dp, int[] arr, int l, int r, int n, ArrayList<Integer> path,
			ArrayList<ArrayList<Integer>> ans) {
		if (l == r) {
			path.add(l);
			if (path.size() == n) {
				addPath(ans, path);
			}
		} else {
			if (dp[l + 1][r] + arr[l - 1] * arr[l] * arr[r + 1] == dp[l][r]) {
				ArrayList<Integer> p = new ArrayList<>(path);
				p.add(l);
				generatePath2(dp, arr, l + 1, r, n, p, ans);
			}
			if (dp[l][r - 1] + arr[l - 1] * arr[r] * arr[r + 1] == dp[l][r]) {
				ArrayList<Integer> p = new ArrayList<>(path);
				p.add(r);
				generatePath2(dp, arr, l, r - 1, n, p, ans);
			}
			for (int m = l + 1; m < r; m++) {
				int left = dp[l][m - 1];
				int right = dp[m + 1][r];
				int mid = arr[l - 1] * arr[m] * arr[r + 1];
				if (left + right + mid == dp[l][r]) {
					// 可以先左、后右
					ArrayList<Integer> p1 = new ArrayList<>(path);
					p1.add(m);
					generatePath2(dp, arr, l, m - 1, n, p1, ans);
					generatePath2(dp, arr, m + 1, r, n, p1, ans);
					// 也可以先右、后左
					ArrayList<Integer> p2 = new ArrayList<>(path);
					p2.add(m);
					generatePath2(dp, arr, m + 1, r, n, p2, ans);
					generatePath2(dp, arr, l, m - 1, n, p2, ans);
				}
			}
		}
	}

	public static void addPath(ArrayList<ArrayList<Integer>> ans, ArrayList<Integer> path) {
		ArrayList<Integer> curPath = new ArrayList<>();
		for (int i = path.size() - 1; i >= 0; i--) {
			curPath.add(path.get(i) - 1);
		}
		ans.add(curPath);
	}

//
//	public static int maxCoins0(int[] arr) {
//		// [3,2,1,3]
//		// [1,3,2,1,3,1]
//		int N = arr.length;
//		int[] help = new int[N + 2];
//		for (int i = 0; i < N; i++) {
//			help[i + 1] = arr[i];
//		}
//		help[0] = 1;
//		help[N + 1] = 1;
//		return func(help, 1, N);
//	}
//
//	// L-1位置，和R+1位置，永远不越界，并且，[L-1] 和 [R+1] 一定没爆呢！
//	// 返回，arr[L...R]打爆所有气球，最大得分是什么
//	public static int func(int[] arr, int L, int R) {
//		if (L == R) {
//			return arr[L - 1] * arr[L] * arr[R + 1];
//		}
//		// 尝试每一种情况，最后打爆的气球，是什么位置
//		// L...R
//		// L位置的气球，最后打爆
//		int max = func(arr, L + 1, R) + arr[L - 1] * arr[L] * arr[R + 1];
//		// R位置的气球，最后打爆
//		max = Math.max(max, func(arr, L, R - 1) + arr[L - 1] * arr[R] * arr[R + 1]);
//		// 尝试所有L...R，中间的位置，(L,R)
//		for (int i = L + 1; i < R; i++) {
//			// i位置的气球，最后打爆
//			int left = func(arr, L, i - 1);
//			int right = func(arr, i + 1, R);
//			int last = arr[L - 1] * arr[i] * arr[R + 1];
//			int cur = left + right + last;
//			max = Math.max(max, cur);
//		}
//		return max;
//	}
//
//	public static int maxCoins1(int[] arr) {
//		if (arr == null || arr.length == 0) {
//			return 0;
//		}
//		if (arr.length == 1) {
//			return arr[0];
//		}
//		int N = arr.length;
//		int[] help = new int[N + 2];
//		help[0] = 1;
//		help[N + 1] = 1;
//		for (int i = 0; i < N; i++) {
//			help[i + 1] = arr[i];
//		}
//		return process(help, 1, N);
//	}
//
//	// 打爆arr[L..R]范围上的所有气球，返回最大的分数
//	// 假设arr[L-1]和arr[R+1]一定没有被打爆
//	public static int process(int[] arr, int L, int R) {
//		if (L == R) {// 如果arr[L..R]范围上只有一个气球，直接打爆即可
//			return arr[L - 1] * arr[L] * arr[R + 1];
//		}
//		// 最后打爆arr[L]的方案，和最后打爆arr[R]的方案，先比较一下
//		int max = Math.max(arr[L - 1] * arr[L] * arr[R + 1] + process(arr, L + 1, R),
//				arr[L - 1] * arr[R] * arr[R + 1] + process(arr, L, R - 1));
//		// 尝试中间位置的气球最后被打爆的每一种方案
//		for (int i = L + 1; i < R; i++) {
//			max = Math.max(max, arr[L - 1] * arr[i] * arr[R + 1] + process(arr, L, i - 1) + process(arr, i + 1, R));
//		}
//		return max;
//	}
//
//	public static int maxCoins2(int[] arr) {
//		if (arr == null || arr.length == 0) {
//			return 0;
//		}
//		if (arr.length == 1) {
//			return arr[0];
//		}
//		int N = arr.length;
//		int[] help = new int[N + 2];
//		help[0] = 1;
//		help[N + 1] = 1;
//		for (int i = 0; i < N; i++) {
//			help[i + 1] = arr[i];
//		}
//		int[][] dp = new int[N + 2][N + 2];
//		for (int i = 1; i <= N; i++) {
//			dp[i][i] = help[i - 1] * help[i] * help[i + 1];
//		}
//		for (int L = N; L >= 1; L--) {
//			for (int R = L + 1; R <= N; R++) {
//				int ans = help[L - 1] * help[L] * help[R + 1] + dp[L + 1][R];
//				ans = Math.max(ans, help[L - 1] * help[R] * help[R + 1] + dp[L][R - 1]);
//				for (int i = L + 1; i < R; i++) {
//					ans = Math.max(ans, help[L - 1] * help[i] * help[R + 1] + dp[L][i - 1] + dp[i + 1][R]);
//				}
//				dp[L][R] = ans;
//			}
//		}
//		return dp[1][N];
//	}

}
