package D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1206_VIEW {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = 10;
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for(int tc = 1; tc<=10; tc++){
            int result = 0;
            int size = Integer.parseInt(br.readLine());
            int[] buildings = new int[size];

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<buildings.length;i++){
                buildings[i] = Integer.parseInt(st.nextToken());
            }

            int max = Integer.MIN_VALUE;
            for(int i = 2; i<buildings.length-2;i++){
                max = Integer.MAX_VALUE;
                max = Math.min(buildings[i] - buildings[i-2], max);
                max = Math.min(buildings[i] - buildings[i-1], max);
                max = Math.min(buildings[i] - buildings[i+1], max);
                max = Math.min(buildings[i] - buildings[i+2], max);
                if(max > 0 ){
                    result += max;
                }
            }


            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb.toString());
    }
}
