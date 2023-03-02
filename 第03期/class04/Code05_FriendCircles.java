package class04;

// 本题为leetcode原题
// 测试链接：https://leetcode.cn/problems/friend-circles/
// 可以直接通过
public class Code05_FriendCircles {

	public static int findCircleNum(int[][] M) {
		int N = M.length;
		UnionFind uf = new UnionFind(N);
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (M[i][j] == 1) { // i和j互相认识
					uf.union(i, j);
				}
			}
		}
		return uf.sets();
	}

	public static class UnionFind {
		private int[] father;
		private int[] size;
		private int[] help;
		private int sets;

		public UnionFind(int N) {
			father = new int[N];
			size = new int[N];
			help = new int[N];
			sets = N;
			for (int i = 0; i < N; i++) {
				father[i] = i;
				size[i] = 1;
			}
		}

		private int find(int i) {
			int hi = 0;
			while (i != father[i]) {
				help[hi++] = i;
				i = father[i];
			}
			for (hi--; hi >= 0; hi--) {
				father[help[hi]] = i;
			}
			return i;
		}

		public void union(int i, int j) {
			int f1 = find(i);
			int f2 = find(j);
			if (f1 != f2) {
				if (size[f1] >= size[f2]) {
					size[f1] += size[f2];
					father[f2] = f1;
				} else {
					size[f2] += size[f1];
					father[f1] = f2;
				}
				sets--;
			}
		}

		public int sets() {
			return sets;
		}
	}

}
