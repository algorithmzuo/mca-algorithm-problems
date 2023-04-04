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

public class Code04_Knapsack2 {

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
			int ans1 = f1();
			int ans2 = f2();
			out.println(ans1);
			out.println(ans2 == -1 ? 0 : ans2);
			out.flush();
		}
	}

	public static int f1() {
		for (int j = 0; j <= m; j++) {
			dp[n][j] = 0;
		}
		for (int i = n - 1; i >= 0; i--) {
			for (int j = 0; j <= m; j++) {
				dp[i][j] = dp[i + 1][j];
				if (j - v[i] >= 0) {
					dp[i][j] = Math.max(dp[i][j], w[i] + dp[i + 1][j - v[i]]);
				}
			}
		}
		return dp[0][m];
	}

	public static int f2() {
		dp[n][0] = 0;
		for (int j = 1; j <= m; j++) {
			dp[n][j] = -1;
		}
		for (int i = n - 1; i >= 0; i--) {
			for (int j = 0; j <= m; j++) {
				dp[i][j] = dp[i + 1][j];
				if (j - v[i] >= 0 && dp[i + 1][j - v[i]] != -1) {
					dp[i][j] = Math.max(dp[i][j], w[i] + dp[i + 1][j - v[i]]);
				}
			}
		}
		return dp[0][m];
	}

}
