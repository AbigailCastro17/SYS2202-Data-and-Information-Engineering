import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryNode {
	
	private int data;
	private BinaryNode leftChild;
	private BinaryNode rightChild;
	
	public BinaryNode(int data) {
		this.data = data;
		this.leftChild = null;
		this.rightChild = null;
	}
	
	//getters and setters
	public int getData() {
		return this.data;
	}
	
	public BinaryNode getLeftChild() {
		return this.leftChild;
	}
	
	public BinaryNode getRightChild() {
		return this.rightChild;
	}
	
	public void setLeftChild(BinaryNode newLeftChild) {
		this.leftChild = newLeftChild;
	}
	
	public void setRightChild(BinaryNode newRightChild) {
		this.rightChild = newRightChild;
	}
	
	/**
	 * Gets the number of leaves in the tree with "this" as root
	 * @return the number of leaves in the tree
	 */
    public int numLeaves() {
		// YOUR CODE HERE
        int leafCount=0;
	    if (this==null) {
	        return 0; 
	    }
	    else if (this.getLeftChild()==null && this.getRightChild()==null) {
	        return 1;
	    }
	    else {
	        if (this.leftChild!=null && this.rightChild!=null){
	            leafCount = this.leftChild.numLeaves() + this.rightChild.numLeaves();
	        }
	        else if (this.leftChild!=null) {
	            leafCount=this.leftChild.numLeaves();
	        }
	        else {
	            leafCount=this.rightChild.numLeaves();
	        }
	    }
	    return leafCount;      
	}
	
//	/**
//	 * Gets the breadth-first search traversal of the tree with "this" as root
//	 * Each node's data should have the format (data) Ex: (5) and (10)
//	 * @return the bfs traversal
//	 */
	public static String breadthFirstSearch(BinaryNode root) {
		Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
		String s ="";
		
		//put root on queue to start it off
		if (root!=null) {
		    queue.add(root);
		}
		
		//YOUR CODE HERE
		while (queue.size()>0) {
		    BinaryNode n = queue.remove();
		    if (n!=null) {
		        s = s + "("+n.getData()+")";
		        if (n.leftChild!=null && n.rightChild!=null){
	                queue.add(n.leftChild);
	                queue.add(n.rightChild);
	            }
	            else if (n.leftChild!=null) {
	                queue.add(n.leftChild);
	            }
	            else {
	                queue.add(n.rightChild);
	            }
	                
            }
		}	
		return s;
	}
	
	public static  void main(String[] args) {
	    BinaryNode root = new BinaryNode(2);
        
        BinaryNode node0 = new BinaryNode(0);
        BinaryNode node1 = new BinaryNode(1);
        BinaryNode node3 = new BinaryNode(3);
        BinaryNode node4 = new BinaryNode(4);
        BinaryNode node5 = new BinaryNode(5);
        BinaryNode node7 = new BinaryNode(7);
        BinaryNode node9 = new BinaryNode(9);
        
        //build the tree (same as one in Exercise 1)
        node3.setLeftChild(node1);
        node7.setLeftChild(node3);
        node9.setRightChild(node4);
        node5.setLeftChild(node9);
        node5.setRightChild(node0);
        
        root.setLeftChild(node7);
        root.setRightChild(node5);
        
        System.out.println(root.numLeaves());
        
        System.out.println(BinaryNode.breadthFirstSearch(root));
	}
}
