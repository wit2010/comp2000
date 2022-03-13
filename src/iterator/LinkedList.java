package iterator;

import utils.Node;

import java.util.Iterator;
import java.util.NoSuchElementException;

import list.List;

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
    public T remove (int givenPosition) {
        if (givenPosition < 0 || givenPosition >= getLength())
            throw new IndexOutOfBoundsException();
        T dataItem;
        Node <T> nextNode;
        numberOfEntries --;

        if (givenPosition == 0) {
            dataItem = firstNode.getData();
            firstNode = firstNode.getNext();
        }
        else {
            Node <T> currNode = getNodeAt(givenPosition -1);
            nextNode = currNode.getNext();
            assert (nextNode != null);
            dataItem = nextNode.getData();
            currNode.setNext(nextNode.getNext());
        }
        return dataItem;
    }

	@Override
	public boolean remove(T anEntry) {
        Node <T> currNode = firstNode;
        Node <T> prevNode = null;
        while (currNode != null) {
            if (anEntry.equals(currNode.getData())) {
                numberOfEntries --;
                if (prevNode == null) {// match at the beginning
                    currNode = currNode.getNext();
                    firstNode = currNode;
                }
                else  // in the middle or at the end
                    prevNode.setNext (currNode.getNext()); // unlink currNode

                return true;
            }
            else {
                prevNode = currNode;
                currNode = currNode.getNext();
            }
        }
        return false;
	}

	@Override
	public void clear() {
        firstNode = null;
        numberOfEntries = 0;
	}

	@Override
	public T replace(int givenPosition, T newEntry) {
        if (givenPosition < 0 || givenPosition >= getLength())
            throw new IndexOutOfBoundsException();
        Node <T> node = getNodeAt(givenPosition);
        T data = node.getData();
        node.setData(newEntry);
        return data; // to implement
	}

	@Override
	public T getEntry(int givenPosition) {
        if (givenPosition < 0 || givenPosition >= getLength())
            throw new IndexOutOfBoundsException();
        Node <T> currNode = getNodeAt(givenPosition);
        return currNode.getData();
	}

	@Override
	public int getLength() {
        return numberOfEntries;
	}

	@Override
	public boolean isEmpty() {
        if (numberOfEntries == 0 ^ firstNode == null)
            throw new IllegalStateException("Corrupted chain of nodes");
        return numberOfEntries == 0;
	}

	@Override
	public boolean contains(T anEntry) {
        for (Node <T> currNode = firstNode; currNode != null; currNode = currNode.getNext()){
            if (anEntry.equals(currNode.getData()))
                return true;
        }
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

	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}

	private class LinkedListIterator implements Iterator<T> {

		private Node<T> nextNode;

		public LinkedListIterator() {
			if (isEmpty())
				throw new IllegalStateException("Cannot iterate on empty list");
			nextNode = firstNode;
		}

		public boolean hasNext() {
			return nextNode != null;
		}

		public T next() {
			if (!hasNext())
				throw new NoSuchElementException("Illegal call: iterator after the end of the list");
			T result = nextNode.getData();
			nextNode = nextNode.getNext();
			return result;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

}
