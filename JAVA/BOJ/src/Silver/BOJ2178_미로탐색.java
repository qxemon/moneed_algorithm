package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ2178_미로탐색 {
    static int N, M, ans;
    static int[][] map;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    private static boolean search;
    private static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visit = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = s.charAt(j);
                map[i][j] = c - '0';
            }
        } // end input

        ans = 1; // 시작 칸도 세기 때문에
        bfs(0, 0);
        System.out.println(ans);


    }

    static void bfs(int starti, int startj) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(starti, startj));
        visit[starti][startj] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- != 0) {
                Point now = queue.poll();
                if(now.i == N-1 && now.j == M-1){
                    return;
                }
                for (int i = 0; i < 4; i++) {
                    int ni = now.i + di[i];
                    int nj = now.j + dj[i];

                    if(ni>=0 && ni< N && nj >=0 && nj < M && map[ni][nj] == 1 && !visit[ni][nj]){
                        queue.offer(new Point(ni, nj));
                        visit[ni][nj] = true;
                    }
                }
            } // 같은 너비 탐색 종료
            ans++;
        }

    }

    static class Point {
        int i, j;

        Point(int i, int j) {
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
