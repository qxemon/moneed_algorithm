import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, a, b;
    static int depth;
    static List<List<Integer>> graph;
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        graph = new ArrayList<>();

        N = Integer.parseInt(st.nextToken()); //사람의 수 N
        M = Integer.parseInt(st.nextToken()); // 친구 관계의 수 M

        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());// 그래프 초기화
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
//        for (int i = 0; i < graph.size(); i++) {
//            System.out.print(i+": ");
//            for (int j = 0; j < graph.get(i).size(); j++) {
//                System.out.print(graph.get(i).get(j)+" ");
//            }
//            System.out.println();
//        }

        for (int i = 0; i < N; i++) {
            visit = new boolean[N];
            dfs(i, 0);
            if(depth == 4){
                System.out.println(1);
                return;
            }


        }

        System.out.println(0);
    }

    static void dfs(int idx, int d){
        if(d == 4){
            depth = 4;
            return;
        }
        visit[idx] = true;
        for (int i = 0; i < graph.get(idx).size(); i++) {
            if(!visit[graph.get(idx).get(i)]){
                dfs(graph.get(idx).get(i), d+1);
            }
        }
        visit[idx] = false;
    }
}