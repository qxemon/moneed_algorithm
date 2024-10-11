package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ13460_구슬탈출2 {

    static int N, M; // map의 행, 열
    static char[][] map;
    static int[] red = new int[2];
    static int[] blue = new int[2];

    static boolean[][][][] visited;
    static int ans;

    //상하좌우
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        visited = new boolean[N][M][N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'R') {
                    red[0] = i;
                    red[1] = j;
                    map[i][j] = '.';
                } else if (map[i][j] == 'B') {
                    blue[0] = i;
                    blue[1] = j;
                    map[i][j] = '.';
                }
            }
        }
        ans = -1;
        bfs();

        System.out.println(ans);

    }

    static void bfs() {
        Queue<simulation> queue = new ArrayDeque<>();
        queue.offer(new simulation(red[0], red[1], blue[0], blue[1], 0));
        visited[red[0]][red[1]][blue[0]][blue[1]] = true;

        //queue 시작
        while (!queue.isEmpty()) {
            simulation now = queue.poll();

            //예외. 10회 이상 구슬을 움직이면 실패
            if (now.count > 9) {
                break;
            }

            //사방탐색 1. 방향 정하기
            for (int d = 0; d < 4; d++) {
                int nRx = now.redX;
                int nRy = now.redY;
                int nBx = now.blueX;
                int nBy = now.blueY;
                int curCount = now.count;

                boolean blueGoal = false;
                boolean redGoal = false;

                //사방탐색 2. 해당 방향 끝까지 구슬 굴리기

                while (map[nBx + di[d]][nBy + dj[d]] != '#') {
                    nBx += di[d];
                    nBy += dj[d];
                    if (map[nBx][nBy] == 'O') {
                        blueGoal = true;
                        break;
                    }
                }

                while (map[nRx + di[d]][nRy + dj[d]] != '#') {
                    nRx += di[d];
                    nRy += dj[d];
                    if (map[nRx][nRy] == 'O') {
                        redGoal = true;
                        break;
                    }
                }

                //예외. blue가 들어가면 무효
                if (blueGoal) continue;

                //정답 찾음
                if (redGoal) {
                    ans = curCount + 1;
                    return;
                }

                //파란구슬과 빨간구슬이 같은 좌표로 도달했을때
                // -> 거리를 비교하여 선후관계를 파악한다.
                if (nRx == nBx && nRy == nBy) {
                    int redDist = Math.abs(nRx - now.redX) + Math.abs(nRy - now.redY);
                    int blueDist = Math.abs(nBx - now.blueX) + Math.abs(nBy - now.blueY);
                    if (redDist < blueDist) {
                        nBx -= di[d];
                        nBy -= dj[d];
                    } else {
                        nRx -= di[d];
                        nRy -= dj[d];
                    }
                }

                if (!visited[nRx][nRy][nBx][nBy]) {
                    visited[nRx][nRy][nBx][nBy] = true;
                    queue.offer(new simulation(nRx, nRy, nBx, nBy, curCount + 1));
                }


            }
        }


    }

    static class simulation {
        int redX;
        int redY;
        int blueX;
        int blueY;
        int count;

        public simulation(int redX, int redY, int blueX, int blueY, int count) {
            this.redX = redX;
            this.redY = redY;
            this.blueX = blueX;
            this.blueY = blueY;
            this.count = count;
        }
    }
}
