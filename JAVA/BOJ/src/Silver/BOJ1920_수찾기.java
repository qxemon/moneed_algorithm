package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1920_수찾기 {

    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st =new StringTokenizer(br.readLine());
        int k = 0;
        while(st.hasMoreTokens()){
            arr[k] = Integer.parseInt(st.nextToken());
            k++;
        }

        Arrays.sort(arr); // 정렬(NlogN)
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()){
            int m = Integer.parseInt(st.nextToken());

            System.out.println(binary_search(arr,m,0, arr.length-1));

            

        }

    }

    public static int binary_search(int[] arr, int target, int left, int right){
        while(left <= right){
            int mid = (left + right) / 2;
            if(arr[mid] == target){
                return 1;
            }
            else if(arr[mid] > target){
                right = mid-1;
            }
            else if(arr[mid] < target){
                left = mid + 1;
            }
        }
        return 0;
    }
}
