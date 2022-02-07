package stack;

import java.util.EmptyStackException;

import utils.Node;

public class LinkedStack<T> implements Stack<T> {

	private Node<T> head;

	public LinkedStack() {
		head = null;
	}

	@Override
	public void push(T entry) {
		Node<T> node = new Node<>(entry);
		node.setNext(head);
		head = node;
	}

	@Override
	public T pop() {
        if (isEmpty())
            throw new EmptyStackException();
        T data = head.getData();
        head = head.getNext();
        return data;
	}

	@Override
	public T peek() {
        if (isEmpty())
            throw new EmptyStackException();
        return head.getData();
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	};

	@Override
	public void clear() {
		head = null;
	};

}
