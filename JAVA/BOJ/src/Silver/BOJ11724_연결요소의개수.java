package Silver;

import java.util.*;
import java.io.*;

public class BOJ11724_연결요소의개수 {

    private static ArrayList<Integer>[] graph;
    private static boolean[] visited;
    private static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        int ans = 0;

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        } // end of input

        for (int i = 1; i <= N; i++) {
            if(!visited[i]) {
                bfs(i);
                ans++;
            }
        }

        System.out.println(ans);
    }

    public static void bfs(int start){
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start] = true;
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            for (int i = 0; i < graph[cur].size(); i++) {
                if(!visited[graph[cur].get(i)]) {
                    queue.add(graph[cur].get(i));
                    visited[graph[cur].get(i)] = true;
                }
            }
        }
    }
}
