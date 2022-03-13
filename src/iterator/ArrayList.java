package iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

	private T[] array;
	private int numberOfEntries;
	private int capacity;
	private static final int DEFAULT_CAPACITY = 10;

	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		if (capacity < DEFAULT_CAPACITY)
			capacity = DEFAULT_CAPACITY;
		this.capacity = capacity;
		array = (T[]) new Object[capacity];
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
			array = Arrays.copyOf(array, capacity);
		}
	}

	@Override
	public void add(T newEntry) {
		if (newEntry == null)
			throw new IllegalArgumentException();
		array[numberOfEntries] = newEntry;
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
			array[i] = array[i - 1];
		array[newPosition] = newEntry;
		numberOfEntries++;
		ensureCapacity();
	}

	@Override
	public T remove(int givenPosition) {
		if (givenPosition < 0 || givenPosition >= numberOfEntries)
			throw new IndexOutOfBoundsException();
		T data = array[givenPosition];
		for (int i = givenPosition; i < numberOfEntries; i++)
			array[i] = array[i + 1];
		numberOfEntries--;
		return data;
	}

	@Override
	public boolean remove(T anEntry) {
		for (int i = 0; i < numberOfEntries; i++)
			if (anEntry.equals(array[i])) {
				remove(i);
				return true;
			}

		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		capacity = DEFAULT_CAPACITY;
		array = (T[]) new Object[capacity];
		numberOfEntries = 0;
	}

	@Override
	public T getEntry(int givenPosition) {
		if (givenPosition < 0 || givenPosition >= numberOfEntries)
			throw new IndexOutOfBoundsException();
		return array[givenPosition];
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
	public T replace(int index, T newEntry) {
		if (index < 0 || index >= numberOfEntries)
			throw new IndexOutOfBoundsException();
		T oldEntry = array[index];
		array[index] = newEntry;
		return oldEntry;
	}

	@Override
	public boolean contains(T anEntry) {
		for (int i = 0; i < numberOfEntries; i++)
			if (anEntry.equals(array[i]))
				return true;
		return false;
	}

	@Override
	public Object[] toArray() {
		Object[] result = Arrays.copyOf(array, numberOfEntries);
		return result;
	}

	@Override
	public Iterator<T> iterator() {
		return new ArrayListIterator();
	}

	private class ArrayListIterator implements Iterator<T> {

		private int cursor;
 
		private boolean canRemove;

		public ArrayListIterator() {
			cursor = 0;
		}

		@Override
		public boolean hasNext() {
			return cursor < numberOfEntries;
		}

		@Override
		public T next() {
			if (!hasNext())
				throw new NoSuchElementException("Illegal call: iterator after the end of the list");
			canRemove = true;
			return array[cursor++];
		}

		@Override
		public void remove() {
			if (!canRemove)
				throw new IllegalStateException("remove was called without call to next");
			ArrayList.this.remove(--cursor);
			canRemove = false;
		}
	}

}
