import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA4008_숫자만들기_sol {
    static int N;
    static int[] num;
    static int max, min;
    static int[] op;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=TC; tc++) { //50
            N = Integer.parseInt(br.readLine()); // 숫자의 개수 N 3~ 12
            StringTokenizer st = new StringTokenizer(br.readLine());

            op = new int[4];
            for(int i=0; i<op.length;i++){
                op[i] = Integer.parseInt(st.nextToken());//연산자 카드 개수 4개(+, -, *, /)
            }
            num = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<num.length;i++){
                num[i] = Integer.parseInt(st.nextToken());//N개 숫자 1~9
            }

            //최댓값 변수 초기화
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;//최소값 변수 초기화

            dfs(1,num[0]); //보내

            sb.append("#").append(tc).append(" ").append(max-min).append("\n");

        }//end of tc
        System.out.println(sb.toString());
    }//end main

    /** 연산자 카드로 만들 수 있는 모든 순열 만들기, 완성시 계산 최대값, 최소값 갱신
     * 재귀 장점 최대화 하려면 재귀 프레임 안에 지역변수 저장, 콜스택을 이용한 자료 이용
     * step: 단계, val: 계산결과 */
    static void dfs(int step, int val){
        if (step == N) {//종료파트
            // 최대값, 최소값업데이트
            if(max < val) max = val;
            if(val < min) min = val;
            return;
        }
        //재귀파트
        for(int i=0; i<op.length;i++){
            if(op[i] == 0) continue;
            op[i]--;//연산개수 감소
            switch (i){        //재귀호출 연산 결과를 보냄
                case 0:
                    dfs(step+1, val+num[step]);
                    break;
                case 1:
                    dfs(step+1, val-num[step]);
                    break;
                case 2:
                    dfs(step+1, val*num[step]);
                    break;
                case 3:
                    dfs(step+1, val/num[step]);
                    break;
            }
            op[i]++;//연산개수 원복

        }


    }//end of dfs
}// end of class

