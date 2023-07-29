import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] mushrooms = new int[10];
        for(int i =0; i<10; i++){
            mushrooms[i] = Integer.parseInt(br.readLine());
        }
        int score = 0;
        int gap = 9999;
        int tmp = 0;
        for(int j=0;j<10;j++){
            tmp += mushrooms[j];
            if(tmp == 100){
                score = tmp;
                break;
            }
            if(Math.abs(100-tmp) <= gap){
                score = tmp;
                gap = Math.abs(100-tmp);
            }
        }

        System.out.println(score);
    }
}