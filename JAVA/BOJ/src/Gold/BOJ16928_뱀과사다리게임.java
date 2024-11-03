package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ16928_뱀과사다리게임 {
    private static int N, M;
    private static Map<Integer, Integer> event;
    private static boolean[] visited;
    private static int ans;
    private final static int[] dice = {1,2,3,4,5,6};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        event = new HashMap<Integer, Integer>();
        visited = new boolean[101];

        for (int i = 0; i < N+M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            event.put(a, b);
        } // end of input

        bfs(1);

        System.out.println(ans);

    }

    public static void bfs(int start){
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start] = true;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                int cur = queue.poll();
                if(cur == 100) return;

                for (int d = 0; d < 6; d++) {
                    int next = cur + dice[d];
                    if(next <= 100 && !visited[next]){
                        visited[next] = true;
                        if(event.containsKey(next)){
                            next = event.get(next);
                        }
                        queue.add(next);
                        visited[next] = true;
                    }
                }
            }
            ans++;
        }
    }
}
