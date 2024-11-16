package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ12851_숨바꼭질2 {

    private static int N, K;
    private static int[] step = {-1,1,2};
    private static int time;
    private static int count;
    private static boolean[] visited = new boolean[100001];



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        time = Integer.MAX_VALUE;
        count = 0;

        bfs();

        System.out.println(time);
        System.out.println(count);
    }

    public static void bfs(){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);
        visited[N] = true;
        int[] minTimes = new int[100001];

        while(!queue.isEmpty()){
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                int cur = queue.poll();
                if(cur == K) {
                    if(minTimes[cur] == time){
                        count++;
                    }
                    else if(minTimes[cur] < time){ // 새로운 최소시간 발견 시
                        time = minTimes[cur];
                        count = 1;
                    }
                }

                for (int i = 0; i < 3; i++) {
                    int next =  (i == 2) ? cur*step[i] : cur + step[i];

                    if(next >= 0 && next <= 100000){
                        if(!visited[next] || minTimes[next] >= minTimes[cur] + 1){
                            queue.add(next);
                            visited[next] = true;
                            minTimes[next] = minTimes[cur] + 1;
                        }
                    }
                }

            }

        }

    }
}
