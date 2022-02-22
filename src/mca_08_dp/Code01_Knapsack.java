package mca_08_dp;

public class Code01_Knapsack {

	// weight和value数组，等长！
	public static int maxValue1(int[] weight, int[] value, int bagLimit) {
		return f1(weight, value, 0, bagLimit);
	}

	// 0....n-1
	// 0...index-1 已经不能选了！
	// index...n-1这些货！自由挑选
	// 背包还剩下多少容量，rest，自由挑选是不能超过rest的！
	// index...n-1，在符合要求的情况下，最大价值能达到多少
	public static int f1(int[] weight, int[] value, int index, int rest) {
		if (rest < 0) { // 剩余的负重是负数，说明之前的选择是错误的！
			return -1; // 无效解！
		}
		// rest >= 0
		if (index == weight.length) { // 没货了！
			return 0;
		}
		// 既有负重，又有货物
		// 第一种选择：当前index位置的货，没要
		int p1 = f1(weight, value, index + 1, rest);
		// 第二种选择：当前index位置的货，要！
		int p2 = -1;
		int next = f1(weight, value, index + 1, rest - weight[index]);
		if (next != -1) {
			p2 = value[index] + next;
		}
		return Math.max(p1, p2);
	}
	
	
	// weight和value数组，等长！
	public static int maxValue2(int[] weight, int[] value, int bagLimit) {
		int n = weight.length;
		// index: 0...n
		// bag : 100   0..100
		int[][] dp = new int[n+1][bagLimit+1];
		for(int i = 0; i<= n;i++) {
			for(int j = 0 ; j <= bagLimit;j++) {
				dp[i][j] = -2;
			}
		}
		return f2(weight, value, 0, bagLimit, dp);
	}

	public static int f2(int[] weight, int[] value, int index, int rest, int[][] dp) {
		if (rest < 0) {
			return -1;
		}
		if(dp[index][rest] != -2) { // 之前算过！
			return dp[index][rest];
		}
		// 缓存没命中！
		int ans = 0;
		if(index == weight.length) {
			ans = 0;
		} else {
			int p1 =  f2(weight, value, index + 1, rest, dp);
			int p2 = -1;
			int next = f2(weight, value, index + 1, rest - weight[index], dp);
			if (next != -1) {
				p2 = value[index] + next;
			}
			ans = Math.max(p1, p2);
		}
		dp[index][rest] = ans;
		return ans;
	}
	
	

	// 所有的货，重量和价值，都在w和v数组里
	// 为了方便，其中没有负数
	// bag背包容量，不能超过这个载重
	// 返回：不超重的情况下，能够得到的最大价值
//	public static int maxValue(int[] w, int[] v, int bag) {
//		if (w == null || v == null || w.length != v.length || w.length == 0) {
//			return 0;
//		}
//		// 尝试函数！
//		return process(w, v, 0, bag);
//	}
//
//	// index 0~N
//	// rest 负~bag
//	public static int process(int[] w, int[] v, int index, int rest) {
//		if (rest < 0) {
//			return -1;
//		}
//		if (index == w.length) {
//			return 0;
//		}
//		int p1 = process(w, v, index + 1, rest);
//		int p2 = 0;
//		int next = process(w, v, index + 1, rest - w[index]);
//		if (next != -1) {
//			p2 = v[index] + next;
//		}
//		return Math.max(p1, p2);
//	}
//
//	public static int dp(int[] w, int[] v, int bag) {
//		if (w == null || v == null || w.length != v.length || w.length == 0) {
//			return 0;
//		}
//		int N = w.length;
//		int[][] dp = new int[N + 1][bag + 1];
//		for (int index = N - 1; index >= 0; index--) {
//			for (int rest = 0; rest <= bag; rest++) {
//				int p1 = dp[index + 1][rest];
//				int p2 = 0;
//				int next = rest - w[index] < 0 ? -1 : dp[index + 1][rest - w[index]];
//				if (next != -1) {
//					p2 = v[index] + next;
//				}
//				dp[index][rest] = Math.max(p1, p2);
//			}
//		}
//		return dp[0][bag];
//	}
	
	public static int[] randomArray(int len, int value) {
		int[] arr = new int[len];
		for(int i = 0 ; i< len;i++) {
			arr[i] = (int)(Math.random() * value) + 1;
		}
		return arr;
	}

	public static void main(String[] args) {
		int len = 30;
		int value = 50;
		
		int[] weight = randomArray(len, value);
		int[] values = randomArray(len, value );
		int bag = 2000;
		
		long start;
		long end;
		
		// 2 ^ 30
		start = System.currentTimeMillis();
		System.out.println(maxValue1(weight, values, bag));
		end = System.currentTimeMillis();
		System.out.println("运行时间（毫秒）：" + (end - start));
		
		// 30 * 2000 -> 60000
		start = System.currentTimeMillis();
		System.out.println(maxValue2(weight, values, bag));
		end = System.currentTimeMillis();
		System.out.println("运行时间（毫秒）：" + (end - start));
	}

}
