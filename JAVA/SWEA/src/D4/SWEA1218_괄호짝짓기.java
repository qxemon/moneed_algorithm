package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class SWEA1218_괄호짝짓기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 테케는 10 첫째줄엔 테케 길이, 다음엔 테스트 괄호들
		for (int tc = 1; tc <= 10; tc++) {
			Stack<Character> stack = new Stack<>();
			int len = Integer.parseInt(br.readLine());
			String b = br.readLine();
			char[] bracket = b.toCharArray();
			int result = 0;
			for (int i = 0; i < bracket.length; i++) {
				// 1. 예외, 비었는데, 닫힌 괄호 왔을 때 바로 for문 탈출
				if (stack.isEmpty()
						&& (bracket[i] == ')' || bracket[i] == '}' || bracket[i] == ']' || bracket[i] == '>')) {
					stack.add(bracket[i]);
					break;
				}
				// 2. 닫힌 괄호 들어왔을 때
				if ((bracket[i] == '(' || bracket[i] == '{' || bracket[i] == '[' || bracket[i] == '<')) {
					stack.add(bracket[i]);
				} else if (stack.peek() == '(' && bracket[i] == ')') {
					stack.pop();
				} else if (stack.peek() == '[' && bracket[i] == ']') {
					stack.pop();
				} else if (stack.peek() == '{' && bracket[i] == '}') {
					stack.pop();
				} else if (stack.peek() == '<' && bracket[i] == '>') {
					stack.pop();
				} else if (stack.peek() == '(' && bracket[i] != ')') {
					break;
				} else if (stack.peek() == '{' && bracket[i] != '}') {
					break;
				} else if (stack.peek() == '[' && bracket[i] != ']') {
					break;
				} else if (stack.peek() == '<' && bracket[i] != '>') {
					break;
				}
			}
			if (stack.isEmpty()) {
				result = 1;
			}
			System.out.println("#"+tc+" "+ result);

		}
	}
}
