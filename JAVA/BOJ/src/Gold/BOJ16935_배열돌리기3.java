package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16935_배열돌리기3 {
	static int N, M, R;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 행 개수
		M = Integer.parseInt(st.nextToken()); // 열 개수
		R = Integer.parseInt(st.nextToken()); // 연산 횟수
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 입력 완료

		st = new StringTokenizer(br.readLine());
		while (true) {
			if (!st.hasMoreTokens())
				break;
			int op = Integer.parseInt(st.nextToken());
			switch (op) {
			case 1:
				operation1();
				break;
			case 2:
				operation2();
				break;
			case 3:
				operation3();
				break;
			case 4:
				operation4();
				break;
			case 5:
				operation5();
				break;
			case 6:
				operation6();
				break;

			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());

	}// end of main

	private static void operation1() { // 배열 상하 반전
		int depth = map.length / 2;
		for (int d = 0; d < depth; d++) {
			for (int i = 0; i < map[0].length; i++) {
				int temp = map[d][i];
				map[d][i] = map[map.length - 1 - d][i]; // 상하 반전
				map[map.length - 1 - d][i] = temp;
			}
		}

	}

	private static void operation2() { // 배열 좌우 반전
		int depth = map[0].length / 2;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < depth; j++) {
				int temp = map[i][j];
				map[i][j] = map[i][map[0].length - 1 - j];
				map[i][map[0].length - 1 - j] = temp;
			}
		}
	}

	private static void operation3() { // 오른쪽 90도 회전
		int[][] newMap = new int[map[0].length][map.length];
		for (int i = 0; i < map[0].length; i++) {
			for (int j = 0; j < map.length; j++) {
				newMap[i][j] = map[map.length - 1 - j][i];
			}
		}
		map = newMap;
	}

	private static void operation4() { // 왼쪽 90도 회전
		int[][] newMap = new int[map[0].length][map.length];
		for (int i = 0; i < map[0].length; i++) {
			for (int j = 0; j < map.length; j++) {
				newMap[i][j] = map[j][map[0].length - 1 - i];
			}
		}
		map = newMap;
	}

	private static void operation5() {// 1->2->3->4->1
		int d1 = map.length / 2; // 상하 나누기
		int d2 = map[0].length / 2; // 좌우 나누기
		// 1사분면 4사분면 상하바꾸기
		for (int i = 0; i < d1; i++) {
			for (int j = d2; j < map[0].length; j++) {
				int temp = map[i][j];
				map[i][j] = map[i + d1][j];
				map[i + d1][j] = temp;
			}
		}
		// 1사분면, 2사분면 좌우 바꾸기
		for (int i = 0; i < d1; i++) {
			for (int j = 0; j < d2; j++) {
				int temp = map[i][j];
				map[i][j] = map[i][j + d2];
				map[i][j + d2] = temp;
			}
		}

		// 2사분면, 3사분면 상하 바꾸기
		for (int i = 0; i < d1; i++) {
			for (int j = 0; j < d2; j++) {
				int temp = map[i][j];
				map[i][j] = map[i + d1][j];
				map[i + d1][j] = temp;
			}
		}

	}

	private static void operation6() {// 1->4->3->2->1
		int d1 = map.length / 2; // 상하 나누기
		int d2 = map[0].length / 2; // 좌우 나누기
		// 1사분면 4사분면 상하바꾸기
		for (int i = 0; i < d1; i++) {
			for (int j = d2; j < map[0].length; j++) {
				int temp = map[i][j];
				map[i][j] = map[i + d1][j];
				map[i + d1][j] = temp;
			}
		}
		// 3사분면, 4사분면 좌우 바꾸기
		for (int i = d1; i < map.length; i++) {
			for (int j = 0; j < d2; j++) {
				int temp = map[i][j];
				map[i][j] = map[i][j + d2];
				map[i][j + d2] = temp;
			}
		}
		// 2사분면, 3사분면 상하 바꾸기
		for (int i = 0; i < d1; i++) {
			for (int j = 0; j < d2; j++) {
				int temp = map[i][j];
				map[i][j] = map[i + d1][j];
				map[i + d1][j] = temp;
			}
		}

	}
}
