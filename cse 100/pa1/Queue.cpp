#include "Queue.h"

/**
 * Implement Queue constructor
 */
Queue::Queue() {
    
    /* YOUR CODE HERE */
    head= nullptr;
    tail= nullptr;
    numElements=0;
}

/**
 * Implement the Queue destructor
 */
Queue::~Queue() {
    /* YOUR CODE HERE */
    clear();
}

/**
 * Implement size() correctly
 */
unsigned int Queue::size() {
    /* YOUR CODE HERE */
    return numElements;
}

/**
 * Implement clear() correctly without memory leaks
 */
void Queue::clear() {
    /* YOUR CODE HERE */
    while (numElements!=0){
        Node* current=head;
        head=head->next;
        delete current;
        numElements--;
    }
    head= nullptr;
    tail = nullptr;
}

/**
 * Implement push() correctly
 */
void Queue::push(string s) {
    /* YOUR CODE HERE */
    Node* new_node=new Node(s);
    if (numElements==0){
        head=new_node;
        tail=new_node;
        numElements++;
    } else {
        tail->next=new_node;
        tail=new_node;
        numElements++;
    }

}

/**
 * Implement pop() correctly without memory leaks
 */
string Queue::pop() {
    /* YOUR CODE HERE */
    string temp= head->data;
    if (numElements==1){
        Node* current;
        current=head;
        head= nullptr;
        tail= nullptr;
        delete current;
        numElements--;
    }
    else {
        Node* current;
        current=head;
        head=head->next;
        delete current;
        numElements--;
    }
    return temp;
}
