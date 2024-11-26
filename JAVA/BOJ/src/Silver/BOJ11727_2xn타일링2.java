package Silver;

import java.util.Scanner;

public class BOJ11727_2xn타일링2 {

    private static int[] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = 3;
        for (int d = 2; d < n; d++) {
            dp[d] = ( dp[d-1] + (2 * dp[d-2])) % 10007;
        }

        System.out.println(dp[n-1]);
    }
}
