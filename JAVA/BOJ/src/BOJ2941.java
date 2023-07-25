import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class BOJ2941 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line = br.readLine();
		char[] c = line.toCharArray();
		
		//System.out.println(Arrays.toString(c));
		
		int len = 0;
		
		for(int i=0;i<c.length;i++) {
			if(c[i] == 'j') {
				if(i-1 < 0) { //범위 초과 그냥  s 문자
					len++;
					continue;
				}
				if(c[i-1] == 'l' || c[i-1] == 'n') {
					//앞 문자가 l 혹은 n이니? -> count 하지 않고 넘기기
					continue;
				}
			}
			else if(c[i]=='-') {
				if(i-1 < 0) {
					len++;
					continue;
					// 근데 -가 먼저 나올일이 없는데 체크 해야될까?
				}
				if(c[i-1] == 'c' || c[i-1] == 'd') {
					continue;
				}
			}
			else if(c[i] == '=') {
				if(i-1 < 0) {
					len++;
					continue;
					// 근데 -가 먼저 나올일이 없는데 체크 해야될까?
				}
				if(c[i-1] == 's' || c[i-1] == 'c' || c[i-1] == 'z') {
					continue;
				}
				
			}
			else if (c[i] == 'z') {
				if(i-1 < 0 || i+1 >= c.length) {
					len++;
					continue;
				}
				if(c[i-1] == 'd' && c[i+1] == '=') {
					continue;
				}
			
			}

			len++;
			
		}
	
		System.out.println(len);
		
		
	}
}
