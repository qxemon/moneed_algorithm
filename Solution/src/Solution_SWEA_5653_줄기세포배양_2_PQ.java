import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_SWEA_5653_줄기세포배양_2_PQ {
	private static int N;
	private static int M;
	private static int K;
	private static int[][] map;
	private static PriorityQueue<Cell> q;
	private static PriorityQueue<Cell> tempQ;
	private static final int INACTIVE = 2;
	private static final int ACTIVE   = 1;
	private static final int DEATH    = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine()); // 50
		for (int testCase = 1; testCase <= TC; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken()); // 세로 크기(행) N, 1 ≤ N ≤ 50
            M = Integer.parseInt(st.nextToken()); // 가로 크기(열) M, 1 ≤ M ≤ 50
            K = Integer.parseInt(st.nextToken()); // 배양 시간 K, 1 ≤ K ≤ 300
            map = new int[N + K][M + K]; // 최대 확장가능한 크기만큼 미리생성
			q = new PriorityQueue<Cell>();
			tempQ = new PriorityQueue<Cell>();
			
			int start = K / 2; // 저장을 시작할 중간위치, 시작좌표 
			for (int i = start; i < N + start; i++) { // 입력받은 값을 중간 위치에 저장
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = start; j < M + start; j++) {
					int X = Integer.parseInt(st.nextToken()); // 줄기 세포의 생명력 X, 1 ≤ X ≤ 10
					if (X != 0) {
						map[i][j] = X;
						q.add(new Cell(i, j, X));
					}
				}
			}
			sb.append('#').append(testCase).append(' ').append(bfs()).append('\n');
		} // end of for testCase
		System.out.print(sb);
	} // end of main
	private static int[] dr = {-1, 1, 0, 0}; // 상하좌우
	private static int[] dc = { 0, 0,-1, 1};
	/** K시간동안 BFS 탐색후 살아있는 세포 수 리턴 */
	public static int bfs() {
		while(K-- > 0) {
// 세포 번식시 두 개 이상의 줄기 세포가 하나의 그리드 셀에 동시 번식하려고 하는 경우 생명력 수치가 높은 줄기 세포가 해당 그리드 셀을 혼자서 차지하게 된다.
// 우선순위 큐를 사용
			int size = q.size(); // 현재 큐의 크기
			tempQ.clear(); // 이번 턴에서 사용할 임시 큐 초기화
			for (int i = 0; i < size; i++) {
				Cell cell = q.poll(); // 큐에 있는 내용을 하나 꺼내기
				if (cell.status == ACTIVE) { // 활성화 상태인 경우 인접한 칸을
					for (int j = 0; j < 4; j++) {
						int nr = cell.r + dr[j];
						int nc = cell.c + dc[j];
						if (map[nr][nc] == 0) { // 방문하지 않았으면, 배열 범위 벗어나는 경우는 없도록 크게 만들었으니까 체크 안함
							map[nr][nc] = cell.X;
							tempQ.add(new Cell(nr, nc, map[nr][nc])); // 번식된 세포 큐에 넣기 (임시큐)
						}
					}
				}
				cell.next(); // next 세포가 한단계 성숙작업
				if (cell.status != DEATH) {
					tempQ.add(cell); // 비활성화, 활성화 이면 다시 큐에 넣어주기 (죽은 세포는 queue에서 제외) (임시큐)
				}
			} // end of for q.size()
			q.addAll(tempQ); // 임시큐를 원래 큐에 옮기기
		} // end of while K시간만큼
		return q.size(); // 살아있는 세포(비활성화, 활성화)의 개수
	} // end of bfs
	public static class Cell implements Comparable<Cell> {
		int r, c; // 좌표
		int X; // 생명력
		int val; // 현재값
		int status = INACTIVE; // 상태변수
		
		public Cell(int r, int c, int X) {
			super();
			this.r = r;
			this.c = c;
			this.X = X;
			this.val = X;
		}
// 세포를 진행시킴, 상태변경됨, 비활성화상태에서 K시간후 활성화됨, 활성화상태에서 K시간후 죽음, (활성화 상태에서는 세포 확산됨)
		public void next() { // 1시간 지날때마다 호출해주자
			val--; // 현재값 감소
			if (val > 0) return; // 상태는 안바뀜
			if (status == INACTIVE) { // 비활성화 상태
				status = ACTIVE;
				val = X; // 생명력 값으로 다시 셋팅
			} else if (status == ACTIVE) { // 활성화 상태
				status = DEATH;
			}
		}
		public int compareTo(Cell o) { // 생명력이 큰 순서대로
			return o.X - this.X;
		}
		@Override
		public String toString() {
			return String.format("[(%d,%d)=%d_%d:%d]", r,c,X,val,status);
		}
	}
} // end of class