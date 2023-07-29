import java.util.Scanner;

//나머지
public class BOJ3052 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] num = new int[10];
        int[] remainder = new int[42]; // 나머지 0~41
        int rem= 0;
        //입력
        for(int i=0; i<num.length;i++){
            num[i] = sc.nextInt();
        }

        int count = 0;
        for(int i=0;i< num.length;i++){
            rem = num[i] % 42;
            if(remainder[rem] ==0){
                count++;
                remainder[rem] = 1;
            }
        }

        System.out.println(count);
    }
}
