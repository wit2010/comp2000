package sortedlist;

import utils.Node;

public class SortedLinkedList<T extends Comparable<? super T>> implements SortedList<T> {

	private Node<T> head;
	private int numberOfEntries;

	protected final Node<T> getFirstNode() {
		return head;
	}

	protected final void addFirstNode(Node<T> toAdd) {
		toAdd.setNext(head);
		head = toAdd;
		numberOfEntries++;
	}

	protected final void addNodeAfter(Node<T> currNode, Node<T> toAdd) {
		assert (currNode != null);
		toAdd.setNext(currNode.getNext());
		currNode.setNext(toAdd);
		numberOfEntries++;
	}

	protected final Node<T> getNodeAt(int position) {
		if (position < 0 || position >= numberOfEntries)
			throw new IndexOutOfBoundsException("searching outside the chain");
		Node<T> currNode = head;
		for (int i = 0; i < position; i++)
			currNode = currNode.getNext();
		return currNode;
	}

	protected final void removeFirstNode() {
		if (isEmpty())
			throw new IllegalStateException("No removal from empty chain");
		head = head.getNext();
		numberOfEntries--;
	}

	protected void removeNodeAfter(Node<T> currNode) {
		Node<T> nextNode = currNode.getNext();
		if (nextNode == null)
			return;
		currNode.setNext(nextNode.getNext());
		numberOfEntries--;
	}

	public boolean isEmpty() {
		if (head == null ^ numberOfEntries == 0)
			throw new IllegalStateException("Corrupted chain of nodes");
		return numberOfEntries == 0;
	}

	public void clear() {
		head = null;
		numberOfEntries = 0;
	}

	public int getLength() {
		return numberOfEntries;
	}

	public boolean contains(T anEntry) {
		for (Node<T> currNode = head; currNode != null; currNode = currNode.getNext()) {
			if (anEntry.equals(currNode.getData()))
				return true;
		}
		return false;
	}

	public T getEntry(int givenPosition) {
		if (givenPosition < 0 || givenPosition >= numberOfEntries)
			throw new IndexOutOfBoundsException();
		Node<T> currNode = getNodeAt(givenPosition);
		return currNode.getData();
	}

	public T remove(int givenPosition) {
		if (givenPosition < 0 || givenPosition >= numberOfEntries)
			throw new IndexOutOfBoundsException();
		T outData;
		if (givenPosition == 0) { // remove first node
			outData = head.getData();
			removeFirstNode();
		} else {
			Node<T> currNode = getNodeAt(givenPosition - 1);
			Node<T> nextNode = currNode.getNext();
			assert (nextNode != null);
			outData = nextNode.getData();
			removeNodeAfter(currNode);
		}
		return outData;
	}

	public Object[] toArray() {
		Object[] result = new Object[getLength()];
		int i = 0;
		for (Node<T> currNode = head; currNode != null; currNode = currNode.getNext()) {
			result[i++] = currNode.getData();
		}
		return result;
	}

	private Node<T> getNodeBefore(T anEntry) {
		Node<T> currNode = null;
		for (Node<T> nextNode = getFirstNode(); nextNode != null; nextNode = nextNode.getNext()) {
			if (anEntry.compareTo(nextNode.getData()) > 0)
				currNode = nextNode;
			else
				break;
		}
		return currNode;
	}

	@Override
	public void addEntry(T newEntry) {
		Node<T> toInsert = new Node<>(newEntry);
		Node<T> before = getNodeBefore(newEntry);

		if (before == null) // add to the beginning
			addFirstNode(toInsert);

		else
			addNodeAfter(before, toInsert);
	}

	@Override
	public boolean removeEntry(T anEntry) {
		if (isEmpty())
			return false;
		Node<T> before = getNodeBefore(anEntry);
		if (before == null) {
			if (anEntry.equals(getFirstNode().getData())) {
				removeFirstNode();
				return true;
			}
		} else {
			Node<T> next = before.getNext();
			if (next != null && anEntry.equals(next.getData())) {
				removeNodeAfter(before);
				return true;
			}
		}

		return false;
	}

	@Override
	public int getPosition(T anEntry) {
		int position = 0;
		int length = getLength();
		Node<T> currNode = getFirstNode();
		int compValue = 0;
		while (position < length) {
			compValue = anEntry.compareTo(currNode.getData());
			if (compValue > 0) {
				position++;
				currNode = currNode.getNext();
			} else
				break;
		}
		if (position >= length || compValue != 0)
			position = -1 - position;
		return position;
	}

	public static void main(String[] args) {
		SortedLinkedList<Character> list = new SortedLinkedList<>();

		list.addEntry('r');
		list.addEntry('c');
		list.addEntry('e');
		list.addEntry('x');

		Object[] characters = list.toArray();
		for (Object character : characters)
			System.out.print(character);
		System.out.println("\n position of r is " + list.getPosition('r'));
		System.out.println("position of z is " + list.getPosition('z'));
		System.out.println("\n position of a is " + list.getPosition('a'));
		System.out.println("\n position of m is " + list.getPosition('m'));
		System.out.println("\n position of x is " + list.getPosition('x'));
		list.removeEntry('c');
		list.removeEntry('c');
		list.removeEntry('l');
		list.removeEntry('y');

		System.out.println("\nList contains e? " + list.contains('e'));
		System.out.println("List contains y? " + list.contains('y'));
		characters = list.toArray();
		for (Object character : characters)
			System.out.print(character);
		list.clear();
		list.removeEntry('b');
	}

}
