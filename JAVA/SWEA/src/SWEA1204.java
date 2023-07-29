import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1204 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        for(int tc=1;tc<=t;tc++){
            String n = br.readLine();
            int[] score =new int[101];
            int[] student = new int[1000];
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<1000;i++){
                student[i] = Integer.parseInt(st.nextToken());
            }
            for(int i=0;i<1000;i++){
                score[student[i]]++;
            }

            int result=100;
            int max =score[100];
            for(int i=99; i>=0; i--){
                if(max < score[i]){
                    max = score[i];
                    result = i;
                }
            }

            System.out.println("#"+tc+" "+result);

        }
    }
}
