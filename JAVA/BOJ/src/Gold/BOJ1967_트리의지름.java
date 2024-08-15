package Gold;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1967_트리의지름 {

    static int N;

    static ArrayList<ArrayList<Node>> tree;
    static boolean[] visited;
    static int result;
    static int newNode;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        result = 0;
        newNode = 1;
        tree = new ArrayList<>();
        visited = new boolean[N+1];

        for (int i = 0; i < N+1; i++) {
            tree.add(new ArrayList<>());
        } // 리스트 초기화

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            tree.get(a).add(new Node(b, value));
            tree.get(b).add(new Node(a, value));

        }// end of input

        dfs(1,0); //루트노드에서 한번 실행, max 가중치 찾기
        result = 0;
        Arrays.fill(visited,false);
        dfs(newNode, 0);

        System.out.println(result);


    }


    public static void dfs(int node, int sum){
        visited[node] = true;
        if(sum > result){
            result = sum;
            newNode = node;
        }

        for(int i=0; i<tree.get(node).size(); i++){
            int nextNode = tree.get(node).get(i).number;
            int cost = tree.get(node).get(i).value;

            if(!visited[nextNode]){
                dfs(nextNode,sum+cost);
            }
        }

    }
    public static class Node {
        int number;
        int value;

        public Node(int number, int value) {
            this.number = number;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "number=" + number +
                    ", value=" + value +
                    '}';
        }
    }
}
