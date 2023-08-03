import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//창고다각형
public class BOJ2304 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[] ls = new int[1001];//위치 저장 배열(범위가 1~1000이하 이므로 그냥 1001짜리 만듦)
        int N = Integer.parseInt(br.readLine()); // 기둥 개수

        //입력
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());//위치
            int H = Integer.parseInt(st.nextToken());//높이
            ls[L] = H;
        }

        //최대 높이 인덱스 찾기
        int max = 0;
        int maxIndex = 1;
        for(int i=1; i<=1000;i++) {
            if(max < Math.max(ls[i], max)) {
                max = ls[i];
                maxIndex = i;
            }
        }

        int highL = 0;
        int sumLeft = 0;
        //1. 최대 높이 인덱스 왼쪽 합(최대높이 이전)
        for(int i=1;i<maxIndex;i++) {
            if(highL < ls[i]) {
                highL = ls[i];
            }
            sumLeft += highL;
        }


        int highR = 0;
        int sumRight = 0;

        //2. 최대 높이 인덱스 오른쪽 합(최대높이까지)
        for(int i=1000; i>=maxIndex;i--) {
            if(highR < ls[i]) {
                highR = ls[i];
            }
            sumRight += highR;
        }

        int area = sumLeft + sumRight;
        System.out.println(area);
    }
}
