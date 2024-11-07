package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ9095_123더하기 {

    private static int T, N;
    private static int[] set = {1,2,3};
    private static int ans;

    private static int[] table;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        table = new int[11];
        dp();

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            ans = 0;
            //1. 중복조합
//            comb(0);
            //2. dp
            ans = table[N-1];
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    public static void comb(int num) {
        if(num == N){
            ans++;
            return;
        }


        for (int i = 0; i < 3; i++) {
            if(num + set[i] <= N){
                comb(num + set[i]);
            }
        }
    }

    public static void dp() {
        table[0] = 1;
        table[1] = 2;
        table[2] = 4;

        for (int i = 3; i < 11; i++) {
            table[i] = table[i - 1] + table[i-2] + table[i-3];
        }
    }



}
