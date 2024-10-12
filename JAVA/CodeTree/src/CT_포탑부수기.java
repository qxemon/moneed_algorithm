import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CT_포탑부수기 {
	static final int MAX_N = 10;
	static final int MAX_M = 10;
	static final int MAX_K = 1000;

	static int N, M, K;

	static Tower[][] map;

	static int[] towers;

	static int ans;

	static int attacker, defender;

	// dfs용 변수들
	static boolean[][] visited;
	static int[] route;
	static int shortest = Integer.MAX_VALUE;

	// 우하좌상
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	// 8방탐색 변수
	static int[] dre = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dce = { -1, 0, 1, -1, 1, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		ans = -1;

		map = new Tower[N][M];
		towers = new int[N * M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int p = Integer.parseInt(st.nextToken()); // 초기 타워의 힘
				map[i][j] = new Tower(p, 0, true);
				towers[i * M + j] = p;
			}
		} // end of input


		for (int k = 1; k <= K; k++) {
			// * 예외: 남은 포탑이 1개면 즉시 종료
			int remain = 0;
			for (int i = 0; i < towers.length; i++) {
				if (towers[i] > 0)
					remain++;
			}
			if (remain == 1) {
				break;
			}

			// 보너스 초기화
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					map[i][j].bonus = true;
				}
			}

			// 1. 공격자 선정
			// 공격력이 가장 낮고, 최근에 공격했으며, 행+열의 값이 가장 크며, 열 값이 가장 큰 것
			attacker = chooseAttacker(k);
//			System.out.println("a: "+attacker+" power: "+ towers[attacker]);
			// 1-2. 선택된 포탑 N+M만큼의 공격력 보정
			towers[attacker] += (N + M);
			map[attacker / M][attacker % M].power += (N + M);

//			System.out.println("a: "+attacker+" power: "+ towers[attacker]);
			// 2. 공격
			// 2-1. 공격받을 포탑 선정
			defender = chooseDefender(attacker);

//			System.out.println("b: " + defender + " power: " +towers[defender]);
			// 2-2-1. 레이저 공격
			visited = new boolean[N][M];
			shortest = Integer.MAX_VALUE;
			route = new int[2];
			
			boolean lazer = bfs(attacker / M, attacker % M);
			

//			for (int i = 0; i < route.length; i++) {
//				System.out.print(route[i]+" ");
//			}
//			System.out.println();

			// 2-2-2. 레이저 공격이 안되면 -> 포탑공격
			// 3. 포탑 부서짐
			crashTower(lazer);
			// 4. 포탑 정비: 공격과 연관없던 포탑의 공격력 +1 // 연관없음: 공격자x, 피해x
//			print2();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j].power > 0 && map[i][j].bonus) {
						map[i][j].power++;
						towers[i * M + j]++;
					}
				}
			}
			
//			print();
		}


		// 가장 공격력이 큰 포탑 출력
		for (int i = 0; i < towers.length; i++) {
			ans = Math.max(ans, towers[i]);
		}
		System.out.println(ans);

	}

	public static void printTowers() {
		for (int i = 0; i < towers.length; i++) {
			System.out.print(towers[i] + " ");
		}
		System.out.println();
		System.out.println("----------------------------");
	}

	public static void crashTower(boolean lazer) {
		if (lazer) {
			// 레이저 공격 (가는 길은 절반 피해, 타겟은 전체피해
			
			for (int i = 1; i < route.length; i++) {
				int num = route[i];
				Tower t = map[num / M][num % M];
				if (i == route.length - 1) {
					t.power -= towers[attacker];
					if (t.power < 0)
						t.power = 0;
					towers[num] = t.power;
					t.bonus = false;
				} else {
					t.power -= (towers[attacker] / 2);
					if (t.power < 0)
						t.power = 0;
					towers[num] = t.power;
					t.bonus = false;

				}

			}
		} else {
			// 포탑공격 defender로부터 8방탐색
			int r = defender / M;
			int c = defender % M;

			Tower t = map[r][c];
			t.power -= towers[attacker];
			if (t.power < 0)
				t.power = 0;
			towers[defender] = t.power;
			t.bonus = false;

			// 8방탐색
			for (int d = 0; d < 8; d++) {
				int nr = r + dre[d];
				int nc = c + dce[d];
				
				
				if (!inRange(nr, nc)) {
					nr = (nr + N) % N;
					nc = (nc + M) % M;

				}
				
				if(nr * M + nc == attacker) continue;

				
				map[nr][nc].power -= towers[attacker] / 2;
				if (map[nr][nc].power < 0)
					map[nr][nc].power = 0;
				towers[nr * M + nc] = map[nr][nc].power;
				map[nr][nc].bonus = false;
			}

		}
	}

	public static boolean bfs(int sr, int sc) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.offer(new int[] { sr, sc });
		visited[sr][sc] = true;

		// 경로 추적 변수
		Map<Integer, Integer> prev = new HashMap<>();
		prev.put(sr * M + sc, -1);// 출발지는 이전 지점 없으니 -1로 설정

		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int r = now[0];
			int c = now[1];

			if (r * M + c == defender) {
				List<Integer> path = new ArrayList<Integer>();
				int cur = defender;
				while (cur != -1) {
					path.add(cur);
					cur = prev.get(cur);
				}
				
				Collections.reverse(path);
				route = path.stream().mapToInt(Integer::intValue).toArray();
				return true;
			}

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (!inRange(nr, nc)) {
					nr = (nr + N) % N;
					nc = (nc + M) % M;
				}

				// 방문하지 않은 포탑이 있는 경우
				if (inRange(nr, nc) && map[nr][nc].power > 0 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					queue.offer(new int[] { nr, nc }); // 다음 탐색할 위치 추가
					prev.put(nr * M + nc, r * M + c); // 이전 노드 정보 저장
				}
			}

		}

		return false;
	}

	public static boolean inRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	public static void print2() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j].bonus + " ");
			}
			System.out.println();
		}
		System.out.println("==============================");
	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j].power + " ");
			}
			System.out.println();
		}
		System.out.println("==============================");
	}

	public static int chooseAttacker(int k) {
		int attacker = -1;
		// 공격력이 가장 낮고, 최근에 공격했으며, 행+열의 값이 가장 크며, 열 값이 가장 큰 것
		int p = Integer.MAX_VALUE;
		int u = -1;
		int mr = -1;
		int mc = -1;

		// 열, 행 우하 부터 세로로 탐색
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 부서진 포탑 제외
				if (map[i][j].power == 0)
					continue;

				if (map[i][j].power < p) {
					p = map[i][j].power;
					u = map[i][j].update;
					mr = i;
					mc = j;
				} else if (map[i][j].power == p) {
					if (map[i][j].update > u) {
						p = map[i][j].power;
						u = map[i][j].update;
						mr = i;
						mc = j;
					} else if (map[i][j].update == u) {
						if (mr + mc < i + j) {
							p = map[i][j].power;
							u = map[i][j].update;
							mr = i;
							mc = j;
						} else if (mr + mc == i + j) {
							if (mc < j) {
								p = map[i][j].power;
								u = map[i][j].update;
								mr = i;
								mc = j;
							}
						}
					}
				}

			}
		}

		attacker = mr * M + mc;
		// 선택된 포탑의 update 갱신
		map[mr][mc].update = k;
		map[mr][mc].bonus = false;

		return attacker;
	}

	public static int chooseDefender(int attacker) {
		int defender = -1;
		// 공격력이 가장 높고, 공격한지 가장 오래됐으며, 행+열이 가장 작고, 열값이 가장 작은

		int p = -1;
		int u = 1001;
		int mr = 11;
		int mc = 11;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 부서진 포탑이거나 공격자면 패스
				if (map[i][j].power == 0 || i * M + j == attacker)
					continue;

				if (map[i][j].power > p) {
					p = map[i][j].power;
					u = map[i][j].update;
					mr = i;
					mc = j;
				} else if (map[i][j].power == p) {
					if (map[i][j].update < u) {
						p = map[i][j].power;
						u = map[i][j].update;
						mr = i;
						mc = j;
					} else if (map[i][j].update == u) {
						if (i + j < mr + mc) {
							p = map[i][j].power;
							u = map[i][j].update;
							mr = i;
							mc = j;
						} else if (i + j == mr + mc) {
							if (j < mc) {
								p = map[i][j].power;
								u = map[i][j].update;
								mr = i;
								mc = j;
							}
						}
					}
				}

			}
		}

		defender = mr * M + mc;
		map[mr][mc].bonus = false;

		return defender;
	}

	public static class Tower {
		int power;
		int update;
		boolean bonus;

		public Tower(int p, int u, boolean b) {
			this.power = p;
			this.update = u;
			this.bonus = b;
		}

		@Override
		public String toString() {
			return "Tower [power=" + power + ", update=" + update + ", bonus=" + bonus + "]";
		}

	}
}
