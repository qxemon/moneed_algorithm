package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1260_DFS와BFS {

    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static StringBuilder dfsBuilder;


    public static void main(String[] args) throws IOException {
        int N, M, V;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        dfsBuilder = new StringBuilder();

        //그래프 초기화 (정점 수 만큼)
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 1; i<=M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        } // end of input



        for (int i = 1; i <= N ; i++) {
            Collections.sort(graph[i]);
        }


        //dfs
        visited = new boolean[N+1];
        dfsBuilder.append(V).append(" ");
        visited[V] = true;
        dfs(V);
        System.out.println(dfsBuilder.toString());

        //bfs
        visited = new boolean[N+1];
        bfs(V);

    }


    public static void dfs(int start){
        for (int i = 0; i < graph[start].size(); i++) {
            int n = graph[start].get(i);

            if(!visited[n]){
                visited[n] = true;
                dfsBuilder.append(n).append(" ");
                dfs(n);
            }
        }

    }

    public static void bfs(int start){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        StringBuilder result = new StringBuilder();
        result.append(start).append(" ");
        while(!queue.isEmpty()){
            int cur = queue.poll();
            for (int i = 0; i < graph[cur].size(); i++) {
                int n = graph[cur].get(i);
                if(!visited[n]){
                    queue.add(n);
                    visited[n] = true;
                    result.append(n).append(" ");
                }
            }
        }

        System.out.println(result.toString().trim());
    }
}
