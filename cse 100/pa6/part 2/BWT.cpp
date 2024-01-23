#include <string>
#include <vector>
#include "BWT.h"
using namespace std;

/**
 * Implement bwt() correctly
 */
string bwt(const string & s) {
    //add the code here
    int n = s.length();
    vector<string> table(n);

    // Step 1: Form all rotations of the input string.
    for (int i = 0; i < n; ++i) {
        table[i] = s.substr(i) + s.substr(0, i);
    }

    // Step 2: Sort the rotations lexicographically.
    for(int i=0; i<n-1; i++){
        for(int j=0; j<n-i-1; j++){
           if(table[j]>table[j+1]){
                string temp = table[j];
                table[j] = table[j+1];
                table[j+1] = temp;
           }
        }
     }
    string end;
    for(int i=0; i<n; i++){
        end+=table[i][n-1];
    }
    return end;


}
