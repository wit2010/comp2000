package bag;

public interface BagInterface <T> {
    /**
     Gets the current number of objects in the bag
     @return the integer number of objects in the bag
     */
    public int getCurrentSize();

    /**
     Test whether the bag is empty
     @return true if it is empty; false otherwise
     */
    public boolean isEmpty();

    /**
     Adds a new item to the bag
     @param newEntry: an object of type T
     @return true if the item is added successfully; false if not
     */
    public boolean add(T newEntry);

    /**
     Removes the certain item from the bag
     @param anEntry: an object of type T
     @return true if the item is removed successfully; false if not
     */
    public boolean remove(T anEntry);

    /**
     Removes an unspecified item from the bag
     @return the removed item if the removal succeeded; null otherwise
     */
    public T remove();

    /**
     Removes all the items from the bag
     */
    public void clear();

    /**
     Tests whether a certain item is in the bag
     @param anEntry: object of type T
     @return true if the item is in the bag; false otherwise
     */
    public boolean contains(T anEntry);


    /**
     Counts the number of times a given item occurs in the bag
     @param an anEntry: object of type T
     @return the number of times the specified item occurs in the bag
     */
    public int getFrequencyOf (T anEntry);

    /**
     Retrieves all entries that are in the bag
     @return a newly allocated array of all the entries in the bag.
     Note: if the bag is empty, the array is also empty.
     Although the method is declared as array of type T, the runtime
     type is Object
     */
    public T[] toArray();

}
