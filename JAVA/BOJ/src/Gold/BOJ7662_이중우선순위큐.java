package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ7662_이중우선순위큐 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            int input = Integer.parseInt(br.readLine());
            for (int i = 0; i < input; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String cal = st.nextToken();
                int num = Integer.parseInt(st.nextToken());
                if(cal.equals("I")){
                    pq.add(num);
                } else{
                    
                }
            }
        }
    }
}
