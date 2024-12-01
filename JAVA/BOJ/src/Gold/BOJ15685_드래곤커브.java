package Gold;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ15685_드래곤커브 {

    private static final int MAX = 101;

    private static int N, x,y,d,g;

    private static int[][] map;
    private static ArrayList<Integer> dirArr;

    //우상좌하
    private static final int[] dx = {1,0,-1,0};
    private static final int[] dy = {0,-1,0,1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("JAVA/BOJ/input/BOJ15685.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[MAX][MAX]; // 0 <= x,y <= 100

        dirArr = new ArrayList<>();// 드래곤 커브 방향들을 저장할 리스트

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            dirArr.clear();

            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());

            map[y][x] = 1;
            y += dy[d];
            x += dx[d];
            map[y][x] = 1;

            dirArr.add(d);


            for (int j = 0; j < g; j++) {
                makeCurve();
            }

        }

        System.out.println(count());

    }

    public static void print() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("=============");
    }

    public static void makeCurve(){
        int size = dirArr.size();

        for (int s = size-1; s >= 0 ; s--) {
            //다음 방향 정하기
            int nd = (  dirArr.get(s) + 1 ) % 4;
            //맵 칠하기
            int ny = y + dy[nd];
            int nx = x + dx[nd];

            if(inRange(nx, ny)) {
                map[ny][nx] = 1;
                dirArr.add(nd);
                x = nx;
                y = ny;
            }

        }
    }

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < MAX && y >= 0 && y < MAX;
    }

    public static int count(){
        int count = 0;

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if(map[i][j] == 1 && map[i][j+1] == 1 && map[i+1][j] == 1 && map[i+1][j+1] == 1){
                    count++;
                }
            }
        }
        return count;
    }
}
