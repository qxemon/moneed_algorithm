package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ11000_강의실배정 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());// start
            arr[i][1] = Integer.parseInt(st.nextToken());
        } // end of input

        // 시작시간 기준 오름차순 정렬
        Arrays.sort(arr, (o1, o2) -> {
            if(o1[0] == o2[0]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });

        // 우선 순위 큐를 만들어서 배열의 첫 end값 큐에 넣음
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(arr[0][1]);

        //pq의 개수가 곧 필요한 강의실의 개수가 될 것임
        //순회하면서 강의의 시작시간이 pq 맨 앞 값보다 작거나 같으면 poll 해서 빼줌
        for (int i = 1; i < N; i++) {
            if(pq.peek() <= arr[i][0]) pq.poll();
            pq.add(arr[i][1]);
        }

        System.out.println(pq.size());




    }
}
