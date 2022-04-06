package tree;

import java.util.Iterator;
import java.util.Stack;
import java.util.NoSuchElementException;
import queue.ArrayQueue;

public class BinaryTree<T> implements IBinaryTree<T> {

	private BinaryNode<T> root;

	public BinaryTree() {
		root = null;
	}

	public BinaryTree(T rootData) {
		root = new BinaryNode<>(rootData);
	}

	public BinaryTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
		initializeTree(rootData, leftTree, rightTree);
	}

	@Override
	public void setTree(T rootData) {
		root = new BinaryNode<>(rootData);
	}

	@Override
	public void setTree(T rootData, IBinaryTree<T> leftTree, IBinaryTree<T> rightTree) {
		initializeTree(rootData, (BinaryTree<T>) leftTree, (BinaryTree<T>) rightTree);
	}

	private void initializeTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
		root = new BinaryNode<>(rootData);
		if (leftTree != null && !leftTree.isEmpty())
			root.setLeftChild(leftTree.getRootNode());
		if (rightTree != null && !rightTree.isEmpty()) {
			if (rightTree == leftTree)
				root.setRightChild(rightTree.getRootNode().copy());
			else
				root.setRightChild(rightTree.getRootNode());
		}
		if (leftTree != null && leftTree != this)
			leftTree.clear();
		if (rightTree != null && rightTree != this)
			rightTree.clear();
	}

	protected BinaryNode<T> getRootNode() {
		return root;
	}

	protected void setRootNode(BinaryNode<T> newRoot) {
		root = newRoot;
	}

	@Override
	public T getRootData() {
		return root.getData();
	}

	@Override
	public int getHeight() {
		return root.getHeight();
	}

	@Override
	public int getNumberOfNodes() {
		return root.getNumberOfNodes();
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public void clear() {
		root = null;
	}

	@Override
	public Iterator<T> getPreorderIterator() {
		return new PreorderIterator();
	}

	@Override
	public Iterator<T> getPostorderIterator() {
		return new PostOrderIterator();
	}

	@Override
	public Iterator<T> getInorderIterator() {
		return new InorderIterator();
	}

	@Override
	public Iterator<T> getLevelorderIterator() {
		return new LevelOrderIterator();
	}

	private class PreorderIterator implements Iterator<T> {
		private Stack<BinaryNode<T>> nodeStack;

		public PreorderIterator() {
			if (root == null)
				throw new IllegalArgumentException("No iteration on empty tree");
			nodeStack = new Stack<>();
			nodeStack.push(root);
		}

		@Override
		public boolean hasNext() {
			return (!nodeStack.isEmpty());
		}

		@Override
		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();
			BinaryNode<T> currNode = nodeStack.pop();
			T item = currNode.getData();
			if (currNode.hasRightChild())
				nodeStack.push(currNode.getRightChild());
			if (currNode.hasLeftChild())
				nodeStack.push(currNode.getLeftChild());
			return item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	private class InorderIterator implements Iterator<T> {
		private Stack<BinaryNode<T>> nodeStack;

		public InorderIterator() {
			nodeStack = new Stack<>();
			BinaryNode<T> currNode = root;
			while (currNode != null) {
				nodeStack.push(currNode);
				currNode = currNode.getLeftChild();
			}
		}

		@Override
		public boolean hasNext() {
			return (!nodeStack.isEmpty());
		}

		@Override
		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();
			BinaryNode<T> currNode = nodeStack.pop();
			T data = currNode.getData();
			currNode = currNode.getRightChild();
			while (currNode != null) {
				nodeStack.push(currNode);
				currNode = currNode.getLeftChild();
			}
			return data;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	private class PostOrderIterator implements Iterator<T> {
		private Stack<BinaryNode<T>> nodeStack;
		private BinaryNode<T> currNode = null;
		private BinaryNode<T> nextNode;

		public PostOrderIterator() {
			nodeStack = new Stack<>();
			nextNode = leftMostLeaf(root);
		}

		public boolean hasNext() {
			return (nextNode != null);
		}

		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();
			T outData = nextNode.getData();
			currNode = nextNode;
			if (!nodeStack.isEmpty()) {
				nextNode = nodeStack.pop();
				if (nextNode.getRightChild() != currNode && nextNode.getLeftChild() != currNode) {
					nextNode = leftMostLeaf(nextNode);
				}
			} else
				nextNode = null;
			return outData;
		}

		private BinaryNode<T> leftMostLeaf(BinaryNode<T> node) {
			if (node == null)
				return null;
			boolean left, right;
			while (node != null) {
				nodeStack.push(node);
				left = node.hasLeftChild();
				right = node.hasRightChild();
				if (left && right) {
					nodeStack.push(node.getRightChild());
					node = node.getLeftChild();
				} else if (right) {
					node = node.getRightChild();
				} else
					node = node.getLeftChild();
			}
			return nodeStack.pop();
		}

	}
	private class LevelOrderIterator implements Iterator<T> {
		private ArrayQueue<BinaryNode<T>> nodeQueue = new ArrayQueue<BinaryNode<T>>();

		public LevelOrderIterator() {
			nodeQueue.enqueue(root);
		}

		public boolean hasNext() {
			return (!nodeQueue.isEmpty());
		}

		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();
			BinaryNode<T> currNode = nodeQueue.dequeue();
			if (currNode.hasLeftChild())
				nodeQueue.enqueue(currNode.getLeftChild());
			if (currNode.hasRightChild())
				nodeQueue.enqueue(currNode.getRightChild());
			return currNode.getData();
		}

	}

	private BinaryNode<T> getNodeFromRoot(BinaryNode<T> rootNode, T data) {
		if (data.equals(rootNode.getData()))
			return rootNode;
		BinaryNode<T> outNode = null;
		if (rootNode.hasLeftChild())
			outNode = getNodeFromRoot((BinaryNode<T>) rootNode.getLeftChild(), data);
		if (outNode == null && rootNode.hasRightChild())
			outNode = getNodeFromRoot((BinaryNode<T>) rootNode.getRightChild(), data);
		return outNode;
	}

	protected BinaryNode<T> getNode(T data) {
		return getNodeFromRoot(root, data);
	}

	public boolean contains(T data) {
		Iterator<T> preorder = getPreorderIterator(); // or any other iterator
		while (preorder.hasNext()) {
			if (preorder.next().equals(data))
				return true;
		}
		return false;
	}

	public static void main(String[] args) {
		BinaryTree<String> hTree = new BinaryTree<>("H");
		BinaryTree<String> iTree = new BinaryTree<>("I");
		BinaryTree<String> emptyTree = new BinaryTree<>();
		BinaryTree<String> dTree = new BinaryTree<>("D", hTree, emptyTree);
		BinaryTree<String> eTree = new BinaryTree<>("E");
		BinaryTree<String> fTree = new BinaryTree<>("F", emptyTree, iTree);
		BinaryTree<String> gTree = new BinaryTree<>("G");
		BinaryTree<String> bTree = new BinaryTree<>("B", dTree, eTree);
		BinaryTree<String> cTree = new BinaryTree<>("C", fTree, gTree);
		BinaryTree<String> aTree = new BinaryTree<>("A", bTree, cTree);

		Iterator<String> preIterator = aTree.getPreorderIterator();
		while (preIterator.hasNext())
			System.out.print(preIterator.next() + " ");
		System.out.println();
		Iterator<String> inIterator = aTree.getInorderIterator();
		while (inIterator.hasNext())
			System.out.print(inIterator.next() + " ");

		BinaryNode<String> result = aTree.getNode("A");

		System.out.println("\n" + result);
	}

}
