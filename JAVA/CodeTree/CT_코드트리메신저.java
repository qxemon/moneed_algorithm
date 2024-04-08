import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CT_코드트리메신저 {

    static int N, Q;
    static Map<Integer,Node> tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken()); // 트리 노드 수
        Q = Integer.parseInt(st.nextToken()); // 명령어 수

        tree = new TreeMap<>();

        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());

        }
    }

    public static void alarm(){

    }

    public static void changePower(int c, int power){
        tree.get(c).power = power;
    }

    public static void exchangeParent(){

    }

    public static void checkRoom(){
        
    }

    public class Node{
        int num;
        int power;
        int parent;
        boolean mute;
        Node leftChild;
        Node rightChild;

        public Node(int num, int power, int parent, boolean mute) {
            this.num = num;
            this.power = power;
            this.parent = parent;
            this.mute = mute;

        }
    }
}


//사내 메신저 준비 100 p1, p2, ... pn
//알림망 설정 200 c
// 권한 세기 변경 300 c power
// 부모 채팅방 교환 400 c1 c2 같은 depth
// 알림 받을 채팅방 수 조회 500 c

