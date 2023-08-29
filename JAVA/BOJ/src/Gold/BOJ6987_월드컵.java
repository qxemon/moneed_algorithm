package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ6987_월드컵 {

    static int[][] schedule = {
            {0,0,0,0,0,1,1,1,1,2,2,2,3,3,4},
            {1,2,3,4,5,2,3,4,5,3,4,5,4,5,5}
    }; //일어날 경기의 경우의 수 15가지

    static int[][] board;
    static int[][] made;
    static int ans;

    static final int WIN = 0, DRAW = 1, LOSE = 2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int tc = 0; tc < 4; tc++) {
            board = new int[6][3];
            made = new int[6][3];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            } // end input
            ans = 0;
            simulation(0);

            System.out.print(ans+" ");
        }
    }

    private static void simulation(int day) {
        if(day == 15){
            if (check()){
                ans = 1;
            }
            return;
        }
        int team1 = schedule[0][day];
        int team2 = schedule[1][day];
        //1. team 1이 이기는 경우 (team 2가 지는 경우)
        if(made[team1][WIN] + 1 <= board[team1][WIN] && made[team2][LOSE] + 1 <= board[team2][LOSE]){ // 아직 누적 가능
            made[team1][WIN]++;
            made[team2][LOSE]++;
            simulation(day + 1);
            made[team1][WIN]--;
            made[team2][LOSE]--; // 되돌려 놔야지 나머지 if 문들이 제대로 된 값을 가짐!
        }
        
        //2. 무승부
        if(made[team1][DRAW]+1 <= board[team1][DRAW] && made[team2][DRAW] + 1 <= board[team2][DRAW]){
            made[team1][DRAW]++;
            made[team2][DRAW]++;
            simulation(day + 1);
            made[team1][DRAW]--;
            made[team2][DRAW]--;
        }
        
        //3. team 1이 지는 경우 ( team 2가 이기는 경우)
        if(made[team1][LOSE] + 1 <= board[team1][LOSE] && made[team2][WIN] + 1 <= board[team2][WIN]){ // 아직 누적 가능
            made[team1][LOSE]++;
            made[team2][WIN]++;
            simulation(day + 1);
            made[team1][LOSE]--;
            made[team2][WIN]--;
        }
    }

    private static boolean check() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                if(board[i][j] != made[i][j]) return false; // 예정과 일치하지 않아
            }
        }
        return true;
    }


}
