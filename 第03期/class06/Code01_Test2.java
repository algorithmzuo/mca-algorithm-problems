package class06;

public class Code01_Test2 {

	public static class UnionFind {

		// father[i] : i号点往上的，是几号点
		public int[] father;
		// size[i] : i号点如果是代表点的话，size[i]才有意义，代表集合的大小
		public int[] size;
		public int[] help;

		public UnionFind(int n) {
			father = new int[n];
			size = new int[n];
			help = new int[n];
			for (int i = 0; i < n; i++) {
				father[i] = i;
				size[i] = 1;
			}
		}

		// i号点，往上到不能再往上，是什么点？返回！
		private int find(int i) {
			int size = 0;
			while (i != father[i]) {
				help[size++] = i;
				i = father[i];
			}
			while (size > 0) {
				father[help[--size]] = i;
			}
			return i;
		}

		public boolean isSameSet(int x, int y) {
			return find(x) == find(y);
		}

		// x背后的集合，彻底和，y背后的集合，合在一起
		public void union(int x, int y) {
			int fx = find(x);
			int fy = find(y);
			if (fx != fy) {
				int sizeX = size[fx];
				int sizeY = size[fy];
				if (sizeX >= sizeY) {
					father[fy] = fx;
					size[fx] += size[fy];
				} else {
					father[fx] = fy;
					size[fy] += size[fx];
				}
			}
		}
	}

	public static void main(String[] args) {
		int n = 100;
		UnionFind uf = new UnionFind(n);
		System.out.println(uf.isSameSet(4, 60));
		// 4 0 90
		// 60 80 30
		uf.union(4, 0);
		uf.union(60, 80);
		uf.union(0, 90);
		uf.union(80, 30);
		uf.union(90, 30);
		System.out.println(uf.isSameSet(4, 60));
	}

}
