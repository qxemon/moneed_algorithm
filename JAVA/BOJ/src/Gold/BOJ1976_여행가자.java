package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1976_여행가자 {
    static List<List<Integer>> graph;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 도시 수
        int m = Integer.parseInt(br.readLine()); // 여행 계획에 속한 도시 수
        StringTokenizer st;

        graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        } // 그래프 초기화


        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int info = Integer.parseInt(st.nextToken());
                if (info == 1) {
                    graph.get(i).add(j);
                }
            }
        } // end of input

        st = new StringTokenizer(br.readLine()); // 내가 여행할 경로

        visited = new boolean[n];
        int[] route =  new int[m];

        for (int i = 0; i < m; i++) {
            int start = Integer.parseInt(st.nextToken()) - 1;
            route[i] = start;
        }


        bfs(route[0]);

        for (int i = 0; i < m; i++) {
            if(!visited[route[i]]){
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
        



    }


    static void bfs(int start){
        Queue<Integer> queue = new ArrayDeque<>();
        visited[start] = true;

        queue.offer(start);
        while(!queue.isEmpty()){
            int now = queue.poll();

            for (int i = 0; i < graph.get(now).size(); i++) {
                int vertex = graph.get(now).get(i);
                if(!visited[vertex]) {
                    queue.offer(vertex);
                    visited[vertex] = true;
                }

            }
            
        }

    }


}
