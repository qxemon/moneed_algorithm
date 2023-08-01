import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

//최대 힙
//Collections.reverseOrder와 add와 poll을 구현하시오
public class Main {

    public static void main(String[] args) throws IOException {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());
            if (x == 0) {
                if (maxHeap.isEmpty()) {
                    System.out.println(0);
                } else {
                    System.out.println(maxHeap.poll());//맨앞(제일 큰수) 출력 후 삭제
                }
            } else {
                maxHeap.add(x);
            }
        }

    }
}