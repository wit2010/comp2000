package tree;

interface IBinaryNode<T> {

	public T getData();

	public void setData(T newData);

	public IBinaryNode<T> getLeftChild();

	public IBinaryNode<T> getRightChild();

	public void setLeftChild(IBinaryNode<T> leftChild);

	public void setRightChild(IBinaryNode<T> rightChild);

	public boolean hasLeftChild();

	public boolean hasRightChild();

	public boolean isLeaf();

	public int getNumberOfNodes();

	public int getHeight();

	public IBinaryNode<T> copy();

}
