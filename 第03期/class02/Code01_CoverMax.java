package class02;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Code01_CoverMax {

	public static int maxCover(int[][] m) {
		Arrays.sort(m, (a, b) -> (a[0] - b[0]));
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		int max = 0;
		for (int[] line : m) {
			while (!heap.isEmpty() && heap.peek() <= line[0]) {
				heap.poll();
			}
			heap.add(line[1]);
			max = Math.max(max, heap.size());
		}
		return max;
	}

}
