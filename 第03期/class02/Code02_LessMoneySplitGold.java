package class02;

import java.util.PriorityQueue;

public class Code02_LessMoneySplitGold {

	public static int lessMoney(int[] arr) {
		// 小根堆！
		PriorityQueue<Integer> pQ = new PriorityQueue<>();
		// 所有数字扔到小根堆里
		for (int num : arr) {
			pQ.add(num);
		}
		int sum = 0;
		int cur = 0;
		// 小根堆只剩一个数字的时候停！
		while (pQ.size() > 1) {
			cur = pQ.poll() + pQ.poll();
			sum += cur;
			pQ.add(cur);
		}
		return sum;
	}

}
