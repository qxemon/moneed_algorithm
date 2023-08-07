import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[] S;
    private static int K;
    private static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            if(K == 0){
                break;
            }
            S = new int[K];
            visit = new boolean[K];
            for(int i=0;i<S.length;i++){
                S[i] = Integer.parseInt(st.nextToken());
            }

            comb(0, 0);
            System.out.println();
        }


    }//end of main

    public static void comb(int idx, int cnt) {
        if(cnt == 6){
            for(int i=0;i<visit.length;i++){
                if(visit[i]){
                    System.out.print(S[i]+" ");
                }
            }
            System.out.println();
            return;
        }
        if(idx == K) return;

        visit[idx] = true;
        comb(idx+1, cnt +1);
        visit[idx] = false;
        comb(idx+1, cnt);

    }

}//end of class