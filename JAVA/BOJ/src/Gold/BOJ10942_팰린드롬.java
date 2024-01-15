package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10942_팰린드롬 {

    static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N+1];
        dp = new boolean[N+1][N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < numbers.length; i++) {
            numbers[i] =Integer.parseInt(st.nextToken());
        }

        //여기서 DP 테이블 미리 만들어 두고 뒤에 M개의 질문은 쏙쏙 꺼내 쓰면 됨
        palindrome(numbers, N);

        int M = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            if(dp[S][E]) sb.append("1").append("\n");
            else{
                sb.append("0").append("\n");
            }

        }
        System.out.println(sb.toString());
    }

    static void palindrome(int[] arr, int n){
        // 길이가 1인 경우
        for (int i = 1; i < n + 1 ; i++) {
            dp[i][i] = true;
        }
        // 길이가 2인 경우
        for (int i = 1; i < n ; i++) {
            if(arr[i] == arr[i+1]) dp[i][i+1] = true;
        }

        //길이가 3 이상인 경우
        // 시작점+1 ~ 끝점-1 까지 팰린드롬 (미리 저장된 것임) 이고, 시작점 == 끝점 이면 팰린드롬
        for (int i = 2; i < n; i++) {
            for (int j = 1; j <= n-i ; j++) {
                if(dp[j+1][j+i-1] && arr[j] == arr[j+i]) dp[j][j+i] = true;
            }
        }
    }
}
