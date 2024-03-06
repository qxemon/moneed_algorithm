package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ1208_부분수열의합2 {

    static int[] arr;
    static int mid;
    static int s;
    static long ans;

    static HashMap<Integer, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        arr = new int[n];
        ans = 0;
        map = new HashMap<>();

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        mid = n / 2;

        subset(0, 0);
        subset2(mid, 0);

        if (s == 0) {
            System.out.println(ans - 1);
        } else {
            System.out.println(ans);

        }
    }

    public static void subset(int idx, int sum) {
        if (idx == mid) {
            map.put(sum, map.getOrDefault(sum, 0) + 1);
            return;
        }

        subset(idx + 1, sum);
        subset(idx + 1, sum + arr[idx]);

    }

    public static void subset2(int idx, int sum) {
        if (idx == arr.length) {
            int key = s - sum;
            ans += map.getOrDefault(key, 0);
            return;
        }

        subset2(idx + 1, sum);
        subset2(idx + 1, sum + arr[idx]);
    }

}
