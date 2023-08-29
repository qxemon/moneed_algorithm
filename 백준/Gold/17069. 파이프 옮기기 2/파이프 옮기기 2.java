import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static long[][][] memo;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        map = new int[N + 1][N + 1];



        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        if (map[N][N] == 1) {
            System.out.println(0);
            return;
        }

        memo = new long[N + 1][N + 1][3]; //0: 가로로 오는 것 1: 대각선 오는 것, 2: 세로로 오는 것 개수 저장할거임

        memo[1][2][0] = 1;// 초기화


        for (int i = 1; i <= N; i++) {
            for (int j = 3; j <= N; j++) {
                if (map[i][j] == 1)
                    continue;

                memo[i][j][0] = memo[i][j - 1][0] + memo[i][j - 1][1]; // 가로 처리
                if (i == 1) continue;
                memo[i][j][2] = memo[i - 1][j][1] + memo[i - 1][j][2]; //세로 처리

                if (map[i - 1][j] == 1 || map[i][j - 1] == 1) continue;

                memo[i][j][1] = memo[i - 1][j - 1][0] + memo[i - 1][j - 1][1] + memo[i - 1][j - 1][2];



        }
    }

     long ans = memo[N][N][0] + memo[N][N][1] + memo[N][N][2];

        System.out.println(ans);


}

}