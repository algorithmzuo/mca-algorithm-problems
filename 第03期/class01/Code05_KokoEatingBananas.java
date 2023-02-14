package class01;

// leetcode 875题
// 一定有答案
// 管理员，1小时，5堆，-1
public class Code05_KokoEatingBananas {

	public static int minEatingSpeed(int[] piles, int h) {
		int L = 1;
		int R = 0;
		for (int pile : piles) {
			R = Math.max(R, pile);
		}
		// 1......Max
		int ans = 0;
		int M = 0;
		while (L <= R) {
			M = L + ((R - L) >> 1);
			if (hours(piles, M) <= h) {
				ans = M;
				R = M - 1;
			} else {
				L = M + 1;
			}
		}
		return ans;
	}

	// piles : 每一堆香蕉的个数，都在piles里
	// speed : 猩猩的速度
	// 在这个速度下，几小时吃完！
	public static int hours(int[] piles, int speed) {
		int ans = 0;
		int offset = speed - 1;
		for (int pile : piles) {
			ans += (pile + offset) / speed;
		}
		return ans;
	}

}
