import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean is369 = false;
        for(int i =1; i<=n; i++){
            is369 = false;
            int g = i;
            while(g > 0){
                if(g%10 == 3 || g%10 == 6 || g%10 == 9){
                    is369 = true;
                    System.out.print("-");
                }
                g /= 10;
            }
            if(is369)
                System.out.print(" ");
            else
                System.out.print(i+" ");
        }
    }
}