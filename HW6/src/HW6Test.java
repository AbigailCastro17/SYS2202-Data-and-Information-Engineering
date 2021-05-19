import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
/**
 * 
 * @author Abigail Castro abc3gnm
 *
 */

public class HW6Test {
    
    BinarySearchTree<Integer> root = new BinarySearchTree<Integer>();
    

    @Test
    public void testSize1() {
        root.insert(5);
        root.insert(3);
        root.insert(1);
        assertEquals(3, root.size());
    }

    @Test
    public void testHeight1() {
        root.insert(5);
        root.insert(3);
        root.insert(1);
        assertEquals(3, root.height());
    }
    
    
    @Test
    public void testFind1() {
        root.insert(5);
        root.insert(3);
        root.insert(1);
        assertTrue(root.find(3));
    }
    
    @Test
    public void testFind2() {
        root.insert(5);
        root.insert(3);
        root.insert(1);
        assertFalse(root.find(17));
    }
    
    
    @Test
    public void testInsert1() {
        root.insert(5);
        root.insert(3);
        root.insert(1);
        assertFalse(root.insert(3));
    }
    
    @Test
    public void testInsert2() {
        root.insert(5);
        root.insert(3);
        root.insert(1);
        assertTrue(root.insert(17));
    }
    
    @Test
    public void testInOrder1() {
        root.insert(5);
        root.insert(3);
        root.insert(1);
        root.insert(17);
        assertEquals("(1)(3)(5)(17)", root.inOrder());
    }
    
    @Test
    public void testInOrder2() {
        root.insert(5);
        root.insert(3);
        root.insert(1);
        root.insert(17);
        root.insert(15);
        assertEquals("(1)(3)(5)(15)(17)", root.inOrder());
    }
    
    
    @Test
    public void testPostOrder1() {
        root.insert(5);
        root.insert(3);
        root.insert(1);
        root.insert(17);
        assertEquals("(1)(3)(17)(5)", root.postOrder());
    }
    
    @Test
    public void testPostOrder2() {
        root.insert(5);
        root.insert(3);
        root.insert(1);
        root.insert(17);
        root.insert(15);
        assertEquals("(1)(3)(15)(17)(5)", root.postOrder());
    }
    
    
    @Test
    public void testBuildFromList1() {
        ArrayList<Integer> myTree = new ArrayList<Integer>();
        myTree.add(5);
        myTree.add(3);
        myTree.add(1);
        myTree.add(17);
        assertTrue(root.buildFromList(myTree));
    }
    
    @Test
    public void testBuildFromList2() {
        ArrayList<Integer> myTree = new ArrayList<Integer>();
        myTree.add(5);
        myTree.add(3);
        myTree.add(1);
        myTree.add(17);
        myTree.add(3);
        assertFalse(root.buildFromList(myTree));
    }
    
    @Test
    public void testBuildFromListSize() {
        ArrayList<Integer> myTree = new ArrayList<Integer>();
        myTree.add(5);
        myTree.add(3);
        myTree.add(1);
        myTree.add(17);
        root.buildFromList(myTree);
        assertEquals(4, root.size());
    }
    
    @Test
    public void testBuildFromListHeight() {
        ArrayList<Integer> myTree = new ArrayList<Integer>();
        myTree.add(5);
        myTree.add(3);
        myTree.add(1);
        myTree.add(17);
        root.buildFromList(myTree);
        assertEquals(3, root.height());
    }
    
    @Test
    public void testBuildFromListInOrder() {
        ArrayList<Integer> myTree = new ArrayList<Integer>();
        myTree.add(5);
        myTree.add(3);
        myTree.add(1);
        myTree.add(17);
        root.buildFromList(myTree);
        assertEquals("(1)(3)(5)(17)", root.inOrder());
    }
    
    @Test
    public void testBuildFromListPostOrder() {
        ArrayList<Integer> myTree = new ArrayList<Integer>();
        myTree.add(5);
        myTree.add(3);
        myTree.add(1);
        myTree.add(17);
        root.buildFromList(myTree);
        assertEquals("(1)(3)(17)(5)", root.postOrder());
    }
    
    
    @Test
    public void testToString1() {
        root.insert(5);
        root.insert(3);
        root.insert(1);
        root.insert(17);
        assertEquals("(1)(3)(5)(17)", root.toString());
    }
    
    @Test
    public void testToString2() {
        root.insert(5);
        root.insert(3);
        root.insert(1);
        root.insert(17);
        root.insert(15);
        assertEquals("(1)(3)(5)(15)(17)", root.toString());
    }

}
