package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ1501_영어읽기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Map<String, Integer> map = new HashMap<>();


        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            if (word.length() == 1) {
                map.put(word, map.getOrDefault(word, 0) + 1);
            } else {
                String newWord = makeKey(word);
                map.put(newWord, map.getOrDefault(newWord, 0) + 1);

            }
        }
        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int count = 1;

            while (st.hasMoreTokens()) {
                String token = st.nextToken();

                if (token.length() == 1) {
                    count *= map.getOrDefault(token, 0);
                } else {
                    count *= map.getOrDefault(makeKey(token), 0);

                }

            }
            sb.append(count).append("\n");
        }

        System.out.println(sb.toString());

    }

    static String makeKey(String word) {
        char first = word.charAt(0);
        char end = word.charAt(word.length() - 1);

        StringBuilder newWord = new StringBuilder();

        int[] alphabet = new int[52];
        newWord.append(first).append(end);

        for (int i = 1; i < word.length() - 1; i++) {
            if (word.charAt(i) - 'a' < 0) { // 대문자의 경우
                alphabet[word.charAt(i) - 'A' + 26]++;
            } else {
                alphabet[word.charAt(i) - 'a']++;
            }
        }

        for (int a : alphabet) {
            newWord.append("#").append(a);
        }

        return newWord.toString();
    }
}
