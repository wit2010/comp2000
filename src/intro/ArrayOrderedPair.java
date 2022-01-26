package intro;

public class ArrayOrderedPair<T> implements IOrderedPair<T> {

	private T[] items;

	@SuppressWarnings("unchecked")
	public ArrayOrderedPair(T first, T second) {
		Object[] temp = { first, second };
		items = (T[]) temp;
	}

	@Override
	public T getFirst() {
		return items[0];
	}

	@Override
	public T getSecond() {
		return items[1];
	}

	@Override
	public void setFirst(T first) {
		items[0] = first;
	}

	@Override
	public void setSecond(T second) {
		items[1] = second;
	}

	@Override
	public void swap() {
		T temp = items[0];
		items[0] = items[1];
		items[1] = temp;
	}

	public String toString() {
		return "(" + items[0] + ", " + items[1] + ")";
	}

	public IOrderedPair<T> copy() {
		return new ArrayOrderedPair<>(items[0], items[1]);
	}
}
