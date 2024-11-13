package Silver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ9375_패션왕신해빈 {

    static HashMap<String, Integer> category;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("JAVA/BOJ/input/BOJ9375.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
    
        int TC = Integer.parseInt(br.readLine());
        for (int t = 0; t < TC; t++) {
            int N = Integer.parseInt(br.readLine());
            category = new HashMap<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                String name = st.nextToken();
                String type = st.nextToken();

                category.put(type, category.getOrDefault(type, 0) + 1);
            }
            int ans = 1;
            for(int count : category.values()) {
                ans *= (count + 1);
            }

            ans--;

            System.out.println(ans);



        }
    }
}
