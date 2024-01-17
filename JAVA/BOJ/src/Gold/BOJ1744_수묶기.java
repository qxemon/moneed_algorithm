package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ1744_수묶기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

//        List<Integer> arr = new ArrayList<>();
        List<Integer> plusArr = new ArrayList<>();

        List<Integer> minusArr = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 0) {
                plusArr.add(num);
            } else {
                minusArr.add(num);
            }
//            arr.add(Integer.parseInt(br.readLine()));
        } // end of input

        //step1. 오름차순 정렬을 한다.
//        Collections.sort(arr); // 이게 문젠가? 아오!!!
        Collections.sort(plusArr, Collections.reverseOrder()); // 내림차순
        Collections.sort(minusArr);




        int i = 0;
        long ans = 0;
        while (true) { // 음수~0 처리
            if (i >= minusArr.size()) break;

            if (i + 1 < minusArr.size()) { //다음 수가 존재 하며, 다음 수가 음수이거나 0이라면
                ans += minusArr.get(i) * minusArr.get(i + 1);  // 곱한 후 합산하는게 더 크다.
                i += 2;
            } else {
                ans += minusArr.get(i); // 음수가 홀수인데, 다음 수가 양수이면 그냥 전체 합에 더하는 게 더 크다.
                i++;
            }
        }

        i = 0;
        while (true) {
            if (i >= plusArr.size()) break;

            if (i + 1 < plusArr.size() && plusArr.get(i + 1) > 1) {
                ans += plusArr.get(i) * plusArr.get(i + 1);
                i += 2;
            } else {
                ans += plusArr.get(i);
                i++;
            }
        }


        System.out.println(ans);
    }
}
