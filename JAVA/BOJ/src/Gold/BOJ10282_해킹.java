package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ10282_해킹 {

    static ArrayList<ArrayList<Hack>> graph;
    static int[] dist;
    static int max, count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < TC; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            dist = new int[N + 1];
            for (int n = 0; n < N + 1; n++) {
                graph.add(new ArrayList<>());
                dist[n] = Integer.MAX_VALUE;
            }
            max = -1;
            count = 0;
            // 초기화


            for (int d = 0; d < D; d++) {
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                int S = Integer.parseInt(st.nextToken());
                graph.get(B).add(new Hack(A, S));
            }

            bfs(C);

            for (int i : dist) {
                if (i != Integer.MAX_VALUE) {
                    count++;
                    if(max < i) max = i;
                }
            }
            System.out.println(count + " " + max);

        }

    }

    static void bfs(int start) {
        PriorityQueue<Hack> queue = new PriorityQueue<>();
        dist[start] = 0;
        queue.offer(new Hack(start, 0));

        while (!queue.isEmpty()) {
            Hack now = queue.poll();
            int cur = now.cur;


            for (int i = 0; i < graph.get(cur).size(); i++) {
                Hack next = graph.get(cur).get(i);

                //경로 갱신
                if (dist[next.cur] > dist[cur] + next.time) {
                    dist[next.cur] = dist[cur] + next.time;
                    queue.add(graph.get(cur).get(i));
                }
            }

        }
    }


    static class Hack implements Comparable<Hack> {
        int cur;
        int time;

        public Hack(int cur, int time) {
            this.cur = cur;
            this.time = time;
        }

        @Override
        public String toString() {
            return "Infection{" +
                    "cur=" + cur +
                    ", time=" + time +
                    '}';
        }

        @Override
        public int compareTo(Hack o) {
            return this.time - o.time;
        }
    }
}
