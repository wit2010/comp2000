package tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class Example1 {

	public static void main(String[] args) {
		BinarySearchTree<City> capitalTree = new BinarySearchTree<>();
		String line;
		boolean success = false;
		try (Scanner input = new Scanner(System.in)) {
			System.out.println("Sort by City (press 1) or by State (press 2) or by name length (press 3)");
			System.out.print("Press 0 for no sorting ");
			int sorting = input.nextInt();
			try {
				Scanner read = new Scanner(new File("statecapitals.txt"));
				while (read.hasNext()) {
					line = read.nextLine();
					if (line.isEmpty())
						continue;
					capitalTree.add(new City(line, sorting));
				}
				success = true;
			} catch (FileNotFoundException ex) {
				System.out.println("Error: file statecapitals.txt not found. Please check file directory");
			}
		}
		if (success) {
			Iterator<City> capitalIterator = capitalTree.getInorderIterator();
			int count = 1;
			while (capitalIterator.hasNext()) {
				System.out.println(count + ":" + capitalIterator.next());
				count++;
			}
			System.out.println("The height is " + capitalTree.getHeight());
			System.out.println("Number of states: " + capitalTree.getNumberOfNodes());
		}
	}

	private static class City implements Comparable<City> {
		private String city;
		private String state;
		private int sortingMethod;

		public City(String fullName, int method) {
			String[] parts = fullName.split(", ");
			if (parts.length != 2) {
				city = state = null;
			}
			city = parts[0];
			state = parts[1];
			sortingMethod = method;

		}

		public String getCity() {
			return city;
		}

		public String getState() {
			return state;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			City city1 = (City) o;
			return getCity().equals(city1.getCity()) && getState().equals(city1.getState());
		}

		/*
		 * !!!! when compareTo() returns 0, the objects may not be equal
		 */
		public int compareTo(City other) {
			switch (sortingMethod) {
			case 1:
				return getCity().compareToIgnoreCase(other.getCity());
			case 2:
				return getState().compareToIgnoreCase(other.getState());
			case 3:
				return (getCity().length() - other.getCity().length());
			default:
				return 0;
			}
		}

		@Override
		public String toString() {
			return city + ", " + state;
		}
	}
}
