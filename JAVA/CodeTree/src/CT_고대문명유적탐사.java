import java.util.*;
import java.io.*;

public class CT_고대문명유적탐사 {

	private static final int MAX_M_LENGTH = 305;

	private static int K, M;
	private static int[][] map;
	private static int[][] map_copy;
	private static int[][] max_map;

	private static Queue<Integer> relics = new ArrayDeque<Integer>();

	// 좌상 ~ 우하까지 순서대로
	private static int[] dr = { -1, -1, -1, 0, 0, 0, 1, 1, 1 };
	private static int[] dc = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };

	private static int[] di = { -1, 1, 0, 0 };
	private static int[] dj = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		K = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[5][5];
		map_copy = new int[5][5];
		max_map = new int[5][5];

		int[][] original = new int[3][3];
		int[][] copy = new int[3][3];

		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			relics.add(Integer.parseInt(st.nextToken()));
		}
		// end of input

		for (int k = 0; k < K; k++) {

			int ans = 0;
			max_map = new int[5][5];

			// 최대 이익이 되는 회전 좌표 찾고 회전, 초기 점수 구하기
			for (int cnt = 1; cnt <= 3; cnt++) {
				for (int sc = 1; sc < 4; sc++) {
					for (int sr = 1; sr < 4; sr++) {

						int d = 0;
						for (int m = 0; m < 3; m++) {
							for (int n = 0; n < 3; n++) {
								original[m][n] = map[sr + dr[d]][sc + dc[d]];
								d++;
							}
						}

						map_copy = rotate(sr, sc, original, copy, cnt);

						int score = bfs(map_copy);
						if (ans < score) {
							ans = score;
							copyArr(map_copy, max_map);
						}

					}
				}
			}

			if (isEmptyArr(max_map)) {
				break;
			}

			copyArr(max_map, map);
			while (true) {
				fill();
				int bonusScore = bfs(map);
				if (bonusScore == 0)
					break;
				ans += bonusScore;
			}

			System.out.print(ans + " ");
		}

	}

	/**
	 * 수집한 유적 공간을 다시 채우는 함수
	 */
	private static void fill() {
		// 열이작고 행이 큰 순서대로
		for (int j = 0; j < 5; j++) {
			for (int i = 4; i >= 0; i--) {
				if (map[i][j] == 0 && !relics.isEmpty()) {
					map[i][j] = relics.poll();
				}
			}
		}
	}

	/**
	 * 구할 수 있는 유적이 없어 시뮬레이션을 종료하는 함수
	 * @param a map 배열
	 * @return
	 */
	private static boolean isEmptyArr(int[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				if (a[i][j] != 0) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 범위 확인 함수
	 * @param i
	 * @param j
	 * @return
	 */
	private static boolean inRange(int i, int j) {
		return i >= 0 && i < 5 && j >= 0 && j < 5;
	}

	/**
	 * 탐색 함수 : 점수 계산 및 빈 공간 좌표 저장
	 * @param a
	 * @return
	 */
	private static int bfs(int[][] a) {
		int score = 0;
		boolean[][] visited = new boolean[5][5];

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (!visited[i][j]) {
					Queue<int[]> queue = new ArrayDeque<>();
					Queue<int[]> trace = new ArrayDeque<>();

					queue.offer(new int[] { i, j });
					trace.offer(new int[] { i, j });
					visited[i][j] = true;
					while (!queue.isEmpty()) {
						int[] cur = queue.poll();

						for (int d = 0; d < 4; d++) {
							int ni = cur[0] + di[d];
							int nj = cur[1] + dj[d];

							// 체크: 범위 안, 같은 숫자, 방문 x
							if (inRange(ni, nj) && a[cur[0]][cur[1]] == a[ni][nj] && !visited[ni][nj]) {
								queue.offer(new int[] { ni, nj });
								trace.offer(new int[] { ni, nj });
								visited[ni][nj] = true;
							}
						}
					}

					if (trace.size() >= 3) {
						score += trace.size();
						// 맵의 유물 삭제
						while (!trace.isEmpty()) {
							int[] t = trace.poll();
							a[t[0]][t[1]] = 0;
						}
					}

				}
			}
		}

		return score;
	}

	
	/**
	 * 배열 복사 함수
	 * @param a
	 * @param b
	 */
	private static void copyArr(int[][] a, int[][] b) {
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b.length; j++) {
				b[i][j] = a[i][j];
			}
		}
	}

	/**
	 * 디버그용 print 함수
	 * @param arr
	 */
	private static void print(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println("============================");
	}

	/**
	 * 회전 함수
	 * @param r   중심점 행
	 * @param c   중심점 열
	 * @param a   기존 33배열
	 * @param b   회전 33배열
	 * @param cnt 몇도 돌리는지
	 * @return
	 */
	public static int[][] rotate(int r, int c, int[][] a, int[][] b, int cnt) {
		int[][] arr = new int[5][5];
		// 복사
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				arr[i][j] = map[i][j];
			}
		}
		if (cnt == 1) {
			rotate90(a, b);
		} else if (cnt == 2) {
			rotate180(a, b);
		} else if (cnt == 3) {
			rotate270(a, b);
		}

		int m = 0;
		for (int k = r - 1; k < r + 2; k++) {
			int n = 0;
			for (int l = c - 1; l < c + 2; l++) {
				arr[k][l] = b[m][n];
				n++;
			}
			m++;
		}

		return arr;
	}

	private static void rotate270(int[][] a, int[][] b) {
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b.length; j++) {
				b[b.length - 1 - j][i] = a[i][j];
			}
		}
	}

	private static void rotate180(int[][] a, int[][] b) {
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b.length; j++) {
				b[i][j] = a[b.length - 1 - i][b.length - 1 - j];
			}
		}
	}

	private static void rotate90(int[][] a, int[][] b) {
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				b[i][j] = a[b.length - 1 - j][i];
			}
		}
	}

}
