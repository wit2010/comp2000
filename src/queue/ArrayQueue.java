package queue;

@SuppressWarnings("unchecked")
public class ArrayQueue<T> implements Queue<T> {

	private static final int DEFAULT_CAPACITY = 10;

	private T[] array;
	private int frontIndex;
	private int backIndex;
	private int capacity;

	public ArrayQueue(int capacity) {
		array = (T[]) new Object[capacity];
		this.capacity = capacity;
		frontIndex = 0;
		backIndex = capacity - 1;
	}

	public ArrayQueue() {
		this(DEFAULT_CAPACITY);
	}

	@Override
	public void enqueue(T newEntry) {
		backIndex = (backIndex + 1) % capacity;
		array[backIndex] = newEntry;

		if (isFull())
			ensureCapacity();
	}

	@Override
	public T dequeue() {
		if (isEmpty())
			throw new EmptyQueueException();
		T front = array[frontIndex];
		array[frontIndex++] = null;
		if (isEmpty())
			clear();
		return front;
	}

	@Override
	public T getFront() {
		if (isEmpty())
			throw new EmptyQueueException();
		return array[frontIndex];
	}

	@Override
	public boolean isEmpty() {
		return (frontIndex == (backIndex + 1) % capacity);
	}

	@Override
	public void clear() {
		if (capacity > DEFAULT_CAPACITY)
			capacity = DEFAULT_CAPACITY;
		array = (T[]) new Object[capacity];
		frontIndex = 0;
		backIndex = capacity - 1;
	}

	private boolean isFull() {
		return frontIndex == (backIndex + 2) % capacity;
	}

	private void ensureCapacity() {
		T[] oldArray = array;
		T[] newArray = (T[]) new Object[2 * capacity];
		int index;
		for (index = 0; index < capacity; index++) {
			newArray[index] = oldArray[(frontIndex + index) % capacity];
			oldArray[(frontIndex + index) % capacity]= null;
		}
		frontIndex = 0;
		backIndex = capacity - 2;
		capacity *= 2;
		array = newArray;
	}

	public static void main(String[] args) {
		ArrayQueue<String> queue = new ArrayQueue<>();

		queue.enqueue("A");
		queue.enqueue("B");
		queue.enqueue("C");
		queue.enqueue("D");

		while (!queue.isEmpty())
			System.out.print(queue.dequeue() + " ");
	}

}
