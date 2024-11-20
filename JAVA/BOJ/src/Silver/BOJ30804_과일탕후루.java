package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ30804_과일탕후루 {
    static int N;
    static int[] count;
    static int[] fruits;
    static int ans, kind;
    static int left, right;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        fruits = new int[N];
        count = new int[10];
        ans = 0;
        kind = 0;
        left = right = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            fruits[i] = Integer.parseInt(st.nextToken());
        } // end of input

        solve();

        System.out.println(ans);

    }

    public static void solve(){

        while(right < N){
            if(count[fruits[right]] == 0){
                kind++;
            }
            count[fruits[right]]++;
            right++;

            while(kind > 2){
                count[fruits[left]]--;
                if(count[fruits[left]] == 0){
                    kind--;
                }
                left++;
            }

            ans = Math.max(ans, right - left);
        }
    }

}
