
/**
 * Binary Tree Node
 * 
 * Tree node that has two children: left and right
 * 
 * @param <T> The type of data this tree node stores
 */

public class BinaryTreeNode<T> {

    /**
     * Reference pointer to the left subtree
     */
    private BinaryTreeNode<T> left;

    /**
     * Reference pointer to the right subtree
     */
    private BinaryTreeNode<T> right;

    /**
     * Data stored at this node
     */
    private T data;

    /**
     * Default Constructor [1]
     * 
     * Creates a binary tree node with null data and null children
     */
    public BinaryTreeNode(){
        this(null,null,null); // calls full Constructor [3] passing three null values; thus assigning all to null
    }

    /**
     * Data-only Constructor [2]
     * 
     * Creates a binary tree node with the given data and null children
     * 
     * @param theData The data to store at this node
     */
    public BinaryTreeNode(T theData){
        this(theData,null,null); // calls full Constructor [3] where left and right values are null
    }							 // thus: this.data = theData; this.left = null; this.right = null; 


    /**
     * Full Constructor [3]
     * 
     * Creates a binary tree node with the given data and child reference pointers
     * 
     * @param theData The data to store at this node
     * @param leftChild A reference pointer to the left subtree
     * @param rightChild A reference pointer to the right subtree
     */
    public BinaryTreeNode(T theData, BinaryTreeNode<T> leftChild, BinaryTreeNode<T> rightChild){
        this.data = theData;
        this.left = leftChild;
        this.right = rightChild;
    }


    /**
     * Left Child/Subtree getter
     * 
     * @return A reference pointer to the root of the left subtree
     */
    public BinaryTreeNode<T> getLeft() {
        return this.left;
    }

    /**
     * Left Child/Subtree Setter
     * 
     * @param left A reference pointer to the new left subtree's root node
     */
    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    /**
     * Right Child/Subtree getter
     * 
     * @return A reference pointer to the root of the right subtree
     */
    public BinaryTreeNode<T> getRight() {
        return this.right;
    }

    /**
     * Right Child/Subtree Setter
     * 
     * @param left A reference pointer to the new right subtree's root node
     */
    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }

    /**
     * Get the data at this node
     * 
     * @return The data stored at this node
     */
    public T getData() {
        return this.data;
    }

    /**
     * Set the data at this node
     * 
     * @param data The data to be stored at this node
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * toString method
     *
     */
    @Override
    public String toString() {
        String retVal = "";
        if (this.left != null)
            retVal += this.left.toString(); // recursive call on left
        if (this.right != null) 
            retVal += this.right.toString(); // recursive call on right
        retVal += "("+this.data+")"; // add this node's data
        return retVal;
    }


    /**
     * Main method
     * 
     * Your code should go here
     * 
     * @param args Command-line arguments
     */
    public static void main(String[] args) {

		// Create a BinaryTreeNode that holds data of type Integer
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(1); // data value is 1
		

		//TODO: Add  your code here 
        BinaryTreeNode<Integer> farLeft = new BinaryTreeNode<Integer>(6);
        BinaryTreeNode<Integer> farLeftRoot = new BinaryTreeNode<Integer>(4);
        BinaryTreeNode<Integer> secondLeft = new BinaryTreeNode<Integer>(7);
        BinaryTreeNode<Integer> secondRight = new BinaryTreeNode<Integer>(8);
        BinaryTreeNode<Integer> middleRoot = new BinaryTreeNode<Integer>(5,secondLeft,secondRight);
        BinaryTreeNode<Integer> farRight = new BinaryTreeNode<Integer>(3);
        BinaryTreeNode<Integer> rootLeft = new BinaryTreeNode<Integer>(2,farLeftRoot,middleRoot);
        root.setLeft(rootLeft);
        root.setRight(farRight);
        farLeftRoot.setLeft(farLeft);
        

        //Print the tree using toString() method (starting at the root of the tree) 
        System.out.println("Printing the tree:");
        System.out.println(root.toString());
    }

}
