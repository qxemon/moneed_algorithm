package D3;

import java.util.Scanner;

public class SWEA5601_쥬스나누기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int TC = sc.nextInt();
        for (int tc = 1; tc <= TC; tc++) {
            int N = sc.nextInt();
            System.out.print("#"+tc+" ");
            for (int i = 0; i < N; i++) {
                System.out.print(1+"/"+N+" ");
            }
            System.out.println();
        }
    }
}
