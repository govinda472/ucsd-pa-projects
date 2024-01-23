#include "Set.h"

/**
 * Implement the MultiwayTrieSet constructor
 */
MultiwayTrieSet::MultiwayTrieSet() {
    /* YOUR CODE HERE */
     root=new Node();
    numElements=0;
}

//helper method
void del(MultiwayTrieSet::Node* node) {
        if (node) {
            for (auto child : node->children) {
                del(child.second);
           }
            delete node;
        }
    }

/**
 * Implement the MultiwayTrieSet destructor
 */
MultiwayTrieSet::~MultiwayTrieSet() {
    del(root);
    numElements=0;
    /* YOUR CODE HERE */
}


    
/**
 * Implement the MultiwayTrieSet methods correctly
 */
unsigned int MultiwayTrieSet::size() {
    /* YOUR CODE HERE */
    return numElements;
}

void MultiwayTrieSet::insert(string s) {
    /* YOUR CODE HERE */
    Node* curr=root;
    // check if child exists that contain first character of string
    for(int i=0;i<s.length();i++){
        if(curr->children.find(s[i])==curr->children.end()){
            curr->children[s[i]]=new Node();
        }
        curr=curr->children[s[i]];
    }
    if(curr->isWord==false){
        curr->isWord=true;
        numElements++;
    }
    
}

void MultiwayTrieSet::remove(const string & s) {
    /* YOUR CODE HERE */
    Node* curr=root;
    for(int i=0;i<s.length();i++){
        if(curr->children.find(s[i])==curr->children.end()){
            return;
        }
        curr=curr->children[s[i]];
    }
    if(curr->isWord==false){
        return;
    }
    else{
    curr->isWord=false;
    numElements--;
    }
   
}

bool MultiwayTrieSet::find(const string & s) {
    /* YOUR CODE HERE */
    Node* curr=root;
    for(int i=0;i<s.length();i++){
        if(curr->children.find(s[i])==curr->children.end()){
            return false;
        }
        curr=curr->children[s[i]];
    }
    if(curr->isWord==false){
        return false;
    }
    return true;
}

