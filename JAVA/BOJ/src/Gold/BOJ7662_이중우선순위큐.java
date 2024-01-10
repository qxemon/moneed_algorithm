package Gold;

import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7662_이중우선순위큐 {

    static Map<Integer, Integer> map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int input = Integer.parseInt(br.readLine());

            PriorityQueue<Integer> pqs = new PriorityQueue<>(); //오름차순 pq
            PriorityQueue<Integer> pqb = new PriorityQueue<>(Collections.reverseOrder()); // 내림차순
            map = new HashMap<>(); //각 숫자를 기억할 맵

            for (int i = 0; i < input; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String cal = st.nextToken();

                int num = Integer.parseInt(st.nextToken());
                if(cal.equals("I")){
                    pqs.add(num);
                    pqb.add(num);
                    map.put(num,map.getOrDefault(num,0)+1);

                } else{

                    if(map.size()==0) continue;

                    if (num == 1) { // 최댓값 제거
                        delete(pqb);
                    }
                    else{ //최솟값 제거
                        delete(pqs);
                    }

                }
            }

            if(map.size()==0) sb.append("EMPTY\n");
            else{
                int res = delete(pqb);
                sb.append(res).append(" ");
                if(map.size()>0) res = delete(pqs);
                sb.append(res).append("\n");
            }
        }

        System.out.println(sb.toString());
    }
    public static int delete(Queue<Integer> pq){
        int n =0;
        while(true){
            n = pq.poll();

            int cnt = map.getOrDefault(n, 0);
            if(cnt == 0) continue;

            if(cnt == 1) map.remove(n);
            else map.put(n,cnt-1);
            break;
        }
        return n;
    }

}
