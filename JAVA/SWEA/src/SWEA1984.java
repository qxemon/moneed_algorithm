import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//SWEA1984_중간평균값구하기
public class SWEA1984 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <=t; tc++) {
	
			//문자열을 정수로 바꾸어 정수배열에 넣기
			String[] snums = (br.readLine()).split(" ");
			int[] inums = new int[10];
			for(int i=0;i<inums.length;i++) {
				inums[i]= Integer.parseInt(snums[i]);
			}
			
			//최소, 최댓값 구하기
			int max=-1, min=10001;
			for(int i=0;i<inums.length;i++) {
				max = Math.max(inums[i], max);
				min = Math.min(inums[i], min);
			}
		
			int ct = 0;
			int sum = 0;
			//최소 최댓값 제외한 값들을 더하기
			for(int i=0;i<inums.length;i++) {
				if((inums[i] == max || inums[i] == min) && ct < 2) { //최소, 최댓값 제외 및 최소,최댓값 중복 시 하나만 카운팅 되게
					ct++;
					continue;
				}
				sum+=inums[i];
			}
			
			//소수점을 구해야 하니까 double~
			double avg = (double)sum / 8.0;
			
			//최종답
			int sol = 0;
			
			//반올림하기
			if((avg*10)%10 >=5) {
				sol = (int)avg + 1;
			}
			else {
				sol = (int)avg;
			}
			
			
			System.out.print("#"+tc+" ");
			System.out.println(sol);
			
		}
	
	}
}
