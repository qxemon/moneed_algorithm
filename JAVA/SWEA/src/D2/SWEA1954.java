package D2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//달팽이 숫자
public class SWEA1954 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        // 우하좌상
        int[] deltai = { 0, 1, 0, -1 };
        int[] deltaj = { 1, 0, -1, 0 };

        for (int tc = 1; tc <= t; tc++) {
            int n = Integer.parseInt(br.readLine());

            int[][] snail = new int[n][n];

            //초기값 설정
            int i = 0, j = 0, cnt = 1, d = 0;
            snail[i][j] = cnt;
            cnt++;

            int nextI, nextJ;

            while (true) {
                if (cnt > (n * n)) {
                    break;
                }
                nextI = i + deltai[d];
                nextJ = j + deltaj[d];

                //증가충족 조건을 세우고 그 안에서 행동하기
                if(nextI < n && nextI >= 0 && nextJ >= 0 && nextJ < n && snail[nextI][nextJ] == 0){
                    i = nextI;
                    j = nextJ;
                    snail[i][j] = cnt;
                    cnt++;
                }
                else{ //if문 만족안하면 뭔가 진행에 문제가 있는거니까 방향 전환!
                    d = (d+1) % 4;
                }
            }

            System.out.println("#"+tc);
            for (int k = 0; k < n; k++) {
                for (int h = 0; h < n; h++) {
                    System.out.print(snail[k][h] + " ");
                }
                System.out.println();
            }

        }
    }
}

