package Silver;

import java.io.IOException;
import java.util.*;

public class BOJ11053_가장긴증가하는부분수열 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int[] arr = new int[N];
        int[] dp = new int[N];

        Arrays.fill(dp,1);

        for (int i = 0; i < N; i++) {
            int num = sc.nextInt();
            arr[i] = num;
        }

        for(int i =1; i<N; i++){
            for (int j = 0; j < i; j++) {
                if(arr[j] < arr[i]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }

        int ans = 0;

        for (int i = 0; i < N; i++) {
            ans = Math.max(ans, dp[i]);
        }

        System.out.println(ans);



    }
}
