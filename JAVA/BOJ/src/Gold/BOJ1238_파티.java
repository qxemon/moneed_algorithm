package Gold;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1238_파티 {
    static List<ArrayList<Node>> graph;
    static int N, M, X;
    static int ans;
    static int[][] distance;


    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("JAVA/BOJ/input/BOJ1238.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        distance = new int[N+1][N+1];

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }

        graph = new ArrayList<ArrayList<Node>>();
        for (int n = 0; n <= N; n++) {
            graph.add(new ArrayList<>());
        }
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Node(end, weight));
        } // end of input


        for (int i = 1; i <= N ; i++) {
            dijkstra(i);
        }

        // 1. 본인의 마을에서 파티마을로 이동
        //2. 파티마을에서 본인의 마을로 이동
        //3. 해당 값 합산의 최대가 답
        for (int i = 1; i <= N ; i++) {
            int goParty = distance[i][X];
            int goHome = distance[X][i];
            ans = Math.max(ans, goParty+goHome);
        }

        System.out.println(ans);
    }

    static void print(){
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(distance[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("==================");
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        distance[start][start] = 0;

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int curVertex = curNode.vertex;

            for(Node n : graph.get(curVertex)) {
                int dist = distance[start][curVertex] + n.weight;
                if(dist < distance[start][n.vertex]){
                    distance[start][n.vertex] = dist;
                    pq.add(new Node(n.vertex, distance[start][n.vertex]));
                }

            }
        }

    }




    // 목적지와 가중치
    static class Node implements Comparable<Node> {
        int vertex;
        int weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "vertex=" + vertex +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }
    }
}
