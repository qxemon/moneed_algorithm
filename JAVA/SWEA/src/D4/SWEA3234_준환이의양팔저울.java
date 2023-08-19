package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA3234_준환이의양팔저울 {
    static int[] weight;
    static int N, ans, whole;
    static boolean used[];
    static int[] factorial ={1,1,2,6,24,120,720,5040,40320,362880};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            N = Integer.parseInt(br.readLine());
            weight = new int[N];
            used = new boolean[N];
            ans = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                weight[i] = Integer.parseInt(st.nextToken());
                whole += weight[i];
            }

            func(0, 0, 0);


            System.out.println("#"+tc+" "+ans);
        }//end tc
    }//end main

    static void func(int cnt, int Left, int Right) {
        if (cnt == N) {
            ans++;
            return;
        }




        if(Left > whole - Left){
            int res = factorial[N-cnt] * (int)Math.pow(2,N-cnt);
            ans += res;
            return;
        }



        for (int i = 0; i < N; i++) {
            if (used[i]) continue;
            used[i] = true;
            func(cnt + 1, Left + weight[i], Right);
            if (Left >= Right + weight[i]) {
                func(cnt + 1, Left, Right + weight[i]);
            }
            used[i] = false;
        }
    }

}//end class
