package class07;

import java.util.ArrayList;
import java.util.PriorityQueue;

// Dijkstra算法
// leetcode 743题，可以用这道题来练习Dijkstra算法
// 测试链接 : https://leetcode.cn/problems/network-delay-time
public class Code04_NetworkDelayTime {

	public static int networkDelayTime(int[][] times, int n, int k) {
		ArrayList<ArrayList<int[]>> nexts = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			nexts.add(new ArrayList<>());
		}
		for (int[] delay : times) {
			nexts.get(delay[0]).add(new int[] { delay[1], delay[2] });
		}
		PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		heap.add(new int[] { k, 0 });
		boolean[] used = new boolean[n + 1];
		int num = 0;
		int max = 0;
		while (!heap.isEmpty() && num < n) {
			int[] record = heap.poll();
			int cur = record[0];
			int delay = record[1];
			if (used[cur]) {
				continue;
			}
			used[cur] = true;
			num++;
			max = Math.max(max, delay);
			for (int[] next : nexts.get(cur)) {
				if (!used[next[0]]) {
					heap.add(new int[] { next[0], delay + next[1] });
				}
			}
		}
		return num < n ? -1 : max;
	}

}
