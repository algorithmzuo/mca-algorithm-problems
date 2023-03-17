package class06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Code01_Test1 {

	// 题目描述，这个题是关于数组，求XXX东西
	// 
	// 数组长度N，N <= 10^5
	
	// 100
	// a b c ... 
	// 200
	// a b c ...
	// 100000
	// a b c ..
	
	public static int MAXN = 100010;
	
	public static int[] arr = new int[MAXN];
	
	public static int n;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			n = (int)in.nval;
			for(int i = 0 ; i < n; i++) {
				in.nextToken();
				arr[i] = (int)in.nval;
			}
			// n = 1000
			// arr 0..999 
			
			out.flush();
			
		}
	}

}
