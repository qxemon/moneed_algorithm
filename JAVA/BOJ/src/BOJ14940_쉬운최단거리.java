import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14940_쉬운최단거리 {
    static int N, M;
    static int[][] map;
    static int[][] trace;
    static int sr, sc;
    static boolean[][] visited;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("JAVA/BOJ/input/BOJ14940Input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        trace = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    sr = i;
                    sc = j;
                }
                else if(map[i][j] == 1){
                    trace[i][j] = -1;
                }
            }
        } // end of input

        bfs(sr, sc);

        print(trace);

    }


    public static void bfs(int sr, int sc) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(sr, sc, 1));
        visited[sr][sc] = true;
        while (!queue.isEmpty()) {
            Point p = queue.poll();

            for (int d = 0; d < 4; d++) {
                int ni = p.r + dr[d];
                int nj = p.c + dc[d];

                if (inRange(ni, nj) && !visited[ni][nj] && map[ni][nj] == 1) {
                    trace[ni][nj] = p.step;
                    visited[ni][nj] = true;
                    queue.offer(new Point(ni, nj, p.step + 1));
                }
            }
        }

    }

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public static class Point {
        int r, c, step;

        public Point(int r, int c, int step) {
            this.r = r;
            this.c = c;
            this.step = step;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    ", step=" + step +
                    '}';
        }
    }

    public static void print(int[][] a) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }
}
