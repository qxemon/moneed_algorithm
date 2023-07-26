import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1974 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            int[][] sudoku = new int[9][9];

            // 스도쿠 숫자 넣기
            for (int i = 0; i < 9; i++) {
                String line = br.readLine();
                st = new StringTokenizer(line);
                for (int j = 0; j < 9; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    sudoku[i][j] = num;
                }
            }

            // 방향 배열들
            int[] di = { 0, 1, 1, 1, 0, -1, -1, -1 };
            int[] dj = { -1, -1, 0, 1, 1, 1, 0, -1 };

            int sol = 1;

            // 탐색
            for (int i = 0; i < 9; i++) {
                int rSum = 0; // 행(가로 합)
                int cSum = 0; // 열(세로 합)
                int aSum = 0; // 내부 9칸 합
                for (int j = 0; j < 9; j++) {
                    // 가로 합 체크 (1~9 잘 들어가면 45가 되겠지?)
                    rSum += sudoku[i][j];
                    cSum += sudoku[j][i];

                    // 가운데 9개 비교 (중간 값 1,4,7) -> 범위 초과 안되겠지?? 라고 생각중
                    if (i % 3 == 1 && j % 3 == 1 && i == j) {
                        aSum += sudoku[i][j];
                        for (int k = 0; k < 8; k++) {
                            int nextI = i + di[k];
                            int nextJ = j + dj[k];
                            aSum += sudoku[nextI][nextJ];
                        }
                        if (aSum != 45) {
                            sol = 0;
                            break;// j 나감
                        }
                    }

                }

                //가운데 값에서 이미 실패 검증 됐거나, 열, 행이 맞지 않으면
                if (sol == 0 || (rSum != 45 || cSum != 45)) {
                    sol = 0;
                    break; // i 나감
                }

            }

            System.out.println("#" + tc + " " + sol);
        }

    }
}

