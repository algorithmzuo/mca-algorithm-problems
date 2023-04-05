package class09;

import java.util.Arrays;

// 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
// 测试链接 : https://leetcode.cn/problems/longest-substring-without-repeating-characters/
public class Code02_LongestSubstringWithoutRepeatingCharacters {

	public static int lengthOfLongestSubstring(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		// 长度 >= 1
		char[] s = str.toCharArray();
		int n = s.length;
		int[] preIndex = new int[256];
		Arrays.fill(preIndex, -1);
		int ans = 1;
		int preAns = 1;
		preIndex[s[0]] = 0;
		for (int i = 1; i < n; i++) {
			int preApear = preIndex[s[i]];
			int p1 = i - preApear;
			int p2 = preAns + 1;
			int curAns = Math.min(p1, p2);
			ans = Math.max(ans, curAns);
			preIndex[s[i]] = i;
			preAns = curAns;
		}
		return ans;
	}

//	public static int lengthOfLongestSubstring(String s) {
//		if (s == null || s.equals("")) {
//			return 0;
//		}
//		char[] str = s.toCharArray();
//		int[] map = new int[256];
//		for (int i = 0; i < 256; i++) {
//			map[i] = -1;
//		}
//		map[str[0]] = 0;
//		int N = str.length;
//		int ans = 1;
//		int pre = 1;
//		for (int i = 1; i < N; i++) {
//			pre = Math.min(i - map[str[i]], pre + 1);
//			ans = Math.max(ans, pre);
//			map[str[i]] = i;
//		}
//		return ans;
//	}

}
