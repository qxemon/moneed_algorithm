package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ2239_스도쿠 {

    static int[][] sudoku;
    static char[] convert;
    static ArrayList<Integer> zeroList;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        sudoku = new int[9][9];

        zeroList = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            convert = br.readLine().toCharArray();
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = convert[j] - '0';
                if(sudoku[i][j] == 0){
                    zeroList.add(9*i+j);
                }
            }
        }

//        print();

        dfs(0);
    }



    static void dfs(int count){

        if(count == zeroList.size()){
            print();
            System.exit(1); // 그냥 처음 구한게 가장 작은 값이니까 함수를 종료한다.
        }

        //여기가 코드

        //바꿀 좌표를 구하자
        int ni = zeroList.get(count) / 9;
        int nj = zeroList.get(count) % 9;

        boolean[] check = new boolean[10];
        //검사 1. 가로
        for (int i = 0; i < 9; i++) {
            if(sudoku[ni][i] != 0){
                check[sudoku[ni][i]] = true; // 쓰였다.
            }
        }
        //검사 2. 세로
        for (int i = 0; i < 9; i++) {
            if(sudoku[i][nj] != 0){
                check[sudoku[i][nj]] = true; // 쓰였다.
            }
        }

        //3*3 검사
        int startI = (ni/3) * 3;
        int startJ = (nj/3) * 3;
        for (int i = startI; i < startI+3; i++) {
            for (int j = startJ; j < startJ+3; j++) {
                if(sudoku[i][j] != 0){
                    check[sudoku[i][j]] = true;
                }
            }
        }

        //검사 end

        //이제 맵을 채울 숫자 정할거임
        for (int i = 1; i <= 9; i++) {
            if(!check[i]){
                // 아직 안쓰였다는 거임, 그리고 자연스럽게 작은 수부터 채우게 돼서 사전상 작은 수 채우게 됨
                sudoku[ni][nj] = i;
                dfs(count+1);
                sudoku[ni][nj] = 0;

            }
        }

    }
    static void print(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j]);
            }
            System.out.println();
        }
    }


}
