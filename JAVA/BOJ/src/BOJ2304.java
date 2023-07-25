import java.util.Arrays;
import java.util.Scanner;

public class BOJ2304 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] x = new int[N];
        int[] y = new int[N];

        for(int i=0;i<N;i++){
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
        }
        System.out.println(Arrays.toString(x));
        System.out.println(Arrays.toString(y));




    }
}
