package D2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//백만장자 프로젝트 : 범위를 잘 고려할 것
public class SWEA1859 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t= Integer.parseInt(br.readLine());

        for(int tc=1; tc<=t; tc++){
            int len = Integer.parseInt(br.readLine());
            int[] price = new int[len];
            st = new StringTokenizer(br.readLine());

            for(int i=0;st.hasMoreTokens();i++){
                price[i] = Integer.parseInt(st.nextToken());
            }

            long max = Long.MIN_VALUE;
            long result = 0;
            for(int j=len-1;j>=0;j--){
                if(max < price[j])
                    max = price[j];
                else
                    result += max-price[j];


            }

            System.out.println("#"+tc+" "+result);


        }
    }
}
