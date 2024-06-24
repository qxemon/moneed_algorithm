package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ1620_나는야포켓몬마스터이다솜 {
    public static void main(String[] args) throws IOException {
        int n, m;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 도감 수
        m = Integer.parseInt(st.nextToken()); // 문제 수

        HashMap<Integer, String> pokemonDictionary1 = new HashMap<>();
        HashMap<String, Integer> pokemonDictionary2 = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            String pokemon = br.readLine();
            pokemonDictionary1.put(i,pokemon);
            pokemonDictionary2.put(pokemon, i);
        }

        for (int i = 1; i <= m; i++) {
            String question = br.readLine();
            if(isInteger(question)){
                System.out.println(pokemonDictionary1.get(Integer.parseInt(question)));
            }
            else{
                System.out.println(pokemonDictionary2.get(question));
            }
        }


    }


    public static boolean isInteger(String s){
        return s.matches("[0-9]{1,}");
    }
}
