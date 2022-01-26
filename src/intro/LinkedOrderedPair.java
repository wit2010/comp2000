package intro;

import utils.Node;

public class LinkedOrderedPair<T> implements IOrderedPair<T> {

	private Node<T> head;

	public LinkedOrderedPair(T first, T second) {
		head = new Node<>(first);
		Node<T> tail = new Node<>(second);
		head.setNext(tail);
		tail.setNext(head);
	}

	@Override
	public T getFirst() {
		return head.getData();
	}

	@Override
	public T getSecond() {
		return head.getNext().getData();
	}

	@Override
	public void setFirst(T first) {
		head.setData(first);
	}

	@Override
	public void setSecond(T second) {
		head.getNext().setData(second);
	}

	@Override
	public void swap() {
		head = head.getNext();
	}

	@Override
	public String toString() {
		return "(" + head.getData() + ", " + head.getNext().getData() + ")";
	}

	public IOrderedPair<T> copy() {
		return new LinkedOrderedPair<>(getFirst(), getSecond());
	}

}
