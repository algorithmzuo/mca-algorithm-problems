package mca_07_about_monotonicity;

// leetcode原题
// 测试链接：https://leetcode.com/problems/split-array-largest-sum/
public class Code08_SplitArrayLargestSum {

	public static int splitArray3(int[] nums, int M) {
		long sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}
		// 0 ~ sum
		long l = 0;
		long r = sum;
		long ans = 0;
		while (l <= r) {
			long limit = (l + r) / 2;
			long cur = f(nums, limit);
			if (cur <= M) {
				ans = limit;
				r = limit - 1;
			} else {
				l = limit + 1;
			}
		}
		return (int) ans;
	}

	public static int f(int[] arr, long limit) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > limit) {
				return Integer.MAX_VALUE;
			}
		}
		int parts = 1;
		int all = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (all + arr[i] > limit) {
				parts++;
				all = arr[i];
			} else {
				all += arr[i];
			}
		}
		return parts;
	}


}
