package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ9019_DSLR {
    private static final String[] arr = {"D", "S", "L", "R"};

    private static int T;
    private static boolean[] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int goal = Integer.parseInt(st.nextToken());
            visited = new boolean[10005];

            sb.append(bfs(start, goal)).append("\n");
        }

        System.out.println(sb.toString());
    }


    public static String bfs(int start, int goal) {
        Queue<State> queue = new ArrayDeque<>();
        visited[start] = true;
        queue.add(new State(start, ""));
        while (!queue.isEmpty()) {
            State cur = queue.poll();

            if(cur.num == goal) {
                return cur.path;
            }

            for (int d = 0; d < 4; d++) {
                int result = chooseMethod(cur.num, d);
                if(!visited[result]){
                    visited[result] = true;
                    queue.add(new State(result, cur.path + arr[d]));
                }
            }
        }


        return "";
    }

    static class State{
        int num;
        String path;

        public State(int num, String path) {
            this.num = num;
            this.path = path;
        }
    }

    public static int chooseMethod(int s, int num) {
        switch (num) {
            case 0:
                return d(s);
            case 1:
                return s(s);
            case 2:
                return l(s);
            case 3:
                return r(s);
        }

        return 0;
    }

    public static int d(int s) {
        return (s * 2) % 10000;
    }

    public static int s(int s) {

        if (s == 0) {
            return 9999;
        } else {
            return s - 1;
        }
    }

    public static int l(int s) {
        int left = s * 10;
        int share = left / 10000;
        int rest = left % 10000;

        return rest + share;


    }

    public static int r(int s) {

        int share = s / 10;
        int rest = (s % 10) * 1000;

        return rest + share;
    }

}
