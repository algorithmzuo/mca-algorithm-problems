package class04;

// 本题测试链接 : https://leetcode.cn/problems/longest-increasing-subsequence
public class Code03_LIS {

	public static int common(int[] arr) {
		int n = arr.length;
		int[] dp = new int[n];
		// ...arr[0]
		dp[0] = 1;
		int maxLen = 1;
		for (int i = 1; i < n; i++) {
			int preLen = 0;
			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i]) {
					preLen = Math.max(preLen, dp[j]);
				}
			}
			dp[i] = preLen + 1;
			maxLen = Math.max(maxLen, dp[i]);
		}
		return maxLen;
	}

	public static int lengthOfLIS(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int[] ends = new int[arr.length];
		// ends[i] : 所有长度为i+1的递增子序列，最小结尾！
		// 0.....n-1
		// 0 1...
		ends[0] = arr[0];
		int max = 1;
		// ends填到了哪
		int right = 0;
		for (int i = 1; i < arr.length; i++) {
			int l = 0;
			int r = right;
			while (l <= r) {
				int m = (l + r) / 2;
				if (arr[i] > ends[m]) {
					l = m + 1;
				} else {
					r = m - 1;
				}
			}
			// l : 就是返回的下标
			ends[l] = arr[i];
			right = Math.max(right, l);
			max = Math.max(max, l + 1);
		}
		return max;
	}

}