package iterator;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ArrayListWithListIterator<T> implements List<T> {

	private T[] list;
	private int numberOfEntries;
	private int capacity;
	private static final int DEFAULT_CAPACITY = 10;
	private static final int MAX_CAPACITY = 10000;

	@SuppressWarnings("unchecked")
	public ArrayListWithListIterator(int capacity) {
		if (capacity < DEFAULT_CAPACITY)
			capacity = DEFAULT_CAPACITY;
		else
			checkCapacity(capacity);
		this.capacity = capacity;
		list = (T[]) new Object[capacity];
		numberOfEntries = 0;
	}

	public ArrayListWithListIterator() {
		this(DEFAULT_CAPACITY);
	}

	private void checkCapacity(int capacity) {
		if (capacity > MAX_CAPACITY)
			throw new InvalidParameterException("Capacity too big: should not exceed " + MAX_CAPACITY);
	}

	private boolean isFull() {
		return (numberOfEntries == capacity);
	}

	private void ensureCapacity() {
		if (isFull()) {
			capacity *= 2;
			checkCapacity(capacity);
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

	private void makeRoom(int newPosition) {
		assert (newPosition >= 0 && newPosition <= numberOfEntries);
		for (int idx = numberOfEntries; idx > newPosition; idx--)
			list[idx] = list[idx - 1];
	}

	@Override
	public void add(int newPosition, T newEntry) {
		if (newEntry == null)
			throw new IllegalArgumentException();
		if (newPosition < 0 || newPosition > numberOfEntries)
			throw new IndexOutOfBoundsException();
		makeRoom(newPosition);
		list[newPosition] = newEntry;
		numberOfEntries++;
		ensureCapacity();
	}

	public void addAll(T[] items) {
		for (T item : items)
			add(item);
	}

	private void removeGap(int givenPosition) {
		assert (givenPosition >= 0 && givenPosition < numberOfEntries);
		for (int index = givenPosition; index < numberOfEntries; index++) {
			list[index] = list[index + 1];
		}
	}

	@Override
	public T remove(int givenPosition) {
		if (givenPosition < 0 || givenPosition >= numberOfEntries)
			throw new IndexOutOfBoundsException();
		T outData = list[givenPosition];
		removeGap(givenPosition);
		numberOfEntries--;
		return outData;
	}

	@Override
	public boolean remove(T anEntry) {
		for (int idx = 0; idx < numberOfEntries; idx++)
			if (anEntry.equals(list[idx])) {
				remove(idx);
				return true;
			}

		return false;
	}

	public boolean removeAll(T anEntry) {
		boolean removed = false;
		while (remove(anEntry)) {
			removed = true;
		}
		return removed;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		capacity = DEFAULT_CAPACITY;
		list = (T[]) new Object[capacity];
		numberOfEntries = 0;
	}

	@Override
	public T replace(int givenPosition, T newEntry) {
		if (givenPosition < 0 || givenPosition >= numberOfEntries)
			throw new IndexOutOfBoundsException();
		T oldValue = list[givenPosition];
		list[givenPosition] = newEntry;
		return oldValue;
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
	public boolean contains(T anEntry) {
		for (int idx = 0; idx < numberOfEntries; idx++)
			if (anEntry.equals(list[idx]))
				return true;
		return false;
	}

	public int getPosition(T anEntry) {
		for (int idx = 0; idx < numberOfEntries; idx++)
			if (anEntry.equals(list[idx]))
				return idx;
		return -1;
	}

	public int getLastPosition(T anEntry) {
		for (int idx = numberOfEntries - 1; idx >= 0; idx--) {
			if (anEntry.equals(list[idx]))
				return idx;
		}
		return -1;
	}

	@Override
	public Object[] toArray() {
		Object[] result = Arrays.copyOf(list, numberOfEntries);
		return result;
	}

	@Override
	public ListIterator<T> iterator() {
		return new ArrayListIterator();
	}

	private class ArrayListIterator implements ListIterator<T> {

		private int cursor;
		private boolean isRemoveOrSetLegal = false;
		private int lastMove = -1;
		private static final int NEXT = 1;
		private static final int PREV = 0;

		public ArrayListIterator() {
			cursor = 0;
		}

		@Override
		public boolean hasNext() {
			return (cursor < numberOfEntries);
		}

		@Override
		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();
			T item = list[cursor++];
			isRemoveOrSetLegal = true;
			lastMove = NEXT;
			return item;
		}

		@Override
		public boolean hasPrevious() {
			return cursor > 0;
		}

		@Override
		public T previous() {
			if (!hasPrevious())
				throw new NoSuchElementException();
			T item = list[--cursor];
			isRemoveOrSetLegal = true;
			lastMove = PREV;
			return item;
		}

		/**
		 * Returns the index of the element that would be returned by a subsequent call
		 * to next(). (Returns list size if the list iterator is at the end of the
		 * list.)
		 * 
		 * @return the index of the element that would be returned by a subsequent call
		 *         to next, or list size if the list iterator is at the end of the list
		 */
		@Override
		public int nextIndex() {
			return cursor;
		}

		@Override
		public int previousIndex() {
			return cursor - 1;
		}

		/**
		 * Removes from the list the last element that was returned by next() or
		 * previous() (optional operation). Precondition: next() or previous() has been
		 * called but remove() or add() method has not been called. This call can only
		 * be made once per call to next or previous. The list has not been altered
		 * during the iteration except by calls to iterator's remove(), add(), or set()
		 * methods.
		 * 
		 * @throws UnsupportedOperationException - if the remove operation is not
		 *                                       supported by this list iterator
		 * @throws IllegalStateException         - if neither next nor previous have
		 *                                       been called, or remove or add have been
		 *                                       called after the last call to next or
		 *                                       previous
		 */
		@Override
		public void remove() {
			if (!isRemoveOrSetLegal)
				throw new IllegalStateException("Illegal call to remove: " + "next or previous was not called");
			cursor -= lastMove;
			ArrayListWithListIterator.this.remove(cursor);
			isRemoveOrSetLegal = false;
		}

		/**
		 * Replaces the last element returned by next() or previous()
		 * with the specified element (optional operation). This call can be made only
		 * if neither remove() nor add(E) have been called after the last call to next
		 * or previous.
		 */
		@Override
		public void set(T e) {
			if (!isRemoveOrSetLegal)
				throw new IllegalStateException("Illegal call to set: " + "next or previous was not called");
			list[cursor - lastMove] = e;
		}

		/**
		 * Inserts the specified element into the list (optional operation). The element
		 * is inserted immediately before the element that would be returned by next(),
		 * if any, and after the element that would be returned by previous(), if any.
		 * (If the list contains no elements, the new element becomes the sole element
		 * on the list.) The new element is inserted before the implicit cursor: a
		 * subsequent call to next would be unaffected, and a subsequent call to
		 * previous would return the new element.(This call increases by one the value
		 * that would be returned by a call to nextIndex or previousIndex.)
		 * 
		 * @param newEntry an object to be inserted
		 * @throws UnsupportedOperationException - if the add method is not supported by
		 *                                       this list iterator
		 * @throws ClassCastException            - if the class of the specified element
		 *                                       prevents it from being added to this
		 *                                       list
		 * @throws IllegalArgumentException      - if some aspect of this element
		 *                                       prevents it from being added to this
		 *                                       list
		 */
		@Override
		public void add(T newEntry) {
			ArrayListWithListIterator.this.add(cursor, newEntry);
			cursor++;
			isRemoveOrSetLegal = false;
		}
	}

	public static void main(String[] args) {
		ArrayListWithListIterator<String> list = new ArrayListWithListIterator<>();

		list.add("Ann");
		list.add("Ben");
		list.add("Charlie");
		list.add("David");
		list.add("Ethan");
		list.add("Faina");

		ListIterator<String> iterator = list.iterator();
		
		String current = iterator.next();
		System.out.println(current);

		current = iterator.previous();
		System.out.println(current);

		while (iterator.hasNext())
			current = iterator.next();
		iterator.remove();

		while (iterator.hasPrevious())
			current = iterator.previous();
		iterator.remove();

		for (String name : list)
			System.out.print(name + " ");
		System.out.println();

		iterator.add("Jill");

		iterator.previous();

		while (iterator.hasNext()) {
			String old = iterator.next();
			iterator.set(old + ",junior");
		}

		for (String name : list)
			System.out.print(name + " ");
		System.out.println();
	}

}
