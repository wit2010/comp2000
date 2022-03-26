package dictionary;

import java.util.Iterator;

public interface Dictionary<K, V> {
	/**
	 * Adds a new entry to the dictionary. If the given search key already exists in
	 * the dictionary, replaces the value
	 * 
	 * @param key:   an object search key for the new entry
	 * @param value: an object associated with the given search key
	 * @return the replaced value or null if the value did not exist in the
	 *         dictionary
	 */
	public V add(K key, V value);

	/**
	 * Removes the specific entry from the dictionary.
	 * 
	 * @param key an object search key for the entry to be removed
	 * @return the removed value or null if the value did not exist in the
	 *         dictionary
	 */
	public V remove(K key);

	/**
	 * Retrieves the value associated with the given search key
	 * 
	 * @param key an object search key for the entry to be retrieved
	 * @return the retrieved value or null if the value did not exist in the
	 *         dictionary
	 */
	public V getValue(K key);

	/**
	 * Sees whether an entry with the given search key is in the dictionary
	 * 
	 * @param key an object search key of the desired entry
	 * @return true or false
	 */
	public boolean contains(K key);

	/**
	 * Creates an iterator that traverses all search keys in the dictionary
	 * 
	 * @return an iterator that provides sequential access to all search keys in the
	 *         dictionary
	 */
	public Iterator<K> getKeyIterator();

	/**
	 * Creates an iterator that traverses all values in the dictionary
	 * 
	 * @return an iterator that provides sequential access to all values in the
	 *         dictionary
	 */
	public Iterator<V> getValueIterator();

	/**
	 * Sees whether the dictionary is empty
	 * 
	 * @return true if the dictionary is empty; false otherwise.
	 */
	public boolean isEmpty();

	/**
	 * Removes all entries from the dictionary
	 */
	public void clear();

	public int getSize();

}
