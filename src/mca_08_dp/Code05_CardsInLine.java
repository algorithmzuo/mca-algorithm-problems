package mca_08_dp;

public class Code05_CardsInLine {

	public static int getWinnerScore(int[] arr) {
		int xian = xian(arr, 0, arr.length - 1);
		int hou = hou(arr, 0, arr.length - 1);
		return Math.max(xian, hou);
	}

	// 目前，是在arr[L...R]这个范围上玩牌！
	// 返回，先手最终的最大得分
	public static int xian(int[] arr, int L, int R) {
		if (L == R) {
			return arr[L];
		}
		if (L == R - 1) {
			return Math.max(arr[L], arr[R]);
		}
		// L...R上，不止两张牌！
		// 可能性1：拿走L位置的牌
		int p1 = arr[L] + hou(arr, L + 1, R);
		// 可能性2：拿走R位置的牌
		int p2 = arr[R] + hou(arr, L, R - 1);
		return Math.max(p1, p2);
	}

	// 目前，是在arr[L...R]这个范围上玩牌！
	// 返回，后手最终的最大得分
	public static int hou(int[] arr, int L, int R) {
		if (L == R) {
			return 0;
		}
		if (L == R - 1) {
			return Math.min(arr[L], arr[R]);
		}
		// L..R上，不止两张牌
		// 后手！
		// 可能性1：对手，先手，拿走L位置的牌，接下来！你就可以在L+1..R上先手了！
		int p1 = xian(arr, L + 1, R);
		// 可能性2：对手，先手，拿走R位置的牌，接下来！你就可以在L..R-1上先手了！
		int p2 = xian(arr, L, R - 1);
		return Math.min(p1, p2);
	}

	public static int getWinnerScore2(int[] arr) {
		int n = arr.length;
		int[][] dpxian = new int[n][n];
		int[][] dphou = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				dpxian[i][j] = -1;
				dphou[i][j] = -1;
			}
		}
		int xian = xian2(arr, 0, arr.length - 1, dpxian, dphou);
		int hou = hou2(arr, 0, arr.length - 1, dpxian, dphou);
		return Math.max(xian, hou);
	}

	public static int xian2(int[] arr, int L, int R, int[][] dpxian, int[][] dphou) {
		if (dpxian[L][R] != -1) {
			return dpxian[L][R];
		}
		int ans = 0;
		if (L == R) {
			ans = arr[L];
		} else if (L == R - 1) {
			ans = Math.max(arr[L], arr[R]);
		} else {
			int p1 = arr[L] + hou2(arr, L + 1, R, dpxian, dphou);
			int p2 = arr[R] + hou2(arr, L, R - 1, dpxian, dphou);
			ans = Math.max(p1, p2);
		}
		dpxian[L][R] = ans;
		return ans;
	}

	public static int hou2(int[] arr, int L, int R, int[][] dpxian, int[][] dphou) {
		if (dphou[L][R] != -1) {
			return dphou[L][R];
		}
		int ans = 0;
		if (L == R) {
			ans = 0;
		} else if (L == R - 1) {
			ans = Math.min(arr[L], arr[R]);
		} else {
			int p1 = xian2(arr, L + 1, R, dpxian, dphou);
			int p2 = xian2(arr, L, R - 1, dpxian, dphou);
			ans = Math.min(p1, p2);
		}
		dphou[L][R] = ans;
		return ans;
	}

//	// 根据规则，返回获胜者的分数
//	public static int win1(int[] arr) {
//		if (arr == null || arr.length == 0) {
//			return 0;
//		}
//		int first = f1(arr, 0, arr.length - 1);
//		int second = g1(arr, 0, arr.length - 1);
//		return Math.max(first, second);
//	}
//
//	// arr[L..R]，先手获得的最好分数返回
//	public static int f1(int[] arr, int L, int R) {
//		if (L == R) {
//			return arr[L];
//		}
//		int p1 = arr[L] + g1(arr, L + 1, R);
//		int p2 = arr[R] + g1(arr, L, R - 1);
//		return Math.max(p1, p2);
//	}
//
//	// // arr[L..R]，后手获得的最好分数返回
//	public static int g1(int[] arr, int L, int R) {
//		if (L == R) {
//			return 0;
//		}
//		int p1 = f1(arr, L + 1, R); // 对手拿走了L位置的数
//		int p2 = f1(arr, L, R - 1); // 对手拿走了R位置的数
//		return Math.min(p1, p2);
//	}
//
//	public static int win2(int[] arr) {
//		if (arr == null || arr.length == 0) {
//			return 0;
//		}
//		int N = arr.length;
//		int[][] fmap = new int[N][N];
//		int[][] gmap = new int[N][N];
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				fmap[i][j] = -1;
//				gmap[i][j] = -1;
//			}
//		}
//		int first = f2(arr, 0, arr.length - 1, fmap, gmap);
//		int second = g2(arr, 0, arr.length - 1, fmap, gmap);
//		return Math.max(first, second);
//	}
//
//	// arr[L..R]，先手获得的最好分数返回
//	public static int f2(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
//		if (fmap[L][R] != -1) {
//			return fmap[L][R];
//		}
//		int ans = 0;
//		if (L == R) {
//			ans = arr[L];
//		} else {
//			int p1 = arr[L] + g2(arr, L + 1, R, fmap, gmap);
//			int p2 = arr[R] + g2(arr, L, R - 1, fmap, gmap);
//			ans = Math.max(p1, p2);
//		}
//		fmap[L][R] = ans;
//		return ans;
//	}
//
//	// // arr[L..R]，后手获得的最好分数返回
//	public static int g2(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
//		if (gmap[L][R] != -1) {
//			return gmap[L][R];
//		}
//		int ans = 0;
//		if (L != R) {
//			int p1 = f2(arr, L + 1, R, fmap, gmap); // 对手拿走了L位置的数
//			int p2 = f2(arr, L, R - 1, fmap, gmap); // 对手拿走了R位置的数
//			ans = Math.min(p1, p2);
//		}
//		gmap[L][R] = ans;
//		return ans;
//	}
//
//	public static int win3(int[] arr) {
//		if (arr == null || arr.length == 0) {
//			return 0;
//		}
//		int N = arr.length;
//		int[][] fmap = new int[N][N];
//		int[][] gmap = new int[N][N];
//		for (int i = 0; i < N; i++) {
//			fmap[i][i] = arr[i];
//		}
//		for (int startCol = 1; startCol < N; startCol++) {
//			int L = 0;
//			int R = startCol;
//			while (R < N) {
//				fmap[L][R] = Math.max(arr[L] + gmap[L + 1][R], arr[R] + gmap[L][R - 1]);
//				gmap[L][R] = Math.min(fmap[L + 1][R], fmap[L][R - 1]);
//				L++;
//				R++;
//			}
//		}
//		return Math.max(fmap[0][N - 1], gmap[0][N - 1]);
//	}

	public static void main(String[] args) {
		int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };
		
		System.out.println(getWinnerScore(arr));
		System.out.println(getWinnerScore2(arr));

	}

}
