import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BinaryNodeTest {
    
    BinaryNode root;

    @Test
    public void testNumLeaves1() {
        buildTree1();
        assertEquals(3, root.numLeaves());
    }
    
    @Test
    public void testNumLeaves2() {
        buildTree2();
        assertEquals(4, root.numLeaves());
    }
    
  @Test
  public void testBFS1() {
      buildTree1();
      assertEquals("(2)(7)(5)(3)(9)(0)(1)(4)", BinaryNode.breadthFirstSearch(root));
  }
  
  @Test
  public void testBFS2() {
      buildTree2();
      assertEquals("(8)(3)(10)(1)(6)(14)(4)(7)(13)", BinaryNode.breadthFirstSearch(root));
  }
  
    // Builds the tree from the worksheet exercise 1
    public void buildTree1() {
        root = new BinaryNode(2);
        
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
    }
    
    // Builds the BST from the worksheet exercise 4
    public void buildTree2() {
        root = new BinaryNode(8);
        
        
        BinaryNode node1 = new BinaryNode(1);
        BinaryNode node3 = new BinaryNode(3);
        BinaryNode node4 = new BinaryNode(4);
        BinaryNode node6 = new BinaryNode(6);
        BinaryNode node7 = new BinaryNode(7);
        BinaryNode node10 = new BinaryNode(10);
        BinaryNode node13 = new BinaryNode(13);
        BinaryNode node14 = new BinaryNode(14);
        
        node6.setLeftChild(node4);
        node6.setRightChild(node7);
        node14.setLeftChild(node13);
        node10.setRightChild(node14);
        node3.setLeftChild(node1);
        node3.setRightChild(node6);
        
        root.setLeftChild(node3);
        root.setRightChild(node10);
    }

}
