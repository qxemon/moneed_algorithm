package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ2641_다각형그리기 {

    public static void main(String[] args) throws IOException {
        //같다는 걸 어떻게 알지?
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //수열의 길이
        int len = Integer.parseInt(br.readLine());

        // 모양 수열
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder forward = new StringBuilder();
        StringBuilder reverse = new StringBuilder();

        while(st.hasMoreTokens()){
            String s = st.nextToken();
            int rs = (Integer.parseInt(s)+2) % 4;
            if (rs == 0) rs = 4;

            forward.append(s);
            reverse.insert(0, reverse(s));

        }

        //정방향, 역방향으로 만들 수 있는 모든 수열을 Set에 저장합니다. (중복 방지)
        Set<String> set = new HashSet<>();
        for (int i = 0; i < len; i++) {
            set.add(forward.toString());
            set.add(reverse.toString());

            //맨 앞자리 숫자를 뒤로 보내기
            forward.append(forward.charAt(0));
            reverse.append(reverse.charAt(0));

            //맨 앞 숫자 삭제하기 (뒤로 보냈으니까)
            forward.deleteCharAt(0);
            reverse.deleteCharAt(0);
        }

        //비교
        int result = 0; // 일치하는 수열 개수
        StringBuilder res = new StringBuilder(); // 일치하는 수열 저장
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < len; j++) {
                sb.append(st.nextToken());
            }

            if(set.contains(sb.toString())){
                result++;
                for (int j = 0; j < len; j++) {
                    res.append(sb.charAt(j)).append(" ");
                }
                res.append("\n");
            }

        }

        System.out.println(result);
        System.out.println(res);


    }

    static public String reverse(String s){
        switch (s) {
            case "1":
                return "3";
            case "2":
                return "4";
            case "3":
                return "1";
            default:
                return "2";
        }
    }
}
