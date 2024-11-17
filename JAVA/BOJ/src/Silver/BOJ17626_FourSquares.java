package Silver;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ17626_FourSquares {

    static int[] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        //초기 세팅
        dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        System.out.println(solve(n));

    }

    public static int solve(int n){

        for (int i = 1; i <= n; i++) {
            int j = 1;
            while(j*j <= i){
                dp[i] = Math.min(dp[i], dp[i-j*j] + 1);
                j++;
            }
        }

        return dp[n];
    }
}
