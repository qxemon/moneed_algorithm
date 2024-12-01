package Gold;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ19238_스타트택시 {

    private static int N, M;
    private static int[][] map;
    private static Taxi taxi;
    private static ArrayList<Point> start;
    private static ArrayList<Point> end;
    private static boolean deplete;
    private static boolean[] arrive;
    private static int[][] rec;

    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("JAVA/BOJ/input/BOJ19238.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int f = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];
        rec = new int[N+1][N+1];
        deplete = false;
        arrive = new boolean[M+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        taxi = new Taxi(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), f);

        start = new ArrayList<>();
        end = new ArrayList<>();
        start.add(new Point(0, 0));
        end.add(new Point(0, 0));
        arrive[0] = true;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int sr = Integer.parseInt(st.nextToken());
            int sc = Integer.parseInt(st.nextToken());
            int er = Integer.parseInt(st.nextToken());
            int ec = Integer.parseInt(st.nextToken());

            start.add(new Point(sr, sc));
            end.add(new Point(er, ec));
        } // end of input

        simulation();

        if(deplete){
            System.out.println(-1);
        }
        else {
            System.out.println(taxi.fuel);
        }

    }

    public static void simulation() {

        while(true) {

            // 모든 승객이 목적지에 도착했으면 시뮬레이션 종료
            if(finish()){
                break;
            }

            //아직 모든 승객이 도착하지 않았는데 연료가 없으면 시뮬레이션 종료
            if(taxi.fuel == 0){
                deplete = true;
                break;
            }

            int passenger = -1;
            int dist = -1;

            //1. 최단거리 승객 구하기
            // 택시로부터 모든 길의 거리를 기록함 -> rec배열
            recDist();


            List<int[]> candidates = new ArrayList<>();

            for (int m = 1; m <= M ; m++) {
                if(arrive[m]) continue;
                Point p = start.get(m);
                int distance = rec[p.r][p.c];
                if(distance != Integer.MAX_VALUE) {
                    candidates.add(new int[]{m, distance, p.r, p.c}); //번호, 거리, 행, 열
                }
            }

            candidates.sort((a,b) -> {
                if(a[1] != b[1]) return Integer.compare(a[1], b[1]); // 거리
                if(a[2] != b[2]) return Integer.compare(a[2], b[2]); // 행
                return Integer.compare(a[3], b[3]); //열 순 정렬
            });

            if(!candidates.isEmpty()){
                passenger = candidates.get(0)[0];
            }


            //최단거리 승객을 찾지 못함
            if(passenger == -1){
                deplete = true;
                break;
            }

            //택시 상태 갱신
            taxi.r = start.get(passenger).r;
            taxi.c = start.get(passenger).c;
            taxi.fuel -= candidates.get(0)[1];

            //2. 승객 이동
            dist = move(passenger);

            if(dist == -1){ // 이동하지 못함
                deplete = true;
                break;
            }

        }


    }


    public static boolean finish(){
        for (int i = 1; i <= M; i++) {
            if(!arrive[i]){
                return false;
            }
        }
        return true;
    }

    public static void recDist() {
        for (int[] row : rec) Arrays.fill(row, Integer.MAX_VALUE); // 초기화
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(taxi.r, taxi.c));
        rec[taxi.r][taxi.c] = 0;

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];
                if (inRange(nr, nc) && map[nr][nc] == 0 && rec[nr][nc] == Integer.MAX_VALUE) {
                    rec[nr][nc] = rec[now.r][now.c] + 1;
                    queue.add(new Point(nr, nc));
                }
            }
        }


    }

    public static int move(int p) {
        recDist(); // 택시로부터 모든 거리 재계산
        Point goal = end.get(p);
        int distance = rec[goal.r][goal.c];

        if(distance == Integer.MAX_VALUE) return -1;

        if(distance > taxi.fuel) return -1;

        taxi.r = goal.r;
        taxi.c = goal.c;
        taxi.fuel -= distance;
        taxi.fuel += distance * 2;
        arrive[p] = true;

        return distance;
    }

    public static boolean inRange(int r, int c) {
        return r > 0 && r <= N && c > 0 && c <= N;
    }


    public static class Point{
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static class Taxi{
        int r, c, fuel;

        public Taxi(int r, int c, int fuel) {
            this.r = r;
            this.c = c;
            this.fuel = fuel;
        }

        @Override
        public String toString() {
            return "Taxi{" +
                    "r=" + r +
                    ", c=" + c +
                    ", fuel=" + fuel +
                    '}';
        }
    }
}
