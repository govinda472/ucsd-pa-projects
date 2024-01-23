/*
* Name: govinda sahoo
* Email: gsahoo@ucsd.edu
* PID: A16378764
* Sources used: tutors
*
* This file is used to create a customTester for MyBST
* and its methods implemented for 
* Insert,remove,search,inorder and successor
*/

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 * This class contains Custom test cases for MyBST.java
 */
public class CustomTester {

    /**
     * Helper method to set up 2 BST for the Tests
     * 
     * @param tree       BST tree with 6 elements
     * @param tree_empty only 1 node
     * @param tree_em    zeronode
     */
    MyBST<Integer, Integer> tree;
    MyBST<Integer, Integer> tree_empty;
    MyBST<Integer, Integer> tree_em;

    // set up the BST tree
    @Before
    public void setup() {
        MyBST.MyBSTNode<Integer, Integer> root = new MyBST.MyBSTNode<>(4, 1, null);
        MyBST.MyBSTNode<Integer, Integer> two = new MyBST.MyBSTNode<>(2, 1, root);
        MyBST.MyBSTNode<Integer, Integer> six = new MyBST.MyBSTNode<>(6, 1, root);
        MyBST.MyBSTNode<Integer, Integer> one = new MyBST.MyBSTNode<>(1, 2, two);
        MyBST.MyBSTNode<Integer, Integer> three = new MyBST.MyBSTNode<>(3, 30, two);
        MyBST.MyBSTNode<Integer, Integer> five = new MyBST.MyBSTNode<>(5, 50, six);

        this.tree = new MyBST<>();
        this.tree.root = root;
        root.setLeft(two);
        root.setRight(six);
        two.setLeft(one);
        two.setRight(three);
        six.setLeft(five);
        tree.size = 6;

    }

    // setup the BST null tree
    @Before
    public void stepup_nulltree() {
        MyBST.MyBSTNode<Integer, Integer> empty_root = new MyBST.MyBSTNode<>(5, 1, null);
        this.tree_empty = new MyBST<>();
        this.tree_empty.root = empty_root;
        empty_root.setLeft(null);
        empty_root.setRight(null);
        tree_empty.size = 1;
    }

    @Before
    public void step_emptytree() {
        MyBST.MyBSTNode<Integer, Integer> em_root = new MyBST.MyBSTNode<>(null, null, null);
        this.tree_em = new MyBST<>();
        this.tree_em.root = em_root;
        em_root.setLeft(null);
        em_root.setRight(null);
        tree_em.size = 0;
    }

    /** BST Tests */
    /** successor */

    /** Test for root successor when no right NODE */
    @Test
    public void Successor_root() {
        MyBST.MyBSTNode<Integer, Integer> treeRoot = tree.root;
        treeRoot.getRight().setParent(null);
        treeRoot.setRight(null);
        assertEquals(null, treeRoot.successor());
    }

    /** Test for successor node with no right node */
    @Test
    public void Successor1() {
        MyBST.MyBSTNode<Integer, Integer> treeRoot = tree.root.getLeft().getLeft();
        assertSame(tree.root.getLeft(),
                treeRoot.successor());
    }

    /** Test for successor node at different point in the BST */
    @Test
    public void Successor2() {
        MyBST.MyBSTNode<Integer, Integer> treeRoot = tree.root.getLeft().getRight();
        assertSame(tree.root, treeRoot.successor());
    }

    /** Test for successor node at different point in the BST */
    @Test
    public void Successor3() {

        MyBST.MyBSTNode<Integer, Integer> treeRoot = tree.root.getLeft();
        assertSame(tree.root.getLeft().getRight(),
                treeRoot.successor());
    }

    /** Test for successor node at different point in the BST */
    @Test
    public void Successor4() {
        MyBST.MyBSTNode<Integer, Integer> treeRoot = tree.root;
        assertSame(tree.root.getRight().getLeft(),
                treeRoot.successor());
    }

    /** Test for successor node at different point in the BST */
    @Test
    public void Successor5() {
        MyBST.MyBSTNode<Integer, Integer> treeRoot = tree.root.getRight();
        assertEquals(null, treeRoot.successor());
    }

    /** Test for successor node at different point in the BST */
    @Test
    public void Successor6() {
        MyBST.MyBSTNode<Integer, Integer> treeRoot = tree.root.getRight().getLeft();
        assertEquals(tree.root.getRight(), treeRoot.successor());
    }

    /** insert */
    /** test for null pointer expceptiom */
    @Test(expected = NullPointerException.class)
    public void insert_null1() {
        tree.insert(null, 1);
    }

    /** test for null pointer expceptiom: */
    @Test(expected = NullPointerException.class)
    public void insert_null2() {
        tree.insert(null, null);
    }

    /** test for Test insert into a empty BST and test multiple insertions */
    @Test
    public void insert_empty() {

        tree_empty.insert(5, 1);
        assertEquals((int) tree_empty.root.getValue(), 1);
        assertEquals((int) tree_empty.root.getKey(), 5);
        // insert right
        tree_empty.insert(8, 10);
        tree_empty.insert(7, 20);
        tree_empty.insert(9, 30);

        // insert left
        tree_empty.insert(3, 40);
        tree_empty.insert(2, 50);
        tree_empty.insert(4, 60);

        // Test insert into balanced list so it goes in the right branch
        // key greater than root key
        assertEquals((int) tree_empty.root.getRight()
                .getValue(), 10);
        assertEquals((int) tree_empty.root.getRight()
                .getKey(), 8);
        assertSame(tree_empty.root, tree_empty.root.getRight().getParent());

        assertEquals((int) tree_empty.root.getRight().getRight()
                .getValue(), 30);
        assertEquals((int) tree_empty.root.getRight().getRight().getKey(), 9);
        assertSame(tree_empty.root.getRight(),
                tree_empty.root.getRight().getRight().getParent());

        assertEquals((int) tree_empty.root.getRight().getLeft().getValue(), 20);
        assertEquals((int) tree_empty.root.getRight().getLeft().getKey(), 7);
        assertSame(tree_empty.root.getRight(),
                tree_empty.root.getRight().getLeft().getParent());

        // Test insert into balanced list so it goes in the left branch
        // key less than root key

        assertEquals((int) tree_empty.root.getLeft().getValue(), 40);
        assertEquals((int) tree_empty.root.getLeft().getKey(), 3);
        assertSame(tree_empty.root, tree_empty.root.getLeft().getParent());

        assertEquals((int) tree_empty.root.getLeft().getLeft().getValue(), 50);
        assertEquals((int) tree_empty.root.getLeft().getLeft().getKey(), 2);
        assertSame(tree_empty.root.getLeft(), tree_empty.root.getLeft().getLeft().getParent());

        assertEquals((int) tree_empty.root.getLeft().getRight().getValue(), 60);
        assertEquals((int) tree_empty.root.getLeft().getRight().getKey(), 4);
        assertSame(tree_empty.root.getLeft(), tree_empty.root.getLeft().getRight().getParent());

        assertEquals("size of tree", 7, tree_empty.size);
    }

    /** test for insert when key already exists in BST */
    @Test
    public void insert_swap() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        tree.insert(2, 50);
        assertEquals(2, root.getLeft().getKey().intValue());
        assertEquals(50, root.getLeft().getValue().intValue());
        assertSame(root, root.getLeft().getParent());
        assertEquals("size of tree", 6, tree.size);
    }

    /** remove */
    /** Test when key is null */
    @Test
    public void Remove_null() {
        assertNull(tree.remove(null));
        assertNull(tree.remove(25));
    }

    /** Test remove root when only 1 node is left */
    // remove with no children
    @Test
    public void Remove_nochild() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        // left child
        assertEquals(2, tree.remove(1).intValue());
        assertNull(root.getLeft().getLeft());

        // right child
        assertEquals(30, tree.remove(3).intValue());
        assertNull(root.getLeft().getRight());
        assertEquals("size of tree", 4, tree.size);
    }

    /** Test remove when 1 child */
    @Test
    public void remove_1child() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        MyBST.MyBSTNode<Integer, Integer> current = tree.root.getRight().getLeft();
        assertEquals(1, tree.remove(6).intValue());
        assertNull(root.getRight().getRight());
        assertNull(root.getRight().getLeft());
        assertSame(root.getRight(), current);

        assertEquals("size of tree", 5, tree.size);
    }

    /** Test remove when 2 child */
    @Test
    public void remove_2child() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        MyBST.MyBSTNode<Integer, Integer> current = tree.root.getLeft().getRight();
        assertEquals(1, tree.remove(2).intValue());
        assertEquals((int) root.getLeft().getValue(), (int) current.getValue());
        assertEquals((int) root.getLeft().getKey(), (int) current.getKey());
        assertEquals((int) root.getLeft().getLeft().getValue(), 2);
        assertEquals((int) root.getLeft().getLeft().getKey(), 1);
        assertNull(root.getLeft().getRight());
        assertNull(root.getLeft().getLeft().getLeft());

        assertEquals("size of tree", 5, tree.size);
    }

    /** Test remove root when 2 child */
    @Test
    public void remove_root1() {
        MyBST.MyBSTNode<Integer, Integer> new_root = tree.root.getRight().getLeft();
        assertEquals(1, tree.remove(4).intValue());
        assertEquals((int) new_root.getKey(), (int) tree.root.getKey());
        assertEquals((int) new_root.getValue(), (int) tree.root.getValue());

        assertEquals((int) tree.root.getRight().getValue(), 1);
        assertEquals((int) tree.root.getRight().getKey(), 6);

        assertNull(tree.root.getRight().getLeft());
        assertNull(tree.root.getRight().getRight());

        assertEquals("size of tree", 5, tree.size);
    }

    /** inorder */

    /** Test when BST is null */
    @Test
    public void testInorder() {
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> expectedRes = new ArrayList<>();
        assertEquals(expectedRes, tree_em.inorder());
    }

    /** search */
    /** Test search different elements of the BST */
    @Test
    public void testSearch() {
        assertEquals(1, tree.search(6).intValue());
        assertEquals(1, tree.search(4).intValue());
        assertEquals(1, tree.search(2).intValue());
        assertEquals(50, tree.search(5).intValue());
    }

}