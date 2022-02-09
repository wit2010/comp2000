package stack;

import java.util.Scanner;

public class Example2 {
	
	public static boolean isBalanced(String expression) {
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

	public static boolean isPairedParentheses(char open, char close) {
		return open == '(' && close == ')' ||
				open == '[' && close == ']' ||
				open == '{' && close == '}';
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter an algebraic expression");
		String expression = scanner.nextLine();		
		if (isBalanced(expression))
			System.out.println("The expression is balanced");
		else
			System.out.println("The expression is not balanced");

		scanner.close();
	}

}
