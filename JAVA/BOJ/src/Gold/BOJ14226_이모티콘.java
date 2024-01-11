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


    }


    public static int bfs(int s){
        Queue<Integer> queue = new ArrayDeque<>();
        int cnt = 0;
        int num = 0;
        if(s > 1){
            //정답이 1이 아니라면 일단 무조건 클립보드에 복사를 해야하기 때문에
            num = 1;
            cnt++;
        }

        queue.add(num);
        while(queue.isEmpty()){

            int now = queue.poll();
            int a = now + num; // 그냥 추가하는 경우
            num =  //클립보드에 추가하는 경우
            int c = now - 1; // 삭제 하는 경우



        }




        return cnt;
    }



    static class clip{
        int cnt;
        int copy;

        public clip(int cnt, int copy) {
            this.cnt = cnt;
            this.copy = copy;
        }
    }
}
