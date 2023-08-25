import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA1767_프로세서연결하기 {

    static ArrayList<Point> cores;
    static int ans;
    static int N, cnt; // cell 크기 NxN
    static int[][] map, copy;
    static int coreCnt;

    static int[] di = {-1,1,0,0,0};
    static int[] dj = {0,0,-1,1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int TC = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= TC; tc++) {

            N = Integer.parseInt(br.readLine().trim());
            map = new int[N][N];
            cores = new ArrayList<>();
            cnt = 0; // core의 개수
            ans = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] == 1){
                        cores.add(new Point(i,j));
                        cnt++;
                    }
                }
            } // end input
//            print(map);
//            System.out.println("==============");

            coreCnt = Integer.MIN_VALUE;
            dfs(0, map, 0); // ArrayList 인덱스 0 부터




            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb.toString());
    }

    static void dfs(int idx, int[][] origin, int use){
        if(idx == cnt){
//            System.out.println("use:"+use);
//            print(origin);
//            System.out.println("--------------------");

            if(coreCnt < use){
                coreCnt = use;
                ans = Integer.MAX_VALUE;
                int len = count(origin);
                ans = Math.min(ans, len);
            }
            else if(coreCnt == use){
                int len = count(origin);
                ans = Math.min(ans, len);
            }

            return;
        }

        int[][] copy = deepcopy(origin);

        if(cores.get(idx).i == 0 || cores.get(idx).i == N-1 || cores.get(idx).j == 0 || cores.get(idx).j == N-1){
            dfs(idx+1, copy, use+1);
        }
        else{
            for (int d = 0; d < 5; d++) {
//                print(copy);
//                System.out.println("-----------------");
                List<Point> temp = new ArrayList<>(); // 변경이 일어나는 좌표를 저장할 리스트
                if(d == 4){ // 전선 연결 안하는 경우
                    dfs(idx+1, copy, use);
                }
                else if(draw(temp, cores.get(idx).i,cores.get(idx).j,d,copy)) {
                    dfs(idx + 1, copy, use+1);
                }
                //되돌리자....
//                copy = deepcopy(origin); // 그냥 냅다 되돌리는 방법
                for (int i = 0; i < temp.size(); i++) { // 바뀐 부분만 되돌리는 방법
                    Point p = temp.get(i);
                    copy[p.i][p.j] = 0;
                }
//                print(copy);
//                System.out.println("====================");

            }
        }


    }

    static boolean draw(List<Point> list, int si, int sj, int dir, int[][] copy){
        int ni = si + di[dir];
        int nj = sj + dj[dir];
        boolean canDraw = true;
        while(true){
            if(ni<0 || ni>=N || nj<0 || nj>=N) break;
            if(copy[ni][nj] == 1 || copy[ni][nj] == 7) {
                canDraw = false;
                return canDraw;
            }
            copy[ni][nj] = 7; // 벽을 만나거나 뛰쳐나가지 않으면 칠하기
            list.add(new Point(ni,nj));
            ni += di[dir];
            nj += dj[dir];
        }

        return canDraw;
    }



    static int count(int[][] arr){
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(arr[i][j] == 7){
                    cnt++;
                }
            }
        }

        return cnt;
    }

    static int[][] deepcopy(int[][] origin){
        int[][] copy = new int[origin.length][origin[0].length];

        for(int i=0; i<origin.length; i++) {
            for(int j=0; j<origin[i].length; j++) {
                copy[i][j] = origin[i][j];
            }
        }
        return copy;
    }
    static class Point{
        int i, j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    static void print(int[][] arr){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
}
