package mca_03_about_experience;

// 测试链接 : https://leetcode.com/problems/maximum-gap/
public class Code08_MaxGap {

	public static int maximumGap(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}
		int N = nums.length;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			min = Math.min(min, nums[i]);
			max = Math.max(max, nums[i]);
		}
		if (min == max) {
			return 0;
		}
		// N 个数  N + 1个桶
		// 0~9  min max boolean
		// 10~19 min max boolean
		boolean[] hasNum = new boolean[N + 1];
		int[] maxs = new int[N + 1];
		int[] mins = new int[N + 1];
		int bid = 0;
		for (int i = 0; i < N; i++) {
			// 17 0~99 10个桶，算一下，我该去哪个桶
			bid = bucket(nums[i], N, min, max);
			mins[bid] = hasNum[bid] ? Math.min(mins[bid], nums[i]) : nums[i];
			maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], nums[i]) : nums[i];
			hasNum[bid] = true;
		}
		int res = 0;
		int lastMax = maxs[0];
		int i = 1;
		// 当前不空的桶，min - 之前不空的桶max，
		// 把差值最大的记录！
		for (; i <= N; i++) {
			if (hasNum[i]) {
				res = Math.max(res, mins[i] - lastMax);
				lastMax = maxs[i];
			}
		}
		return res;
	}

	public static int bucket(long num, long len, long min, long max) {
		return (int) ((num - min) * len / (max - min));
	}

}
