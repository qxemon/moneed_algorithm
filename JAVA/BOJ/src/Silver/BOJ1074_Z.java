package Silver;

import java.util.Scanner;

public class BOJ1074_Z {
    static int r;
    static int c;
    static int quad;
    static int result;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 배열의 크기 2^N * 2^N
        r = sc.nextInt();//목표좌표 행
        c = sc.nextInt();//목표좌표 열
        result = 0;
        double size = Math.pow(2.0, (double)N);// 배열의 크기 2^N * 2^N

        quad = 0;


        divide(0,0,(int)size);

    }

    static void divide(int sr, int sc, int size){
        if(size == 2){//기저조건: 최대한으로 쪼갬
            if(sr == r && sc == c){
                System.out.println(result);
            }
            else if(sr == r && sc+1 == c){
                System.out.println(result+1);
            }
            else if(sr+1 == r && sc == c){
                System.out.println(result+2);
            }
            else if(sr+1 == r && sc+1 == c){
                System.out.println(result+3);
            }

            return;
        }

        //쪼갤거야
        int half = size/2;
        int q = quad(sr, sc, half);
        int idx = half * half;
        switch (q){
            case 1:
                result += idx;
                divide(sr, sc+half, half);
                break;
            case 2:
                divide(sr, sc, half);
                break;
            case 3:
                result += idx * 2;
                divide(sr+half, sc, half);
                break;
            case 4:
                result += idx * 3;
                divide(sr+half, sc+half, half);
                break;
        }
    }

    static int quad(int row, int col, int mid){
        if(r < row + mid && c >= col + mid){
            quad = 1;
        }
        else if(r < row + mid && c < col + mid){
            quad = 2;
        }
        else if (r >= row + mid && c < col + mid){
            quad = 3;
        }
        else {
            quad = 4;
        }
        return quad;
    }

}
