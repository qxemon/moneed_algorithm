import java.util.Scanner;

public class Main {
	
	static int target;
	static boolean[] select;
	static int[] trolls;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		trolls = new int[9];
		int sum = 0;
		target = 0;
		select = new boolean[9];
		for(int i=0; i<trolls.length;i++) {
			trolls[i] = sc.nextInt();
			sum += trolls[i];
		}
		
		target = sum - 100;
		comb(0, 0);
		
		
	}
	private static void comb(int idx, int cnt) {
		if(cnt == 2) {
			int sum = 0;
			for(int i=0; i<9; i++) {
				if(select[i]) {
					sum += trolls[i];
				}
			}
			if(sum == target) {
				for(int j=0;j<9;j++) {
					if(select[j]) continue;
					System.out.println(trolls[j]);
				}
			}
			return;
		}
		if(idx == 9) return;
		
		select[idx] = true;
		comb(idx+1, cnt+1);
		select[idx] = false;
		comb(idx+1, cnt);
	}

	
	
}