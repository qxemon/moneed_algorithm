package Gold;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ16236_아기상어 {

    private static int N;
    private static int[][] map;
    private static boolean[][] visited;
    private static BabyShark shark;
    private static PriorityQueue<Fish> pq;

    //상좌우하
    private static int[] dr = {-1, 0, 0, 1};
    private static int[] dc = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("JAVA/BOJ/input/BOJ16236.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        pq = new PriorityQueue<>();
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    shark = new BabyShark(i, j, 2, 0, 0);
                    map[i][j] = 0;
                }
            }
        } // end of input



        while (true) {

            pq.clear();
            bfs(shark);
            if(pq.isEmpty()) {
                break;
            }
            Fish f = pq.poll(); // 다음 잡아먹을 물고기
            updateShark(f);
            map[f.r][f.c] = 0;


        }

        System.out.println(shark.time);

    }

    public static void print(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("=====================");
    }


    public static void bfs(BabyShark s) {
        Queue<int[]> queue = new LinkedList<>();
        visited = new boolean[N][N];
        visited[s.r][s.c] = true;
        queue.offer(new int[]{s.r, s.c});
        int time = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = cur[0] + dr[d];
                    int nc = cur[1] + dc[d];
                    if(inRange(nr, nc) && !visited[nr][nc]){

                        if(map[nr][nc] <= s.size){ // 이동 가능
                            queue.offer(new int[]{nr, nc});
                            visited[nr][nc] = true;
                            if(map[nr][nc] != 0 && map[nr][nc] < s.size){
                                pq.add(new Fish(nr,nc,time+1));
                            }
                        }
                    }
                }
            }
            time++;
        }
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }


    public static void updateShark(Fish f) {
        shark.fishEaten++;
        if (shark.fishEaten == shark.size) {
            shark.size++;
            shark.fishEaten = 0;
        }
        shark.r = f.r;
        shark.c = f.c;
        shark.time += f.dist;


    }

    public static class Fish implements Comparable<Fish> {
        int r, c;
        int dist;

        public Fish(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }


        @Override
        public int compareTo(Fish o) {
            if(this.dist == o.dist) {
                if(this.r == o.r){
                    return this.c - o.c;
                }
                return this.r - o.r;
            }
            return this.dist - o.dist;
        }

        @Override
        public String toString() {
            return "Fish{" +
                    "r=" + r +
                    ", c=" + c +
                    ", dist=" + dist +
                    '}';
        }
    }

    public static class BabyShark {
        int r, c;
        int size;
        int fishEaten;
        int time;

        public BabyShark(int r, int c, int size, int fishEaten, int time) {
            this.r = r;
            this.c = c;
            this.size = size;
            this.fishEaten = fishEaten;
            this.time = time;
        }
    }
}
