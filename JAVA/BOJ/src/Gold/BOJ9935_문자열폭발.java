package Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ9935_문자열폭발 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String boom = br.readLine();
        
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            //폭발 문자열이 완성될 시 폭발하여 pop 할예정

            //1. stack에 문자 삽입
            stack.push(str.charAt(i));
            //2. 문자열 길이가 폭발 문자열 보다 크거나 같으면 비교 시작
            if(stack.size()>=boom.length()){
                boolean flag = true;

                for (int j = 0; j < boom.length(); j++) {

                    // 스택 크기가 커질수록 0..1..2...N번째 자리부터 폭발 문자열 0...M을 비교하게 됨
                    if(stack.get(stack.size()-boom.length()+j) != boom.charAt(j)){
                        flag = false;
                        break;
                    }
                }
                if(flag){ // 폭발 문자열 완성 되었으면 그만큼 stack에서 pop
                    for (int j = 0; j < boom.length(); j++) {
                        stack.pop();
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(Character c : stack){
            sb.append(c);
        }

        if(sb.length() == 0){
            System.out.println("FRULA");

        } else{
            System.out.println(sb);
        }

    }
}
