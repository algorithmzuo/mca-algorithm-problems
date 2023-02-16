package class02;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Code01_CoverMax {

	public static void main(String[] args) {
		// 堆
		// log N
		PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> a - b);
		heap.add(8);
		heap.add(2);
		heap.add(3);
		heap.add(9);
		heap.add(4);
		heap.add(4);
		while (!heap.isEmpty()) {
			System.out.println(heap.poll());
		}
	}

	public static int maxCover(int[][] m) {
		// m = [ [4, 7], [3, 6], [1, 5] , [2, 9] ]
		// 开始位置排序！
		// m = [ [1, 5], [2, 9], [3, 6] , [4, 7] ]
		Arrays.sort(m, (a, b) -> (a[0] - b[0]));
		// 小根堆！建立！
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		int max = 0;
		for (int[] line : m) {
			// line : line[0]  line[1]
			// <= line[0]
			while (!heap.isEmpty() && heap.peek() <= line[0]) {
				heap.poll();
			}
			heap.add(line[1]);
			max = Math.max(max, heap.size());
		}
		return max;
	}

}
