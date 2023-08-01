import java.util.Arrays;
import java.util.Scanner;

//N과 M
public class Main {
	static int[] arr;
	static int[] result;
	static boolean[] used;
	static int n;
	static int m;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		arr = new int[n];
		result = new int[m];
		used = new boolean[n];
		for(int i=0;i<n;i++) {
			arr[i] =i+1;
		}
		
		sequence(0);
	}
	
	
	static void sequence(int idx) {
		if(idx == m) {
			for(int i=0;i<m;i++) {
				System.out.print(result[i]+" ");
			}
			System.out.println();
			return;
		}
		
		for(int i=0; i<n;i++) {
			if(!used[i]) {
				result[idx] = arr[i];
				used[i] = true;
				sequence(idx+1);
				used[i] = false;
			}
		}
		return;
	}
}