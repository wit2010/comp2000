package iterator;

public interface List<T> extends Iterable<T> {
	/**
	 * Add new entry at the end of the list
	 * 
	 * @param newEntry
	 */
	public void add(T newEntry);

	/**
	 * Adds new entry at specified position; shifts the entries to reserve space for
	 * newEntry
	 * 
	 * @param newPosition
	 * @param newEntry
	 * @throws IndexOutOfBoundsException if either givenPosition < 0 or
	 *                                   givenPosition > getLength()
	 */
	public void add(int newPosition, T newEntry);

	/**
	 * Removes an entry at given position
	 * 
	 * @param givenPosition
	 * @return the removed entry
	 * @throws IndexOutOfBoundsException if either givenPosition < 0 or
	 *                                   givenPosition >= getLength()
	 */
	public T remove(int givenPosition);

	/**
	 * Removes the specified entry from the list
	 * 
	 * @param anEntry
	 * @return true if the entry was in the list; false otherwise
	 */
	public boolean remove(T anEntry);

	/**
	 * Removes all entries from the list
	 */
	public void clear();

	/**
	 * Replaces an entry at given position with a new one
	 * 
	 * @param givenPosition
	 * @param newEntry
	 * @return the replaced entry
	 * @throws IndexOutOfBoundsException if either givenPosition < 0 or
	 *                                   givenPosition >= getLength()
	 */
	public T replace(int givenPosition, T newEntry);

	/**
	 * Retrieves an entry at given position
	 * 
	 * @param givenPosition
	 * @return the entry at givenPosition
	 * @throws IndexOutOfBoundsException if either givenPosition < 0 or
	 *                                   givenPosition >= getLength()
	 */
	public T getEntry(int givenPosition);

	/**
	 * Gets the length of this list /* @return the integer number of entries
	 * currently in the list
	 */
	public int getLength();

	/**
	 * Sees whether this list is empty /* return true if the list is empty, false
	 * if not
	 */
	public boolean isEmpty();

	/**
	 * Sees whether the list contains the given entry /* @param anEntry the
	 * object: desired entry /* @return true if the list contains anEntry; false if
	 * not.
	 */
	public boolean contains(T anEntry);

	/**
	 * Retrieves all entries that are in this list in the order they occur in the
	 * list. @ return a newly allocated array of all the entries in the list If the
	 * list is empty, the returned array is empty
	 */
	public Object[] toArray();

}
