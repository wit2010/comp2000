package bag;

import utils.Node;

public class LinkedBag<T> implements Bag<T> {
	private Node<T> head;
	private int numberOfEntries = 0;

	public LinkedBag() { // empty bag
		head = null;
	}

	@Override
	public int getCurrentSize() {
		return numberOfEntries;
	}

	@Override
	public boolean isEmpty() {
		if (numberOfEntries == 0 ^ head == null) {
			throw new IllegalStateException("Corrupted chain of nodes");
		}
		return (numberOfEntries == 0);
	}

	@Override
	public boolean add(T newEntry) {
		Node<T> toAdd = new Node<>(newEntry);
		toAdd.setNext(head);
		// Node <T> toAdd = newNode (newEntry, head);
		head = toAdd;
		numberOfEntries++;
		return true;

	}

	@Override
	public boolean remove(T anEntry) {
		for (Node<T> currNode = head; currNode != null; currNode = currNode.getNext()) {
			if (anEntry.equals(currNode.getData())) {
				currNode.setData(head.getData());
				head = head.getNext();
				numberOfEntries--;
				return true;
			}
		}
		return false;
	}

	@Override
	public T remove() {
		if (isEmpty())
			return null;
		T outData = head.getData();
		head = head.getNext();
		numberOfEntries--;
		return outData;
	}

	@Override
	public void clear() {
		head = null;
		numberOfEntries = 0;
	}

	@Override
	public boolean contains(T anEntry) {
		for (Node<T> currNode = head; currNode != null; currNode = currNode.getNext()) {
			if (anEntry.equals(currNode.getData()))
				return true;
		}
		return false;
	}

	@Override
	public int getFrequencyOf(T anEntry) {
		int count = 0;
		for (Node<T> currNode = head; currNode != null; currNode = currNode.getNext()) {
			if (anEntry.equals(currNode.getData())) {
				count++;
			}
		}
		return count;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T[] toArray() {
		T[] result = (T[]) new Object[numberOfEntries];
		int idx = 0;
		for (Node<T> currNode = head; currNode != null; currNode = currNode.getNext()) {
			result[idx++] = currNode.getData();
		}
		return result;
	}

	public LinkedBag<T> copy() {
		LinkedBag<T> result = new LinkedBag<>();
		for (Node<T> currNode = head; currNode != null; currNode = currNode.getNext())
			result.add(currNode.getData());
		return result;
	}

	@SuppressWarnings("unchecked")
	public boolean equals(Object o) {
		LinkedBag<T> otherBag = (LinkedBag<T>) o;
		if (otherBag.getCurrentSize() != numberOfEntries)
			return false;
		otherBag = otherBag.copy();
		for (Node<T> currNode = head; currNode != null; currNode = currNode.getNext()) {
			if (!otherBag.remove(currNode.getData()))
				return false;
		}
		return true;
	}

	public LinkedBag<T> union(LinkedBag<T> otherBag) {
		LinkedBag<T> result = otherBag.copy();
		for (Node<T> currNode = head; currNode != null; currNode = currNode.getNext())
			result.add(currNode.getData());
		return result;
	}

	public LinkedBag<T> intersection(LinkedBag<T> other) {
		LinkedBag<T> result = new LinkedBag<>();
		LinkedBag<T> otherCopy = other.copy();
		for (Node<T> currNode = head; currNode != null; currNode = currNode.getNext()) {
			if (otherCopy.remove(currNode.getData()))
				result.add(currNode.getData());
		}
		return result;
	}
}
