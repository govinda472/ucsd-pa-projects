#include "BinaryTree.h"
//#include <iostream> 
//#include <unordered_map> 
/**
 * Implement balanceFactors() correctly
 */

// Forward declarations

void pre(BinaryTree::Node* node, std::vector<BinaryTree::Node*>& result) {
    if (node == nullptr) {
        return;
    }
    result.push_back(node);
    pre(node->leftChild, result);
    pre(node->rightChild, result);
}

std::vector<BinaryTree::Node*> getall(BinaryTree::Node* node){
    // add code finish it 
    std::vector<BinaryTree::Node*> result;
    pre(node, result);
    return result;
}
//get height
int height(BinaryTree::Node* node){
    if(node==nullptr){
        return 0;
    }
    int l_h=height(node->leftChild);
    int r_h=height(node->rightChild);
    if (r_h>l_h){
        return (r_h+1);
    }
    if (r_h<l_h){
        return (l_h+1);
    }
    else{
        return r_h+1;
        }

}



unordered_map<int,int> BinaryTree::balanceFactors() {
    /* YOUR CODE HERE */
    unordered_map<int, int> balance_map; 

    // find everynode
    //Node* alln_array[]=getall(root);
    std::vector<Node*> alln_array=getall(BinaryTree::root);
    for(Node* x:alln_array){
        Node* current=x;
        int balance=height(current->rightChild)-height(current->leftChild);
        balance_map[x->label]=balance;
    }
    return balance_map;

}
//

    // perform dfs 
// 
