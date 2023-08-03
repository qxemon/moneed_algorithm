package D5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1247 {
	static int[] X, Y, HOME, COMPANY;
	static boolean[] visited;
	static int[] result;
	static int MIN, SUM;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			int n = Integer.parseInt(br.readLine());
			X = new int[n];
			Y = new int[n];
			visited = new boolean[n];
			result = new int[n];
			HOME = new int[2];
			COMPANY = new int[2];
			MIN = 999999999;

			// 좌표들
			st = new StringTokenizer(br.readLine());
			COMPANY[0] = Integer.parseInt(st.nextToken());
			COMPANY[1] = Integer.parseInt(st.nextToken());
			HOME[0] = Integer.parseInt(st.nextToken());
			HOME[1] = Integer.parseInt(st.nextToken());
			for (int i = 0; st.hasMoreTokens(); i++) {
				if (i % 2 == 0) { // 짝수면
					X[i / 2] = Integer.parseInt(st.nextToken());
				} else {
					Y[i / 2] = Integer.parseInt(st.nextToken());
				}
			}

			perm(0);
			System.out.println("#"+tc+" "+MIN);

		}
	}

	static void perm(int idx) {
		if (idx == X.length) {
			SUM = (Math.abs(COMPANY[0] - X[result[0]])) + (Math.abs(COMPANY[1] - Y[result[0]])); // 초기 값은 순열 첫째값과 회사와의
																									// 거리
			for (int i = 1; i < result.length; i++) {

				SUM += (Math.abs(X[result[i]] - X[result[i - 1]])) + (Math.abs(Y[result[i]] - Y[result[i - 1]]));
			}
			//마지막은 집과 순열 마지막값의 거리 더해줌
			SUM += (Math.abs(HOME[0] - X[result[result.length-1]])) + (Math.abs(HOME[1] - Y[result[result.length-1]]));
			MIN = Math.min(SUM, MIN);

			return;
		}

		for (int i = 0; i < X.length; i++) {
			if (!visited[i]) {
				result[idx] = i;
				visited[i] = true;
				perm(idx + 1);
				visited[i] = false;
			}

		}
	}

}
