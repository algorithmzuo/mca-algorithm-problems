package mca_05_about_math_greedy;

public class Code03_EatGrass {

	
	
	
	
	// 剩余的草，是rest
	// 对于process(rest)这个过程来说，当前的先手先拿！
	// 返回值，是个字符串，"先"  "后"
	// "先" ：当前的先手赢！
	// "后" : 当前的先手输！
	public static String process(int rest) {
		if(rest < 5) {
			return (rest == 0 || rest == 2) ? "后" : "先";
		}
		// rest >= 5;
		// 当前的先手，可以开始选择了
		// 1   4   16    
		for(int choice = 1; choice <= rest; choice *= 4) {
			int next = rest - choice;
			if(process(next).equals("后")) {
				return "先";
			}	
		}
		return "后";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 如果n份草，最终先手赢，返回"先手"
	// 如果n份草，最终后手赢，返回"后手"
	public static String whoWin(int n) {
		if (n < 5) {
			return n == 0 || n == 2 ? "后手" : "先手";
		}
		// 进到这个过程里来，当前的先手，先选
		int want = 1;
		while (want <= n) {
			if (whoWin(n - want).equals("后手")) {
				return "先手";
			}
			if (want <= (n / 4)) {
				want *= 4;
			} else {
				break;
			}
		}
		return "后手";
	}

	public static String winner1(int n) {
		if (n < 5) {
			return (n == 0 || n == 2) ? "后手" : "先手";
		}
		int base = 1;
		while (base <= n) {
			if (winner1(n - base).equals("后手")) {
				return "先手";
			}
			if (base > n / 4) { // 防止base*4之后溢出
				break;
			}
			base *= 4;
		}
		return "后手";
	}

	public static String winner2(int n) {
		if (n % 5 == 0 || n % 5 == 2) {
			return "后手";
		} else {
			return "先手";
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i <= 50; i++) {
			System.out.println(i + " : " + process(i));
		}
	}

}
