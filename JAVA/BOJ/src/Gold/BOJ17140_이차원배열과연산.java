package Gold;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ17140_이차원배열과연산 {

    private static int r,c,k;

    private static int[][] arr;

    private static int curR, curC;

    private static int ans;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("JAVA/BOJ/input/BOJ17140.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken()) -1;
        c = Integer.parseInt(st.nextToken()) -1;
        k = Integer.parseInt(st.nextToken());

        arr = new int[100][100];
        curR = 3;
        curC = 3;

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = simulation();

        if(ans > 100) {
            System.out.println(-1);
        }
        else {
            System.out.println(ans);
        }
    }


    public static int simulation() {
        int time = 0;
        while(true) {

            if(time > 100) break;

            if(arr[r][c] == k) break;


            if(curR >= curC){
                //R 연산
                int max = 0;

                for (int i = 0; i < curR; i++) {
                    int[] row = arr[i];
                    int[] copy = calculate(row);

                    arr[i] = copy;

                    int len  = calLength(arr[i]);
                    max = Math.max(max, len);

                }

                curC = max;

            }
            else {
                //C 연산
                int max = 0;
                for (int i = 0; i < curC; i++) {
                    int[] col = new int[100];
                    for (int j = 0; j < curR; j++) {
                        col[j] = arr[j][i];
                    }
                    int[] copy = calculate(col);

                    for (int j = 0; j < 100; j++) {
                        arr[j][i] = copy[j];
                    }

                    int len = calLength(copy);
                    max = Math.max(max, len);
                }
                curR = max;
            }

            time++;
        }

        return time;
    }
    public static int[] calculate(int[] a) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int j = 0; j < a.length; j++) {
            if (a[j] == 0) continue;
            map.put(a[j], map.getOrDefault(a[j], 0) + 1);
        }

        ArrayList<Pair> list = new ArrayList<>();
        for (int key : map.keySet()) {
            list.add(new Pair(key, map.get(key)));
        }

        list.sort(Pair::compareTo);
        int[] newArr = new int[100];
        int m = 0;
        for(Pair p : list) {
            int num = p.num;
            int count = p.count;
            newArr[m] = num;
            m++;
            newArr[m] = count;
            m++;
        }


        return newArr;


    }

    public static int calLength(int[] a){
        int len = 0;
        for (int value : a) {
            if (value != 0) len++;
        }
        return len;
    }


    public static void print(){
        for (int i = 0; i < curR; i++) {
            for (int j = 0; j < curC ; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("===============================");
    }



    public static class Pair implements Comparable<Pair> {
        int num;
        int count;

        public Pair(int num, int count) {
            this.num = num;
            this.count = count;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "num=" + num +
                    ", count=" + count +
                    '}';
        }


        @Override
        public int compareTo(Pair o) {
            if(this.count == o.count) return Integer.compare(this.num, o.num);
            return Integer.compare(this.count, o.count);
        }
    }

}
