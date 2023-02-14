package class01;

public class Code01_Swap {

	public static void printBinary(int num) {
		for (int i = 31; i >= 0; i--) {
			System.out.print((num & (1 << i)) != 0 ? "1" : "0");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int a = 16;
		int b = -1237871;
		printBinary(a);
		printBinary(b);
		System.out.println("=======");
		printBinary(a & b);
		System.out.println(a & b);

		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		System.out.println(a);
		System.out.println(b);
	}

}
