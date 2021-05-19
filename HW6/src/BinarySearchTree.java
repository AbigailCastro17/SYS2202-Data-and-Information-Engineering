import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

/**
 * Binary Search Tree Class
 * 
 * The head class for a binary search tree implementation.
 * 
 * @author Abigail Castro abc3gnm
 * @param <Comparable> Type of data to store in the binary tree
 */
public class BinarySearchTree<T extends Comparable<T>> {

	/**
	 * A reference pointer to the root of the tree
	 */
	private BinaryTreeNode<T> root;

	/**
	 * Default constructor
	 * 
	 * Creates a binary tree object with null root note (empty tree)
	 */
	public BinarySearchTree() {
		this(null);
	}

	/**
	 * Constructor
	 * 
	 * Creates a binary tree object with the given node as root
	 * 
	 * @param newRoot The root of the tree
	 */
	public BinarySearchTree(BinaryTreeNode<T> newRoot) {
		this.root = newRoot;
	}
	
	/**
	 * Get the root of the tree
	 * 
	 * @return The root of the tree
	 */
	public BinaryTreeNode<T> getRoot() {
		return root;
	}

	/**
	 * Set the root of the tree
	 * 
	 * @param root  The new root of this tree
	 */
	public void setRoot(BinaryTreeNode<T> root) {
		this.root = root;
	}
	
	
	/**
	 * Returns the size of the tree (that is, the 
	 * number of nodes in the tree). 
	 *
	 */
	public int size() {
	    int size = 0;
        // if the root is not null, call size() on the root
        if (this.root != null) {
            size = root.size(); // call size() on the root of the tree
        }
        return size;  
	}
	
	/**
	 * Returns the height of the tree. 
	 *
	 */
	public int height() {
	    int height = 0;
		if (this.root != null) {
		    height = root.height();
		}
		return height;
	}
	
	/**
	 * Find if an element exists
	 * 
	 * Checks to see if the value val appears in the
	 * tree (recursively).  Returns true if it appears
	 * and false otherwise.
	 * 
	 * @param val The value to find
	 * @return True if the tree contains the value, false otherwise
	 */
	public boolean find(T val) {
	    boolean flag = false;
	    if (this.root != null) {
	        flag = root.find(val);
	    }
	    return flag;
	}
	
	/**
	 * Insert an element
	 * 
	 * Inserts val into the tree where it should appear, returning
	 * true on success and false otherwise
	 * 
	 * @param val The value to insert
	 * @return True on success, false otherwise
	 */
	public boolean insert(T val) {
	    boolean flag = false;
	    if (this.root == null) {
	        BinaryTreeNode<T> rootNode = new BinaryTreeNode<T>(val);
	        this.root = rootNode;
            flag=true;  
	    }
	    else {
	        if (!root.find(val)) {
                flag = root.insert(val);
            }
	    }
	    return flag;
	}
	
	/**
	 * Return a string that represents the data held at each 
	 * node following the rules of an in-order traversal.
	 * 
	 * Covered in class Wednesday, April 22
	 */
	public String inOrder() {
	    String s = "";
        if (this.root != null) {
            s = root.inOrder();
        }
        return s;
	}
	
	/**
	 * Return a string that represents the data held at each 
	 * node following the rules of a post-order traversal.
	 * 
	 * Covered in class Wednesday, April 22
	 */
	
//	public String postOrder() {
//	    String s = "";
//        if (this.root != null) {
//            s = root.postOrder(root);
//        }
//        return s;
//	}
	
	   public String postOrder() {
	        if (this.root == null) {
	            return "";
	        }
	        return this.root.postOrder(root);
	    }

	/**
	 * Build from a list
	 * 
	 * Build the tree from the given list, overwriting any tree data
	 * previously stored in this tree.  Should read from beginning to
	 * end of the list and repeatedly call insert() to build the tree
	 * 
	 * If the tree is not empty when this method is called, you should
	 * empty the tree before adding any elements in list.
	 *
	 * @param list The list from which to build the tree
	 * @return True if successfully built, false otherwise
	 */
	public boolean buildFromList(ArrayList<T> list) {
	    boolean flag = false;
	    if (list != null) {
	        for(int i=0; i<list.size();i++) {
	            flag = this.insert(list.get(i));
	        } 
	    }
	    return flag;
	}


	   
    /**
     * toString method
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.inOrder();
    }
	
	/**
	 * Main method
	 * 
	 * For testing, etc
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
//	    BinarySearchTree<Integer> root = new BinarySearchTree<Integer>();
//	    ArrayList<Integer> myTree = new ArrayList<Integer>();
//        myTree.add(5);
//        myTree.add(3);
//        myTree.add(1);
//        myTree.add(17);
//        
//        System.out.println(root.buildFromList(myTree));
//        System.out.println(root.toString());
//        System.out.println(root.size());
//        System.out.println(root.height());
        
        BinarySearchTree<Integer> root = new BinarySearchTree<Integer>();
//        ArrayList<Integer> myTree = new ArrayList<Integer>();
//        myTree.add(1);
//        myTree.add(5);
//        myTree.add(17);
//        myTree.add(3);
//        myTree.add(8);
        ArrayList<Integer> myTree = new ArrayList<Integer>();
        myTree.add(5);
        myTree.add(3);
        myTree.add(1);
        myTree.add(17);
        root.buildFromList(myTree);
        System.out.println(root.postOrder());
	}
}
