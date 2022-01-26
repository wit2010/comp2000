package intro;

import java.util.Scanner;

public class OrderedPairTest {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);

		System.out.print("Who is the head of household? ");
		String boss = input.nextLine();

		System.out.print("Who is dependent? ");
		String dependent = input.nextLine();
		
		System.out.print("What implementation to use ? (1 for pair, 2 for array, 3 for chain) ");
		
		IOrderedPair<String> couple = null;
		switch (input.nextInt()) {
		case 1:
			couple = new OrderedPair<>(boss, dependent);
			break;
		case 2:
			couple = new ArrayOrderedPair<>(boss, dependent);
			break;
		case 3:
			couple = new LinkedOrderedPair<>(boss, dependent);
			break;
		default:
			System.exit(0);
		}

		System.out.println("Boss is " + couple.getFirst() + ", dependent is " + couple.getSecond());

		IOrderedPair<String> coupleCopy = couple.copy();

		System.out.println(couple == coupleCopy ? "copy is identical" : "copy is not identical");

		System.out.println(couple.equals(coupleCopy) ? "copy is the same" : "copy is not the same");
		
		couple.swap();
		System.out.print("After reverse: ");
		System.out.println("Boss is " + couple.getFirst() + ", dependent is " + couple.getSecond());

		System.out.println("The new couple is " + couple);

		System.out.println(couple.equals(coupleCopy) ? "copy is the same" : "copy is not the same");
	}

}
