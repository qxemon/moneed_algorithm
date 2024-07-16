package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ8983_사냥꾼 {
    static int M, N, L;
    static int[] archer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        archer = new int[M];
        for (int i = 0; i < archer.length; i++) {
            archer[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(archer);

        int ans = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(binary_search(x,y,0, archer.length-1)){
                ans++;
            }

        }

        System.out.println(ans);

    }

    static boolean binary_search(int x, int y, int left, int right){

        while(left <= right){

            int mid = (left + right) / 2;
            int distance = Math.abs(archer[mid] - x) + y;

            if(distance <= L){
                return true;
            }
            else if (archer[mid] > x){
                right = mid - 1;
            }
            else if (archer[mid] <= x){
                left =  mid + 1;
            }

        }

        return false;
    }
}
