package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2632_피자판매 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int wantPizza = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int[] pizzaA = new int[a];
        int maxA = 0;
        for (int i = 0; i < a; i++) {
            int size = Integer.parseInt(br.readLine());
            pizzaA[i] = size;
            maxA += size;
        }

        int[] pizzaB = new int[b];
        int maxB = 0;
        for (int i = 0; i < b; i++) {
            int size = Integer.parseInt(br.readLine());
            pizzaB[i] = size;
            maxB += size;
        } // end of input


        // 부분합
        int[] psumA = new int[Math.max(maxA, wantPizza)+1];
        psumA[0] = 1;
        psumA[maxA] = 1;
        count(pizzaA, psumA, wantPizza);



        int[] psumB = new int[Math.max(maxB, wantPizza)+1];
        psumB[0] = 1;
        psumB[maxB] = 1;
        count(pizzaB, psumB, wantPizza);

        int ans = 0;
        for (int i = 0; i <= wantPizza; i++) {
            ans += (psumA[i] * psumB[wantPizza-i]);
        }

        System.out.println(ans);
    }

    public static void count(int[] pizza, int[] count, int size) {
        for(int i = 0; i < pizza.length; i++) { //시작하는 피자의 인덱스
            int sum = 0;
            for(int j = 1; j < pizza.length; j++) { //선택하는 피자 조각의 개수
                int sum_j = pizza[(i + j) % pizza.length];
                if(sum + sum_j > size) break;
                sum += sum_j;
                count[sum]++;
            }
        }
    }
}
