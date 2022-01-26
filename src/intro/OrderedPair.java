package intro;

public class OrderedPair<T> implements IOrderedPair<T> {

	// Attributes
	private T first;
	private T second;

	// Constructor
	public OrderedPair(T first, T second) {
		this.first = first;
		this.second = second;
	}

	// Methods
	@Override
	public T getFirst() {
		return first;
	}

	@Override
	public T getSecond() {
		return second;
	}

	@Override
	public void setFirst(T first) {
		this.first = first;
	}

	@Override
	public void setSecond(T second) {
		this.second = second;
	}

	@Override
	public void swap() {
		T temp = first;
		first = second;
		second = temp;
	}

	@Override
	public String toString() {
		return "(" + first + ", " + second + ")";
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		OrderedPair<T> other = (OrderedPair<T>) o;
		return (first.equals(other.getFirst()) && second.equals(other.getSecond()));
	}

	@Override
	public IOrderedPair<T> copy() {
		return new OrderedPair<>(first, second);
	}
}
