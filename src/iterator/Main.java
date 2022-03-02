package iterator;

import java.util.Iterator;

public class Main {

	public static void main(String[] args) {

		ArrayList<String> list = new ArrayList<>();
		list.add("Mary");
		list.add("Joseph");
		list.add("Jacob");
		list.add("David");

		for (String name : list)
			System.out.print(name + "  ");
		System.out.println(" ");

		Iterator<String> iterator = list.iterator();

		iterator.next(); // Mary
		iterator.remove();
		iterator.next(); // Joseph
		iterator.next(); // Jacob
		iterator.remove();

		for (String name : list)
			System.out.print(name + "  ");
		System.out.println(" ");
	}
	

}
