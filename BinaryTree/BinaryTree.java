import java.util.ArrayList; 
/**
 *	Binary Tree of Comparable values.
 *	The tree only has unique values. It does not add duplicate values.
 *	
 *	@author	Rishi Salvi
 *	@since	13 May 2024
 */
public class BinaryTree<E extends Comparable<E>> {

	private TreeNode<E> root;		// the root of the tree
	
	private final int PRINT_SPACES = 3;	// print spaces between tree levels
										// used by printTree()
	
	/**	constructor for BinaryTree */
	public BinaryTree() { 
		root = null; 
	}
	
	/**	Field accessors and modifiers */
	
	/**	Add a node to the tree
	 *	@param value		the value to put into the tree
	 */
	public void add(E value) { 
		TreeNode<E> tmp = new TreeNode(value); 
		if (root == null)
			root = tmp; 
		else{
			boolean isAdded = false; 
			TreeNode<E> curr = root; 
			while (!isAdded){
				if (curr.getValue().compareTo(value) > 0){
					if (curr.getLeft() != null)
						curr = curr.getLeft(); 
					else{
						curr.setLeft(tmp);
						isAdded = true; 
					}
				}
				else if (curr.getValue().compareTo(value) < 0){
					if (curr.getRight() != null)
						curr = curr.getRight(); 
					else{
						curr.setRight(tmp);
						isAdded = true; 
					}
				}
			}
		}
	}
	
	/**
	 *	Print Binary Tree Inorder
	 */
	public void printInorder() { 
		inorder(root); 
	}

	public void inorder(TreeNode<E> curr){
		if (curr.getLeft() != null)
			inorder(curr.getLeft());
		System.out.print(curr.getValue() + " "); 
		if (curr.getRight() != null)
			inorder(curr.getRight());
	}
	
	/**
	 *	Print Binary Tree Preorder
	 */
	public void printPreorder() { 
		preorder(root);
	}

	public void preorder(TreeNode<E> curr){
		System.out.print(curr.getValue() + " "); 
		if (curr.getLeft() != null)
			preorder(curr.getLeft());
		if (curr.getRight() != null)
			preorder(curr.getRight());
	}
	
	/**
	 *	Print Binary Tree Postorder
	 */
	public void printPostorder() { 
		postorder(root);
	}

	public void postorder(TreeNode<E> curr){
		if (curr.getLeft() != null)
			postorder(curr.getLeft());
		if (curr.getRight() != null)
			postorder(curr.getRight());
		System.out.print(curr.getValue() + " "); 
	}
		
	/**	Return a balanced version of this binary tree
	 *	@return		the balanced tree
	 */
	public BinaryTree<E> makeBalancedTree() {
		BinaryTree<E> balancedTree = new BinaryTree<E>();
		ArrayList<E> arr = new ArrayList<>(); 
		getSortedTree(root, arr);
		makeBalanced(balancedTree, arr, 0, arr.size() - 1);
		return balancedTree;
	}

	public void getSortedTree(TreeNode<E> curr, ArrayList<E> arr){
		if (curr.getLeft() != null)
			getSortedTree(curr.getLeft(), arr);
		arr.add(curr.getValue());
		if (curr.getRight() != null)
			getSortedTree(curr.getRight(), arr);
	}

	public void makeBalanced(BinaryTree<E> tree, ArrayList<E> arr, int start, int end){
		int middle = (start + end)/2; 
		if (start <= end){
			tree.add(arr.get(middle));
			makeBalanced(tree, arr, start, middle - 1);
			makeBalanced(tree, arr, middle + 1, end);
		}
	}
	
	/**
	 *	Remove value from Binary Tree
	 *	@param value		the value to remove from the tree
	 *	Precondition: value exists in the tree
	 */
	public void remove(E value) {
		root = remove(root, value);
	}
	/**
	 *	Remove value from Binary Tree
	 *	@param node			the root of the subtree
	 *	@param value		the value to remove from the subtree
	 *	@return				TreeNode that connects to parent
	 */
	public TreeNode<E> remove(TreeNode<E> node, E value) {
		if (node.getValue().compareTo(value) > 0){
			if (node.getLeft().getValue().compareTo(value) != 0)
				return remove(node.getLeft(), value); 
			else
				return handleRemoving(node.getLeft(), node); 
		}
		else if (node.getValue().compareTo(value) < 0){
			if (node.getRight().getValue().compareTo(value) != 0)
				return remove(node.getRight(), value); 
			else
				return handleRemoving(node.getRight(), node); 
		}
		return node; 
	}

	public TreeNode<E> handleRemoving(TreeNode<E> node, TreeNode<E> parent){
		if (node.getLeft() == null && node.getRight() == null){
			if (parent.getLeft().equals(node))
				parent.setLeft(null);
			else
				parent.setRight(null); 
		}
		else if (node.getRight() == null){
			if (parent.getLeft().equals(node))
				parent.setLeft(node.getLeft());
			else
				parent.setRight(node.getLeft());
		}
		else{
			handleRight(node, parent, node.getLeft()); 
		}
		return node; 
	}

	public TreeNode<E> handleRight(TreeNode<E> node, TreeNode<E> parent, TreeNode<E> left){
		TreeNode<E> right = node.getRight(); 
		System.out.println("right: " + right.getValue());
		TreeNode<E> curr = node.getRight(); 
		TreeNode<E> currParent = node; 
		while (curr.getLeft() != null){
			currParent = curr; 
			curr = curr.getLeft(); 
		}
		System.out.println("curr: " + curr.getValue());
		System.out.println("currp: " + currParent.getValue());
		currParent.setLeft(null); 
		if (parent.getValue().compareTo(node.getValue()) > 0)
			parent.setLeft(curr);
		else
			parent.setRight(curr); 
		curr.setLeft(left);
		if (!currParent.equals(node)){
			TreeNode<E> curr2 = curr; 
			while (curr2.getRight() != null)
				curr2 = curr2.getRight(); 
			curr2.setRight(right); 
		}
		return curr;
	}
	

	/*******************************************************************************/	
	/********************************* Utilities ***********************************/	
	/*******************************************************************************/	
	/**
	 *	Print binary tree
	 *	@param root		root node of binary tree
	 *
	 *	Prints in vertical order, top of output is right-side of tree,
	 *			bottom is left side of tree,
	 *			left side of output is root, right side is deepest leaf
	 *	Example Integer tree:
	 *			  11
	 *			/	 \
	 *		  /		   \
	 *		5			20
	 *				  /	  \
	 *				14	   32
	 *
	 *	would be output as:
	 *
	 *				 32
	 *			20
	 *				 14
	 *		11
	 *			5
	 ***********************************************************************/
	public void printTree() {
		printLevel(root, 0);
	}
	
	/**
	 *	Recursive node printing method
	 *	Prints reverse order: right subtree, node, left subtree
	 *	Prints the node spaced to the right by level number
	 *	@param node		root of subtree
	 *	@param level	level down from root (root level = 0)
	 */
	private void printLevel(TreeNode<E> node, int level) {
		if (node == null) return;
		// print right subtree
		printLevel(node.getRight(), level + 1);
		// print node: print spaces for level, then print value in node
		for (int a = 0; a < PRINT_SPACES * level; a++) System.out.print(" ");
		System.out.println(node.getValue());
		// print left subtree
		printLevel(node.getLeft(), level + 1);
	}
	
	
}