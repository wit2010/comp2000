package stack;

public interface Stack<T> {
	/**
	 * Adds a new entry to the top of the stack
	 * 
	 * @param anEntry of type T
	 */
	public void push(T entry);

	/**
	 * removes and returns the top entry
	 * 
	 * @return the top entry;
	 * @throws EmptyStackException when the stack is empty
	 */
	public T pop();

	/**
	 * Returns the top entry; null if the stack is empty
	 * 
	 * @throws EmptyStackException when the stack is empty
	 * @return the top entry
	 */
	public T peek();

	/**
	 * detects an empty stack
	 * 
	 * @return true if the stack is empty; false otherwise
	 */
	public boolean isEmpty();

	/**
	 * removes all entries from the stack
	 */
	public void clear();

}
