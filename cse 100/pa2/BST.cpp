#include "BST.h"

/**
 * Implement the BST constructor
 */
BST::BST() {
    /* YOUR CODE HERE */
                root=nullptr;
                numElements=0;
}

/**
 * Implement the BST destructor
 */
BST::~BST() {
    /* YOUR CODE HERE */
    clear();
}

/**
 * Implement size() correctly
 */
unsigned int BST::size() const {
    return numElements;
    /* YOUR CODE HERE */
}

/**
 * Implement clear() correctly without memory leaks
 */
void BST::clear() {
    /* YOUR CODE HERE */
    if(numElements>0){  
    Node* temp;
    temp=root;

    while(numElements>0){
        if (temp->rightChild==nullptr && temp->leftChild==nullptr){
            Node* current=temp;
            if(temp->parent!=nullptr){
                temp=temp->parent;
                }

            if (temp->leftChild==current){
                temp->leftChild=nullptr;
            }  
            if (temp->rightChild==current){
                 temp->rightChild=nullptr;
            }  
            
            delete current;
            numElements--;
            //apply the post order traversal
            //use recusion 
        }

       if(numElements!=0) {
        if(temp->leftChild!=nullptr){
            temp=temp->leftChild;
        }
        else if(temp->rightChild!=nullptr){
            temp=temp->rightChild;
        }
        }
    }
    }


}

/**
 * Implement insert() correctly
 */
bool BST::insert(int element) {
    /* YOUR CODE HERE */
    if(find(element)){
        return false;
    }
    Node* newnode=new Node(element);
    if (numElements==0) {
        root = newnode;
        numElements++;
        return true;
    }
    else{

    Node* temp=root;
    while (temp!=nullptr){
        if (temp->data<element){
             if (temp->rightChild==nullptr){ 
                    temp->rightChild=newnode;
                    newnode->parent=temp;
                    numElements++;
                    return true;
                }
                else{
                     temp=temp->rightChild;
                }
        }
        else{
            if (temp->leftChild==nullptr){ 
                    temp->leftChild=newnode;
                    newnode->parent=temp;
                    numElements++;
                    return true;
            }
            else{
                temp=temp->leftChild;
            }
        }
    }
    }
}

/**
 * Implement find() correctly
 */
bool BST::find(const int & query) const {

    if(root==nullptr || numElements==0){
        return false;
    }


    Node* current;
    current = root;
    while(current->data!=query){
        if (current->data>query){
            if(current->leftChild!=nullptr){
                current=current->leftChild;
            }
            else{
                return false;
            }
        }
        else if (current->data<query){
            if(current->rightChild!=nullptr){
                current=current->rightChild;
            }
            else{
                return false;
            }
        }
    }
    
    return true;

}


/**
 * Implement the getLeftMostNode() function correctly
 */
BST::Node* BST::getLeftMostNode() {

    /* YOUR CODE HERE */
    if(numElements==0){
        return nullptr;
    }

    if (root->leftChild==nullptr){
        return nullptr;
    }
    else{
        Node* temp;
        temp=root;
        while(temp->leftChild->leftChild!=nullptr){
            temp=temp->leftChild;
        }
        temp=temp->leftChild;
        return temp;
    }
    //make it more simple
}

/**
 * Implement the BST::Node successor function correctly
 */
BST::Node* BST::Node::successor() {
    /* YOUR CODE HERE */
    // has a right child then the successor is the leftmost node in the right subtree
    if(rightChild!=nullptr){
        Node* temp;
        temp=rightChild;
        while(temp->leftChild!=nullptr){
            temp=temp->leftChild;
        }
        return temp;
    }
    // if no right child then the successor is the lowest ancestor of the node whose left child is also an ancestor of the node
    else{
        Node* temp;
        temp=this;
        while(temp->parent!=nullptr){
            if(temp->parent->leftChild==temp){
                return temp->parent;
            }
            else{
                temp=temp->parent;
            }
        }
        return nullptr;
    }


    return nullptr;
}

