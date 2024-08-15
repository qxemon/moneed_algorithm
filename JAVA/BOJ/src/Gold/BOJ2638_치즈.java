package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2638_치즈 {
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    static int[][] map;
    static int[][] visited;
    static int N, M, ans;

    static Queue<Pair> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        } // end of input
        ans = 0;

        bfs(0,0);


        System.out.println(ans);

    }


    static void bfs(int si,int sj){
        while(true){

            visited = new int[N][M];
            //공기면 탐색
            airBfs(si, sj);
            ans++;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    //치즈 녹이기
                    if(visited[i][j] >= 2){
                        map[i][j] = 0;
                    }
                }
            }

            int count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(map[i][j] == 0){
                        count++;
                    }
                }
            }
            if(count == (N*M)) break;

        }

    }

    static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("++++++++++++++++++");
    }


    static void airBfs(int si, int sj) {
        Queue<Pair> q = new ArrayDeque<>();
        q.offer(new Pair(si, sj));
        visited[si][sj]++;

        while (!q.isEmpty()) {
            Pair p = q.poll();
            for (int d = 0; d < 4; d++) {
                int ni = p.x + di[d];
                int nj = p.y + dj[d];
                if (ni >= 0 && ni < N && nj >= 0 && nj < M) {
                    if (map[ni][nj] == 0 && visited[ni][nj] == 0) {
                        q.offer(new Pair(ni, nj));
                        visited[ni][nj] = 1;

                    } else if (map[ni][nj] == 1) {
                        visited[ni][nj]++;
                    }


                }

            }
        }

    }


    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
