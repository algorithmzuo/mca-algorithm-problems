package class04;

import java.util.Arrays;

// 在一个 n x n 的整数矩阵 grid 中，
// 每一个方格的值 grid[i][j] 表示位置 (i, j) 的平台高度。
// 当开始下雨时，在时间为 t 时，水池中的水位为 t 。
// 你可以从一个平台游向四周相邻的任意一个平台，但是前提是此时水位必须同时淹没这两个平台。
// 假定你可以瞬间移动无限距离，也就是默认在方格内部游动是不耗时的。
// 当然，在你游泳的时候你必须待在坐标方格里面。
// 你从坐标方格的左上平台 (0，0) 出发。
// 返回 你到达坐标方格的右下平台 (n-1, n-1) 所需的最少时间 。
// 测试链接 ：https://leetcode.cn/problems/swim-in-rising-water
public class Code07_SwimInRisingWater {

	public static int swimInWater(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		int[][] points = new int[n * m][3];
		int pi = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				points[pi][0] = i;
				points[pi][1] = j;
				points[pi++][2] = grid[i][j];
			}
		}
		Arrays.sort(points, (a, b) -> a[2] - b[2]);
		UnionFind uf = new UnionFind(n, m);
		int ans = 0;
		for (int i = 0; i < points.length; i++) {
			int r = points[i][0];
			int c = points[i][1];
			int v = points[i][2];
			if (r > 0 && grid[r - 1][c] <= v) {
				uf.union(r, c, r - 1, c);
			}
			if (r < n - 1 && grid[r + 1][c] <= v) {
				uf.union(r, c, r + 1, c);
			}
			if (c > 0 && grid[r][c - 1] <= v) {
				uf.union(r, c, r, c - 1);
			}
			if (c < m - 1 && grid[r][c + 1] <= v) {
				uf.union(r, c, r, c + 1);
			}
			if (uf.isSameSet(0, 0, n - 1, m - 1)) {
				ans = v;
				break;
			}
		}
		return ans;
	}

	public static class UnionFind {
		public int col;
		public int pointsSize;
		public int[] father;
		public int[] size;
		public int[] help;

		public UnionFind(int n, int m) {
			col = m;
			pointsSize = n * m;
			father = new int[pointsSize];
			size = new int[pointsSize];
			help = new int[pointsSize];
			for (int i = 0; i < pointsSize; i++) {
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
			while (hi > 0) {
				father[help[--hi]] = i;
			}
			return i;
		}

		private int index(int i, int j) {
			return i * col + j;
		}

		public void union(int row1, int col1, int row2, int col2) {
			int f1 = find(index(row1, col1));
			int f2 = find(index(row2, col2));
			if (f1 != f2) {
				if (size[f1] >= size[f2]) {
					father[f2] = f1;
					size[f1] += size[f2];
				} else {
					father[f1] = f2;
					size[f2] += size[f1];
				}
			}
		}

		public boolean isSameSet(int row1, int col1, int row2, int col2) {
			return find(index(row1, col1)) == find(index(row2, col2));
		}

	}

}
