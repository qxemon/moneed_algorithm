package Bronze;

import java.util.Scanner;

public class BOJ13300_방배정 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[][] student = new int [2][7]; //성별과  학년을 저장할 배열
		int res = 0; //방의 개수를 저장할 배열
		
		for(int i=0; i<N; i++) {
			int S = sc.nextInt();
			int Y = sc.nextInt();
			
			student[S][Y]++; //학생담기
		}
		
		
		//방배정
		for(int i=0; i<2;i++) { //(0~1)
			for(int j=1; j<7; j++) { //
				res += student[i][j] / K;
				
				if(student[i][j] % K > 0) { // 해당 성별/학년 학생 인원이 남아도 -> 방을 추가로 줘야함
					res++;
				}
			}
		}
		
		
		System.out.println(res);
		
	}
}
