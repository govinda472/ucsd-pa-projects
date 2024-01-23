#include "Set.h"
//just needs to be the same functionality as 
/**
 * Implement the LinkedListSet methods correctly
 */
unsigned int LinkedListSet::size() {
    /* YOUR CODE HERE */
    return linked.size();
}

void LinkedListSet::insert(string s) {
    /* YOUR CODE HERE */
    

    for (string item : linked)
    {
    if(s==item){
        return;
    } }
    linked.push_back(s);
    
}

void LinkedListSet::remove(const string & s) {
    /* YOUR CODE HERE */
            linked.remove(s);
    
    
}

bool LinkedListSet::find(const string & s) {
    /* YOUR CODE HERE */

    for (string item : linked)
    {
    if(s==item){
        return true;
    } }
return false;

}
