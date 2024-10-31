package Silver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11723_집합 {

    static final int SIZE = 20;
    static int TC;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("JAVA/BOJ/input/BOJ11723.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        TC = Integer.parseInt(br.readLine());
        StringTokenizer st;
        arr = new int[SIZE + 1];

        for (int tc = 1; tc <= TC; tc++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            if (command.equals("add")) {
                int index = Integer.parseInt(st.nextToken());
                add(index);
            } else if (command.equals("remove")) {
                int index = Integer.parseInt(st.nextToken());
                remove(index);
            } else if (command.equals("check")) {
                int index = Integer.parseInt(st.nextToken());

                sb.append(checkIndex(index)).append("\n");
            } else if (command.equals("toggle")) {
                int index = Integer.parseInt(st.nextToken());
                toggle(index);
            } else if (command.equals("all")) {
                arr = new int[SIZE + 1];
                for (int i = 1; i <= 20; i++) {
                    arr[i] = i;
                }
            } else if (command.equals("empty")) {
                arr = new int[SIZE + 1];
            }

        }

        System.out.println(sb.toString());

    }

    public static void remove(int index) {
        arr[index] = 0;
    }

    public static void add(int index) {
        arr[index] = index;
    }

    public static void toggle(int index) {
        if (arr[index] == index) {
            arr[index] = 0;
        } else {
            arr[index] = index;
        }
    }

    static int checkIndex(int index) {
        if (arr[index] == index) return 1;

        return 0;
    }


}
