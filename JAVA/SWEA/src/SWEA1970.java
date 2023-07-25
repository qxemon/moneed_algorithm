import java.util.Arrays;
import java.util.Scanner;

public class SWEA1970 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		int[] money = {50000, 10000, 5000, 1000, 500, 100, 50, 10};
		
		for(int tc=1; tc<=t;tc++) {
			int N = sc.nextInt();
			int[] count = new int[8];
			
			
			for(int i=0;i<money.length;i++) {
				if (N == 0)
					break;
				count[i] = N / money[i];
				N %= money[i];
			}
			
			System.out.println("#"+tc);
			for(int i=0;i<count.length;i++) {
				if(i == count.length-1) {
					System.out.println(count[i]);
				}
				else
					System.out.print(count[i]+" ");
			}
		}
	}
}