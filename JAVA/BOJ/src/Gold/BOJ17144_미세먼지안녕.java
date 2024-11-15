package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17144_미세먼지안녕 {
    static int R, C, T;
    static int[][] map, board;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static Point[] cleaner = new Point[2];

    static Queue<Point> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        board = new int[R][C];
        queue = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == -1 && map[i-1][j] == -1){
                    cleaner[1] = new Point(i, j);
                    cleaner[0] = new Point(i-1, j);
                }
                else if(map[i][j] > 0){
                    queue.add(new Point(i, j));
                }

            }
        } // end of input

        solve();

        int ans =0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j] > 0){
                    ans += map[i][j];
                }
            }
        }
        System.out.println(ans);

    }

    static void solve(){
        int time = 0;
        while(true){
            if(time >= T) break;
            int size = queue.size();
            Arrays.fill(board, 0);
            //1. 미세먼지 확산
            spread();
            //2. 공기청정기 작동
            cleaning();
            //3. queue에 다시 미세먼지 넣기

            copy(map,board);
            addQueue();

            time++;
        }
    }

    static void addQueue(){
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j] > 0){
                    queue.add(new Point(i, j));
                }
            }
        }
    }

    static void copy(int[][] a, int[][] b){
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                a[i][j] = b[i][j];
            }
        }
    }

    static void print(int[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("=================");
    }


    static void spread(){
        while(!queue.isEmpty()){
            Point p = queue.poll();
            int count = 0;
            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];
                if(inRange(nr,nc) && !meetCleaner(nr,nc)){
                    board[nr][nc] += map[p.r][p.c] / 5;
                    count++;
                }
            }
            board[p.r][p.c] += map[p.r][p.c] - ((map[p.r][p.c] / 5) * count);
        }
    }

    static boolean meetCleaner(int i, int j){
        return (i == cleaner[0].r && j == cleaner[0].c)|| (i == cleaner[1].r && j == cleaner[1].c);
    }

    static boolean inRange(int i, int j){
        return i >= 0 && i < R && j >= 0 && j < C;
    }
    static void cleaning(){
        //상단부
        leftShift();
        //하단부
        rightShift();
    }

    static void leftShift(){
        int prev= 0;
        int temp;
        int sr = cleaner[0].r;
        int sc = cleaner[0].c;
        for(int i=1; i < C; i++){
            temp = board[sr][i];
            board[sr][i] = prev;
            prev = temp;
        }
        for (int i = sr-1; i >=0 ; i--) {
            temp = board[i][C-1];
            board[i][C-1] = prev;
            prev = temp;
        }
        for(int i= C-2; i>=0; i--){
            temp = board[0][i];
            board[0][i] = prev;
            prev = temp;
        }
        for (int i = 1; i < sr; i++) {
            temp = board[i][0];
            board[i][0] = prev;
            prev = temp;
        }
        board[sr][sc] = -1;
    }
    static void rightShift(){
        //하단부
        int prev = 0;
        int temp;

        int sr = cleaner[1].r;
        int sc = cleaner[1].c;

        for (int i = 1; i < C; i++) {
            temp = board[sr][i];
            board[sr][i] = prev;
            prev = temp;
        }
        for (int i = sr+1; i < R; i++) {
            temp = board[i][C-1];
            board[i][C-1] = prev;
            prev = temp;
        }
        for (int i = C-2; i >= 0 ; i--) {
            temp = board[R-1][i];
            board[R-1][i] = prev;
            prev = temp;
        }
        for (int i = R-2; i > sr  ; i--) {
            temp = board[i][0];
            board[i][0] = prev;
            prev = temp;
        }
        board[sr][sc] = -1;
    }


    static class Point {
        int r,c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
