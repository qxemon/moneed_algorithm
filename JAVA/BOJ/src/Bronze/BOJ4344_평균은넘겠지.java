package Bronze;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class BOJ4344_평균은넘겠지 {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int members = sc.nextInt();

			int sum=0; //전체점수
			double ever=0; //평균
			int[] score = new int[members];
			for (int i = 0; i < members; i++) {
				score[i] = sc.nextInt();
				sum += score[i]; // 전체 점수 구하기
			}
			
			ever = sum / members; //평균 구하기
			int count = 0;//평균 넘는 친구들 카운트 변수
			
			for(int i = 0; i < members; i++) {
				if(score[i] > ever) {
					count++;
				}
			}
			
			double per = ((double)count / (double)members) * 100;
			
			System.out.print("#"+tc+" ");
			System.out.printf("%.3f%%\n",per);


		}

	}
}
