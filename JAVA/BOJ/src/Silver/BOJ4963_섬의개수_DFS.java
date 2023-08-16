package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ4963_섬의개수_DFS {
    static int w, h, map[][];
    static int[] di = { -1, -1, -1, 0, 1, 1, 1, 0 }; // 좌상, 상, 우상, 우, 우하, 하, 좌하, 좌
    static int[] dj = { -1, 0, 1, 1, 1, 0, -1, -1 };

    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if (w == 0 && h == 0)
                break;

            map = new int[h][w];


            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            result = 0;

            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    if(map[i][j] == 1){
                        dfs(i,j);
                        result++;
                    }

                }
            }

            sb.append(result).append("\n");
        }

        System.out.println(sb);

    }//end of main
    static void dfs(int i, int j){
        map[i][j] = 2; //방문 처리
        for(int d=0;d<8;d++){
            int ni = i+di[d];
            int nj = j+dj[d];
            if(ni>=0 && ni <map.length && nj>=0 && nj < map[0].length){
                if(map[ni][nj] == 1){
                    dfs(ni,nj);
                }
            }
        }

        return;
    }
}
