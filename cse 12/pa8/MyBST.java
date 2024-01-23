/*
* Name: govinda sahoo
* Email: gsahoo@ucsd.edu
* PID: A16378764
* Sources used: tutors
*
* This file is used to create MyBST, which implements binary search tree
* and creates methods for inserting,removing, searching and traversing the BST
*/

import java.util.ArrayList;



/**
* This class implements a binary search tree
* by using Array
*
* root=stores the root node of the tree
* size=stores the size of the BST
*/
public class MyBST<K extends Comparable<K>, V> {
    //instance variables
    MyBSTNode<K, V> root = null;
    int size = 0;

    /**
     * This method returns the size of BST
     * which is the number of nodes in the BST
     * @return size of the BST
     * 
    */
    public int size() {
        return size;
    }

    /**
    * This method inserts a node into the BST
    * and returns the value null if didn't raplce a node
    * else the value of the node
    * @param key is the key of the Node to inserted/replaced
    * @param value is the value of the node to be inserted
    * @return the value of the replaced node
    */
    public V insert(K key, V value) {
        //checks for invalid arguements
        if (key == null) {
            throw new NullPointerException();
        }

        MyBSTNode<K, V> Insert_Node = new MyBSTNode(key, value, null);
        //if Bst is empty
        if (size == 0) {
            this.root = Insert_Node;
            size++;
            return null;
        } else if (size > 0) {
            MyBSTNode<K, V> curNode = root;
            while (true) {
                if (key.compareTo(curNode.key) < 0) {
                    if (curNode.left == null) {
                        curNode.left = Insert_Node;
                        Insert_Node.parent = curNode;
                        size++;
                        return null;
                    } else {
                        curNode = curNode.left;
                    }
                } else if (key.compareTo(curNode.key) > 0) {
                    if (curNode.right == null) {
                        curNode.right = Insert_Node;
                        Insert_Node.parent = curNode;
                        size++;
                        return null;
                    } else {
                        curNode = curNode.right;
                    }
                } else {
                    V old_value = curNode.value;
                    curNode.value = value;
                    return old_value;
                }
            }
        }

        return null;
    }

    /**
    * This method finds the Node in the BST with the key
    * and returns the value if it is found, otherwise null
    * if it doesn't exist 
    * @param key the key of the node being searched 
    * @return the value of the Node with the key
    * 
    */
    public V search(K key) {
        MyBSTNode<K, V> curNode = root;
        while (curNode != null) {
            if (key.compareTo(curNode.key) < 0) {
                curNode = curNode.left;
            } else if (key.compareTo(curNode.key) > 0) {
                curNode = curNode.right;
            } else {
                return curNode.value;
            }
        }
        return null;
    }
    
    /**
    * This method removes the Node with the key
    * from the BST and returns its values if it exists
    * otherwise it returns null
    * 
    * @param key the key of the Node to be removed
    * @return the value of the removed node
    * 
    */
    public V remove(K key) {

        // checks invalid inputs
        if (key == null) {
            return null;
        }
        V val = search(key);
        if (val == null) {
            return null;
        }

        // conditions
        MyBSTNode<K, V> curNode = root;

        while (curNode.key != key) {
            // parent key is greater than cur node key
            if (key.compareTo(curNode.key) < 0) { 
                curNode = curNode.left;
            } else if (key.compareTo(curNode.key) > 0) {
                curNode = curNode.right;
            }
        }

        // case 1: no children
        if (curNode.getRight() == null && curNode.getLeft() == null) {
            if (curNode.getKey() != root.getKey()) {
                K p_key = curNode.getParent().getKey();
                if (p_key.compareTo(key) > 0) {
                    curNode.getParent().setLeft(null);
                } // removes connection to left node
                else {
                    curNode.getParent().setRight(null);
                } // removes connection to right node
                curNode.setParent(null);
                size--;
                if (size == 0) {
                    root = null;
                }
                return val;
            } else {
                root = null;
                return val;
            }
        }
        // case 2: only 1 child
        else if ((curNode.getRight() == null && curNode.getLeft() != null)
                || (curNode.getRight() != null && curNode.getLeft() == null)) {
            //if its not the root
            if (curNode.getKey() != root.getKey()) {
                K p_key = curNode.getParent().getKey();
                if (p_key.compareTo(key) > 0) {
                    curNode.getParent().setLeft(curNode.getLeft());
                } else {
                    curNode.getParent().setRight(curNode.getLeft());
                }
                curNode.getLeft().setParent(curNode.getParent());
                curNode.setParent(null);
                curNode.setLeft(null);
            } else {
                if (curNode.getRight() != null) {
                    curNode.getRight().setParent(null);
                    root = curNode.getRight();
                } else {
                    curNode.getLeft().setParent(null);
                    root = curNode.getLeft();
                }
            }

            size--;
            return val;
        }
        // case 3: has atleast 2 nodes
        else {
            MyBSTNode<K, V> newNode = curNode.successor();
            K temp_key = newNode.getKey();
            curNode.setValue(newNode.getValue());
            remove(newNode.getKey());
            curNode.setKey(temp_key);
            // size--;
            return val;
        }

        // test
    }

    /**
    * This method adds all the nodes in the BST inorder
    * to a arraylist with order of key values, 
    * and returns that list
    * @return arraylist containing the nodes
    * 
    */
    public ArrayList<MyBSTNode<K, V>> inorder() {
        ArrayList<MyBSTNode<K, V>> tree_trav = new ArrayList<>();
        MyBSTNode<K, V> curNode = root;

        while (curNode.getLeft() != null) {
            curNode = curNode.getLeft();
        }

        for (int i = 0; i < size; i++) {
            tree_trav.add(curNode);
            curNode = curNode.successor();
        }

        // TODO
        return tree_trav;
    }
    /**
    * This class creates the Node for BST
    * and implements core methods for 
    * modify and traversing the nodes and the BST
    *
    * key=stores the key of the current node
    * value=stores the value of the current node
    * parent= stores the parent node of the current node
    * left= stores the left node of the current node
    * right= stores the right node of the current node 
    */
    static class MyBSTNode<K, V> {
        private static final String TEMPLATE = "Key: %s, Value: %s";
        private static final String NULL_STR = "null";
        //instance variables
        private K key;
        private V value;
        private MyBSTNode<K, V> parent;
        private MyBSTNode<K, V> left = null;
        private MyBSTNode<K, V> right = null;

        /**
         * Creates a MyBSTNode storing specified data
         *
         * @param key    the key the MyBSTNode will store
         * @param value  the data the MyBSTNode will store
         * @param parent the parent of this node
         */
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        /**
         * Return the key stored in the the MyBSTNode
         *
         * @return the key stored in the MyBSTNode
         */
        public K getKey() {
            return key;
        }

        /**
         * Set the key stored in the MyBSTNode
         *
         * @param newKey the key to be stored
         */
        public void setKey(K newKey) {
            this.key = newKey;
        }

        /**
         * Return data stored in the MyBSTNode
         *
         * @return the data stored in the MyBSTNode
         */
        public V getValue() {
            return value;
        }

        /**
         * Set the data stored in the MyBSTNode
         *
         * @param newValue the data to be stored
         */
        public void setValue(V newValue) {
            this.value = newValue;
        }

        /**
         * Return the parent
         *
         * @return the parent
         */
        public MyBSTNode<K, V> getParent() {
            return parent;
        }

        /**
         * Set the parent
         *
         * @param newParent the parent
         */
        public void setParent(MyBSTNode<K, V> newParent) {
            this.parent = newParent;
        }

        /**
         * Return the left child
         *
         * @return left child
         */
        public MyBSTNode<K, V> getLeft() {
            return left;
        }

        /**
         * Set the left child
         *
         * @param newLeft the new left child
         */
        public void setLeft(MyBSTNode<K, V> newLeft) {
            this.left = newLeft;
        }

        /**
         * Return the right child
         *
         * @return right child
         */
        public MyBSTNode<K, V> getRight() {
            return right;
        }

        /**
         * Set the right child
         *
         * @param newRight the new right child
         */
        public void setRight(MyBSTNode<K, V> newRight) {
            this.right = newRight;
        }
        /**
         * This method finds and returns
         *  the successor of the current Node
         *
         * @return the successor
         */
        public MyBSTNode<K, V> successor() {

            MyBSTNode<K, V> suc;
            MyBSTNode<K, V> papa;
            if ((this.parent == null) && (this.right == null)) {
                return null;
            } else if (right == null) {
                suc = this;
                papa = suc.parent;
                while (papa != null && suc == papa.right) {
                    suc = papa;
                    papa = papa.parent;
                }
                return papa;
            } else {
                suc = this.right;
                while (suc.left != null) {
                    suc = suc.left;
                }
                return suc;
            }

        }

        /**
         * This method compares if two node objects are equal.
         *
         * @param obj The target object that the currect object compares to.
         * @return Boolean value indicates if two node objects are equal
         */
        public boolean equals(Object obj) {
            if (!(obj instanceof MyBSTNode))
                return false;

            MyBSTNode<K, V> comp = (MyBSTNode<K, V>) obj;

            return ((this.getKey() == null ? comp.getKey() == null
                    : this.getKey().equals(comp.getKey()))
                    && (this.getValue() == null ? comp.getValue() == null
                            : this.getValue().equals(comp.getValue())));
        }

        /**
         * This method gives a string representation of node object.
         *
         * @return "Key:Value" that represents the node object
         */
        public String toString() {
            return String.format(
                    TEMPLATE,
                    this.getKey() == null ? NULL_STR : this.getKey(),
                    this.getValue() == null ? NULL_STR : this.getValue());
        }
    }

}
