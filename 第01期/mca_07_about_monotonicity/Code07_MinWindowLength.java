package mca_07_about_monotonicity;

public class Code07_MinWindowLength {

	// 字符串都是小写字母
	public static int validMinLen(String s1, String s2) {
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int n = str1.length;
		int m = str2.length;
		if (n < m) {
			return -1;
		}
		// n >= m
		int[] counts = new int[256];
		for (char s2char : str2) {
			counts[s2char]++;
		}
		int all = m;
		int ans = Integer.MAX_VALUE;
		int r = 0;
		// [i..r)
		for (int i = 0; i < n; i++) {
			r = Math.max(r, i);
			while (r < n && all > 0) {
				counts[str1[r]]--;
				if (counts[str1[r]] >= 0) {
					all--;
				}
				r++;
			}
			if (all == 0) {
				ans = Math.min(ans, r - i);
			}
			counts[str1[i]]++;
			if (counts[str1[i]] > 0) {
				all++;
			}
		}
		return ans == Integer.MAX_VALUE ? -1 : ans;
	}

	public static int minLength(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() < s2.length()) {
			return Integer.MAX_VALUE;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int[] map = new int[256]; // map[37] = 4 37 4次
		for (int i = 0; i != str2.length; i++) {
			map[str2[i]]++;
		}
		int all = str2.length;
		int L = 0;
		int R = 0;
		int minLen = Integer.MAX_VALUE;
		while (R != str1.length) {
			map[str1[R]]--;
			if (map[str1[R]] >= 0) {
				all--;
			}
			if (all == 0) {
				while (map[str1[L]] < 0) {
					map[str1[L++]]++;
				}
				minLen = Math.min(minLen, R - L + 1);
				all++;
				map[str1[L++]]++;
			}
			R++;
		}
		return minLen == Integer.MAX_VALUE ? 0 : minLen;
	}

	// 测试链接 : https://leetcode.com/problems/minimum-window-substring/
	public static String minWindow(String s, String t) {
		if (s.length() < t.length()) {
			return "";
		}
		char[] str = s.toCharArray();
		char[] target = t.toCharArray();
		int[] map = new int[256];
		for (char cha : target) {
			map[cha]++;
		}
		int all = target.length;
		int L = 0;
		int R = 0;
		int minLen = Integer.MAX_VALUE;
		int ansl = -1;
		int ansr = -1;
		while (R != str.length) {
			map[str[R]]--;
			if (map[str[R]] >= 0) {
				all--;
			}
			if (all == 0) {
				while (map[str[L]] < 0) {
					map[str[L++]]++;
				}
				if (minLen > R - L + 1) {
					minLen = R - L + 1;
					ansl = L;
					ansr = R;
				}
				all++;
				map[str[L++]]++;
			}
			R++;
		}
		return minLen == Integer.MAX_VALUE ? "" : s.substring(ansl, ansr + 1);
	}

}
