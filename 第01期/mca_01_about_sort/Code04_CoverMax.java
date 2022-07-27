package mca_01_about_sort;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Code04_CoverMax {

	// [3,7]
	// [0,10]
	// [1,17]
	public static int zuo(int[][] lines) {
		// 根据开始位置排序
		Arrays.sort(lines, (a, b) -> a[0] - b[0]);
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		// 堆：加入一个数字，弹出一个数字，时间复杂度O(log N)
		int max = 0;
		for (int[] line : lines) {
			int start = line[0];
			int end = line[1];
			while (!heap.isEmpty() && heap.peek() <= start) {
				heap.poll();
			}
			heap.add(end);
			int curAns = heap.size();
			max = Math.max(max, curAns);

		}
		return max;
	}

	public static int maxCover1(int[][] lines) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < lines.length; i++) {
			min = Math.min(min, lines[i][0]);
			max = Math.max(max, lines[i][1]);
		}
		int cover = 0;
		for (double p = min + 0.5; p < max; p += 1) {
			int cur = 0;
			for (int i = 0; i < lines.length; i++) {
				if (lines[i][0] < p && lines[i][1] > p) {
					cur++;
				}
			}
			cover = Math.max(cover, cur);
		}
		return cover;
	}

	// for test
	public static int[][] generateLines(int N, int L, int R) {
		int size = (int) (Math.random() * N) + 1;
		int[][] ans = new int[size][2];
		for (int i = 0; i < size; i++) {
			int a = L + (int) (Math.random() * (R - L + 1));
			int b = L + (int) (Math.random() * (R - L + 1));
			if (a == b) {
				b = a + 1;
			}
			ans[i][0] = Math.min(a, b);
			ans[i][1] = Math.max(a, b);
		}
		return ans;
	}

	public static void main(String[] args) {
		System.out.println("test begin");
		int N = 100;
		int L = 0;
		int R = 200;
		int testTimes = 200000;
		for (int i = 0; i < testTimes; i++) {
			int[][] lines = generateLines(N, L, R);
			int ans1 = maxCover1(lines);
			int ans2 = zuo(lines);
			if (ans1 != ans2) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test end");
	}

}
