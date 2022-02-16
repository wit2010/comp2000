package queue;

public interface Queue <T> {
	/*
	 * Adds an element to the back of the queue
	 * @param newEntry
	 */
	public void enqueue (T newEntry);

	/**
	 * Removes the element from the front of the queue. 
	 * Throws EmptyQueueException if the queue is empty
	 * @return the removed entry
	 */
	public T dequeue ();

	/**
	 * Retrieves the element from the front of the queue. 
	 * Throws EmptyQueueException if the queue is empty
	 * @return the front entry
	 */
	public T getFront();

	/**
	 * detects whether the queue is empty
	 * @return true or false
	 */
	public boolean isEmpty();

	/**
	 * removes all entries from the queue
	 */
	public void clear(); 

}
