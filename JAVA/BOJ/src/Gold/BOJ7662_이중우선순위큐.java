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
            PriorityQueue<Integer> pqs = new PriorityQueue<>(); //오름차순 pq
            PriorityQueue<Integer> pqb = new PriorityQueue<>(((o1, o2) -> {
                return o2-o1;
            })); // 내림차순

            int input = Integer.parseInt(br.readLine());

            int inp = 0;
            int del = 0;

            for (int i = 0; i < input; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String cal = st.nextToken();
                int num = Integer.parseInt(st.nextToken());
                if(cal.equals("I")){
                    pqs.add(num);
                    pqb.add(num);
                    inp++;
                } else{
                    if (num == -1) { // 최솟값 제거
                        pqs.poll();
                    }
                    else if (num == 1){ //최댓값 제거
                        pqb.poll();
                    }
                    del++;
                }
            }

            if(inp <= del){
                sb.append("EMPTY").append("\n");
            }
            else{
                sb.append(pqb.poll()).append(" ").append(pqs.poll()).append("\n");
            }
        }

        System.out.println(sb.toString());
    }
}
