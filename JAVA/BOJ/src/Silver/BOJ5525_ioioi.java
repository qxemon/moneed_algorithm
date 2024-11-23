package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ5525_ioioi {

    private static int N, M;
    private static String s, p;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        s = br.readLine();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < (2 * N) + 1; i++) {
            if(i % 2 == 0) {
                sb.append("I");
            }
            else{
                sb.append("O");
            }
        }

        p = sb.toString();

        System.out.println(solve());


    }

    public static int solve() {
        int count = 0;  // 연속된 "IOI" 패턴 수
        int result = 0; // 조건에 맞는 패턴 개수

        for (int i = 1; i < M - 1; i++) {
            // "IOI" 패턴 확인
            if (s.charAt(i - 1) == 'I' && s.charAt(i) == 'O' && s.charAt(i + 1) == 'I') {
                count++; // "IOI"를 하나 발견
                if (count == N) { // N개의 "IOI"가 연속되면
                    result++; // 결과 추가
                    count--; // 중첩된 패턴 처리
                }
                i++; // "IOI"의 끝을 넘기기 위해 인덱스 증가
            } else {
                count = 0; // 패턴이 끊기면 초기화
            }
        }

        return result;
    }

}
