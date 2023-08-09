import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		map = new int[101][101]; // 흰도화지
		
		for(int i=0; i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			for(int j=0; j<10;j++) {
				for(int k=0; k<10; k++) {
					map[x+j][y+k] = 1;
					
				}
			}
		}
		
		int area = 0;
		for(int i=0;i<map.length;i++) {
			for(int j=0; j<map.length; j++) {
				if(map[i][j] == 1)
					area++;
			}
		}
		
		System.out.println(area);
	}//end main


}