package class07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadLine {

	public static void main(String[] args) throws IOException {
		System.out.println("请输入文本");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		while ((line = in.readLine()) != null) {
			System.out.println(line);
		}
		in.close();
	}

}
