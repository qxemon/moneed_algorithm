import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] switchs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int count = Integer.parseInt(br.readLine())+1;
        switchs = new int[count];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<count;i++){
            switchs[i] = Integer.parseInt(st.nextToken());
        }
        int k = Integer.parseInt(br.readLine());

        for(int i=0;i<k;i++){
            st = new StringTokenizer(br.readLine());
            int g = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            if(g == 1){
                for(int j=num;j<switchs.length;j++){
                    if(j % num == 0){
                        switchs[j] = switchs[j] == 0 ? 1 : 0;
                    }
                }
            }
            else if(g == 2){
                recur(num-1, num+1);
            }

        }

        for(int i=1;i< switchs.length;i++){
            System.out.print(switchs[i]+" ");
            if(i%20 == 0){
                System.out.println();
            }
        }
    }

    static void recur(int num1, int num2){
        if((num1 < 1 || num2 >= switchs.length) || switchs[num1] != switchs[num2]){
            for(int k=num1+1; k<num2; k++) {
                switchs[k] = switchs[k] == 0 ? 1 : 0;
            }
            return;
        }


        recur((num1-1), (num2+1));

    }
}