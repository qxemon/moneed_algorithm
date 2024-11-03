package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ11047_동전0 {

    static int N, K, ans;
    static int[] money;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        money = new int[N];
        for (int n = 0; n < N; n++) {
            money[n] = Integer.parseInt(br.readLine());
        } // end of input

        ans = 0;
        money = Arrays.stream(money)
                .boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();
        //내림차순 정렬

        for (int i = 0; i < N; i++) {
            while(K >= money[i]){
                K -= money[i];
                ans++;
            }
        }

        System.out.println(ans);



    }
}
