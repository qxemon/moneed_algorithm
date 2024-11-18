package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ11403_경로찾기 {

    private static int[][] graph;
    private static int[][] connected;
    private static boolean[] visited;

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        connected = new int[N][N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // end of input

        for (int i = 0; i < N; i++) {
            Arrays.fill(visited, false);
            bfs(i);
        }

        print();

    }

    private static void print(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(connected[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void bfs(int i) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);

        while(!queue.isEmpty()) {
            int cur = queue.poll();
            for (int j = 0; j < N; j++) {
                if(graph[cur][j] == 1 && !visited[j]) {
                    connected[i][j] = 1;
                    visited[j] = true;
                    queue.add(j);
                }
            }
        }
    }
}
