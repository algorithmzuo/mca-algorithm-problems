package class08;

public class Test {

	class Trie {

		class Node {
			public int pass;
			public int end;
			public Node[] nexts;

			public Node() {
				pass = 0;
				end = 0;
				nexts = new Node[26];
			}
		}

		private Node root;

		public Trie() {
			root = new Node();
		}

		// 在该结构里，加入str，一次
		public void insert(String str) {
			Node cur = root;
			cur.pass++;
			for (int i = 0; i < str.length(); i++) {
				int path = str.charAt(i) - 'a';
				if (cur.nexts[path] == null) {
					cur.nexts[path] = new Node();
				}
				// 往下跳！
				cur = cur.nexts[path];
				cur.pass++;
			}
			cur.end++;
		}

		// 在该结构里，删掉str，一次
		public void erase(String str) {
			if (countWordsEqualTo(str) != 0) {
				Node cur = root;
				cur.pass--;
				for (int i = 0; i < str.length(); i++) {
					int path = str.charAt(i) - 'a';
					Node next = cur.nexts[path];
					if (next.pass == 1) {
						cur.nexts[path] = null;
						return;
					} else {
						cur = cur.nexts[path];
					}
					cur.pass--;
				}
				cur.end--;
			}
		}

		// 给你一个字符串，str，查一下str出现了几次
		public int countWordsEqualTo(String str) {
			Node cur = root;
			for (int i = 0; i < str.length(); i++) {
				int path = str.charAt(i) - 'a';
				if (cur.nexts[path] == null) {
					return 0;
				}
				// 往下跳！
				cur = cur.nexts[path];
			}
			return cur.end;
		}

		// 给你一个字符串，str，查一下有多少个字符串以str开头
		public int countWordsStartingWith(String str) {
			Node cur = root;
			for (int i = 0; i < str.length(); i++) {
				int path = str.charAt(i) - 'a';
				if (cur.nexts[path] == null) {
					return 0;
				}
				// 往下跳！
				cur = cur.nexts[path];
			}
			return cur.pass;
		}
	}

//	public static void main(String[] args) {
//		char cur = 'z';
//		int path = cur - 'a';
//		System.out.println(path);
//	}

}
