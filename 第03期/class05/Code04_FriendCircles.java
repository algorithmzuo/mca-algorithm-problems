package class05;

// 有 n 个城市，其中一些彼此相连，另一些没有相连
// 如果城市 a 与城市 b 直接相连
// 且城市 b 与城市 c 直接相连
// 那么城市 a 与城市 c 间接相连
// 省份 是一组直接或间接相连的城市
// 组内不含其他没有相连的城市。
// 给你一个 n x n 的矩阵 isConnected
// 其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连
// 而 isConnected[i][j] = 0 表示二者不直接相连。
// 返回矩阵中 省份 的数量。
// 测试链接：https://leetcode.cn/problems/friend-circles/
public class Code04_FriendCircles {

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
