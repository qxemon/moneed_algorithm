import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//오목판정
public class SWEA11315 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            int n = Integer.parseInt(br.readLine());
            char[][] map = new char[n][n];

            // 오목 알 넣기
            for (int i = 0; i < n; i++) {
                String line = br.readLine();
                for (int j = 0; j < n; j++) {
                    char c = line.charAt(j);
                    map[i][j] = c;
                }
            }
            System.out.println("#" + tc + " " + solve(map, n));

        }
    }

    public static String solve(char[][] map, int n) {

        // 탐색
        // 나는... O를 만나면 오른쪽으로 5칸, 아래로 5칸, 왼쪽 대각선 아래 5칸, 오른쪽 대각선 아래 5칸으로 탐색할거야

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int count = 0;
                if(map[i][j] == 'o') {//오목알 발견
                    //1. 오른쪽 5칸 탐색
                    if(j+4 < n) { // 범위 초과 확인
                        for(int k=j;k<j+5;k++) {
                            if(map[i][k]=='o')
                                count++;
                            else
                                break;


                        }
                        if(count == 5) {
                            return "YES";
                        }
                        count = 0;
                    }

                    //2. 아래 5칸 탐색
                    if(i+4 < n) {
                        for(int k=i;k<i+5;k++) {
                            if(map[k][j] =='o')
                                count++;
                            else
                                break;

                        }
                        if(count==5) {
                            return "YES";
                        }
                        count = 0;
                    }

                    //3. 좌측 대각선 아래 탐색
                    if(i+4<n && j-4>=0) {
                        for(int t=0;t<5; t++) {
                            if(map[i+t][j-t] =='o')
                                count++;
                            else
                                break;

                        }
                        if(count==5) {
                            return "YES";
                        }
                        count = 0;
                    }

                    //4. 우측 대각선 아래 탐색
                    if(i+4<n && j+4<n) {
                        for(int t=0;t<5;t++) {
                            if(map[i+t][j+t] == 'o')
                                count++;
                            else
                                break;

                        }
                        if(count==5) {
                            return "YES";
                        }
                        count = 0;
                    }

                }
            }
        }

        return "NO";
    }
}

