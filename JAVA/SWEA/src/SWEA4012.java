import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA4012 {
	static int N, MIN, R;
	static int[][] foods;
	static boolean[] select;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			N = Integer.parseInt(br.readLine());
			R = N / 2;
			foods = new int[N][N];
			select = new boolean[N];
			MIN = 20001;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					foods[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			comb(0,0);
			System.out.println("#"+tc+" "+MIN);

		}
	}

	public static void comb(int idx, int cnt) {
		if (cnt == R) {
			int a =0; int b =0;
			//배열 탐색할거임
			//1. true인 애들 (음식 A)
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(select[i] && select[j]) {
						a += foods[i][j];
					}
				}
			}
			//2.조합 false인 애들 (음식 B)
			for(int i=0;i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!(select[i] || select[j])) {
						b += foods[i][j];
					}
				}
			}
			
			MIN = Math.min(Math.abs(a-b), MIN);
			return;
		}
		if (idx == N)
			return;

		select[idx] = true;
		comb(idx + 1, cnt+1);
		select[idx] = false;
		comb(idx + 1, cnt);
	}
}
