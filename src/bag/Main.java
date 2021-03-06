package bag;

public class Main {

	public static void main(String[] args) {
		ArrayBag<String> words = new ArrayBag<String>();

		words.add("home");
		words.add("sea");
		words.add("home");
		words.add("dog");

		ArrayBag<String> names = new ArrayBag<>();

		names.add("sea");
		names.add("home");
		names.add("dog");
		names.add("home");

		System.out.println("The bags words and names are equal? " + words.equals(names));
		System.out.println("The bags names and words are equal? " + names.equals(words));

		names.add("dog");
		words.add("sea");
		System.out.println("++++++++++++++++++++++++++++++++");
		System.out.println("The bags words and names are equal? " + words.equals(names));
		System.out.println("The bags names and words are equal? " + names.equals(words));

		System.out.print("How many times \"home\" appears in words? ");
		System.out.println(words.getFrequencyOf("home"));

		System.out.print("How many times \"sea\" appears in names? ");
		System.out.println(names.getFrequencyOf("sea"));

		ArrayBag<String> unionbag = words.union(names);
		int len = unionbag.getCurrentSize();

		Object[] content = unionbag.toArray();
		for (int i = 0; i < len; i++)
			System.out.println(content[i]);

		System.out.print("How many times \"sea\" appears in unionbag? ");
		System.out.println(unionbag.getFrequencyOf("sea"));

		System.out.print("How many times \"sea\" appears in names? ");
		System.out.println(names.getFrequencyOf("sea"));

		ArrayBag<String> intersect = names.intersection(words);
		content = intersect.toArray();
		len = intersect.getCurrentSize();
		System.out.println("=======Intersection========");
		for (int i = 0; i < len; i++)
			System.out.println(content[i]);
	}

}
