package Gold;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ17142_연구소3 {

    private static int N, M;

    private static int[][] map;

    private static ArrayList<Point> viruses;

    private static boolean[] chosen;

    private static int ans, time;

    private static int[] di = {-1,1,0,0};
    private static int[] dj = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("JAVA/BOJ/input/BOJ17142.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        ans = Integer.MAX_VALUE;
        viruses = new ArrayList<Point>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) viruses.add(new Point(i, j));
            }
        }

        chosen = new boolean[viruses.size()];

        simulation();

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    public static void simulation() {
        dfs(0,0);
    }

    public static int[][] bfs(List<Point> v){
        Queue<Point> queue = new LinkedList<Point>();
        boolean[][] visited = new boolean[N][N];
        int[][] res = new int[N][N];
        time = 0;

        for (int i = 0; i < N; i++) {
            Arrays.fill(res[i], -1);
        }

        for (int i = 0; i < v.size(); i++) {
            Point p = v.get(i);
            visited[p.r][p.c] = true;
            res[p.r][p.c] = 0;
            queue.add(p);
        }
        int count = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point p = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int ni = p.r + di[d];
                    int nj = p.c + dj[d];
                    if(inRange(ni,nj) && !visited[ni][nj] && (map[ni][nj] == 0 || map[ni][nj] == 2)){
                        //바이러스라면
                        if(map[ni][nj] == 2){
                            res[ni][nj] = -2;
                            visited[ni][nj] = true;
                            queue.add(new Point(ni, nj));
                        }
                        else if(map[ni][nj] == 0){
                            res[ni][nj] = count;
                            visited[ni][nj] = true;
                            queue.add(new Point(ni, nj));
                            if(time < count){
                                time = count;
                            }
                        }
                    }
                }
            }
            count++;
        }

        return res;
    }

    public static boolean inRange(int r, int c) {
        return r>=0 && r<N && c>=0 && c<N;
    }

    public static boolean allSearch(int[][] arr){
        //map 과 비교할것임
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(arr[i][j] == -1 && map[i][j] == 0) return false;
            }
        }
        return true;
    }

    public static void dfs(int index, int pick) {
        if(pick == M){
            // 뽑은 위치 선정
            ArrayList<Point> comb = new ArrayList<>();
            for (int i = 0; i < viruses.size(); i++) {
                if(chosen[i]) {
//                    System.out.print(i + " ");
                    comb.add(viruses.get(i));
                }
            }

//            System.out.println("==================");

            // 시간 계산 배열 -> time 갱신
            int[][] rec = bfs(comb);

            print(rec);
            System.out.println("time: " + time);
            // 모든 곳을 가는지 판단
            if(allSearch(rec)){
                //모두 전염시키면 최단시간 비교 -> 갱신
                ans = Math.min(ans, time);
            }
            //아니면 건너뛰기

            return;
        }

        for (int i = index; i < viruses.size(); i++) {
            chosen[i] = true;
            dfs(i + 1, pick + 1);
            chosen[i] = false;
        }


    }

    public static void print(int[][] arr) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("======================");
    }


    public static class Point{
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }
}
