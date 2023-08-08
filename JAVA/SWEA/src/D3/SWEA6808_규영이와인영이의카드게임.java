package D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA6808_규영이와인영이의카드게임 {
    private static int win;
    private static int lose;
    private static boolean[] visit;
    private static int[] res;
    private static int[] gyu;
    private static int[] in;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=TC; tc++){
            gyu = new int[9];
            in = new int[9];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0;i<gyu.length;i++){
                gyu[i] = Integer.parseInt(st.nextToken());
            } //규영이 배열 넣기 완료
            boolean has = false;
            int indx = 0;
            for(int i=1;i<19;i++){
                has = false;
                for(int j=0; j<gyu.length;j++){
                    if(gyu[j] == i){
                        has = true;
                        break;
                    }
                }
                if(!has){
                    in[indx++] = i;
                }
            }

            win = 0;
            lose = 0;
            visit = new boolean[9];
            res = new int[9];

            perm(0);

            sb.append("#").append(tc).append(" ").append(win).append(" ").append(lose).append("\n");
        }//end of tc
        System.out.println(sb.toString());
    }//end of main

    public static void perm(int idx){
        if(idx == 9){// 기저조건
            int gSum=0, iSum=0;
            for(int i=0; i<9;i++){
                if(gyu[i] > res[i])
                    gSum += gyu[i] + res[i];
                else if(gyu[i] < res[i])
                    iSum += gyu[i] + res[i];
            }
            if(gSum > iSum)
                win++;
            else if(iSum > gSum)
                lose++;
            return;
        }
        for(int i=0; i<9;i++){
            if(!visit[i]){
                res[i] = in[idx];
                visit[i] = true;
                perm(idx + 1);
                visit[i] = false;
            }
        }




    }

}//end of class
