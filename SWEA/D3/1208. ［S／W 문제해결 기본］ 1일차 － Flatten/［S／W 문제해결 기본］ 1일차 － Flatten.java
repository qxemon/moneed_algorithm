import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int tc=1;tc<=10; tc++){
            int dump = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] boxes = new int[100];
            for(int i=0;i<100;i++){
                boxes[i] = Integer.parseInt(st.nextToken());
            }

            for(int i=0;i<dump;i++){
                Arrays.sort(boxes);
                boxes[99]--;
                boxes[0]++;

                if(boxes[99] - boxes[0] <= 1){
                    break;
                }
            }
            Arrays.sort(boxes);

            System.out.println("#"+tc+" "+(boxes[99]-boxes[0]));
        }




    }
}