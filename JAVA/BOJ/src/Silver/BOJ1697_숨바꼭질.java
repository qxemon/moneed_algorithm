package Silver;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class BOJ1697_숨바꼭질 {
    static int N, K, ans;
    static int[] dir = {-1, 1, 2};
    static boolean[] visit = new boolean[100001];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        ans = 0;
        bfs(N);
        System.out.println(ans);


    }

    static void bfs(int pos) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(pos);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- != 0) {
                int now = queue.poll();
                dir[2] = now;
                int next = 0;
                if (now == K) return;
                for (int i = 0; i < 3; i++) {
                    next = now + dir[i];

                    if (next >= 0 && next <= 100000 && !visit[next]) {
                        queue.offer(next);
                        visit[next] = true;
                    }
                }
            }
            ans++;

        }
    }
}
