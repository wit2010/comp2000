package tree;

import java.util.Iterator;

public class Example2 {

	public static void main(String[] args) {
		BinaryTree<String> dTree = new BinaryTree<>("D");
		BinaryTree<String> eTree = new BinaryTree<>("E");
		BinaryTree<String> fTree = new BinaryTree<>("F");
		BinaryTree<String> gTree = new BinaryTree<>("G");
		BinaryTree<String> bTree = new BinaryTree<>("B", dTree, eTree);
		BinaryTree<String> cTree = new BinaryTree<>("C", fTree, gTree);
		BinaryTree<String> fullTree = new BinaryTree<>("A", bTree, cTree);

		System.out.println("Expected preorder traversal: \nA B D E C F G");
		Iterator<String> preorder = fullTree.getPreorderIterator();
		while (preorder.hasNext())
			System.out.print(preorder.next() + " ");
		System.out.println();

		System.out.println("Expected inorder traversal: \nD B E A F C G");
		Iterator<String> inorder = fullTree.getInorderIterator();
		while (inorder.hasNext())
			System.out.print(inorder.next() + " ");
		System.out.println();

		System.out.println("Expected postorder traversal: \nD E B F G C A");
		Iterator<String> postorder = fullTree.getPostorderIterator();
		while (postorder.hasNext())
			System.out.print(postorder.next() + " ");
		System.out.println();

		System.out.println("Expected levelorder traversal: \nA B C D E F G");
		Iterator<String> levelorder = fullTree.getLevelorderIterator();
		while (levelorder.hasNext())
			System.out.print(levelorder.next() + " ");
		System.out.println();

		BinaryTree<String> hTree = new BinaryTree<>("H");
		BinaryTree<String> emptyTree = new BinaryTree<>();
		fTree = new BinaryTree<>("F", hTree, emptyTree);
		BinaryTree<String> iTree = new BinaryTree<>("I");
		BinaryTree<String> kTree = new BinaryTree<>("K");
		gTree = new BinaryTree<>("G", iTree, kTree);
		dTree = new BinaryTree<>("D", emptyTree, fTree);
		eTree = new BinaryTree<>("E", gTree, emptyTree);
		bTree = new BinaryTree<>("B", dTree, emptyTree);
		cTree = new BinaryTree<>("C", emptyTree, eTree);
		BinaryTree<String> sparseTree = new BinaryTree<String>("A", bTree, cTree);

		preorder = sparseTree.getPreorderIterator();
		System.out.println("Expected preorder traversal: \nA B D F H C E G I K");
		while (preorder.hasNext())
			System.out.print(preorder.next() + " ");
		System.out.println();

		inorder = sparseTree.getInorderIterator();
		System.out.println("Expected inorder traversal: \nD H F B A C I G K E");
		while (inorder.hasNext())
			System.out.print(inorder.next() + " ");
		System.out.println();

		postorder = sparseTree.getPostorderIterator();
		System.out.println("Expected postorder traversal: \nH F D B I K G E C A");
		while (postorder.hasNext())
			System.out.print(postorder.next() + " ");
		System.out.println();

		levelorder = sparseTree.getLevelorderIterator();
		System.out.println("Expected levelorder traversal: \nA B C D E F G H I K");
		while (levelorder.hasNext())
			System.out.print(levelorder.next() + " ");
		System.out.println();
	}

}
