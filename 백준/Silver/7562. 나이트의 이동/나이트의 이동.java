import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int starti, startj, goali, goalj;
    static int I, cnt;

    static int[] di = { -1, -2 ,-2 ,-1 ,1, 2, 2, 1}; //나이트 움직임 좌상좌, 좌상상, 우상상, 우상우, 우하우, 우하하, 좌하하, 좌하좌
    static int[] dj = {-2, -1, 1, 2, 2, 1, -1 ,-2};
    static boolean[][] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            I = Integer.parseInt(br.readLine()); // 체스판 크기
            visit = new boolean[I][I];
            st = new StringTokenizer(br.readLine());
            starti = Integer.parseInt(st.nextToken()); //시작점i
            startj = Integer.parseInt(st.nextToken());//시작점j
            st = new StringTokenizer(br.readLine());
            goali = Integer.parseInt(st.nextToken());//도착점i
            goalj = Integer.parseInt(st.nextToken());//도착점j

            cnt = 0;
            bfs(starti,startj);
            System.out.println(cnt);
        }
    }


    static void bfs(int si, int sj){
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(si,sj));
        visit[si][sj] = true;
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size-- != 0){
                Point now = queue.poll();
                if (now.i == goali && now.j == goalj) return;
                for (int i = 0; i < 8; i++) {
                    int ni = now.i + di[i];
                    int nj = now.j + dj[i];
                    if(ni>=0 && ni<I && nj>=0 && nj<I && !visit[ni][nj]){
                        queue.add(new Point(ni, nj));
                        visit[ni][nj] = true;
                    }
                }

            }
            cnt++;
        }
    }

    static class Point{
        int i, j;
        Point(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
}