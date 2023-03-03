package class04;

import java.util.Arrays;

// 给你一个二维整数数组 envelopes
// 其中 envelopes[i] = [wi, hi], 表示第 i 个信封的宽度和高度。
// 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里
// 如同俄罗斯套娃一样
// 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封
// 即可以把一个信封放到另一个信封里面
// 注意：不允许旋转信封
// 本题测试链接 : https://leetcode.cn/problems/russian-doll-envelopes/
public class Code04_EnvelopesProblem {

	public static int maxEnvelopes(int[][] envelopes) {
		Arrays.sort(envelopes, (a, b) -> a[0] != b[0] ? (a[0] - b[0]) : (b[1] - a[1]));
		int n = envelopes.length;
		int[] ends = new int[n];
		ends[0] = envelopes[0][1];
		int right = 0;
		int l = 0;
		int r = 0;
		int m = 0;
		for (int i = 1; i < n; i++) {
			l = 0;
			r = right;
			while (l <= r) {
				m = (l + r) / 2;
				if (envelopes[i][1] > ends[m]) {
					l = m + 1;
				} else {
					r = m - 1;
				}
			}
			right = Math.max(right, l);
			ends[l] = envelopes[i][1];
		}
		return right + 1;
	}

}
