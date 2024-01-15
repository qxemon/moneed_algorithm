package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ14226_이모티콘 {

    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int s = Integer.parseInt(br.readLine());

        int ans = bfs(s);

        System.out.println(ans);

    }


    public static int bfs(int s) {
        Queue<Clip> queue = new ArrayDeque<>();

        visited = new boolean[1001][1001]; //emoji, copy

        Clip first = new Clip(1, 1, 1);

        visited[0][0] = true;
        visited[1][0] = true;
        visited[1][1] = true;

        queue.add(first);
        while (!queue.isEmpty()) {
            Clip now = queue.poll();

            if (now.emoji == s) return now.time;

            //1. 클립보드에 저장
            if(!visited[now.emoji][now.emoji]){
                Clip method1 = new Clip(now.emoji, now.emoji, now.time + 1);
                queue.add(method1);
            }
            //2. 클립보드 이모지 화면 부착
            //현재 이모지와 클립보드 이모지 합이 S보다 작거나 같고, 이전 기록이 없으면
            if (now.copy != 0 &&now.emoji + now.copy <= s && !visited[now.emoji + now.copy][now.copy]) {
                visited[now.emoji + now.copy][now.copy] = true;
                Clip method2 = new Clip(now.emoji + now.copy, now.copy, now.time + 1);
                queue.add(method2);
            }
            //3. 이모지 하나 삭제
            // 0보다 크거나 방문한 적 없으면
            if (now.emoji > 0 && !visited[now.emoji - 1][now.copy]) {
                visited[now.emoji - 1][now.copy] = true;
                Clip method3 = new Clip(now.emoji - 1, now.copy, now.time + 1);

                queue.add(method3);

            }

        }


        return 1;

    }


    static class Clip {
        int emoji;
        int copy;
        int time;

        public Clip(int emoji, int copy, int time) {
            this.emoji = emoji;
            this.copy = copy;
            this.time = time;
        }
    }
}