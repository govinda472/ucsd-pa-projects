#include "Set.h"

/**
 * Implement the RedBlackTreeSet methods correctly
 */
unsigned int RedBlackTreeSet::size() {
    /* YOUR CODE HERE */
    return rbt.size();
}

void RedBlackTreeSet::insert(string s) {
    rbt.insert(s);
    /* YOUR CODE HERE */
}

void RedBlackTreeSet::remove(const string & s) {
    /* YOUR CODE HERE */
   rbt.erase(s);
}

bool RedBlackTreeSet::find(const string & s) {
    /* YOUR CODE HERE */
    if(rbt.find(s)==rbt.end()){
        return false;
    }
    else{
        return true;
    }
}
