package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2458_키순서 {

    static int N, M, count, ans;
    static List<ArrayList<Integer>> biglist;
    static List<ArrayList<Integer>> smallList;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 학생 수
        M = Integer.parseInt(st.nextToken()); // 비교 횟수

        biglist = new ArrayList<>(); // 해당 정점에 대해 큰 애를 담을거임
        smallList = new ArrayList<>();
        visited = new boolean[N+1];

        for (int i = 0; i < N+1; i++) {
            biglist.add(new ArrayList<>()); // n개의 리스트 초기화
            smallList.add(new ArrayList<>());
        }


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int small = Integer.parseInt(st.nextToken());
            int big = Integer.parseInt(st.nextToken());
            biglist.get(small).add(big);
            smallList.get(big).add(small);
        }


        for (int i = 1; i <= N; i++) {
            count = 1;
            visited = new boolean[N+1];
            bfsTall(i);
            bfsSmall(i);

            if(count == N){
                ans++;
            }

        }

        System.out.println(ans);

    }

    static void bfsTall(int index){
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        visited[index] = true;
        for (int i = 0; i < biglist.get(index).size(); i++) {
            queue.add(biglist.get(index).get(i));
            count++;
            visited[biglist.get(index).get(i)] = true;
        }

        while(!queue.isEmpty()){
            int next = queue.poll();
            for (int i = 0; i < biglist.get(next).size(); i++) {
                if(!visited[biglist.get(next).get(i)]){
                queue.add(biglist.get(next).get(i));
                count++;
                visited[biglist.get(next).get(i)] = true;
                }
            }
        }


    }

    static void bfsSmall(int index){
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        visited[index] = true;
        for (int i = 0; i < smallList.get(index).size(); i++) {
            queue.add(smallList.get(index).get(i));
            count++;
            visited[smallList.get(index).get(i)] = true;

        }

        while(!queue.isEmpty()){
            int next = queue.poll();
            for (int i = 0; i < smallList.get(next).size(); i++) {
                if(!visited[smallList.get(next).get(i)]) {
                    queue.add(smallList.get(next).get(i));
                    count++;
                    visited[smallList.get(next).get(i)] = true;
                }
            }
        }
    }

}
