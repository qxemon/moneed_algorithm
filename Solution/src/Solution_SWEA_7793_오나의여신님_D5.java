import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_SWEA_7793_오나의여신님_D5 {
    private static int[] dr = {-1, 1, 0, 0}; // 상하좌우
    private static int[] dc = { 0, 0,-1, 1};
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	int TC = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= TC; testCase++) { // 10개 테스트케이스
        	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken()); // N 행, 2 ≤ R ≤ 50
            int M = Integer.parseInt(st.nextToken()); // M 열, 2 ≤ C ≤ 50
 
//          수연이S, 여신D, 돌X, 악마*, 평범한 지역.  S,D는 유일
            char[][] map = new char[N][]; 
            for (int i = 0; i < N; i++) {
            	map[i] = br.readLine().toCharArray();
			}
            Queue<Point> q = new LinkedList<Point>(); // 악마좌표, 수연좌표
            Point sPoint = null; // 수연의 위치
            for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == '*') { // 악마
						q.offer(new Point(i, j, true));
					} else if (map[i][j] == 'S') { // 수연
						sPoint = new Point(i, j, false);
					}
				}
			}
            q.offer(sPoint);
	        int result = -1;    
            int cnt = 0; // 몇초만에 갔는지
ex:         while(q.size() > 0) {
            	cnt++;
            	int size = q.size();
            	while(--size >= 0) {
            		Point point = q.poll();
            		int r = point.r;
            		int c = point.c;
            		boolean isDevil = point.isDevil;
            		for (int i = 0; i < dr.length; i++) {
						int nr = r + dr[i];
						int nc = c + dc[i];
						if (0<=nr && nr<N && 0<=nc && nc<M) { // 배열 범위 내 
							if (isDevil) { // 악마
								if (map[nr][nc]=='.' || map[nr][nc]=='S') { // 평범한지역. , 수연S
									map[nr][nc] = '*'; // 악마 방문처리 없어도 됨
									q.offer(new Point(nr, nc, true));
								}
							} else { // 수연
								if (map[nr][nc]=='D') { // 여신D
									result = cnt;
									break ex;
								} else if (map[nr][nc]=='.') { // 평범한지역.
									map[nr][nc] = 'S';
									q.offer(new Point(nr, nc, false));
								}
							}
						}
					}
            	}
            }
            sb.append("#").append(testCase).append(" ").append(result == -1 ? "GAME OVER" : cnt).append("\n");
        } // end of for testCase
        System.out.print(sb);
    } // end of main
    private static class Point {
    	private int r;
    	private int c;
    	private boolean isDevil;
		public Point(int r, int c, boolean isDevil) {
			this.r = r;
			this.c = c;
			this.isDevil = isDevil;
		}
    }
} // end of class