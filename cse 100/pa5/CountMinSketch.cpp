#include <limits>
#include "CountMinSketch.h"
#include "HashFunctions.h"

#include <iostream>
#include <limits.h>
/**
 * Implement increment() correctly
 */
void CountMinSketch::increment(const string & s) {
    /* YOUR CODE HERE */
    int pos;
    //if(CountMinSketch::find(s)<1){

    for(int i=0; i<K-1; i++){
        pos = (hash_functions[i](s))%M;
        count[i][pos]=count[i][pos]+1;
    }
    
}

/**
 * Implement find() correctly
 */
unsigned int CountMinSketch::find(const string & s) {
    /* YOUR CODE HERE */
    int min = INT_MAX;
    int pos;
    for(int i=0; i<K-1; i++){
        pos = (hash_functions[i](s))%M;
        if(count[i][pos]<min){
             min=count[i][pos];
        }
    }
    return min;
}
