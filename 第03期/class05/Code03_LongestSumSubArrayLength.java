package class05;

import java.util.HashMap;

// 给定一个数组 nums 和一个目标值 k，找到和等于 k 的最长连续子数组长度
// 如果不存在任意一个符合要求的子数组，则返回 0
// 测试链接 : https://leetcode.cn/problems/maximum-size-subarray-sum-equals-k/
public class Code03_LongestSumSubArrayLength {

	public int maxSubArrayLen(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		// key : 某个前缀和 value : 最早位置
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		// 非常重要 !
		map.put(0, -1);
		int ans = 0;
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			// sum : 0.....i 整体的和
			sum += arr[i];
			if (map.containsKey(sum - k)) {
				// 0.......730 1000 k = 100
				// 0...17 900
				// 18..730
				// 长度 = i - 最早位置
				ans = Math.max(i - map.get(sum - k), ans);
			}
			if (!map.containsKey(sum)) {
				map.put(sum, i);
			}
		}
		return ans;
	}

	public int maxSubArrayLen2(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		// key : 某个前缀和 value : 最早位置
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int ans = 0;
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			if (sum == k) {
				ans = Math.max(i + 1, ans);
			} else {
				if (map.containsKey(sum - k)) {
					ans = Math.max(i - map.get(sum - k), ans);
				}
			}
			if (!map.containsKey(sum)) {
				map.put(sum, i);
			}
		}
		return ans;
	}

}
