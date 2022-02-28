package list;

public class Main {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();

		list.add("MA");
		list.add("RI");
		list.add("CT");
		list.add("NH");
		list.add("VT");
		list.add("ME");

		Object[] states = list.toArray();

		for (Object state : states)
			System.out.print(state + " ");
		System.out.println();

		list.add(6, "NY");
		states = list.toArray();

		for (Object state : states)
			System.out.print(state + " ");
		System.out.println();

		System.out.println(list.remove("RI"));
		System.out.println(list.remove("RI"));

		list.replace(4, "NJ");

		states = list.toArray();
		for (Object state : states)
			System.out.print(state + " ");
		System.out.println();

		System.out.println("Is MA in the state list? " + list.contains("MA"));
		System.out.println("Is IL in the state list? " + list.contains("IL"));
	}

}
