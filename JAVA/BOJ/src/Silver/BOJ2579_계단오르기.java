package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2579_계단오르기 {

    static int[] stairs;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n  = Integer.parseInt(br.readLine());
        stairs = new int[1001];
        dp = new int[1001];

        for (int i = 1; i <= n; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        } // end of input

        dp[1] = stairs[1];
        if(n > 1){
            dp[2] = stairs[1] + stairs[2];
        }

        for (int i = 3; i <= n ; i++) {
            dp[i] = Math.max(dp[i-2] + stairs[i], dp[i-3] + stairs[i-1] + stairs[i]);
        }

        System.out.println(dp[n]);

    }
}
