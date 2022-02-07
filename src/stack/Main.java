package stack;

import java.util.Scanner;

public class Main {
	
	public static boolean isValid(String expression) {
		ArrayStack<Character> stack = new ArrayStack<>();
		for (int i = 0; i < expression.length(); i++) {
			Character ch = expression.charAt(i);
			if (isOpenParenthesis(ch))
				stack.push(ch);
			else if (isClosedParenthesis(ch)) {
				if (stack.isEmpty())
					return false;
				if (!isPairedParentheses(stack.pop(), ch))
					return false;
			}
		}
		return stack.isEmpty();
	}

	public static boolean isOpenParenthesis(char ch) {
		return ch == '(' || ch == '[' || ch == '{';
	}

	public static boolean isClosedParenthesis(char ch) {
		return ch == ')' || ch == ']' || ch == '}';
	}

	private static boolean isPairedParentheses(char open, char close) {
		return open == '(' && close == ')' ||
				open == '[' && close == ']' ||
				open == '{' && close == '}';
	}

	public static String infixToPostfix(String infix) {
		ArrayStack<Character> stack = new ArrayStack<>();
		StringBuilder postfix = new StringBuilder();

		for (int i = 0; i < infix.length(); i++) {
			Character ch = infix.charAt(i);
			if (Character.isLetterOrDigit(ch))
				postfix.append(ch);
			else if (ch == '(' || ch == '^')
				stack.push(ch);
			else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
				while (!stack.isEmpty() && precedence(ch) <= precedence(stack.peek()))
					postfix.append(stack.pop());
				stack.push(ch);
			} else if (ch == ')') {
				while (stack.peek() != '(')
					postfix.append(stack.pop());
				stack.pop();
			}
		}

		while (!stack.isEmpty()) {
			Character topOperator = stack.pop();
			postfix.append(topOperator);
		}

		return postfix.toString();
	}

	public static int precedence(char op) {
		switch (op) {
		case '(':
			return 0;
		case '+':
		case '-':
			return 1;
		case '*':
		case '/':
			return 2;
		case '^':
			return 3;
		default:
			throw new IllegalArgumentException();
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter an algebraic expression");
		String expression = scanner.nextLine();		
		if (isValid(expression))
			System.out.println("The expression is balanced");
		else
			System.out.println("The expression is not balanced");

		System.out.println("Enter an algebraic infix expression");
		expression = scanner.nextLine();
		System.out.println("Postfix form: " + infixToPostfix(expression));

		scanner.close();
	}

}
