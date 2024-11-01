package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1764_듣보잡 {

    private static int N, M;
    private static Map<String, Integer> map;
    private static int count;
    private static ArrayList<String> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        count = 0;

        map = new HashMap<>();
        list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        for (int i = 0; i < M; i++) {
            String s = br.readLine();
            int get = map.getOrDefault(s, 0);
            if(get != 0){
                list.add(s);
                count++;
            }
        }

        //정렬
        list.sort(String::compareTo); // list 자체 정렬
//        Arrays.sort(list.toArray()); // 배열을 정렬하는 거라 list 자체의 정렬이 아님, 정렬을 원하면 String[] 을 만들어 적용
//        String[] arr = list.toArray(new String[0]); // << 이런식으로 적용
        System.out.println(count);

        for (String s : list) {
            System.out.println(s);

        }
    }
}
