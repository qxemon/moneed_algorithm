import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		// 스캐너는 순수하게 10진수 정수 입력받을 때만 씁니다.
		// 스캐너에 있는 nextInt 같은거 못함. 무조건 한줄 통으로 입력받음
//		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());

		for (int i = 0; i < t; i++) {
			String line = br.readLine();
			char[] input = line.toCharArray();
			int n = 0;
			int res = 0;
			for (char ch : input) {
				if (ch == 'O') {
					n++;
					res += n;
				} else {
					n = 0;
				}
			}

			System.out.println(res);
		}

	}

}