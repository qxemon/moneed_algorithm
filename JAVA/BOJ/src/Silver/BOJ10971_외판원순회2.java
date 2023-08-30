package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10971_외판원순회2 {
    static int N;

    static int[][] adjMatrix;
    static int[] result;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        adjMatrix = new int[N][N];
        result = new int[N+1];
        visit = new boolean[N+1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                adjMatrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        result[0] = result[N] = 1;
        visit[1] = true;
        min = Integer.MAX_VALUE;

        perm(1);

        System.out.println(min);

    }
    static int min;
    private static void perm(int idx) {
        if(idx == N){
//            System.out.println(Arrays.toString(result));

            
            int sum = 0;
            for (int i = 0; i < N; i++) {
                if(adjMatrix[result[i]-1][result[i+1]-1] == 0){ // 돌아가는 길이 없는거야
                    return;
                }
                sum += adjMatrix[result[i]-1][result[i+1]-1];
            }
            min = Math.min(sum, min);

            return;
        }

        for (int i = 2; i < N+1; i++) {
            if(visit[i]) continue;
            result[idx] = i;
            visit[i] = true;
            perm(idx+1);
            visit[i] = false;
        }
    }


}
