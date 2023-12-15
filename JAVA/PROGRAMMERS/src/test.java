import java.util.*;
import java.io.*;


public class test
{
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());// start
            arr[i][1] = Integer.parseInt(st.nextToken());
        } // end of input

        // 끝나는시간 기준 오름차순 정렬
        Arrays.sort(arr, (o1, o2) -> {
            if(o1[1] == o2[1]) return o1[0] - o2[0];
            return o1[1] - o2[1];
        });

        // 순회 -> 끝나는 시간 > 시작시간 (패스) , 강의 추가시 끝나는 시간 갱신
        int ans = 0;
        int curTime = 0;
        for (int i = 0; i < N; i++) {
            if(arr[i][0] < curTime) continue;
            else{
                ans++;
                curTime = arr[i][1];
            }
        }

        System.out.println(ans);

    }
}