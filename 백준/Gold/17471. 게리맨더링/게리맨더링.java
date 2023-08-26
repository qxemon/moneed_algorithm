import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;

	static int[] peoples;
	static List<List<Integer>> g;
	static boolean[] select;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 구역의 개수
		StringTokenizer st = new StringTokenizer(br.readLine());
		peoples = new int[N + 1];
		for (int i = 1; i < peoples.length; i++) {
			peoples[i] = Integer.parseInt(st.nextToken());
		} // 각 구역별 인구수를 배열에 저장
		g = new ArrayList<List<Integer>>();
		for (int i = 0; i < N + 1; i++) {
			g.add(new ArrayList<>());
		}
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()); // 인접한 구역의 수
			for (int j = 0; j < num; j++) {
				int a = Integer.parseInt(st.nextToken());
				g.get(i).add(a);
//				g.get(a).add(i);
			}
		} // end input graph

//		for (int i = 1; i < g.size(); i++) {
//			int size = g.get(i).size();
//			System.out.print(i+": ");
//			for (int j = 0; j < size; j++) {
//				System.out.print(g.get(i).get(j)+" ");
//			}
//			System.out.println();
//		}
		select = new boolean[N + 1];
		ans = Integer.MAX_VALUE;

		subSet(1); // 정점은 1부터 시작한다.

		System.out.println(ans != Integer.MAX_VALUE ? ans : -1);

	}// end of main

	static void subSet(int idx) {
		if (idx == g.size()) {
			int countT = 0; // true의 개수
			int countF = 0; // false의 개수
			boolean go = false;

			for (int i = 1; i < select.length; i++) {
				if (select[i]) {
					countT++;
//					System.out.print(""+i + " ");

				} else {
					countF++;
				}
			}//개수 갱신
			
//			System.out.println();
			if (countT == N || countF == N) {
//				System.out.println("ㅂㅂ");
				
				return; // 하나의 영역으로만 채울 수 없음
			}
				

			// 1. 영역들이 다 연결되어 있나요?
			go = connectCheck(countT, countF);

			// 2. 다 연결 되었으면 최솟값을 계산해요
			if (go) {
				int a = 0, b = 0;
				for (int i = 1; i < select.length; i++) {
					if (select[i]) {
						a += peoples[i];
					} else {
						b += peoples[i];
					}
				}
				int dif = Math.abs(a - b);
				ans = Math.min(dif, ans);
			}
			return;
		}

		select[idx] = true;
		subSet(idx + 1);
		select[idx] = false;
		subSet(idx + 1);

	}

	static boolean connectCheck(int countT, int countF) {

//		System.out.println(countT + " " + countF);

		boolean isConnectT = false;
		boolean isConnectF = false;

		for (int i = 1; i < select.length; i++) {
			if (select[i]) {
				if (isConnectT)
					continue;
				int count = bfs(i, select[i], new boolean[N + 1]);
//				System.out.println("countT: " + count);
				if (count == countT)
					isConnectT = true;
			} else {
				if (isConnectF)
					continue;
				int count = bfs(i, select[i], new boolean[N + 1]); //부분집합 완성 시에 T의 개수와 bfs로 인접한 상태같은 정점의 개수가 같은지 -> 전부 연결됐는지 확인
//				System.out.println("countF: " + count);
				if (count == countF)
					isConnectF = true;
			}

			if (isConnectF && isConnectT) { // 영역이 잘 나뉘어졌어
				return true;
			}
		}

		return false;
	}

	static int bfs(int start, boolean status, boolean[] visit) {
		int cnt = 0;
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(start);
		visit[start] = true;
		cnt++; // 맨처음 정점도 카운팅 해줘야함
		while (!queue.isEmpty()) {
//			System.out.println(queue);
			int vertex = queue.poll();
			for (int i = 0; i < g.get(vertex).size(); i++) {
				if (select[g.get(vertex).get(i)] == status && !visit[g.get(vertex).get(i)]) {
					cnt++;
					visit[g.get(vertex).get(i)] = true;
					queue.offer(g.get(vertex).get(i));
				}
			}
		}
//		System.out.println("cnt: "+ cnt);
		return cnt;
	}

}