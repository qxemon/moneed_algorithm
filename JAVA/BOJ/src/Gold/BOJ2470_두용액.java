package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ2470_두용액 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> liquid = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            liquid.add(Integer.parseInt(st.nextToken()));
        } // end of input
        
        //1. 정렬함 O(NlogN)
        Collections.sort(liquid);
        
        Diff result = new Diff(Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        
        
        //2. 인덱스 0과 N 2개로 투포인터 탐색 시작 O(N)
        int lower =0,  upper = N-1;

        while(upper > lower){

            if( liquid.get(upper) + liquid.get(lower) == 0){ // 합이 0인걸 찾았으니 더 이상 탐색 안해도 돼
                result.upper = liquid.get(upper);
                result.lower = liquid.get(lower);
                break;
            }

            if(result.res > Math.abs(liquid.get(upper) + liquid.get(lower))){ // 작은 값이 나왔단다.
                result.res = Math.abs(liquid.get(upper) + liquid.get(lower)); // 그러면 갱신
                result.upper = liquid.get(upper);
                result.lower = liquid.get(lower);

            }


            if( liquid.get(upper) + liquid.get(lower) > 0){ // 양수가 되었다. -> upper를 줄여본다.
                upper--;
            }
            else if( liquid.get(upper) + liquid.get(lower) < 0 ){ // 음수가 되었다. -> lower를 늘린다.
                lower++;
            }

        }

        System.out.println(result.lower+" "+ result.upper);







    }

    static class Diff{
        int upper; // 더 큰 값
        int lower; //더 작은 값
        int res; // 차이 값

        public Diff(int upper, int lower, int res) {
            this.upper = upper;
            this.lower = lower;
            this.res = res;
        }
    }
}
