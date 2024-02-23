package Unrated;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class UR_중위순회 {

    static String[] tree;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int tc = 1; tc <= 10 ; tc++) {
            int n = Integer.parseInt(br.readLine());

            tree = new String[n+1];
            sb = new StringBuilder();

            StringTokenizer st;
            for (int i = 1; i < tree.length; i++) {
                st = new StringTokenizer(br.readLine());
                tree[Integer.parseInt(st.nextToken())] = st.nextToken();
            } // end of input

            inOrder(1);

            System.out.println("#"+tc+" "+sb.toString());
        }





    }

    public static void inOrder(int n){
        if (n > tree.length-1) return;

        inOrder(n*2);
        sb.append(tree[n]);
        inOrder(n*2+1);
    }
}
