package tree;

public class BinarySearchTree <T extends Comparable <? super T>>
        extends BinaryTree <T> implements ISearchTree <T>{

	public BinarySearchTree () {
        super ();
    }
    public BinarySearchTree (T data) {
        super (data);
    }

    @Override
    public void setTree (T rootData) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setTree (T rootData, IBinaryTree <T> leftTree,
                         IBinaryTree <T> rightTree) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T getEntry(T entry) {
        BinaryNode <T> currNode = getRootNode();
        while (currNode != null) {
            int compValue = entry.compareTo(currNode.getData());
            if (compValue == 0)
                return currNode.getData();
            if (compValue > 0)
                currNode = currNode.getRightChild();
            else
                currNode = currNode.getLeftChild();
        }
        return null;
    }

    @Override
    public T add(T newEntry) {
        BinaryNode <T> currNode = getRootNode();
        BinaryNode <T> parent = null;
        BinaryNode <T> toAdd = new BinaryNode <>(newEntry);
        boolean left = false;
        if (currNode == null) { // tree is empty
            setRootNode(toAdd);
            return null;
        }
        while (currNode != null) {
            parent = currNode;
            int compValue = newEntry.compareTo(currNode.getData());
            if (compValue >= 0) {
                left = false;
                currNode = currNode.getRightChild();
            }
            else {
                left = true;
                currNode = currNode.getLeftChild();
            }
        }
        if (left)
            parent.setLeftChild(toAdd);
        else
            parent.setRightChild(toAdd);
        return null;
    }

    @Override
    public T remove(T entry) {
        T result;
        if (isEmpty())
            return null;
        MoveInfo move = new MoveInfo ();
        BinaryNode <T> currNode = getRootNode();
        T rootData;
        int compValue;
        do {
            rootData = move.getCurrent().getData();
            compValue = entry.compareTo (rootData);
            if (compValue > 0 ) { // move to the right
                move.setParent(currNode);
                currNode = currNode.getRightChild();
                move.setCurrent(currNode);
                move.setRight ();
            }
            if (compValue < 0) { // move to the left
                move.setParent(currNode);
                currNode = currNode.getLeftChild();
                move.setCurrent(currNode);
                move.setLeft();
            }
        } while (compValue != 0 && currNode != null);

        if (currNode == null) // no match
            return null;
        assert (compValue == 0);
        result = currNode.getData();
        boolean hasLeft = currNode.hasLeftChild();
        boolean hasRight = currNode.hasRightChild();
        if (!hasLeft ) { // hasRight or leaf
            resetChild (move, currNode.getRightChild());
        }
        else if (!hasRight) {
            resetChild (move, currNode.getLeftChild());
        }
        else { // two children
            BinaryNode <T> toRemove = currNode;
            move.setParent(currNode);
            currNode = currNode.getLeftChild();
            move.setLeft();
            move.setCurrent(currNode);
            while (currNode.hasRightChild()) {
                move.setRight();
                move.setParent(currNode);
                currNode = currNode.getRightChild();
                move.setCurrent(currNode);
            }
            toRemove.setData(currNode.getData());
            resetChild (move, currNode.getLeftChild());
        }

        return result;
    }

    private void resetChild(MoveInfo move, BinaryNode <T> newChild) {
        BinaryNode <T> parent = move.getParent();
        if (parent == null ) // root: the only node with no parent
            setRootNode (newChild);
        else if (move.getLeft())
            parent.setLeftChild (newChild);
        else
            parent.setRightChild (newChild);
    }

    private class MoveInfo {
        private BinaryNode <T> parent;
        private BinaryNode <T> current;
        private boolean left;

        public MoveInfo () {
            parent = null;
            current = getRootNode ();
            left = true;
        }
        public void setParent (BinaryNode<T> parent) {
            this.parent = parent;
        }
        public BinaryNode <T> getParent () {
            return parent;
        }
        public void setCurrent (BinaryNode<T> node) {
            current = node;
        }
        public BinaryNode <T> getCurrent () {
            return current;
        }
        public void setLeft () {
            left = true;
        }
        public void setRight () {
            left = false;
        }
        public boolean getLeft () {
            return left;
        }
    }

}
