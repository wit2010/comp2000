package stack;

import java.util.Scanner;

public class Example1 {
	
	public static String reverse(String string) {
		ArrayStack<Character> stack = new ArrayStack<>();
		for (int i = 0; i < string.length(); i++) 
		       stack.push(string.charAt(i));   

		StringBuilder result = new StringBuilder();
		while (!stack.isEmpty())
		      result.append(stack.pop());

		return result.toString();
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter a string to reverse");
		String string = scanner.nextLine();		
		System.out.println(reverse(string));
		scanner.close();
	}

}
