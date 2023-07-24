import java.util.Arrays;
import java.util.Scanner;

public class BOJ2309 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] dwarfs = new int[9];
        int sum = 0;
        for(int i=0; i<dwarfs.length; i++){
            dwarfs[i] = sc.nextInt();
            sum += dwarfs[i];
        }

        Arrays.sort(dwarfs);

        int minusHundred = sum - 100;
        int x=0, y=0;
        for(int i=0;i<dwarfs.length-1;i++){
            for(int j =1;j<dwarfs.length;j++){
                if(dwarfs[i] + dwarfs[j] == minusHundred){
                    x = i;
                    y = j;
                    break;
                }
            }
            if(x != 0 || y != 0)
                break;
        }


        for(int i=0; i<dwarfs.length;i++){
            if(i==x || i==y)
                continue;
            else{
                System.out.println(dwarfs[i]);
            }
        }


    }
}
