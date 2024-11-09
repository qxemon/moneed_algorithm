package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2606_바이러스 {

    static ArrayList<Integer>[] computers;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        computers = new ArrayList[n+1];
        visited = new boolean[n+1];

        for (int i = 1; i <= n; i++) {
            computers[i] = new ArrayList<>();
        }
        int k = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            computers[a].add(b);
            computers[b].add(a);
        } // end of input


        System.out.println(bfs(1));

    }
    public static int bfs(int n) {
        int answer = 0;
        Queue<Integer> queue = new LinkedList<>();
        visited[n] = true;
        queue.add(n);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int i = 0; i < computers[cur].size(); i++) {
                int next = computers[cur].get(i);
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                    answer++;
                }
            }
        }

        return answer;
    }
}
