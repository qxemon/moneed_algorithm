package Gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1107_리모컨 {
    static boolean[] broken = new boolean[10];
    static int channel;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        channel = Integer.parseInt(s);
        int broken_num = Integer.parseInt(br.readLine());

        //BufferedReader NullPointer Exception 방지를 위함
        if (broken_num > 0) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < broken_num; i++) {
                broken[Integer.parseInt(st.nextToken())] = true;
            }

        }

        if (channel == 100) {
            System.out.println(0);
            return;
        }

        ans = Integer.MAX_VALUE;

        //1. 100번 대에서 +, - 만 눌러 도달하는 횟수
        ans = Math.abs(channel - 100);

        //2. 채널 조합 후 (1~ 500000 ) 1~6자리 확인
        for (int i = 1; i <= 6; i++) {
            perm(0, i, 0);
        }

        System.out.println(ans);

    }

    static void perm(int pick, int len, int num) {
        if (pick == len) {

            System.out.println(num);

            int count = Math.abs(channel - num) + len;

            ans = Math.min(ans, count);

            return;
        }


        for (int i = 0; i < 10; i++) {
            if (!broken[i]) {
                perm(pick + 1, len, num * 10 + i);
            }
        }

    }

}
