package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1541_잃어버린괄호_ver2 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String formula = br.readLine();
        StringBuilder newFormula = new StringBuilder();

        newFormula.append("(").append(formula);

        for (int i = 0; i < newFormula.length(); i++) {
            if(newFormula.charAt(i) == '-'){
                newFormula.insert(i,')');
                newFormula.insert(i+2,'(');
                i += 2;
            }
        }
        newFormula.append(')');



        String postfix = toPostfix(newFormula.toString());
        int result = evaluatePostfix(postfix);

        System.out.println(result);
    }

    private static String toPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);

            if (Character.isDigit(c)) {
                postfix.append(c);
                while (i + 1 < infix.length() && Character.isDigit(infix.charAt(i + 1))) {
                    postfix.append(infix.charAt(++i));
                }
                postfix.append(' ');
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop()).append(' ');
                }
                stack.pop();  // pop '('
            } else if (isOperator(c)) {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(c)) {
                    postfix.append(stack.pop()).append(' ');
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            postfix.append(stack.pop()).append(' ');
        }

        return postfix.toString();
    }

    private static int evaluatePostfix(String postfix) {
        Stack<Integer> stack = new Stack<>();
        Scanner scanner = new Scanner(postfix);

        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                stack.push(scanner.nextInt());
            } else {
                char operator = scanner.next().charAt(0);
                int b = stack.pop();
                int a = stack.pop();
                switch (operator) {
                    case '+':
                        stack.push(a + b);
                        break;
                    case '-':
                        stack.push(a - b);
                        break;
                    case '*':
                        stack.push(a * b);
                        break;
                    case '/':
                        stack.push(a / b);
                        break;
                }
            }
        }

        scanner.close();
        return stack.pop();
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return -1;
    }
}
