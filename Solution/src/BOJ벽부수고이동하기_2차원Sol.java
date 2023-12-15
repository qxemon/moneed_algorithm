import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ벽부수고이동하기_2차원Sol {

    static class Position {
        int y, x, canBreak, count;

        Position(int y, int x, int canBreak, int count) {
            this.y = y;
            this.x = x;
            this.canBreak = canBreak;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++)
                map[i][j] = line.charAt(j) - '0';
        }

        int[][] visited = new int[N][M];
        int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        Queue<Position> queue = new ArrayDeque<>();
        queue.add(new Position(0, 0, 1, 1));

        while (!queue.isEmpty()) {
            Position curr = queue.poll();

            if ((visited[curr.y][curr.x] & (1 << curr.canBreak)) > 0)
                continue;

            visited[curr.y][curr.x] |= (1 << curr.canBreak);

            if (curr.y == N - 1 && curr.x == M - 1) {
                System.out.println(curr.count);
                return;
            }

            for (int[] dir : dirs) {
                int ny = curr.y + dir[0];
                int nx = curr.x + dir[1];

                if (ny < 0 || ny >= N || nx < 0 || nx >= M)
                    continue;

                if (map[ny][nx] == 0 && (visited[ny][nx] & (1 << curr.canBreak)) == 0)
                    queue.add(new Position(ny, nx, curr.canBreak, curr.count + 1));
                else if (curr.canBreak == 1 && map[ny][nx] == 1 && (visited[ny][nx] & 2) == 0)
                    queue.add(new Position(ny, nx, 0, curr.count + 1));
            }
        }
        System.out.println("-1");
    }
}

