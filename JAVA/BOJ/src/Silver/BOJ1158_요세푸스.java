package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ1158_요세푸스 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Deque<Integer> queue = new ArrayDeque<Integer>();

		for(int i=1;i<=N;i++) {
			queue.add(i);
		}
			
		sb.append("<");
		while(!queue.isEmpty()) {
			for(int i=1; i<K; i++) {
				int temp = queue.pop();
				queue.add(temp);
			}
			sb.append(queue.pop()).append(", ");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.deleteCharAt(sb.length()-1);
		sb.append(">");
		System.out.println(sb);
	}
	
}
