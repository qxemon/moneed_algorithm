package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ5430_AC {
    private static int TC, N;
    private static String P, arr;
    private static char[] command;
    private static ArrayDeque<Integer> deque;
    private static StringBuilder sb;
    private static StringTokenizer st;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TC = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        for (int tc = 0; tc < TC; tc++) {
            command = br.readLine().toCharArray();
            N = Integer.parseInt(br.readLine());
            arr = br.readLine();
            st = new StringTokenizer(arr,"[,]");
            deque = new ArrayDeque<>();

            while(st.hasMoreTokens()) {
                deque.add(Integer.parseInt(st.nextToken()));
            }

            solve();

        }

        System.out.println(sb);
    }


    public static void solve() {
        boolean isReverse = false;
        boolean isError = false;

        for (char c : command) {
            if(c == 'R'){
                isReverse = !isReverse; // swap
            }
            else if(c == 'D'){
                if(deque.isEmpty()){
                    isError = true;
                    break;
                }
                if(isReverse){
                    deque.removeLast();
                }
                else{
                    deque.removeFirst();
                }
            }
        }

        if(isError){
            sb.append("error\n");
        } else {
            sb.append("[");

            if(deque.isEmpty()){
                sb.append("]").append("\n");
            } else {
                if(isReverse){
                    Iterator<Integer> it = deque.descendingIterator(); // 덱 거꾸로 반복하는 이터레이터
                    while(it.hasNext()){
                        sb.append(it.next()).append(",");
                    }
                    sb.deleteCharAt(sb.length()-1);
                }
                else{
                    int size = deque.size();
                    for(int i = 0; i < size; i++){
                        sb.append(deque.removeFirst()).append(",");
                    }
                    sb.deleteCharAt(sb.length()-1); // 마지막 쉼표 제거
                }
                sb.append("]").append("\n");
            }
        }
    }
}
