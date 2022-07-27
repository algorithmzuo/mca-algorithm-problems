package mca_05_about_math_greedy;

public class Code01_AppleMinBags {

	public static int minBags(int apple) {
		if (apple < 0) {
			return -1;
		}
		// apple >> 3  -> apple / 8
		// 最多用几个8号袋！
		int bag8 = (apple >> 3);
		// 最多的8号袋，先试试，剩余多少苹果
		int rest = apple - (bag8 << 3);
		while(bag8 >= 0) {
			// rest 个
			if(rest % 6 ==0) {
				return bag8 + (rest / 6);
			} else {
				bag8--;
				rest += 8;
			}
		}
		return -1;
	}

	// O(1)
	public static int minBagAwesome(int apple) {
		if ((apple & 1) != 0) { // 如果是奇数，返回-1
			return -1;
		}
		if (apple < 18) {
			return apple == 0 ? 0 : (apple == 6 || apple == 8) ? 1
					: (apple == 12 || apple == 14 || apple == 16) ? 2 : -1;
		}
		return (apple - 18) / 8 + 3;
	}

	public static void main(String[] args) {
		for(int apple = 1; apple < 200;apple++) {
			System.out.println(apple + " : "+ minBags(apple));
		}

	}

}
