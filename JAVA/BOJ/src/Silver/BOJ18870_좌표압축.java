package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ18870_좌표압축 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] copy = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            copy[i] = arr[i];
        } // end of input

        Arrays.sort(copy);

        HashMap<Integer, Integer> map = new HashMap<>();
        int num = 0;
        for (int i = 0; i < N; i++) {
            if(!map.containsKey(copy[i])) {
                map.put(copy[i],num++);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(map.get(arr[i])).append(" ");
        }

        System.out.println(sb);
    }
}
