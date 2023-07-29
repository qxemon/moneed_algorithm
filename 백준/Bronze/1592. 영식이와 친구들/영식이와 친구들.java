import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int[] mans = new int[n];
        int i=0;
        int count = 0;
        while(true){
            mans[i]++;
            if(mans[i] == m){
                break;
            }
            if(mans[i]%2 == 0){
                //공받은 횟수가 짝수이면 반시계이동(작은번호로)
                if(i-l < 0){
                    i = n + i - l;
                }
                else{
                    i -= l;
                }
                count++;
            }
            else if(mans[i]%2 == 1){
                if(i+l >= n){
                    i = (i + l) % n;
                }
                else{
                    i += l;
                }
                count++;
            }

        }

        System.out.println(count);
    }
}