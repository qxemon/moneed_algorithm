import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int L, C;
    static char[] alphabets;
    static char[] result;
    static boolean[] used;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken()); //암호문 길이
        C = Integer.parseInt(st.nextToken()); //주어진 글자 개수
        st = new StringTokenizer(br.readLine());
        alphabets = new char[C];
        used = new boolean[C];
        result = new char[L];
        for (int i = 0; i < alphabets.length; i++) {
            alphabets[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(alphabets);// 오름차순 정렬 -> 순차적으로 선택했는지 함수로 판단 안해도 됨 그냥 조합 하면 됨
        dfs(0, 0);
    }

    static void dfs(int idx, int cnt) {
        if (cnt == L) {
            if (checkSet()) {
                print();
            }
            return;
        }
        if(idx == C){
            return;
        }

        used[idx] = true;
        dfs(idx+1, cnt+1);
        used[idx] = false;
        dfs(idx+1, cnt);



    }


    static void print() {
        for (int i = 0; i < alphabets.length; i++) {
            if(used[i]) {
                System.out.print(alphabets[i]);
            }
        }
        System.out.println();
    }

    static boolean checkSet() {
        int vowels = 0; // 모음
        int consonants = 0; // 자음
        for (int i = 0; i < alphabets.length; i++) {
            if(used[i]){
                if (alphabets[i] == 'a' || alphabets[i] == 'e' || alphabets[i] == 'i' || alphabets[i] == 'o' || alphabets[i] == 'u') {
                    vowels++;
                } else {
                    consonants++;
                }
            }

        }

        if (vowels >= 1 && consonants >= 2) return true;
        return false;
    }


}