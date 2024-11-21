package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ18111_마인크래프트 {

    private static int N, M, B;
    private static int[][] map;
    private static int max, min;
    private static int ansTime, ansHeight;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
                min = Math.min(min, map[i][j]);
            }
        } // end of input

        ansTime = Integer.MAX_VALUE;
        ansHeight = 0;

        bruteForce();

        System.out.println(ansTime+" "+ansHeight);
    }

    public static void bruteForce(){
        for (int i = min; i <=max ; i++) {
            int time =0;
            int blocks = B;

            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    int diff = map[n][m] - i;
                    if(diff > 0){
                        time += diff * 2;
                        blocks += diff;

                    }
                    else if (diff < 0){
                        time -= diff;
                        blocks += diff;
                    }
                }
            }

            if(blocks >= 0 && time <= ansTime){
                ansTime = time;
                ansHeight = i;
            }

        }
    }

}