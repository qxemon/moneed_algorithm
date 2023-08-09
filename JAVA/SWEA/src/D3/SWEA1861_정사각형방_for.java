package D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1861_정사각형방_for {
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };
	static int N;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			} // 입력 완료

			int nexti, nextj, d = 0;

			int nowi, nowj, count, turn;
			int max = Integer.MIN_VALUE, roomNum = Integer.MAX_VALUE;

			// 탐색
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					count = 1;
					nowi = i;
					nowj = j;
					turn = 0;
					while (true) {
						//4방향 전부 돌았는데 못찾았다. -> 나가
						if(turn == 4) {
							break;
						}

						
						nexti = nowi + di[d];
						nextj = nowj + dj[d];
						if (nexti >= 0 && nexti < N && nextj >= 0 && nextj < N && map[nexti][nextj] - map[nowi][nowj] == 1) { 
							//범위 안이고 다음 행선지가 1차이 나는 곳이면
							nowi = nexti;
							nowj = nextj; // 이동

							count++;// 카운팅
							turn = 0;
						} else {
							d = (d + 1) % 4; // 방향전환
							//방향전환 카운팅
							turn++;
						}

					}//while end
					if(count == max) {
						if(roomNum > map[i][j]) { // 방 번호가 더 작으면
							roomNum = map[i][j];						
						}
						max = count;
					}
					if(count > max) {
						roomNum = map[i][j];
						max = count;
					}

				}//for j end
			}// for i end

			sb.append("#").append(tc).append(" ").append(roomNum).append(" ").append(max).append("\n");
		}//end of tc
		System.out.println(sb.toString());
	}// end of main
}// end of class
