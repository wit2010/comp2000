package tree;

public interface IBinaryTree<T> extends ITree<T>, TreeIterator<T> {

	/**
	 * Sets this binary tree to a one-node binary tree
	 * 
	 * @param rootData the object in the data of the tree's root
	 */
	public void setTree(T rootData);

	/**
	 * Set this binary tree to a new binary tree with the given root and subtrees.
	 * 
	 * @param rootData  the object in the data of the tree's root
	 * @param leftTree  the left subtree of the new tree
	 * @param rightTree the right subtree of the new tree
	 */
	public void setTree(T rootData, IBinaryTree<T> leftTree, IBinaryTree<T> rightTree);

}
