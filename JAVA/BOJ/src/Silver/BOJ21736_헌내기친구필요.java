package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ21736_헌내기친구필요 {

    static int N, M;
    static char[][] map;
    static boolean[][] visited;
    static int ans;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M];
        ans = 0;

        Point p = null;

        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = line[j];
                if (map[i][j] == 'I') {
                    p = new Point(i, j);
                }
            }
        }

        bfs(p);
        if(ans == 0) System.out.println("TT");
        else System.out.println(ans);


    }

    public static void bfs(Point p) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(p);
        visited[p.r][p.c] = true;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if (inRange(nr, nc) && !visited[nr][nc] && map[nr][nc] != 'X') {
                    if (map[nr][nc] == 'P') {
                        ans++;
                    }
                    queue.offer(new Point(nr, nc));
                    visited[nr][nc] = true;

                }
            }
        }

    }

    public static boolean inRange(int i, int j) {
        return i >= 0 && i < N && j >= 0 && j < M;
    }

    public static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
