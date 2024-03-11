package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ4195_친구네트워크 {

    static List<Node> nodeList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < TC; tc++) {
            int friends = Integer.parseInt(br.readLine());
            HashMap<String, Integer> hash = new HashMap<>();
            nodeList = new ArrayList<>();
            for (int f = 0; f < friends; f++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String left = st.nextToken();
                String right = st.nextToken();
                hash.put(left,hash.getOrDefault(left,nodeList.size()));
                nodeList.add(new Node(nodeList.size()));
                hash.put(right, hash.getOrDefault(right, nodeList.size()));
                nodeList.add(new Node(nodeList.size()));

                // kruskal
                //인덱스 번호가 필요하다
                int leftIndex = hash.get(left);
                int rightIndex = hash.get(right);

                union(nodeList.get(leftIndex), nodeList.get(rightIndex));

                // 1. union 함수가 커진 쪽 사이즈를 return 하도록 // 예외 처리를 더 잘해야함

                // 2. left나 right 녀석을 조회해서 부모 찾은 후 부모 사이즈 찾기
                    int parentIndex = find(nodeList.get(leftIndex));
                    sb.append(nodeList.get(parentIndex).size).append('\n');
            }
        }
            System.out.println(sb.toString());
    }

    static void union(Node left, Node right) {
        int leftParent = find(left);
        int rightParent = find(right);

        if(leftParent == rightParent) return;

        if(nodeList.get(leftParent).size >= nodeList.get(rightParent).size){
            nodeList.get(rightParent).parent = leftParent;
            nodeList.get(leftParent).size += nodeList.get(rightParent).size;

        } else {
            nodeList.get(leftParent).parent = rightParent;
            nodeList.get(rightParent).size += nodeList.get(leftParent).size;
        }

    }
    static int find(Node node){
        if(node.parent == node.index) return node.index;
        return node.parent = find(nodeList.get(node.parent));
    }


    public static class Node {

        int index;
        int parent;

        int size;

        public Node(int index) {
            this.index = index;
            this.parent = index;
            this.size = 1;
        }
    }

}
