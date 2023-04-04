package class09;

import java.util.LinkedList;

// 实现一个基本的计算器来计算简单的表达式字符串。
// 表达式字符串只包含非负整数，算符 +、-、*、/ ，左括号 ( 和右括号 ) 
// 整数除法需要 向下截断
// 你可以假定给定的表达式总是有效的。所有的中间结果的范围均满足 [-231, 231 - 1]
// 注意：你不能使用任何将字符串作为表达式求值的内置函数，比如 eval()
// 本题测试链接 : https://leetcode.cn/problems/basic-calculator-iii/
public class Code01_ExpressionCompute {

	public static int calculate(String str) {
		return f(str.toCharArray(), 0)[0];
	}

	// 请从str[i...]往下算，遇到字符串终止位置或者右括号，就停止
	// 返回两个值，长度为2的数组
	// 0) 负责的这一段的结果是多少
	// 1) 负责的这一段计算到了哪个位置
	public static int[] f(char[] str, int i) {
		LinkedList<String> queue = new LinkedList<String>();
		int cur = 0;
		int[] bra = null;
		// 从i出发，开始撸串
		while (i < str.length && str[i] != ')') {
			if (str[i] >= '0' && str[i] <= '9') {
				cur = cur * 10 + str[i++] - '0';
			} else if (str[i] != '(') { // 遇到的是运算符号
				addNum(queue, cur, str[i++]);
				cur = 0;
			} else { // 遇到左括号了
				bra = f(str, i + 1);
				cur = bra[0];
				i = bra[1] + 1;
			}
		}
		addNum(queue, cur, '+');
		return new int[] { getAns(queue), i };
	}

	public static void addNum(LinkedList<String> queue, int num, char op) {
		if (!queue.isEmpty() && (queue.peekLast().equals("*") || queue.peekLast().equals("/"))) {
			String top = queue.pollLast();
			int pre = Integer.valueOf(queue.pollLast());
			num = top.equals("*") ? (pre * num) : (pre / num);
		}
		queue.addLast(String.valueOf(num));
		queue.addLast(String.valueOf(op));
	}

	public static int getAns(LinkedList<String> queue) {
		int ans = Integer.valueOf(queue.pollFirst());
		while (queue.size() > 1) {
			String op = queue.pollFirst();
			int num = Integer.valueOf(queue.pollFirst());
			ans += op.equals("+") ? num : -num;
		}
		return ans;
	}

}
