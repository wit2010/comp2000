package sortedlist;

import list.ArrayList;

public class SortedArrayList<T extends Comparable<? super T>> extends ArrayList<T> implements SortedList<T> {

	public SortedArrayList(int capacity) {
		super(capacity);
	}
	
	public SortedArrayList() {
		super();
	}

	@Override
	public void addEntry(T newEntry) {
		int position = getPosition(newEntry);
		if (position < 0)
			position = -1 - position;
		super.add(position, newEntry);
	}

	@Override
	public boolean removeEntry(T anEntry) {
		int position = getPosition(anEntry);
		if (position >= 0) {
			remove(position);
			return true;
		}
		return false;
	}

	public int getPosition(T anEntry) {
		int position = 0;
		int compValue = 0;
		int length = getLength();
		while (position < length) {
			compValue = anEntry.compareTo(getEntry(position));
			if (compValue <= 0)
				break;
			position++;
		}
		if (position == length || compValue < 0)
			position = -1 - position;
		return position;
	}

	@Override
	public void add(T newEntry) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void add(int newPosition, T newEntry) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T replace(int givenPosition, T newEntry) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(T anEntry) {
		int low = 0;
		int high = getLength() - 1;
		int mid;
		int compValue;
		do {
			mid = (low + high) / 2;
			compValue = anEntry.compareTo(getEntry(mid));
			if (compValue == 0)
				return true;
			else if (compValue > 0)
				low = mid + 1;
			else
				high = mid - 1;
		} while (low <= high);
		return false;
	}

	public static void main(String[] args) {
		SortedArrayList<Integer> list = new SortedArrayList<>();
		list.addEntry(6);
		list.addEntry(9);
		list.addEntry(4);
		list.addEntry(2);
		list.addEntry(5);
		list.addEntry(4);

		Object[] numbers = list.toArray();
		for (Object number : numbers)
			System.out.print(number + " ");

		boolean removed = list.removeEntry(6);
		System.out.println("\n6 is removed? " + removed);
		removed = list.removeEntry(6);
		System.out.println("6 is removed? " + removed);
		list.addEntry(1);
		list.remove(3);
		numbers = list.toArray();
		for (Object number : numbers)
			System.out.print(number + " ");
		System.out.println();

		SortedArrayList<String> list1 = new SortedArrayList<>();

		String[] names = { "John Kennedy", "Dwight Eisenhower", "Richard Nixon", "Lindon Johnson", "Gerald Ford",
				"William Clinton", "George H W Bush" };

		for (String name : names)
			list1.addEntry(name);

		Object[] sortedNames = list1.toArray();
		for (Object sortedName : sortedNames)
			System.out.println(sortedName);

		SortedArrayList<Name> presidents = new SortedArrayList<>();
		for (String name : names)
			presidents.addEntry(new Name(name));
		System.out.println("\n============================================");
		sortedNames = presidents.toArray();
		for (Object sortedName : sortedNames)
			System.out.println(sortedName);

		System.out.println("Is John Kennedy listed? " + presidents.contains(new Name("John Kennedy")));

		System.out.println("Is John Adams listed? " + presidents.contains(new Name("John Adams")));

		SortedArrayList<Student> list2 = new SortedArrayList<>();
		list2.addEntry(new Student("John Smith", 2002, 136));
		list2.addEntry(new Student("Maria Doe", 2002, 138));
		list2.addEntry(new Student("Adam Boile", 2002, 145));

		System.out.println("\n============================================");

		Object[] students = list2.toArray();
		for (Object student : students)
			System.out.println(student);

		SortedArrayList<Character> list3 = new SortedArrayList<>();
		String question = "How can I do it?";
		for (int i = 0; i < question.length(); i++)
			list3.addEntry(question.charAt(i));
		Object[] characters = list3.toArray();
		for (Object character : characters)
			System.out.print(character);
		System.out.println("\n============================================");

		list3.clear();
		for (int i = question.length() - 1; i >= 0; i--)
			list3.addEntry(question.charAt(i));
		characters = list3.toArray();
		for (Object character : characters)
			System.out.print(character);
	}

}
