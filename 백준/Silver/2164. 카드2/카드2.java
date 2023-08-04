import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayDeque<Integer>queue = new ArrayDeque<>();
		int N = Integer.parseInt(br.readLine());
		for(int i = N; i>0;i--) {
			queue.add(i);
		}

		while(true){
			if(queue.size() == 1) break;
			queue.pollLast();
			int move = queue.pollLast();
			queue.addFirst(move);
		}

		System.out.println(queue.pop());
	}
}