package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_게리맨더링{

    static int N, min; // 마을 수 N , 유권자 수 차이 최솟값 min
    static int[] peoples; // 유권자 수를 담을 배열

    static int[][] adjMatrix;
    static boolean[] select;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
//        int TC = Integer.parseInt(br.readLine().trim());
//        for (int tc = 1; tc <= TC; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            min = Integer.MAX_VALUE;
            peoples = new int [N+1]; //1~N번까지 사용할 예정임 !!!
            adjMatrix = new int[N+1][N+1]; //1~N번인덱스 사용 예정
            select = new boolean[N+1]; // 1~N번 인덱스 사용 예정
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < peoples.length; i++) {
                peoples[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 1; i < N+1; i++) {
                st = new StringTokenizer(br.readLine());
                int len = Integer.parseInt(st.nextToken());
//                System.out.println(len);
                for (int j = 1; j < len+1; j++) {
                    int c = Integer.parseInt(st.nextToken());
                    adjMatrix[i][c] = 1;
                }
            }

            //end input


//			print(); //인접행렬 입력 확인
//			System.out.println(Arrays.toString(peoples)); // 유권자 입력 확인

            for (int i = 1; i < (N/2)+1; i++) {
                devide(1, 0, i); // 1번 마을부터 , 아직 선택은 안했어, i개로 선택할것임
            }

            // (/2 +1) 을 한 이유:
            //조합이란게 r개를 선택하면 나머지는 N-r개가 됨 근데 /2+1개를 기점을 T/F 만 바뀌지 사실 구역을 나누는게 똑같아짐 그래서 이렇게 해줌


            System.out.println(min == Integer.MAX_VALUE ? -1 : min);
//            System.out.println("#"+tc+" "+min);
//        }
    }//end of main

    static void devide(int idx, int cnt, int r) { // 구역 나누는 메소드 (조합)
        if(cnt == r) {// 조합 완료

            int idxT = 0, idxF = 0;

            for (int i = 1; i < select.length; i++) {
                if(select[i]) {
//					System.out.print(i+ " ");
                    idxT = i;
                }
                else {
                    idxF = i;
                }
            }
//			System.out.println();

            //T/F 인덱스를 알아냈어 -> 거기부터 BFS로 전부 이어져 있나 확인할거임, T,F 전부 확인해야됨
            boolean connectT = isConnected(idxT, true, r);
            boolean connectF = isConnected(idxF, false, N-r);

            if(connectT && connectF) {
                // 유권자 차이 최소값 계산
                int peopleA = 0, peopleB = 0;
//				System.out.println("연결 되부럿서~~~");
                for (int i = 1; i < select.length; i++) {
                    if(select[i]) {
                        peopleA += peoples[i];
                    }
                    else {
                        peopleB += peoples[i];
                    }
                }

                int sum = Math.abs(peopleA - peopleB);
                min = Math.min(sum, min);

            }


            return;

        }
        if(idx == N+1) { // 조합 실패
            return;
        }

        select[idx] = true;
        devide(idx+1, cnt+1, r);
        select[idx] = false;
        devide(idx+1, cnt, r);



    }

    static boolean isConnected(int start, boolean status, int cnt) {
        //BFS 탐색 코드
        Queue<Integer> queue = new ArrayDeque<>(); // 각 정점을 저장할 큐
        queue.offer(start);
        boolean visit[] = new boolean[N+1]; // 정점 방문 체크 배열
        visit[start] = true;
        int connect = 1; // 처음 구역도 연결된걸로 시작

        while(!queue.isEmpty()) {
            int now = queue.poll(); // 현재 정점
            for (int i = 1; i < N+1; i++) {
                if(adjMatrix[now][i] == 1 && !visit[i] && select[i] == status) { // 연결되어 있고 방문 안했고 같은 구역이면
                    queue.offer(i); // i가 연결된 정점을 나타내기 때문에 i로 넣어도 됨, 인접 리스트라면 get(i)를 넣었어야하겠지만..
                    connect++;
                    visit[i] = true;
                }
            }
        }

        if(connect == cnt) return true;
        return false;

    }

    static void print() {
        for (int i = 1; i < adjMatrix.length; i++) {
            for (int j = 1; j < adjMatrix.length; j++) {
                System.out.print(adjMatrix[i][j]+" ");
            }
            System.out.println();
        }
    }




}//end of class
