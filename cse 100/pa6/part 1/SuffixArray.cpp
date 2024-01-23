#include <string>
#include <vector>
#include "SuffixArray.h"
using namespace std;

/**
 * Implement suffix_array() correctly
 */
vector<unsigned int> suffix_array(const string & s) {
    /* YOUR CODE HERE */
    int n=s.length();
    vector<unsigned int> sorted(n);

    for(int i=0; i<n; i++){
        sorted[i]=i;
    }
    for(int i=0; i<n-1; i++){
        for(int j=0; j<n-i-1; j++){
            if(s.substr(sorted[j])>s.substr(sorted[j+1])){
                int temp=sorted[j];
                sorted[j]=sorted[j+1];
                sorted[j+1]=temp;
            }
        }
    }


    return sorted;
}
