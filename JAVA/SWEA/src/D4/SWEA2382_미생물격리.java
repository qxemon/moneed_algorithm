package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA2382_미생물격리 {

	static int[] di = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int[] dj = { 0, 0, -1, 1 };

	static int N, M, K; // 셀의 개수, 격리 시간, 미생물 군집 수
	static int ans;
	static ArrayList<Micro> mPop; //미생물 군집 객체들을 모아둘 리스트

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken().trim());
			M = Integer.parseInt(st.nextToken().trim());
			K = Integer.parseInt(st.nextToken().trim());

			mPop = new ArrayList<>(); // 미생물 군집 리스트 생성
			ans  = 0;

			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine().trim());
				int i = Integer.parseInt(st.nextToken());
				int j = Integer.parseInt(st.nextToken());
				int population = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				mPop.add(new Micro(i,j,population,dir-1));
			}// end input

			for (int m = 0; m < M; m++) { // m 시간 동안 진행
				//1. 미생물 이동
				for (int i = 0; i < mPop.size(); i++) {
					Micro now = mPop.get(i);
					int ni = now.i + di[now.dir];
					int nj = now.j + dj[now.dir];
					if(ni == 0 || ni == N-1 || nj == 0 || nj == N-1){ // 약품이 발려진 곳 도착
						now.population /= 2; // 절반 사망
						now.dir = reverseDir(now.dir); // 방향은 반대
						now.i = ni;
						now.j = nj;
					}
					else{
						now.i = ni;
						now.j = nj;
					}
					if(now.population == 0 ){
						mPop.remove(i);
						i--; // 왜 줄이냐면 어레이리스트는 지우면 인덱스가 당겨짐
					}
					now.num = now.i * N + now.j; // 지금 위치하는 좌표 번호 갱신
				}// 이동 종료

				//2. 같은 좌표 미생물 병합
				Collections.sort(mPop); // 일단 내림차순으로 새롭게 정렬
//				System.out.println(mPop);

				for (int i = 0; i < mPop.size()-1; i++) {
					Micro big = mPop.get(i);
					Micro small = mPop.get(i+1);
					if(big.num == small.num){ // 같은 좌표다!!
						big.population += small.population; // 미생물 수 병합!
						mPop.remove(i+1); // 잘 가
						i--; // 다시 이전 녀석과 비교해봐야지
					}
				}
			} // 이동 종료

			for(Micro m : mPop){
				ans += m.population;
			}


			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}//end of tc
		System.out.println(sb.toString());
	}

	static int reverseDir(int d){
		switch (d){
			case 0:
				return 1;
			case 1:
				return 0;
			case 2:
				return 3;
			case 3:
				return 2;
		}
		return -1;
	}

	static class Micro implements Comparable<Micro> {
		int i, j, population, dir, num;

		public Micro(int i, int j, int population, int dir) {
			super();
			this.i = i;
			this.j = j;
			this.population = population;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "Micro [i=" + i + ", j=" + j + ", population=" + population + ", dir=" + dir + "]";
		}

		@Override
		public int compareTo(Micro o) { // 1. 있는 번호판 위치(좌표를 변환한거)별로 내림차순 정렬
			if (this.num > o.num) {
				return -1;
			} else if (this.num < o.num) {
				return +1;
			} else { // 좌표가 같은 경우 (미생물 수로 내림차순 정렬)
				return o.population - this.population;
			}

		}

	}
}
