package D3;

import java.util.ArrayDeque;
import java.util.Scanner;

public class SWEA1225_암호생성기 {
	public static void main(String[] args) {
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		Scanner sc = new Scanner(System.in);
		for(int tc = 1; tc<=10;tc++) {
			int t = sc.nextInt();

			for(int i=0;i<8;i++) {
				queue.add(sc.nextInt());
			}

			int cycle = 1;  
					
			while(true) {
				if(queue.peekLast() == 0) break;
				int n = queue.pop();
				n -= cycle;
				if(n <= 0) {
					n = 0;
				}
				queue.add(n);
				
				cycle = cycle == 5 ? 1: cycle+1;

			}
			
			System.out.print("#"+tc+" ");
			for(int i=0; i<8;i++) {
				System.out.print(queue.pop()+" ");
			}
			System.out.println();
		}
	}

}
