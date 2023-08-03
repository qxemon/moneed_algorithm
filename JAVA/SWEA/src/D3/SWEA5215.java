package D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA5215 {
	static int[] score, kcal;
	static int N, L, MaxSatis;
	static boolean[] select;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			score = new int[N];
			kcal = new int[N];
			select = new boolean[N];
			MaxSatis = 0;
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				score[i] = Integer.parseInt(st.nextToken());
				kcal[i] = Integer.parseInt(st.nextToken());
			}
			sub(0);
			//sub2(0,0,0);
			System.out.println("#"+tc+" "+MaxSatis);
		}
	}

	public static void sub(int idx) {
		if(idx == N) {
			int sum=0, max=0;
			for(int i=0;i<N;i++) {
				if(select[i]) {
					sum+=kcal[i];
				}
			}
			if(sum <= L) {
				for(int i=0;i<N;i++) {
					if(select[i]) {
						max += score[i];
					}
				}
			}
			MaxSatis = Math.max(max, MaxSatis);
			return;
		}
		
		select[idx] = true;
		sub(idx+1);
		select[idx] = false;
		sub(idx+1);
		
	}
	
	public static void sub2(int idx, int sum, int max) {
		if(idx == N) {
			if(sum <= L) {
				MaxSatis = Math.max(max, MaxSatis);
			}
			return;
		}
		
		select[idx] = true;
		sub2(idx+1, sum+kcal[idx], max+score[idx]);
		select[idx] = false;
		sub2(idx+1, sum, max);
		
	}
	
}
