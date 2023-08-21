import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, D;
	static int[][] map;
	static int[][] clone;
	static boolean select[];
	static int ans;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		clone = new int[N][M];
		select = new boolean[M];
		ans = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}//end input

		comb(0,0);
		
		System.out.println(ans);
	}//end main
	
	static void comb(int idx, int cnt) {
		if(cnt == 3) {
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[0].length; j++) {
					clone[i][j] = map[i][j]; //복사
				}
			}
			Queue<Point> queue = new ArrayDeque<>();
			int sum = 0;
			
			for (int i = 0; i < N; i++) { //페이즈
				int kill = 0;
				
				for (int j = 0; j < M; j++) {
					int x = -1, y = -1;
					int min = Integer.MAX_VALUE;
					if(select[j]) { // 궁수가 배치된 좌표 (N,j) 없앨 적 좌표 정하기(x, y) -> 사거리 D 내 가장 가까운 적, 가까운적이 많으면 가장 왼쪽;
						for (int k = N-1; k >= 0; k--) {
							for (int k2 = M-1; k2 >= 0 ; k2--) {
								if(clone[k][k2] == 1 && Math.abs(N-k)+Math.abs(j-k2) <= D) {
									if(min > Math.abs(N-k)+Math.abs(j-k2)) {
										min = Math.abs(N-k)+Math.abs(j-k2);
										x = k;
										y = k2;
									} 
									else if(min == Math.abs(N-k)+Math.abs(j-k2) && k2 < y) {
										min = Math.abs(N-k)+Math.abs(j-k2);
										x = k;
										y = k2;
									}
								}
							}
						}
					}
					
					
					
					if(x != -1) {
						queue.offer(new Point(x, y));
					}
					
				}//궁수 end

				while(!queue.isEmpty()) {
					Point now = queue.poll();
					if(clone[now.i][now.j] != 0) {
						kill++;
						clone[now.i][now.j] = 0;
					}
				}
				
				for (int r = N-1; r > 0; r--) {
					clone[r] = clone[r-1];
				}
				clone[0] = new int[M];
				
				
				sum += kill;
				
				
			}//end phase
			ans = Math.max(ans, sum);
			return;
		}
		if(idx == M) {
			return;
		}
		
		select[idx] = true;
		comb(idx+1, cnt+1);
		select[idx] = false;
		comb(idx+1, cnt);
	}
	
	static class Point{
		int i, j;
		Point(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
	
	
	static void print() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}