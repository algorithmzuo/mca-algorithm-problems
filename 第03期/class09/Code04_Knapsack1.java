package class09;

// 背包问题
// 测试链接 : https://www.nowcoder.com/practice/fd55637d3f24484e96dad9e992d3f62e
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下所有代码，把主类名改成Main，可以直接通过
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Code04_Knapsack1 {

	public static int MAXN = 1010;
	public static int[] v = new int[MAXN];
	public static int[] w = new int[MAXN];
	public static int[][] dp = new int[MAXN][MAXN];
	public static int n, m;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			n = (int) in.nval;
			in.nextToken();
			m = (int) in.nval;
			for (int i = 0; i < n; i++) {
				in.nextToken();
				v[i] = (int) in.nval;
				in.nextToken();
				w[i] = (int) in.nval;
			}
			clear();
			int ans1 = f1(0, m);
			clear();
			int ans2 = f2(0, m);
			out.println(ans1);
			out.println(ans2 == -1 ? 0 : ans2);
			out.flush();
		}
	}

	public static void clear() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= m; j++) {
				dp[i][j] = -2;
			}
		}
	}

	public static int f1(int i, int j) {
		if (j < 0) {
			return -1;
		}
		if (i == n) {
			return 0;
		}
		if (dp[i][j] != -2) {
			return dp[i][j];
		}
		int p1 = f1(i + 1, j);
		int p2 = 0;
		int next2 = f1(i + 1, j - v[i]);
		if (next2 != -1) {
			p2 = w[i] + next2;
		}
		int ans = Math.max(p1, p2);
		dp[i][j] = ans;
		return ans;
	}

	public static int f2(int i, int j) {
		if (j < 0) {
			return -1;
		}
		if (i == n) {
			return j == 0 ? 0 : -1;
		}
		if (dp[i][j] != -2) {
			return dp[i][j];
		}
		int p1 = f2(i + 1, j);
		int p2 = -1;
		int next2 = f2(i + 1, j - v[i]);
		if (next2 != -1) {
			p2 = w[i] + next2;
		}
		int ans = Math.max(p1, p2);
		dp[i][j] = ans;
		return ans;
	}

}
