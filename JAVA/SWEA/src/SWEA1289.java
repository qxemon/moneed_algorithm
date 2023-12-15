import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//원재의 메모리 복구하기
public class SWEA1289 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= t; tc++) {

			String m = br.readLine();
			int[] cMemory = new int[m.length()]; // 변형된 메모리 값 0000
			int[] pMemory = new int[m.length()]; // 원래 메모리 값 ex) 0011

			// 메모리 값 하나씩 넣기
			for (int i = 0; i < m.length(); i++) {
				int c = m.charAt(i) - '0';
				pMemory[i] = c;
			}

			// 비교
			int count = 0;

			for (int i = 0; i < cMemory.length; i++) {
				if (cMemory[i] == pMemory[i]) {
					// 값이 같으면 안바꿔도 됨 -> 넘어가기
					continue;
				} else {
					//값이 다르면 switch
					for (int j = i; j < cMemory.length; j++) {
						cMemory[j] = pMemory[i];
					}
					count++;
				}
			}
			
			System.out.println("#" + tc + " "+count);

		}
	}
}
