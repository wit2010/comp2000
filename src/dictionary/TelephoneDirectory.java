package dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class TelephoneDirectory {

	private Dictionary<BusinessName, String> dictionary;

	public TelephoneDirectory(String filename) {
		dictionary = new ArrayDictionary<BusinessName, String>();
//		dictionary = new SortedLinkedDictionary();
		try {
			File file = new File(filename);
			try (Scanner scanner = new Scanner(file)) {
				while (scanner.hasNext()) {
					@SuppressWarnings("unused")
					String line = scanner.nextLine();
					// extract name, town, and number
					String name = "";
					String town = "";
					String number = "";
					BusinessName businessName = new BusinessName(name, town);
					dictionary.add(businessName, number);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.print(e.getMessage());
		}
	}

	public boolean isEmpty() {
		return dictionary.isEmpty();
	}

	public void printDirectory() {
		Iterator<BusinessName> nameIterator = dictionary.getKeyIterator();
		Iterator<String> numberIterator = dictionary.getValueIterator();
		while (nameIterator.hasNext()) {
			System.out.println(nameIterator.next() + ": " + numberIterator.next());
		}
	}

	public String add(String name, String number) {
		BusinessName businessName = new BusinessName(name);
		return dictionary.add(businessName, number);
	}

	public String remove(String name) {
		// To implement
		return null;
	}

	public String getPhoneNumber(BusinessName businessName) {
		// To implement
		return null;
	}

	public static void main(String[] args) {
		TelephoneDirectory directory = new TelephoneDirectory("restaurants.txt");
		if (directory.isEmpty())
			System.exit(0);
		directory.add("Chateau Restaurant, Waltham", "781-894-3339");
		directory.add("Fat Hen", "617-764-1615");
		directory.printDirectory();

		System.out.println(" ========== After removal =========");
		directory.remove("Real Italian Gusto, Medford");
		directory.printDirectory();

		System.out.println("What restaurant are you looking for? ");
		System.out.print("Specify name and municipality");
		try (Scanner scanner = new Scanner(System.in)) {
			String name = scanner.nextLine();
			String number = directory.getPhoneNumber(new BusinessName(name));
			if (number == null)
				System.out.println("No such restaurant in the directory");
			else
				System.out.println("The phone number: " + number);
		}
	}
}
