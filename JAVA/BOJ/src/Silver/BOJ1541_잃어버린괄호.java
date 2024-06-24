package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1541_잃어버린괄호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String formula = br.readLine();

        StringTokenizer tokenizerUseMinus = new StringTokenizer(formula, "-");

        int ans = 0;
        boolean first = true;

        while(tokenizerUseMinus.hasMoreTokens()){
            String seq = tokenizerUseMinus.nextToken();
            StringTokenizer tokenizerUsePlus = new StringTokenizer(seq,"+");
            int seqSum = 0;
            while(tokenizerUsePlus.hasMoreTokens()){
                seqSum += Integer.parseInt(tokenizerUsePlus.nextToken());
            }

            if(first){
                ans += seqSum;
                first = false;
            }
            else {
                ans -= seqSum;
            }
        }

        System.out.println(ans);

    }
}
