package dictionary;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayDictionary<K, V> implements Dictionary<K, V> {

	private Entry<K, V>[] entries;
	private int numberOfEntries;
	private static final int DEFAULT_CAPACITY = 10;

	public ArrayDictionary() {
		this(DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public ArrayDictionary(int capacity) {
		entries = (Entry<K, V>[]) new Entry[capacity];
		numberOfEntries = 0;
	}

	private void ensureCapacity() {
		if (numberOfEntries == entries.length) {// array is full
			Entry<K, V>[] newEntries = (Entry<K, V>[]) Arrays.copyOf(entries, 2 * numberOfEntries);
			entries = newEntries;
		}
	}

	private int locateIndex(K key) { // the method returns numberOfEntries if key is not found
		int index = 0;
		while ((index < numberOfEntries) && !key.equals(entries[index].getKey()))
			index++;

		return index;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public V add(K key, V value) {
		if (key == null || value == null)
			throw new IllegalArgumentException();
		V oldValue = null;
		int keyIndex = locateIndex(key);
		if (keyIndex < numberOfEntries) {
			oldValue = entries[keyIndex].getValue();
			entries[keyIndex].setValue(value);
		} else {
			entries[numberOfEntries++] = new Entry(key, value);
			ensureCapacity();
		}
		return oldValue;
	}

	public V remove(K key) {
		V oldValue = null;
		int keyIndex = locateIndex(key);
		if (keyIndex < numberOfEntries) {
			oldValue = entries[keyIndex].getValue();
			entries[keyIndex] = entries[numberOfEntries - 1];
			entries[numberOfEntries - 1] = null;
			numberOfEntries--;
		}
		return oldValue;
	}

	public V getValue(K key) {
		int keyIndex = locateIndex(key);
		if (keyIndex < numberOfEntries)
			return entries[keyIndex].getValue();
		return null;
	}

	public boolean contains(K key) {
		return locateIndex(key) < numberOfEntries;
	}

	public int getSize() {
		return numberOfEntries;
	}

	public Iterator<K> getKeyIterator() {
		return new KeyIterator();
	}

	public Iterator<V> getValueIterator() {
		return new ValueIterator();
	}

	public boolean isEmpty() {
		return (numberOfEntries == 0);
	}

	public void clear() {
		// to implement
	}

	private class KeyIterator implements Iterator<K> {
		public int cursor;

		public KeyIterator() {
			cursor = 0;
		}

		public boolean hasNext() {
			return (cursor < getSize());
		}

		public K next() {
			K out = (K) entries[cursor++].getKey();
			return out;
		}

		public void remove() {
			throw new UnsupportedOperationException("No remove for dictionary iterator");
		}
	}

	private class ValueIterator implements Iterator<V> {

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public V next() {
			// TODO Auto-generated method stub
			return null;
		}

	}
}
