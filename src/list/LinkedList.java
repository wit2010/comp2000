package list;

import utils.Node;

public class LinkedList<T> implements List<T> {

	private Node<T> firstNode;
	private int numberOfEntries;

	public LinkedList() {
		firstNode = null;
		numberOfEntries = 0;
	}

	private Node<T> getLastNode() {
		if (firstNode == null)
			return null;
		Node<T> currNode = firstNode;
		Node<T> nextNode = currNode.getNext();
		while (nextNode != null) {
			currNode = nextNode;
			nextNode = nextNode.getNext();
		}
		return currNode;
	}

	@Override
	public void add(T newEntry) {
		if (newEntry == null)
			throw new IllegalArgumentException();
		Node<T> newNode = new Node<>(newEntry);
		Node<T> lastNode = getLastNode();
		if (lastNode == null)
			firstNode = newNode;
		else
			lastNode.setNext(newNode);
		numberOfEntries++;
	}

	private Node<T> getNodeAt(int position) {
		assert (position >= 0 && position <= numberOfEntries);
		Node<T> currNode = firstNode;
		for (int i = 0; i < position; i++)
			currNode = currNode.getNext();
		return currNode;
	}

	@Override
	public void add(int newPosition, T newEntry) {
		if (newEntry == null)
			throw new IllegalArgumentException();
		if (newPosition < 0 || newPosition > numberOfEntries)
			throw new IndexOutOfBoundsException();
		Node<T> newNode = new Node<>(newEntry);
		if (newPosition == 0) {
			newNode.setNext(firstNode);
			firstNode = newNode;
		} else {
			Node<T> before = getNodeAt(newPosition - 1);
			Node<T> after = before.getNext();
			before.setNext(newNode);
			newNode.setNext(after);
		}
		numberOfEntries++;
	}

	@Override
	public T remove(int givenPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(T anEntry) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public T replace(int givenPosition, T newEntry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getEntry(int givenPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(T anEntry) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object[] toArray() {
		Object[] result = new Object[numberOfEntries];
		int i = 0;
		for (Node<T> currNode = firstNode; currNode != null; currNode = currNode.getNext()) {
			result[i++] = currNode.getData();
		}
		return result;
	}

}
