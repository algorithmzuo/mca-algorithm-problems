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
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, -1);
		int len = 0;
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			if (map.containsKey(sum - k)) {
				len = Math.max(i - map.get(sum - k), len);
			}
			if (!map.containsKey(sum)) {
				map.put(sum, i);
			}
		}
		return len;
	}

}
