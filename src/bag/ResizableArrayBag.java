package bag;

import java.util.Arrays;

public class ResizableArrayBag<T> implements BagInterface<T> {
	private static final int DEFAULT_CAPACITY = 10;
	private T[] bagArray;
	private int numberOfEntries = 0;
	private int capacity;

	/**
	 * Creates an empty bag having a given initial capacity
	 *
	 * @param capacity The integer capacity desired
	 */

	@SuppressWarnings("unchecked")
	public ResizableArrayBag(int capacity) {
		bagArray = (T[]) new Object[capacity];
		this.capacity = capacity;
	}

	/**
	 * Creates an empty bag having the default initial capacity
	 */
	public ResizableArrayBag() {
		this(DEFAULT_CAPACITY);
	}

	private boolean isFull() {
		return (numberOfEntries == capacity);
	}

	private void doubleCapacity() {
		capacity *= 2;
		bagArray = Arrays.copyOf(bagArray, capacity);
	}

	@Override
	public int getCurrentSize() {
		return numberOfEntries;
	}

	@Override
	public boolean isEmpty() {
		return numberOfEntries == 0;
	}

	@Override
	public boolean add(T newEntry) {
		if (isFull())
			doubleCapacity();
		bagArray[numberOfEntries] = newEntry;
		numberOfEntries++;
		return true;
	}

	@Override
	public boolean remove(T anEntry) {
		for (int idx = 0; idx < numberOfEntries; idx++) {
			if (anEntry.equals(bagArray[idx])) {
				bagArray[idx] = bagArray[numberOfEntries - 1];
				bagArray[--numberOfEntries] = null;
				return true;
			}
		}
		return false;
	}

	@Override
	public T remove() {
		if (isEmpty())
			return null;
		T outData = bagArray[numberOfEntries - 1];
		bagArray[--numberOfEntries] = null;
		return outData;
	}

	@Override
	public void clear() {
		for (int idx = 0; idx < numberOfEntries; idx++)
			bagArray[idx] = null;
		numberOfEntries = 0;
	}

	@Override
	public boolean contains(T anEntry) {
		for (int idx = 0; idx < numberOfEntries; idx++)
			if (anEntry.equals(bagArray[idx]))
				return true;
		return false;
	}

	@Override
	public int getFrequencyOf(T anEntry) {
		int count = 0;
		for (int idx = 0; idx < numberOfEntries; idx++)
			if (anEntry.equals(bagArray[idx]))
				count++;
		return count;
	}

	@Override

	public T[] toArray() {
		T[] result = Arrays.copyOf(bagArray, numberOfEntries);
		return result;
	}

	public ResizableArrayBag<T> copy() {
		ResizableArrayBag<T> result = new ResizableArrayBag<>();
		for (int idx = 0; idx < numberOfEntries; idx++)
			result.add(bagArray[idx]);
		return result;
	}

	@SuppressWarnings("unchecked")
	public boolean equals(Object o) {
		ResizableArrayBag<T> otherBag = (ResizableArrayBag<T>) o;
		if (otherBag.getCurrentSize() != numberOfEntries)
			return false;
		otherBag = otherBag.copy();
		for (int idx = 0; idx < numberOfEntries; idx++) {
			if (!otherBag.remove(bagArray[idx]))
				return false;
		}
		return true;
	}

	public ResizableArrayBag<T> union(ResizableArrayBag<T> otherBag) {
		ResizableArrayBag<T> result = otherBag.copy();
		for (int idx = 0; idx < numberOfEntries; idx++)
			result.add(bagArray[idx]);
		return result;
	}

	public ResizableArrayBag<T> intersection(ResizableArrayBag<T> other) {
		ResizableArrayBag<T> result = new ResizableArrayBag<>();
		ResizableArrayBag<T> otherCopy = other.copy();
		for (int idx = 0; idx < numberOfEntries; idx++) {
			if (otherCopy.remove(bagArray[idx]))
				result.add(bagArray[idx]);

		}
		return result;
	}

}