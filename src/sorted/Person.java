package sorted;

public class Person implements Comparable<Person> {
	private String name;
	private int yearBirth;

	public Person(String name, int year) {
		this.name = name;
		yearBirth = year;
	}

	public String getName() {
		return name;
	}

	public int compareTo(Person other) {
		return name.compareTo(other.getName());
	}

	@Override
	public String toString() {
		return name + ", " + yearBirth;
	}
}
