package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2805_나무자르기 {

    private static int N, M;
    private static int[] treeLen;
    private static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        treeLen = new int[N];
        ans = 0;
        int highest = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            treeLen[i] = Integer.parseInt(st.nextToken());
            highest = Math.max(highest, treeLen[i]);
        }

        binarySearch(0, highest);

        System.out.println(ans);


    }

    static void binarySearch(int left, int right) {

        while (left <= right) {

            int mid = left + (right - left) / 2; //정수 오버플로우 방지

            //해당 길이로 잘랐을 때 얻는 나무의 길이
            long tree = 0;
            for (int i = 0; i < N; i++) {
                if (treeLen[i] > mid) {

                    tree += treeLen[i] - mid;
                }
            }

            if (tree >= M) {
                left = mid + 1;
                ans = mid;
            } else {
                right = mid - 1;
            }

        }

    }
}
