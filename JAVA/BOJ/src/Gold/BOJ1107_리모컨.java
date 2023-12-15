package Gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1107_리모컨 {
    static int N, M;
    static int nLen;
    static int[] numbers;
    static int[] broken;
    static int[] result;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        nLen = s.length();
        N = Integer.parseInt(s);
        M = Integer.parseInt(br.readLine());
        if (M == 0){
            numbers = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        }
        else{
            StringTokenizer st = new StringTokenizer(br.readLine());

            broken = new int[M];
            numbers = new int[10-M];
            for (int i = 0; i < M; i++) {
                broken[i] =Integer.parseInt(st.nextToken());
            }
            int idx = 0;
ex:         for(int i=0; i < 10; i++){
                for (int j = 0; j < M; j++) {
                    if(broken[j] == i){
                        continue ex;
                    }
                }
                numbers[idx++] = i;
            }
        }

        if(N == 100){ // 예외, 첫 채널 100 -> 이동 횟수 0
            System.out.println(0);
            return;
        }

//        System.out.println(Arrays.toString(numbers));
        result = new int[nLen];
        ans = Integer.MAX_VALUE;
        perm(0);

        System.out.println(ans + nLen);

    }
    static void perm(int idx){
        if(idx == nLen){
//            System.out.println(Arrays.toString(result));

            int num = 0;
            //숫자 조합하자
            for (int i = 0; i < nLen; i++) {
                num *= 10;
                num += result[i];

            }

//            System.out.println(num);
            int res = Math.abs(num - N);
            ans = Math.min(res, ans);

            return;
        }

        for(int i=0; i<numbers.length; i++){
            result[idx] = numbers[i];
            perm(idx+1);
        }




    }

}
