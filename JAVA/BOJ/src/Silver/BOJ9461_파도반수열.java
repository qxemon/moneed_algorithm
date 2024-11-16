package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9461_파도반수열 {
    static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        arr = new long[101];
        solve();

        for (int i = 0; i < TC; i++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(arr[n]).append("\n");
        }
        System.out.println(sb);
    }

    public static void solve() {
        arr[1]=1;
        arr[2]=1;
        arr[3]=1;
        arr[4]=2;
        arr[5]=2;
        for (int i = 6; i < 101; i++) {
            arr[i] = arr[i-1] + arr[i-5];
        }
    }
}