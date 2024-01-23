#include "Set.h"

/**
 * Implement the ArrayListSet methods correctly
 */
unsigned int ArrayListSet::size() {
    return arr.size();
    /* YOUR CODE HERE */
}

void ArrayListSet::insert(string s) {
    /* YOUR CODE HERE */
    // assuming no duplications allowed
  //  if(arr.size()==arr.capaicty()){
  //      arr.resize(2*capacity());
  //  }
    if (find(s) == false) {
        arr.push_back(s);
    }

}

void ArrayListSet::remove(const string & s) {
    /* YOUR CODE HERE */
    if (ArrayListSet::find(s)==true){
    int pos;
    int run=ArrayListSet::size();
    for (int i=0;i<run;i++){
            if (arr[i]==s){
                pos=i;
            }
    }
    arr.erase(arr.begin() + pos);


    }
}

bool ArrayListSet::find(const string & s) {
    int run=size();
    for (int i=0;i<run;i++){
        if (arr[i]==s){
            return true;
        }
    }
    return false;
    /* YOUR CODE HERE */
}

