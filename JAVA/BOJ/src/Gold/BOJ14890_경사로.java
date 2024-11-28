package Gold;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14890_경사로 {

    private static int N, L;
    private static int[][] map;
    private static int count;


    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("JAVA/BOJ/input/BOJ14890.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        } // end of input

        //횡 검사
        for (int i = 0; i < N; i++) {
            if(check(map[i])) count++;
        }

        //종 검사
        for (int j=0; j<N; j++) {
            int[] col = new int[N];
            for (int i = 0; i < N; i++) {
                col[i] = map[i][j];
            }
            if(check(col)) count++;
        }


        System.out.println(count);




    }

    public static boolean check(int[] path) {
        boolean[] used = new boolean[N]; // 경사로 설치 여부, 중복 체크

        for (int i = 0; i < N-1; i++) {
            if(path[i] == path[i+1]) continue;

            if(Math.abs(path[i] - path[i+1]) > 1) return false; // 1 이상 차이 나면 불가능

            if(path[i] < path[i+1]) { // 오름 경사로
                for (int j = i; j > i-L; j--){
                    if(j < 0 || used[j] || path[j] != path[i]) return false;
                    used[j] = true;
                }
            }
            else {
                for(int j = i+1; j <= i+L; j++){
                    if(j >= N || used[j] || path[j] != path[i+1]) return false;
                    used[j] = true;
                }
            }
        }
        return true;
    }
}
