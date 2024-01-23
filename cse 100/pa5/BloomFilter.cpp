#include "BloomFilter.h"
#include "HashFunctions.h"

/**
 * Implement insert() correctly
 */
void BloomFilter::insert(const string & s) {
    /* YOUR CODE HERE */
    int pos;
    if(BloomFilter::find(s)==false){
        for(int i=0; i<hash_functions.size()-1; i++){
           pos = (hash_functions[i](s))%bits.size();
           bits[pos]=true;
        }
    }

}

/**
 * Implement find() correctly
 */
bool BloomFilter::find(const string & s) {
    /* YOUR CODE HERE */
     int pos;
     for(int i=0; i<hash_functions.size()-1; i++){
         pos = (hash_functions[i](s))%bits.size();
           if(bits[pos]==false){
            return false;
           }
        }
     return true;

}
