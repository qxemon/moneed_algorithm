import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//롤 케이크
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int L = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        int[] pi = new int[N+1];
        int[] ki = new int[N+1];
        int[] cake = new int[L+1];

        int expectData = 0;
        int expectIndex = 0;
        int realData = 0;
        int realIndex = 0;
        for(int i=1;i<N+1;i++){
            st = new StringTokenizer(br.readLine());
            pi[i] = Integer.parseInt(st.nextToken()); //시작 번호
            ki[i] = Integer.parseInt(st.nextToken()); //끝번호
            int currentData = ki[i]-pi[i]+1;
            if(expectData < currentData){//가장 많이 받을것같은 방청객 번호 찾기
                expectIndex = i;
                expectData = currentData;
            }
        }

        for(int i=1; i<N+1; i++){ //방청객 순서대로 탐색
            int count = 0;
            for(int j=pi[i];j<=ki[i];j++){
                if(cake[j] == 0){
                    cake[j] = 1; //이미 가져갔습니다
                    count++;
                }
            }
            if(count > realData){
                realData = count;
                realIndex =i;
            }
        }

        System.out.println(expectIndex);
        System.out.println(realIndex);

    }
}