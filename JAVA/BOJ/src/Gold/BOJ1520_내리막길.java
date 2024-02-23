package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1520_내리막길 {

    static int[][] map;
    static int m, n;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken()); // 세로
        n = Integer.parseInt(st.nextToken());

        map = new int[m][n];
        memo = new int[m][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                memo[i][j] = -1;
            }
        } // end of input

        System.out.println(dfs(0,0));


    }


    static int dfs(int x, int y){
        if(x == m-1 && y == n-1) {
            return 1;
        }

        //방문을 했다면
        if(memo[x][y] != -1){
            return memo[x][y];
        } else {
            memo[x][y] = 0;
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx >= 0 && nx < m && ny >= 0 && ny < n && map[x][y] > map[nx][ny]) {
                    memo[x][y] += dfs(nx, ny);
                }

            }

        }

        return memo[x][y];
    }
}
