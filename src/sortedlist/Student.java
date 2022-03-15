package sortedlist;

public class Student extends Person {
	private int id;

	public Student(String name, int year, int id) {
		super(name, year);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return super.toString() + ", " + id;
	}

	@Override
	public int compareTo(Person other) {
		Student student = (Student) other;
		return getId() - student.getId();
	}

}
