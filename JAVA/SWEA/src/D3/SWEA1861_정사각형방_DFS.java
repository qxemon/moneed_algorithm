package D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1861_정사각형방_DFS {
	static int[][] map;
	static int N;
	static int[] di = { -1, 1, 0, 0 };// 상하좌우
	static int[] dj = { 0, 0, -1, 1 };
	static int count;
	static int max;
	static int mi, mj;
	static int roomNum;
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
			
			max = Integer.MIN_VALUE;
			mi = 0;
			mj = 0;
			roomNum = Integer.MAX_VALUE;
			
			for(int i=0;i<N;i++) {
				for(int j =0; j<N;j++) {
					
					count = 1;
					DFS(i, j);
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
					
				}
			}


			sb.append("#").append(tc).append(" ").append(roomNum).append(" ").append(max).append("\n");
		}

		System.out.println(sb.toString());
	}
	

	
	public static void DFS(int i, int j) {

		// next 확인 먼저
		for (int d = 0; d < 4; d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if (ni < map.length && ni >= 0 && nj < map[0].length && nj >= 0) {
				// 갱신 좌표 호출
				if(map[ni][nj] - map[i][j] == 1){					
					count++;
					DFS(ni, nj);
				}
				
			}
		}


	}

	
	
}
