import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for(int t=1;t<=10;t++){
            //입력
            String tc = br.readLine();
            int[][] map = new int[100][100];


            for(int i=0;i< map.length;i++){
                String line = br.readLine();
                st = new StringTokenizer(line);
                for(int j=0;j<map.length;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int max = 0;

            int dSum1 = 0; //우하향 대각선 합
            int dSum2 = 0; //좌하향 대각선 합
            for(int i=0;i<map.length;i++){

                int rSum = 0;
                int cSum = 0;
                for(int j =0;j<map.length;j++){
                    //대각선 처리 -> 맨위 인덱스에서 오른쪽, 왼쪽으로 내려갈것임
                    //1. 0,0 인덱스에서 오른쪽 아래 대각선 합
                    if(i==0 && j ==0){
                        for(int k=0;k<100;k++){
                            dSum1+=map[k][k];
                        }
                        max = Math.max(dSum1, max);
                    }
                    //2. 0,99 인덱스에서 왼쪽 아래 대각선 합
                    if(i==0 && j == 99){
                        for(int k=0;k<100;k++){
                            dSum2 += map[i+k][j-k];
                        }
                        max = Math.max(dSum2, max);
                    }
                    cSum += map[i][j];
                    rSum += map[j][i];
                }
                max = Math.max(cSum, max);
                max = Math.max(rSum, max);
            }

            System.out.println("#"+t+" "+max);

        }
    }
}