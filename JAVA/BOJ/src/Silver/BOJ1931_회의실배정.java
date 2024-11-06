package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ1931_회의실배정 {

    static int N;
    static Pair[] times;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        times = new Pair[N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            times[i] = new Pair(a, b);
        }

        Arrays.sort(times); // 오름차순 정렬


        int e = 0;
        int ans = 0;

        for (int i = 0; i < N; i++) {
            if(times[i].start >= e){
                e = times[i].end;
                ans++;
            }
        }

        System.out.println(ans);

    }

    static class Pair implements Comparable<Pair> {
        int start, end;
        public Pair(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Pair o) {
            if(this.end == o.end){
                return this.start - o.start;
            }
            return this.end - o.end;

        }

        @Override
        public String toString() {
            return "Pair{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }


}
