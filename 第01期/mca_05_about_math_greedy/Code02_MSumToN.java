package mca_05_about_math_greedy;

public class Code02_MSumToN {

	public static boolean isMSum1(int num) {
		for (int start = 1; start <= num; start++) {
			int sum = start;
			for (int j = start + 1; j <= num; j++) {
				if (sum + j > num) {
					break;
				}
				if (sum + j == num) {
					return true;
				}
				sum += j;
			}
		}
		return false;
	}

	public static boolean isMSum2(int num) {
//		
//		return num == (num & (~num + 1)); // 是2的某次方，不是连续和！
//		
//		
//		return !(num == (num & (~num + 1)));
//		
//		return num == (num & (-num));
//		
//		
		return (num & (num - 1)) != 0;
	}

	public static void main(String[] args) {
		for (int num = 1; num < 200; num++) {
			System.out.println(num + " : " + isMSum1(num));
		}
		System.out.println("test begin");
		for (int num = 1; num < 5000; num++) {
			if (isMSum1(num) != isMSum2(num)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test end");

	}
}
