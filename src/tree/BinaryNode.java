package tree;

class BinaryNode<T> implements IBinaryNode<T> {
	private T data;
	private BinaryNode<T> leftChild;
	private BinaryNode<T> rightChild;

	public BinaryNode(T data) {
		this.data = data;
	}

	@Override
	public T getData() {
		return data;
	}

	@Override
	public void setData(T newData) {
		data = newData;
	}

	@Override
	public BinaryNode<T> getLeftChild() {
		return leftChild;
	}

	@Override
	public BinaryNode<T> getRightChild() {
		return rightChild;
	}

	@Override
	public void setLeftChild(IBinaryNode<T> leftChild) {
		this.leftChild = (BinaryNode<T>) leftChild;
	}

	@Override
	public void setRightChild(IBinaryNode<T> rightChild) {
		this.rightChild = (BinaryNode<T>) rightChild;
	}

	@Override
	public boolean hasLeftChild() {
		return leftChild != null;
	}

	@Override
	public boolean hasRightChild() {
		return rightChild != null;
	}

	@Override
	public boolean isLeaf() {
		return rightChild == null && leftChild == null;
	}

	@Override
	public int getNumberOfNodes() {
		int n = 1;
		if (hasRightChild())
			n += rightChild.getNumberOfNodes();
		if (hasLeftChild())
			n += leftChild.getNumberOfNodes();
		return n;
	}

	@Override
	public int getHeight() {
		int h = 1;
		int leftHeight = leftChild == null ? 0 : leftChild.getHeight();
		int rightHeight = rightChild == null ? 0 : rightChild.getHeight();
		h += (leftHeight > rightHeight ? leftHeight : rightHeight);
		return h;
	}

	@Override
	public IBinaryNode<T> copy() {
		IBinaryNode<T> copied = new BinaryNode<>(data);
		if (hasLeftChild())
			copied.setLeftChild(leftChild.copy());
		if (hasRightChild())
			copied.setRightChild(rightChild.copy());
		return copied;
	}

	public String toString() {
		String out = data.toString();
		boolean children = false;
		if (hasLeftChild()) {
			out += ("\nLeft child: ") + leftChild.getData();
			children = true;
		}
		if (hasRightChild()) {
			if (!children)
				out += "\n";
			else
				out += "; ";
			out += ("Right child: ") + rightChild.getData();
		}
		return out;
	}
}
