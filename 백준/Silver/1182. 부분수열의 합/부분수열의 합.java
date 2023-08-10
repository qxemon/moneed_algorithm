import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, S, result;
    static int[] arr;
    static boolean[] isSelected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        isSelected = new boolean[N];
        result = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        subSet(0,0,0);

        System.out.println(result);

    }

    public static void subSet(int idx, int sum, int cnt){
        if(idx == N){
            if(cnt == 0) return; //공집합 처리
            if(sum == S){
                result++;
            }
            return;
        }
         // 더 조합할 필요 x -> 가지치기

        isSelected[idx] = true;
        subSet(idx+1, sum+arr[idx],cnt+1);
        isSelected[idx] = false;
        subSet(idx+1, sum, cnt);
    }
}