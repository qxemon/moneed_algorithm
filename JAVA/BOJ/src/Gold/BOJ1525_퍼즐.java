package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1525_퍼즐 {
    static String answer = "123456780";
    static Map<String, Integer> map;

    static int[] di = {-1,1,0,0};
    static int[] dj = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String start = "";
        map = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                start += st.nextToken();
            }
        } // end of input

        map.put(start,0);
        int ans = bfs(start);

        System.out.println(ans);
    }

    static int bfs(String start){
        Queue<String> queue = new ArrayDeque<>();
        queue.add(start);
        while(!queue.isEmpty()){
            String now = queue.poll();
            int count = map.get(now);

            if(now.equals(answer)){
                return count;
            }
            int zero = now.indexOf('0');
            int nowi = zero/3;
            int nowj = zero%3;

            //사방탐색
            for (int d = 0; d < 4; d++) {
                int ni = nowi + di[d];
                int nj = nowj + dj[d];
                if(ni>=0 && ni<3 && nj>=0 && nj <3) {
                    int nextMove = ni*3 + nj;
                    char num = now.charAt(nextMove);
                    String nextResult = now.replace(num,'t');
                    nextResult = nextResult.replace('0',num);
                    nextResult = nextResult.replace('t','0');

                    if(!map.containsKey(nextResult)){
                        queue.offer(nextResult);
                        map.put(nextResult,count+1);
                    }

                }
            }

        }

        return -1;
    }
}
