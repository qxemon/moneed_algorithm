import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Point> homes;
	static ArrayList<Point> chickens;

	static Point[] remainChic;
	static boolean[] select;

	static int[][] map;
	static int N;
	static int M;
	
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 전역변수 초기화
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		homes = new ArrayList<>();
		chickens = new ArrayList<>();
		remainChic = new Point[M]; // 남은 치킨집 좌표 담을 배열
		result = Integer.MAX_VALUE;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					homes.add(new Point(i, j));
				} else if (map[i][j] == 2) {
					chickens.add(new Point(i, j));
				}
			}
		} // 입력 완료

		select = new boolean[chickens.size()];


		comb(0,0);
		System.out.println(result);
		
	}// end of main

	public static void comb(int idx, int cnt) {
		if (cnt == M) {
			int k = 0;
			//1. 남길 치킨집을 고른다.
			for (int i = 0; i < chickens.size(); i++) {
				if (select[i]) {
					remainChic[k++] = chickens.get(i);
				}
			}
			//2. 각 집에서 치킨집까지의 최소 거리를 구해 더한다.
			int chicRoad = 0;
			for(int i = 0; i<homes.size(); i++) {
				int min = Integer.MAX_VALUE;
				for(int j=0; j<remainChic.length; j++) {
					min = Math.min(min, Math.abs(homes.get(i).x - remainChic[j].x) + Math.abs(homes.get(i).y - remainChic[j].y));
				}
				chicRoad += min;
			}
			
			result = Math.min(result, chicRoad);
			
			return;
		}
		if (idx == chickens.size())
			return;

		select[idx] = true;
		comb(idx + 1, cnt + 1);
		select[idx] = false;
		comb(idx + 1, cnt);

	}

	public static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}