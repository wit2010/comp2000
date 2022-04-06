package tree;

import java.util.Iterator;

public interface TreeIterator<T> {

	public Iterator<T> getPreorderIterator();

	public Iterator<T> getPostorderIterator();

	public Iterator<T> getInorderIterator();

	public Iterator<T> getLevelorderIterator();

}
