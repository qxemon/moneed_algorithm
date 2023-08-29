import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static long[][][] memo;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        map = new int[N][N];
        memo = new long[N][N][3]; //0: 가로로 오는 것 1: 대각선 오는 것, 2: 세로로 오는 것 개수 저장할거임

        memo[0][1][0] = 1;// 초기화


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if(map[N-1][N-1] == 1){
            System.out.println(0);
            return;
        }


        for (int i = 0; i < N; i++) {
            for (int j = 2; j < N; j++) {
                if(map[i][j] == 1){
                    memo[i][j][0] = memo[i][j][1]= memo[i][j][2] = 0;
                }
                else{
                    memo[i][j][0] = memo[i][j-1][0] +memo[i][j-1][1];
                    if(i>=1){
                        if(map [i-1][j] != 1 && map[i][j-1] != 1){
                            memo[i][j][1] = memo[i-1][j-1][0]+memo[i-1][j-1][1]+memo[i-1][j-1][2];
                        }
                        memo[i][j][2] = memo[i-1][j][1]+memo[i-1][j][2];
                    }

                }
            }
        }

        long ans = memo[N-1][N-1][0] + memo[N-1][N-1][1] + memo[N-1][N-1][2];

        System.out.println(ans);




    }

}