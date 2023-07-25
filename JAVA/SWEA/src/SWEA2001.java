import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//SWEA2001_파리퇴치
public class SWEA2001 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		//입력이 숫자고 많지않고 띄어쓰기인 경우만 scanner 
		StringTokenizer st;
		
		for(int tc=1; tc<=t; tc++) {
			String nm = br.readLine();
			st = new StringTokenizer(nm);
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[][] flies = new int[N][N];

			
			for(int i=0;i<N;i++) {
				String line = br.readLine();
				st = new StringTokenizer(line);
				for(int j=0;j<N;j++) {
					flies[i][j] = Integer.parseInt(st.nextToken());
					//System.out.println(flies[i][j]);
				}
			}
			

			for(int i = 0; i<N;i++) {
				for(int j=0;j<N;j++) {
					int sum = 0;
					for(int a = 0; a<M; a++) {
						for(int b=0; b<M; b++) {
							sum += flies[i+a][j+b];
						}
					}
					
					
				}
			}
			
			
		}
		
		
	}
}
