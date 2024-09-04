package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ28215_대피소 {

    static int N, K;
    static ArrayList<Pair> houses;
    static int[] numbers;
    static int ans;



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        houses = new ArrayList<>();
        numbers = new int[K];
        ans = Integer.MAX_VALUE;
        //초기화


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            houses.add(new Pair(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));

        } // end of input


        comb(0,0);

        System.out.println(ans);

    }

    static void comb(int cnt, int start){
        if(cnt == K){ // 조합이 완성됨
            //가장 긴 거리를 찾아 그것의 최소를 찾아야함
            int longest = 0;
            for (int i = 0; i < N; i++) {
                int shortest = Integer.MAX_VALUE;

                for (int j = 0; j < K; j++) {
                    int dist = Math.abs(houses.get(i).x - houses.get(numbers[j]).x) + Math.abs(houses.get(i).y - houses.get(numbers[j]).y);
                    shortest = Math.min(dist, shortest); // 각 대피소 중 집에 대해 가장 짧은 대피소 선정

                }
                    longest = Math.max(shortest, longest); //각 집에대해 고른 대피소 길이 중 가장 긴 것을 찾아야

            }

            ans = Math.min(ans, longest); // 그 가장 긴 대피소 중 최소
        
            return; 
        }

        for (int i = start; i < N; i++) {
            numbers[cnt] = i; // 조합 선택
            comb(cnt+1,start+1); // 재귀
        }


    }


    static class Pair{
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
