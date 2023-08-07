package D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class SWEA1228_암호문1 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int tc = 1; tc <= 10; tc++) {
			LinkedList<Integer> list = new LinkedList<>();

			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}

			int cmd = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens()) {
				if (st.nextToken().equals("I")) {// 삽입연산
					int sIndex = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					for (int i = sIndex; i < sIndex + y; i++) {
						int s = Integer.parseInt(st.nextToken());
						list.add(i, s);
					}
				}
			}//while end

			System.out.print("#"+tc+" ");
			for(int i=0; i<10;i++) {
				System.out.print(list.get(i)+ " ");
			}
			System.out.println();
		}

	}
}
