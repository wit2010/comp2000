package iterator;

import java.util.Iterator;

public class Main {

	private static void printUsingGet(List<?> list) {
		for (int i = 0; i < list.getLength(); i++)
			System.out.print(list.getEntry(i) + " ");
		System.out.println(" ");
	}
	
	private static void printUsingIterator(List<?> list) {
		Iterator<?> iterator = list.iterator();
		while (iterator.hasNext()) {
			Object name = iterator.next();
			System.out.print(name + " ");
		}
		System.out.println(" ");
	}

	private static void printUsingForLoop(List<?> list) {
		for (Object name : list)
			System.out.print(name + " ");
		System.out.println(" ");
	}

	public static void main(String[] args) {

		ArrayList<String> list = new ArrayList<>();
		list.add("Mary");
		list.add("Joseph");
		list.add("Jacob");
		list.add("David");

		printUsingGet(list);
		
		printUsingIterator(list);
		
		printUsingForLoop(list);
		

		Iterator<String> iterator = list.iterator();

		iterator.next(); // Mary
		iterator.remove();
		iterator.next(); // Joseph
		iterator.next(); // Jacob
		iterator.remove();

		for (String name : list)
			System.out.print(name + " ");
		System.out.println(" ");
	}
	

}
