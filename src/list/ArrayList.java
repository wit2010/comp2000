package list;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {

	private T[] list;
	private int numberOfEntries;
	private int capacity;
	private static final int DEFAULT_CAPACITY = 10;

	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		if (capacity < DEFAULT_CAPACITY)
			capacity = DEFAULT_CAPACITY;
		this.capacity = capacity;
		list = (T[]) new Object[capacity];
		numberOfEntries = 0;
	}

	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	private boolean isFull() {
		return numberOfEntries == capacity;
	}

	private void ensureCapacity() {
		if (isFull()) {
			capacity *= 2;
			list = Arrays.copyOf(list, capacity);
		}
	}

	@Override
	public void add(T newEntry) {
		if (newEntry == null)
			throw new IllegalArgumentException();
		list[numberOfEntries] = newEntry;
		numberOfEntries++;
		ensureCapacity();
	}

	@Override
	public void add(int newPosition, T newEntry) {
		if (newEntry == null)
			throw new IllegalArgumentException();
		if (newPosition < 0 || newPosition > numberOfEntries)
			throw new IndexOutOfBoundsException();
		for (int i = numberOfEntries; i > newPosition; i--)
			list[i] = list[i - 1];
		list[newPosition] = newEntry;
		numberOfEntries++;
		ensureCapacity();
	}

	@Override
	public T remove(int givenPosition) {
		if (givenPosition < 0 || givenPosition >= numberOfEntries)
			throw new IndexOutOfBoundsException();
		T data = list[givenPosition];
		for (int i = givenPosition; i < numberOfEntries; i++)
			list[i] = list[i + 1];
		numberOfEntries--;
		return data;
	}

	@Override
	public boolean remove(T anEntry) {
		for (int i = 0; i < numberOfEntries; i++)
			if (anEntry.equals(list[i])) {
				remove(i);
				return true;
			}

		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		capacity = DEFAULT_CAPACITY;
		list = (T[]) new Object[capacity];
		numberOfEntries = 0;
	}

	@Override
	public T getEntry(int givenPosition) {
		if (givenPosition < 0 || givenPosition >= numberOfEntries)
			throw new IndexOutOfBoundsException();
		return list[givenPosition];
	}

	@Override
	public int getLength() {
		return numberOfEntries;
	}

	@Override
	public boolean isEmpty() {
		return numberOfEntries == 0;
	}

	@Override
	public T replace(int givenPosition, T newEntry) {
		// stub
		return null;
	}

	@Override
	public boolean contains(T anEntry) {
		// stub
		return false;
	}

	@Override
	public Object[] toArray() {
		Object[] result = Arrays.copyOf(list, numberOfEntries);
		return result;
	}

}
