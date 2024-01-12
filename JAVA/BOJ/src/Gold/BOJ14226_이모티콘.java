package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ14226_이모티콘 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int s = Integer.parseInt(br.readLine());

        int ans = bfs(s);

        System.out.println(ans);

    }


    public static int bfs(int s){
        Queue<Clip> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[1001][1001];
        Clip first = new Clip(1,1,1);

        visited[1][1] = true;

        int time = 1;
        queue.add(first);
        while(!queue.isEmpty()){
            Clip now = queue.poll();

            if(now.emoji == s) return now.time;

            Clip[] method = new Clip[3];
            //1. 클립보드에 저장
            method[0] = new Clip(now.emoji, now.emoji,now.time+1);
            //2. 클립보드 이모지 화면 부착
            if(now.copy > 0 && now.emoji+now.copy <= )
            method[1] = new Clip(now.emoji+now.copy, now.copy,now.time+1);
            //3. 이모지 하나 삭제
            method[2] = new Clip(now.emoji-1, now.copy, now.time+1);

            //소거: 뭐가 최선일까?



        }


        return 1;

    }




    static class Clip{
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
