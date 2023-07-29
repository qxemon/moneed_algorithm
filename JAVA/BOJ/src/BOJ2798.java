import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//블랙잭
public class BOJ2798 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 카드의 개수
        int M = Integer.parseInt(st.nextToken()); // 근접해야할 숫자
        st = new StringTokenizer(br.readLine()); //카드의 숫자들
        int[] cards = new int[N]; //카드 숫자들을 담을 배열
        for(int i=0;i<N;i++){
            cards[i] = Integer.parseInt(st.nextToken());
        }

        //3개의 카드의 수의 합을 구하는거니까 3중for문 쓰겠음
        int sum=0;
        int max=0;
        for(int i=0;i<N-2;i++){
            for(int j =i+1; j<N-1;j++){
                for(int k=j+1;k<N;k++){
                    sum = cards[i]+cards[j]+cards[k]; //매번 합 값이 초기화 돼야 하니 k 안에다
                    if(sum <= M){// 블랙잭값 합은 M을 넘길수 없으니까
                        max = Math.max(sum, max);
                    }
                }
            }
        }

        System.out.println(max);
    }
}
