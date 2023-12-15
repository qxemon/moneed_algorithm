import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KnapSackTest {

    static int[][] arr;

    static int dfs(int[][] dp, int idx, int curWeight) {
        if (idx == arr.length) {
            return 0;
        }
        if (dp[idx][curWeight] != -1) {
            return dp[idx][curWeight];
        }

        dp[idx][curWeight] = 0;

        // 안들었따
        dp[idx][curWeight] = Math.max(dp[idx][curWeight], dfs(dp, idx + 1, curWeight));

        // 들었다
        if (arr[idx][0] <= curWeight) {
            dp[idx][curWeight] = Math.max(dp[idx][curWeight], dfs(dp, idx + 1, curWeight - arr[idx][0]) + arr[idx][1]);
        };

        return dp[idx][curWeight];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);
        arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            split = br.readLine().split(" ");
            arr[i][0] = Integer.parseInt(split[0]);
            arr[i][1] = Integer.parseInt(split[1]);
        }
        int[][] dp = new int[n][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m + 1; j++) {
                dp[i][j] = -1;
            }
        }
        dfs(dp, 0, m);
    }
}
