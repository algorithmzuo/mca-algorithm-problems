package class07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Test {

	// n : 1~n, 0~n-1
	// edges(无向的！) : [1,5]
	// [4,8]
	// [3,7]
	// 5 9 X
	// 有向，带权重 : [1,5,10]
	public static ArrayList<ArrayList<Integer>> createGraph(int n, int[][] edges) {
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		// 点的编号，1~n
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		for (int[] e : edges) {
			int from = e[0];
			int to = e[1];
			graph.get(from).add(to);
		}
		return graph;
	}

	public static List<Integer> topoSort(int n, ArrayList<ArrayList<Integer>> graph) {
		// 点的编号，1~n
		int[] inCnts = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			ArrayList<Integer> inexts = graph.get(i);
			for (int next : inexts) {
				inCnts[next]++;
			}
		}
		int[] queue = new int[n + 1];
		int l = 0;
		int r = 0;
		// 谁入度为0，加入到queue里去！
		for (int i = 1; i <= n; i++) {
			if (inCnts[i] == 0) {
				queue[r++] = i;
			}
		}
		List<Integer> ans = new ArrayList<>();
		while (l < r) { // 如果队列里还有东西，就继续
			// cur就是当前弹出的点
			int cur = queue[l++];
			// 消除掉cur的影响！
			for (int curNext : graph.get(cur)) {
				if (--inCnts[curNext] == 0) {
					queue[r++] = curNext;
				}
			}
			ans.add(cur);
		}
		if (ans.size() != n) {
			return null;
		}
		return ans;
	}

	// edges [3,5,10]
	public static int kruskal(int n, int[][] edges) {
		// 建图？没必要！
		Arrays.sort(edges, (a, b) -> a[2] - b[2]);
		UnionFind uf = new UnionFind(n);
//		List<int[]> ans = 
		int ans = 0;
		for (int[] edge : edges) {
			int a = edge[0];
			int b = edge[1];
			int weight = edge[2];
			if (!uf.isSameSet(a, b)) {
				ans += weight;
				uf.union(a, b);
			}
		}
		return ans;
	}

	public static class UnionFind {

		public UnionFind(int n) {
			// n 0 ~ n-1
			// 1 ~ n

		}

		public boolean isSameSet(int a, int b) {
			return true;
		}

		public void union(int a, int b) {

		}

	}

	// n : 1 ~ n
	// start : 源出发点
	// graph : 整个图
	public static int[] dij(int n, int start, ArrayList<ArrayList<int[]>> graph) {
		int[] distance = new int[n + 1];
		// distance[0] : start -> 0 0不存在，所以该空间不用！
		// distance[1] : start -> 1 最短距离
		// distance[i] : start -> i 最短距离

		Arrays.fill(distance, -1);
		// distance[i] = -1, -1代表，start -> i 记录没求过！
		// [点号，cost]
		PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		// start -> start 0
		heap.add(new int[] { start, 0 });
		while (!heap.isEmpty()) {
			int[] cur = heap.poll();
			int i = cur[0];
			int cost = cur[1];
			if (distance[i] == -1) { // 当前点之前没有弹出过！才处理!
				distance[i] = cost;
				for (int[] edge : graph.get(i)) {
					int next = edge[0];
					int weight = edge[1];
					if (distance[next] != -1) {
						heap.add(new int[] { next, cost + weight });
					}
				}
			}
		}
		return distance;
	}

}
