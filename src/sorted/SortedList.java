package sorted;

public interface SortedList<T extends Comparable<? super T>> {
	/**
	 * Adds a new entry to the sorted list. List size is increased by 1.
	 * 
	 * @param newEntry the object to be added
	 */
	public void addEntry(T newEntry);

	/**
	 * Removes the first occurrence of anEntry from the list.
	 * 
	 * @param anEntry the object to be removed
	 * @return true if anEntry was located and removed; false otherwise
	 */
	public boolean removeEntry(T anEntry);

	/**
	 * gets the position of anEntry in the list.
	 * 
	 * @param anEntry
	 * @return the position (starting with 0) of the entry in the list, if the entry
	 *         belongs to the list; negative the position - 1 if the entry does not
	 *         belong but might be at the position
	 */
	public int getPosition(T anEntry);

	// the following methods are the same as ADT List methods.
	public T getEntry(int givenPosition);

	public boolean contains(T anEntry);

	public T remove(int givenPosition);

	public void clear();

	public int getLength();

	public boolean isEmpty();

	public Object[] toArray();

}
