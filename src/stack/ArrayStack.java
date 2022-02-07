package stack;

import java.util.Arrays;
import java.util.EmptyStackException;

public class ArrayStack<T> implements Stack<T> {
	
	private static final int DEFAULT_CAPACITY = 10;

	private T[] array;
	private int size;
	private int capacity;

	@SuppressWarnings("unchecked")
	public ArrayStack(int capacity) {
		array = (T[]) new Object[capacity];
		this.capacity = capacity;
	}

	public ArrayStack() {
		this(DEFAULT_CAPACITY);
	}

	@Override
	public void push(T entry) {
		if (isFull())
			ensureCapacity();
		array[size++] = entry;
	}

	@Override
	public T pop() {
		if (isEmpty())
			throw new EmptyStackException();
		return array[--size];
	}

	@Override
	public T peek() {
		if (isEmpty())
			throw new EmptyStackException();
		return array[size - 1];
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void clear() {
		size = 0;
	}

	private boolean isFull() {
		return size == capacity;
	}

	private void ensureCapacity() {
		capacity *= 2;
		array = Arrays.copyOf(array, capacity);
	}

}
