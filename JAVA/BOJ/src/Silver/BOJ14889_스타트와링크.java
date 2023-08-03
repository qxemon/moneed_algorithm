package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14889_스타트와링크 {
    static int MIN, Teams[][];
    static int N, R;
    static boolean[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        R = N/2;
        Teams = new int[N][N];
        selected = new boolean[N];
        MIN = 9999;

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                Teams[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        comb(0,0);
        System.out.println(MIN);


    }

    static void comb(int idx, int cnt){
        if(cnt == R){ //조합 완료
            int sumS =0, sumL = 0;
            //스타트 팀 능력치
            for(int i=0;i<N;i++){
                for(int j=0; j<N;j++){
                    if(selected[i]&& selected[j]){
                        sumS += Teams[i][j];
                    }
                }
            }
            //링크 팀 능력치
            for(int i=0;i<N;i++){
                for(int j=0; j<N;j++){
                    if(!(selected[i] || selected[j])){
                        sumL += Teams[i][j];
                    }
                }
            }

            //두 팀의 능력차 : 양수 나와야 하니 절댓값으로 자리 신경 x
            MIN = Math.min(MIN, Math.abs(sumS - sumL));
            return; //재귀 종료
        }
        if(idx == N){
            return;
        }

        selected[idx] = true;
        comb(idx+1, cnt+1);
        selected[idx] = false;
        comb(idx+1, cnt);

    }
}
