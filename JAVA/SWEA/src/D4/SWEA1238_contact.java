package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA1238_contact {
    static int len, ans;
    static int start;
    static List<List<Integer>> graph;
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = 10;
        StringTokenizer st;
        ans = Integer.MIN_VALUE;
        for (int tc = 1; tc <= TC ; tc++) {
            st = new StringTokenizer(br.readLine());
            len = Integer.parseInt(st.nextToken());
            start = Integer.parseInt(st.nextToken());
            graph = new ArrayList<>();
            visit = new boolean[101];
            for (int i = 0; i < 101; i++) {
                graph.add(new ArrayList<>());
            }
            st = new StringTokenizer(br.readLine());
            
            
            
            while(st.hasMoreTokens()){
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                graph.get(from).add(to);
            }

//            System.out.println(graph);

            bfs(start);

            System.out.println("#"+tc+" "+ans);

        }
    }//end of main;

    static void bfs(int start){
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visit[start] = true;

        while(!queue.isEmpty()){
            int size = queue.size();
            ans = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                int vertex = queue.poll();
                for (int j = 0; j < graph.get(vertex).size(); j++) {
                    if(!visit[graph.get(vertex).get(j)]){
                        queue.offer(graph.get(vertex).get(j));
                    }
                    visit[graph.get(vertex).get(j)] = true;
                }
                ans = Math.max(ans, vertex);
            }

        }

    }
}//end of class
