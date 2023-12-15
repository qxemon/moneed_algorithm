package D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA9229_한빈이와spotMart {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			String nm = br.readLine();
			st = new StringTokenizer(nm);
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			int[] snacks = new int[N];
			//과자 무게 배열 값 넣기
			for(int i=0;i<N;i++) {
				snacks[i] = Integer.parseInt(st.nextToken());
			}
			
			//탐색
			int max = -1;
			int sum = 0;
			for(int i=0;i<snacks.length;i++) {
				for(int j=i+1;j<snacks.length;j++) {
					sum = 0;
					sum = snacks[i]+snacks[j];
					if(sum <= M) { //MAX 값 보다 작거나 같으면
						max = Math.max(sum, max);
					}
				}
			}
			
			System.out.println("#"+tc+" "+max);
		}

	}
}
