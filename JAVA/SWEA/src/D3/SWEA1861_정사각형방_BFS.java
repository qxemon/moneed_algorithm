package D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1861_정사각형방_BFS {
	static int N;
	static int[][] map;
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	static int roomNum;
	static int max;
	static int count;
	public static void main(String[] args) throws IOException{

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
			
			roomNum = Integer.MAX_VALUE;
			max = 0;
			for(int i=0; i<N;i++) {
				for(int j=0; j<N;j++) {					
					BFS(i,j);
				}
			}
			sb.append("#").append(tc).append(" ").append(roomNum).append(" ").append(max).append("\n");
		}
		System.out.println(sb.toString());
	}//end of main
	private static boolean[][] visited;
	public static void BFS(int i, int j) {
		Queue<Point> queue = new ArrayDeque<>();
		visited = new boolean[N][N];
		count = 1;
		
		visited[i][j] = true;
		queue.add(new Point(i,j));
		
		while(!queue.isEmpty()) {
			Point idx = queue.poll();
			int x = idx.x;
			int y = idx.y;
			//해야할일 알고리즘 -> 사방 탐색
			for(int d=0;d<4;d++) {
				int ni = x + di[d];
				int nj = y + dj[d];
				if(ni < map.length && ni >= 0 && nj < map[0].length && nj >= 0 && !visited[ni][nj]) {
					if(map[ni][nj] - map[x][y] == 1) {
						visited[ni][nj] = true;
						queue.offer(new Point(ni, nj));
						count++;
						
					}
				}
			}
			
			//최대값, 좌표 갱신
			
			if(count > max || ((count == max) && (roomNum > map[i][j]))) {
				roomNum = map[i][j];
				max = count;
			}
		}// end of while
		
		
		
	}
	
	static class Point{
		int x;
		int y;
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}
