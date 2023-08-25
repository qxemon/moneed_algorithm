package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2252_줄세우기 {
    static int N, M; // N명, M번
    static int A, B;
    static int[] degree;
    static List<List<Integer>> graph;
    static int result[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        degree = new int[N+1];
        graph = new ArrayList<List<Integer>>();
        result = new int[N];
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            graph.get(A).add(B); //해당에서 진출하는 정점 저장
            degree[B]++; //진입차수 증가
        }

        bfs();

        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]+" ");
        }

//        graph.get(1).add(new Point(3, 1));
//
//        List<Point> points = graph.get(1);

    }
    static void bfs(){
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i < degree.length; i++) {
            if(degree[i] == 0){
                queue.offer(i);
            }
        }
        int cnt = 0;
        while(!queue.isEmpty()){
            int idx = queue.poll(); //진입 차수가 0인 정점 꺼냄
            result[cnt++] = idx; // 정렬 배열에 넣어줌

            for (int i = 0; i < graph.get(idx).size(); i++) {

                degree[graph.get(idx).get(i)]--; // 인접 정점 하나 뗌
                if(degree[graph.get(idx).get(i)] == 0){ //인접 정점 0 되면
                    queue.offer(graph.get(idx).get(i)); //큐에 넣음
                }
            }
        }

    } //bfs end
}

class Point {
    int next;
    int cost;

    public Point(int next, int cost) {
        this.next = next;
        this.cost = cost;
    }

}