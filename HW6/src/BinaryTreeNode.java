
/**
 * Binary Tree Node
 * 
 * Tree node that has two children: left and right
 * 
 * @author Abigail Castro abc3gnm
 * @param <Comparable> The type of data this tree node stores
 */
public class BinaryTreeNode<T extends Comparable<T>> {
	
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
	 * Default Constructor
	 * 
	 * Creates a binary tree node with null data and null children
	 */
	public BinaryTreeNode(){
		this(null,null,null);
	}
	
	/**
	 * Data-only Constructor
	 * 
	 * Creates a binary tree node with the given data and null children
	 * 
	 * @param theData The data to store at this node
	 */
	public BinaryTreeNode(T theData){
		this(theData,null,null);
	}
	
	
	/**
	 * Full Constructor
	 * 
	 * Creates a binary tree node with the given data and child reference pointers
	 * 
	 * @param theData The data to store at this node
	 * @param leftChild A reference pointer to the left subtree
	 * @param rightChild A reference pointer to the right subtree
	 */
	public BinaryTreeNode(T theData, BinaryTreeNode<T> leftChild, BinaryTreeNode<T> rightChild){
		data = theData;
		left = leftChild;
		right = rightChild;
	}


	/**
	 * Left Child/Subtree getter
	 * 
	 * @return A reference pointer to the root of the left subtree
	 */
	public BinaryTreeNode<T> getLeft() {
		return left;
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
		return right;
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
		return data;
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
	 */
	@Override
	public String toString() {
	    return null;
	}
	
	
	/**
	 * Main method
	 * 
	 * For main method testing, etc
	 * 
	 * @param args Command-line arguments
	 */
	public static void main(String[] args) {
	    
	}

	//helper method for size() method in BinarySearchTree
    public int size() {
        int nodeCount=0;
        if (this==null) {
            return 0; 
        }
        else {
            if (this.left==null && this.right==null) {
                return 1;
            }
            else if (this.left!=null && this.right!=null){
                nodeCount = 1 + this.left.size() + this.right.size();
            } 
            else if (this.left!=null) {
                nodeCount= 1 + this.left.size();
            }
            else {
                nodeCount= 1 + this.right.size();
            }
        }
        return nodeCount;   
    }

    public int height() {
        if (this == null) {
            return 0; 
        }
        else {
            if (this.left==null && this.right==null) {
                return 1;
            }
            else if (this.left!=null && this.right!=null){
                int leftHeight = this.left.height();
                int rightHeight = this.right.height();
                
                if (leftHeight > rightHeight)
                    return leftHeight + 1;
                else
                    return rightHeight + 1;
            } 
            else if (this.left!=null) {
                int leftHeight = this.left.height();
                return leftHeight + 1;
            }
            else {
                int rightHeight = this.right.height();
                return rightHeight + 1;
            }
        }
    }

    public boolean find(T val) {
        if (this == null) {
            return false; 
        }
        if (this.getData() == val) {
            return true;
        }
        else {
            if (this.left!=null && this.right!=null){
                if (this.left.find(val) || this.right.find(val))
                    return true;
            } 
            else if (this.left!=null) {
                return this.left.find(val);
            }
            else if (this.right!=null) {
                return this.right.find(val);
            }
        }
        return false;
    }
    


    public boolean insert(T val) {
        if (val.compareTo(this.getData()) < 0 && this.left==null){
            this.setLeft(new BinaryTreeNode<T>(val));
            return true;
        }
        else if (val.compareTo(this.getData()) > 0 && this.right==null){
            this.setRight(new BinaryTreeNode<T>(val));
            return true;
        }
        else if (val.compareTo(this.getData()) < 0 && this.left!=null){
            return this.left.insert(val);
        }
        else {
            return this.right.insert(val);
        }
    }

    public String inOrder() {
        String s = "";
        if (this.left != null)
            s += this.left.inOrder(); // recursive call on left
        s += "("+this.data+")"; // add this node's data
        if (this.right != null) 
            s += this.right.inOrder(); // recursive call on right 
        return s;
    }

    private String s = "";
    
    public String postOrder(BinaryTreeNode<T> root) {
        if (root == null) {
            return "";
        }
        postOrder(root.left);
        postOrder(root.right);
//        s+=postOrder(root.left);
//        s+=postOrder(root.right);
        s+="("+root.getData()+")";
        return s;
    }

//    public String postOrder() {
//        String s = "";
//        if (this.left != null)
//            s += this.left.postOrder(); // recursive call on left
//        if (this.right != null) 
//            s += this.right.postOrder(); // recursive call on right
//        s += "("+this.data+")"; // add this node's data
//        return s;
//    }

}
