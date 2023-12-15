import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class SWEA2117_홈방범서비스 {

    static int N, M;
    static int[][] map;
    static int ans;

    static int maxK;

    static boolean visited[][];
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 도시 크기 N
            M = Integer.parseInt(st.nextToken()); // 하나의 집이 지불할 수 있는 비용 M
            map = new int[N][N];

            int maxPay = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1) {
                        maxPay += M;
                    }
                }
            } // end input


            // 1. 최대 서비스를 찾을거임 손해를 보면 안되니까 존재하는 집 * 지불 비용 M 이 서비스 운영비용보다 작은 가장 큰 K를 찾을거임
            maxK = 1;
            while (true) {
                maxK++;
                if ((maxK * maxK + (maxK - 1) * (maxK - 1)) > maxPay) {
                    maxK--;
                    break;
                }
            }


            // 2. 이제 카메라 설치해가면서 값이랑 집 수를 구할거야  손해를 안보는 것중 가장 큰 집 수를 구할것임

            ans  = Integer.MIN_VALUE;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    visited = new boolean[N][N];
                    bfs(i, j);
                }
            }


            System.out.println("#" + tc + " " + ans);
        }
    }

    static void bfs(int si, int sj) {
        ArrayDeque<Point> queue = new ArrayDeque<Point>();
        visited[si][sj] = true;
        queue.add(new Point(si, sj));
        int service = 1;
        int home = 0;

        if(map[si][sj] == 1) home++;

        while (service < maxK) {
            service++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point now = queue.poll();

                for (int d = 0; d < 4; d++) {
                    int ni = now.i + di[d];
                    int nj = now.j + dj[d];

                    if(ni>= 0 && ni<N && nj>=0 && nj<N && !visited[ni][nj]){
                        visited[ni][nj] = true;
                        queue.add(new Point(ni,nj));

                        if(map[ni][nj] == 1){
                            home++;
                        }
                    }

                }

                if((home * M) - (service * service + (service - 1) * (service - 1)) > 0){
                    if(ans < home){
                        ans = home;
                    }
                }

            }

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
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

}