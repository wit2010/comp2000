package dictionary;

import java.util.Iterator;

public class SortedLinkedDictionary<K extends Comparable<? super K>, V> implements Dictionary<K, V> {
	private Node head;
	private Node tail;
	private int numberOfEntries;

	public SortedLinkedDictionary() {
		head = null;
		tail = null;
		numberOfEntries = 0;
	}

	public V getValue(K key) {
		for (Node currNode = head; currNode != null; currNode = currNode.getNext()) {
			int result = key.compareTo(currNode.getKey());
			if (result == 0)
				return currNode.getValue();
			if (result < 0)
				break;
		}
		return null;
	}

	public int getSize() {
		return numberOfEntries;
	}

	public void clear() {
		// to implement
	}

	public boolean isEmpty() {
		// to implement
		return false;
	}

	public boolean contains(K key) {
		for (Node currNode = head; currNode != null; currNode = currNode.getNext()) {
			int result = key.compareTo(currNode.getKey());
			if (result == 0)
				return true;
			if (result < 0)
				break;
		}
		return false;
	}

	private Node getNodeAfterKey(K aKey) {
		Node currNode = head;
		while (currNode != null && aKey.compareTo(currNode.getKey()) > 0)
			currNode = currNode.getNext();
		return currNode;
	}

	public V add(K newKey, V newValue) {
		Node nodeAfter = getNodeAfterKey(newKey);
		Node toAdd = new Node(newKey, newValue);
		if (nodeAfter == null) { // adding to end
			toAdd.setPrev(tail);
			if (tail == null) // empty chain)
				head = toAdd;
			else
				tail.setNext(toAdd);
			tail = toAdd;
		} else if (newKey.compareTo(nodeAfter.getKey()) == 0) { // replacing
			V oldValue = nodeAfter.getValue();
			nodeAfter.setValue(newValue);
			return oldValue;
		} else if (nodeAfter == head) {
			toAdd.setNext(head);
			head.setPrev(toAdd);
			head = toAdd;
		} else { // adding in between
			Node prevNode = nodeAfter.getPrev();
			prevNode.setNext(toAdd);
			toAdd.setPrev(prevNode);
			toAdd.setNext(nodeAfter);
			nodeAfter.setPrev(toAdd);
		}
		numberOfEntries++;
		return null;
	}

	public V remove(K aKey) {
		Node nodeAfter = getNodeAfterKey(aKey);
		if (nodeAfter != null && aKey.compareTo(nodeAfter.getKey()) == 0) {
			V oldValue = nodeAfter.getValue();
			if (nodeAfter == head) {
				head = head.getNext();
				if (head == null)
					tail = null;
				else
					head.setPrev(null);
			} else if (nodeAfter == tail) {
				tail = tail.getPrev();
				tail.setNext(null);
			} else {
				Node prevNode = nodeAfter.getPrev();
				Node nextNode = nodeAfter.getNext();
				prevNode.setNext(nextNode);
				nextNode.setPrev(prevNode);
			}
			numberOfEntries--;
			return oldValue;
		}
		return null;
	}

	public Iterator<K> getKeyIterator() {
		return new KeyIterator();
	}

	public Iterator<V> getValueIterator() {
		return new ValueIterator();
	}

	private class KeyIterator implements Iterator<K> {
		private Node nextNode;

		public KeyIterator() {
			nextNode = head;
		}

		public boolean hasNext() {
			return (nextNode != null);
		}

		public K next() {
			if (!hasNext())
				throw new IllegalStateException("Iteration after the list");
			K result = nextNode.getKey();
			nextNode = nextNode.getNext();
			return result;
		}

		public void remove() {
			throw new UnsupportedOperationException("No remove in dictionary iterator");
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

	private class Node {
		private K key;
		private V value;
		private Node next;
		private Node prev;

		public Node(K newKey, V newValue) {
			key = newKey;
			value = newValue;
			next = null;
			prev = null;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V newValue) {
			value = newValue;
		}

		public Node getNext() {
			return next;
		}

		public Node getPrev() {
			return prev;
		}

		public void setNext(Node n) {
			next = n;
		}

		public void setPrev(Node p) {
			prev = p;
		}
	}

}
