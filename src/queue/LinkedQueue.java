package queue;

import utils.Node;

public class LinkedQueue<T> implements Queue<T> {

	private Node<T> frontNode;
	private Node<T> backNode;

	public LinkedQueue() {
		frontNode = null;
		backNode = null;
	}

	@Override
	public void enqueue(T newEntry) {
		Node<T> newNode = new Node<>(newEntry);
		if (isEmpty()) {
			frontNode = newNode;
		} else {
			backNode.setNext(newNode);
		}
		backNode = newNode;
	}

	@Override
	public T dequeue() {
		if (isEmpty())
			throw new EmptyQueueException("No dequeue on empty queue");
		T front = frontNode.getData();
		frontNode = frontNode.getNext();
		if (frontNode == null)
			backNode = null;
		return front;
	}

	@Override
	public T getFront() {
		if (isEmpty())
			throw new EmptyQueueException("No getFront on empty queue");
		return frontNode.getData();
	}

	@Override
	public boolean isEmpty() {
		return frontNode == null;
	}

	@Override
	public void clear() {
		frontNode = null;
		backNode = null;
	}

	public static void main(String[] args) {
		ArrayQueue<String> queue = new ArrayQueue<>();

		queue.enqueue("A");
		queue.enqueue("B");
		queue.enqueue("C");
		queue.enqueue("D");

		while (!queue.isEmpty())
			System.out.println(queue.dequeue());
	}

}
