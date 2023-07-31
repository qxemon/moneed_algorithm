package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//재귀함수가 뭔가요?
public class BOJ17478 {
    public static void main(String[] args) throws IOException {
        String[] sent = {
                "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.",
                "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.",
                "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\""


        };
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int i = Integer.parseInt(br.readLine());
        System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
        recursive(sent,i,0);
    }

    public static int recursive(String[] sent, int i, int n){
        StringBuilder sb = new StringBuilder();
        for(int j=n*4; j>0;j--){
            sb.append("_");
        }
        System.out.println(sb.toString()+"\"재귀함수가 뭔가요?\"");
        if(i == 0){
            System.out.println(sb.toString()+"\"재귀함수는 자기 자신을 호출하는 함수라네\"");
            System.out.println(sb.toString()+"라고 답변하였지.");
            return 1;
        }
        else{
            for(int j=0;j<3;j++){
                System.out.println(sb.toString()+sent[j]);
            }
        }
        try{
            return recursive(sent, i-1, n+1);
        }
        finally {
            System.out.println(sb.toString()+"라고 답변하였지.");
        }

    }
}
