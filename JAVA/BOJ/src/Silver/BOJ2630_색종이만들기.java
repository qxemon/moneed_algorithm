package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2630_색종이만들기 {
    static int[][] paper;
    static int N;
    static int white, blue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        paper = new int[N][N];
        white = 0;
        blue = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        } // end of input

        divide(0,0,N);

        System.out.println(white);
        System.out.println(blue);

    }

    public static void divide(int sr, int sc, int size){
        //기저: 영역이 모두 같은 색인가?
        int leftFirst = paper[sr][sc]; // 좌상단 색을 파악
        boolean same = true;

        //레이블 정의
        outerLoop:
        for (int i = sr; i < sr+size; i++) {
            for (int j = sc; j < sc+size; j++) {
                int color = paper[i][j];
                if(color != leftFirst){
                    same = false;
                    break outerLoop;
                }
            }
        }

        if(same){
            if(leftFirst == 1){
                blue++;
            }
            else if (leftFirst == 0){
                white++;
            }
        }
        else{
            int half = size / 2;
            //네 갈래로 재귀
            divide(sr, sc, half);
            divide(sr, sc+half, half);
            divide(sr+half, sc, half);
            divide(sr+half, sc+half, half);
        }
    }
}
