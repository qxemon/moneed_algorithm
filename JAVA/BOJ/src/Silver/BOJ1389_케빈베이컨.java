package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1389_케빈베이컨 {

    static int N, M;
    static int ans;

    static ArrayList<Integer>[] graph;
    static int[][] score;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        score = new int[N + 1][N + 1];

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
            bfs(i);
        }

        calAns();

        System.out.println(ans);


    }

    static void print(){
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(score[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("===============");
    }

    static void calAns(){
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= N ; i++) {
            int sum = 0;
            for (int j = 1; j <= N; j++) {
                sum += score[i][j];
            }
            if(min > sum){
                min = sum;
                ans = i;
            }

        }
    }


    static void bfs(int start){
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        int count = 1;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int s = 0; s < size; s++){
                int cur = queue.poll();

                for (int i = 0; i < graph[cur].size(); i++) {
                    int num = graph[cur].get(i);
                    if(score[start][num] == 0 && num != start){
                        score[start][num] = count;
                        queue.add(num);
                    }
                }
            }
            count++;
        }
    }
}
