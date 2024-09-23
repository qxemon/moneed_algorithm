import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class POG구명보트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(br.readLine());

        int[] people = new int[size];

        for (int i = 0; i < people.length; i++) {
            people[i] = Integer.parseInt(br.readLine());
        }

        int limit = Integer.parseInt(br.readLine());

        int ans = solution(people, limit);

        System.out.println(ans);
    }


    static public int solution(int[] people, int limit) {
        int answer = 0;

        Arrays.sort(people);
        int start = 0;
        int end = people.length - 1;

        while (start <= end) {
            if(people[start] + people[end] <= limit) {
                start++;
                end--;
                answer++;
            }
            else {
                answer++;
                end--;
            }
        }

        return answer;
    }
}
