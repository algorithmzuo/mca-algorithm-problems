package class01;

public class Code02_EvenTimesOddTimes {

	// arr中，只有一种数，出现奇数次
	public static void printOddTimesNum1(int[] arr) {
		int eor = 0;
		for (int num : arr) {
			eor ^= num;
		}
		System.out.println(eor);
	}

	// 请保证，arr中，有两种数出现奇数次，其他的数都是偶数次
	// 打印
	public static void printOddTimesNumber(int[] arr) {
		int eor = 0;
		for (int num : arr) {
			eor ^= num;
		}
		// eor = a ^ b
		// 请把eor状态中，最右侧的1提取出来
		// eor = 0000110010
		// rightOne = 0000000010 & | ^ << >> >>>
		// >> 带符号右移
		// >>> 不带符号右移
		// java 干啥的，自己查
		int rightOne = eor & (-eor);
		int zuo = 0;
		for (int num : arr) {
			// zuo只去异或一类，另一类忽略
			if ((num & rightOne) == 0) {
				zuo ^= num;
			}
		}
		System.out.println("一个是 ：" + zuo);
		System.out.println("另一个是 ：" + (eor ^ zuo));
	}

	public static void main(String[] args) {
		// 5 7 521 14 10
		int[] arr = { 5, 7, 14, 14, 521, 7, 10, 7, 5, 5, 521, 7, 7, 7, 5, 14 };
		// 请找到 14 和 10
		printOddTimesNumber(arr);
	}

}
