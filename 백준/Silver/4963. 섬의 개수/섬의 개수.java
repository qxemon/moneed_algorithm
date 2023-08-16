import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	static int w, h, map[][];
	static int[] di = { -1, -1, -1, 0, 1, 1, 1, 0 }; // 좌상, 상, 우상, 우, 우하, 하, 좌하, 좌
	static int[] dj = { -1, 0, 1, 1, 1, 0, -1, -1 };

	static int result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		while (true) {
			st = new StringTokenizer(br.readLine(), " ");
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			if (w == 0 && h == 0)
				break;

			map = new int[h][w];
			
			
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			result = 0;
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[0].length; j++) {
					if (map[i][j] == 1) {
						bfs(i,j);
					}
				}
			}


			sb.append(result).append("\n");
		}

		System.out.println(sb);
	}

	private static void bfs(int i, int j) {
		ArrayDeque<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(i,j));
		while(!queue.isEmpty()) {
			Point p = queue.poll();

			map[p.i][p.j] = 2; //현재 좌표 방문처리
			for(int d=0; d<8;d++) {
				int nexti = p.i + di[d];
				int nextj = p.j + dj[d];
				if(nexti>=0 && nexti<h && nextj >=0 && nextj < w) {
					if(map[nexti][nextj] == 1) {
						map[nexti][nextj] = 2; //유효한 다음 좌표 방문처리
						queue.offer(new Point(nexti, nextj));
					}
				}
			}

		}
		result++;
	}

	static class Point {
		int i, j;

		public Point(int x, int y) {
			this.i = x;
			this.j = y;
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