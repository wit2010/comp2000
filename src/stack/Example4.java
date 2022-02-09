package stack;

import java.util.Scanner;

public class Example4 {
	
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

			} else if (Character.isDigit(c)){
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

	private static int calc(String expression) {
		ArrayStack<Character> stack = new ArrayStack<>();
		for (int i = 0; i < expression.length(); i++)
			stack.push(expression.charAt(i));
		return calc(stack);
	}

	private static int calc(ArrayStack<Character> stack) {
		Character c = stack.pop();
		
		if (isOperator(c)) {
			int operand2 = calc(stack);
			int operand1 = calc(stack);
			switch (c) {
			case '+':
				return operand1 + operand2;
			case '-':
				return operand1 - operand2;
			case '*':
				return operand1 * operand2;
			case '/':
				return operand1 / operand2;
			case '^':
				return operand1 ^ operand2;
			default:
				return 0;
			}
		} else {
			return c - '0';
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter an algebraic infix expression");
		String expression = scanner.nextLine();

		System.out.println("Result = " + calc(infixToPostfix(expression)));

		scanner.close();
	}

}
