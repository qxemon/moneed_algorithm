package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ18405_경쟁적전염 {
    static int[][] map;
    static ArrayList<Virus> list;
    static boolean[][] visited;

    static int[] di = {-1,1,0,0};
    static int[] dj = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // nxn 격자
        int k = Integer.parseInt(st.nextToken()); // k번개의 바이러스

        map = new int[n][n];
        visited = new boolean[n][n];
        list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] != 0){
                    list.add(new Virus(i,j,map[i][j]));
                    visited[i][j] = true;
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken()); // s 초후
        int x = Integer.parseInt(st.nextToken()); // 찾을 바이러스 위치의 행
        int y = Integer.parseInt(st.nextToken()); // 찾을 바이러스 위치의 열
        // end of input

        Collections.sort(list);
//        System.out.println(list.toString());

        for (int i = 0; i < s; i++) {
            int size = list.size();
            for (int j = 0; j < size; j++) {
                Virus now = list.get(0);
                list.remove(0);
                for (int d = 0; d < 4; d++) {
                    int ni = now.row + di[d];
                    int nj = now.col + dj[d];
                    if(ni >= 0 && ni < n && nj >= 0 && nj < n && !visited[ni][nj]){
                        list.add(new Virus(ni,nj,now.type));
                        map[ni][nj] = now.type;
                        visited[ni][nj] = true;
                    }
                }
            }
            Collections.sort(list);
//            System.out.println(list.toString());
        }

//        print(n);
        System.out.println(map[x-1][y-1]);


    }

    static void print(int n){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    static class Virus implements Comparable<Virus> {
        int row, col, type;

        public Virus(int row, int col, int type) {
            this.row = row;
            this.col = col;
            this.type = type;
        }


        @Override
        public String toString() {
            return "Virus{" +
                    "row=" + row +
                    ", col=" + col +
                    ", type=" + type +
                    '}';
        }

        @Override
        public int compareTo(Virus o) {
            return this.type - o.type;
        }
    }
}
