package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9465_스티커 {

    public static int[][] stickers;
    public static int[][] dp;
    public static int len;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < TC; tc++) {
            len = Integer.parseInt(br.readLine());
            stickers = new int[2][len];
            dp = new int[3][len];
            for (int i = 0; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < len; j++) {
                    stickers[i][j] = Integer.parseInt(st.nextToken());
                }
            } // end of input

            sb.append(solve()).append("\n");

        }

        System.out.println(sb);
    }

    public static int solve(){

        dp[0][0] = stickers[0][0];
        dp[1][0] = stickers[1][0];

        for (int i = 1; i < len; i++){
            // 1. 1번째 행 스티커를 뗀다. -> 2,3 중 최댓값과 더한다.
            dp[0][i] = Math.max(stickers[0][i] + dp[1][i-1] , stickers[0][i] + dp[2][i-1]);
            // 2. 2번째 행 스티커를 뗀다. -> 1,3 중 최댓값과 더한다.
            dp[1][i] = Math.max(stickers[1][i] + dp[0][i-1], stickers[1][i] + dp[2][i-1]);
            // 3. 아무것도 떼지 않는다. -> 이전 값 중 최댓값으로 갱신한다.
            dp[2][i] = Math.max(dp[0][i-1], Math.max(dp[1][i-1] , dp[2][i-1]));
        }

        return Math.max(dp[0][len-1], Math.max(dp[1][len-1], dp[2][len-1]));

    }
}
