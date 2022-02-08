package stack;

import java.util.Scanner;

public class Boo {
	
	public static String infixToPostfix(String expression) {
		ArrayStack<Character> stack = new ArrayStack<>();

		StringBuilder result = new StringBuilder();

		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);

			if (isOperator(c)) {
				while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(c))
					result.append(stack.pop());
				stack.push(c);

			} else if (c == '(') {
				stack.push(c);

			} else if (c == ')') {
				while (stack.peek() != '(')
					result.append(stack.pop());
				stack.pop();

			} else {
				result.append(c);
			}
		}

        while (!stack.isEmpty())
        	result.append(stack.pop());

		return result.toString();
	}

	public static boolean isOperator(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
	}

	public static int precedence(char op) {
		switch (op) {
		case '+':
		case '-':
			return 1;
		case '*':
		case '/':
			return 2;
		case '^':
			return 3;
		}
		return 0;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter an algebraic infix expression");
		String expression = scanner.nextLine();

		System.out.println("Postfix form: " + infixToPostfix(expression));

		scanner.close();
	}

}
