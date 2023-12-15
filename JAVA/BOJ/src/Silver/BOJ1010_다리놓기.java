package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1010_다리놓기 {

    static int N, R;
    static long ans;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());


        for (int i = 0; i < TC; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            dp = new long[N+1][R+1];
            ans = comb(N, R);

            System.out.println(ans);

        }
    }

    static long comb(int n, int r){
        if(dp[n][r] > 0) return dp[n][r];
        if(n==r || r==0) return 1;
        else return dp[n][r] = comb(n-1,r-1) + comb(n-1,r);
    }


}



//DP
