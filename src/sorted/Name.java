package sorted;

class Name implements Comparable<Name> {
	private String firstName;
	private String lastName;

	public Name(String fullName) {
		int blank = fullName.lastIndexOf(' ');
		if (blank == -1) {
			lastName = fullName;
			firstName = "";
		} else {
			firstName = fullName.substring(0, blank);
			lastName = fullName.substring(blank + 1);
		}

	}

	public String getLast() {
		return lastName;
	}

	@Override
	public String toString() {
		return (firstName + " " + lastName);
	}

	@Override
	public int compareTo(Name other) {
		return lastName.compareTo(other.getLast());
	}

}
