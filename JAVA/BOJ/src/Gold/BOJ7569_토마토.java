package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7569_토마토 {
    private static int[][][] map;
    private static boolean[][][] visited;

    //전후좌우상하
    private static int[] dr ={-1,1,0,0,0,0};
    private static int[] dc = {0,0,-1,1,0,0};
    private static int[] dh = {0,0,0,0,-1,1};

    private static int R,C,H, time;

    private static Queue<Point> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[R][C][H];
        visited = new boolean[R][C][H];
        queue = new LinkedList<>();

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < R; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < C; k++) {
                    map[j][k][i] = Integer.parseInt(st.nextToken());
                    if(map[j][k][i] == 1){
                        queue.add(new Point(j,k,i));
                        visited[j][k][i] = true;
                    }
                }
            }
        } // end of input
        time = 0;

        bfs();

        if(check()){
            System.out.println(time-1);

        }
        else{
            System.out.println(-1);
        }
    }

    public static boolean check(){
        for (int h = 0; h < H; h++) {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if(map[i][j][h] == 0){
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public static void bfs(){
        while(!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point cur = queue.poll();
                for (int d = 0; d < 6; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];
                    int nh = cur.h + dh[d];
                    if(inRange(nr,nc,nh) && !visited[nr][nc][nh] && map[nr][nc][nh] == 0){
                        visited[nr][nc][nh] = true;
                        queue.add(new Point(nr,nc,nh));
                        map[nr][nc][nh] = 1;
                    }
                }
            }

            time++;
        }
    }

    public static boolean inRange(int r, int c, int h){
        return r>=0 && r<R && c>=0 && c<C && h>=0 && h<H;
    }

    public static class Point{
        int r, c, h;
        public Point(int r, int c, int h) {
            this.r = r;
            this.c = c;
            this.h = h;
        }
    }
}
