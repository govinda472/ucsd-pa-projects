#include <unordered_map>
#include <bitset>
#include <string>
#include <queue>
#include <algorithm>
#include "Helper.hpp"
#include "HCTree.hpp"





void deleteTree(HCNode* root) {
    if (root == nullptr) {
        return;
    }
    deleteTree(root->c0); 
    deleteTree(root->c1); 
    delete root; 
}


//later part needd to figured out
    // Open the output file for writing

    // Write enough information (a "file header") to the output file to enable the coding tree to be reconstructed when the file is read by your uncompress program
        //figure out how to write the huffman on the output file

    // Move back to the beginning of the input file to be able to read it, again

    // Using the Huffman coding tree, translate each byte from the input file into its code, and append these codes as a sequence of bits to the output file, after the header

    // Close the input and output files (note that this is handled for you; see Design Notes)


int main(int argc, char* argv[]) {

    char* outputFile = argv[2];
    char* inputFile = argv[1];
    // Open the input file for reading
    FancyInputStream open(inputFile);
    // 1 Parse the command line arguments and throw an error message if the user runs your program incorrectly
    if( !open.good()) {
        cout << "Error: Incorrect usage!" << endl;
        return -1;
    }

    // 2 Read bytes from the file. Count the number of occurrences of each byte value
            int s;
            s= open.filesize();
            if(s==0) {
                FancyOutputStream writeF(outputFile);
                return 0;
            }
            // Use a hash table to count the number of occurrences of each byte value
            unordered_map<unsigned char, int> byte_count;
            for(int i = 0; i < s; i++) {
                unsigned char curr=open.read_byte();
                if(curr == -1) {
                    return 0;
                }
                if(byte_count.find(curr) == byte_count.end()) {
                    byte_count[curr] = 1;
                }
                else {  
                    byte_count[curr]++;
                }
            }
            priority_queue<HCNode*, vector<HCNode*>, HCNodePtrComp> pq;
            for (auto p = byte_count.begin();  p != byte_count.end(); p++) {
                HCNode* curr = new HCNode(p->second, p->first); 
                pq.push(curr);
            }

            // take edge case into consideration where there are only 0 or 1 unique characters
            unordered_map<unsigned char, HCNode*> char_locations;

            int forest_size=pq.size();
                while(forest_size > 1) {
                
                    // Create a new node
                    HCNode* node= new HCNode(0, NULL);

                    HCNode* left_child;
                    if (!pq.empty()){
                    left_child = pq.top();
                    left_child->p = node;
                    
                    if(left_child->symbol != NULL){
                        char_locations[left_child->symbol] = left_child;
                    }
                    pq.pop();
                    }

                    HCNode* right_child;
                     if (!pq.empty()){

                    right_child=pq.top();
                    right_child->p = node;

                    if(right_child->symbol != NULL){
                        char_locations[right_child->symbol] = right_child;
                    }

                    pq.pop();
                     }

                    node->c0 = left_child;
                    node->c1 = right_child;
                    node->count = node->c0->count + node->c1->count;

                    pq.push(node);
                    forest_size--;
                }
       
        // Open the output file for writing;
        FancyOutputStream writeF(outputFile);

        // Write enough information (a "file header") to the output file to enable the coding tree to be reconstructed when the file is read by your uncompress program
        writeF.write_int(byte_count.size());
        for (auto p = byte_count.begin();  p != byte_count.end(); p++) {
                writeF.write_byte(p->first);
                writeF.write_int(p->second);
            }
            
    // Move back to the beginning of the input file to be able to read it, again
    open.reset();

    // Using the Huffman coding tree, translate each byte from the input file into its code, and append these codes as a sequence of bits to the output file, after the header
    int num_symbols=char_locations.size();
    unordered_map<unsigned char, string> char_treevalues;

     for (auto p = char_locations.begin();  p != char_locations.end(); p++) {
                HCNode* curr1=p->second;
                string str;
                while(curr1->p != nullptr) {

                    char_treevalues[curr1->symbol] = curr1->p->count;
                    if(curr1->p->c0 == curr1) {
                        str.append("0");
                    }
                    else {
                        str.append("1");
                    }
                   
                    curr1 = curr1->p;
                }
                reverse(str.begin(), str.end());
                char_treevalues[p->first] = str;
            }


    for(int i = 0; i < s; i++) {

                unsigned char curr=open.read_byte();
                if(curr == -1) {
                    return 0;
                }
                for(int j = 0; j < char_treevalues[curr].size(); j++) {
                   char bit=char_treevalues[curr][j];
                    if(bit == '0') {
                        writeF.write_bit(0);
                    }
                    else {
                        writeF.write_bit(1);
                    }
                }
            }

    if (pq.top() != nullptr) {
        deleteTree(pq.top());
    }


    // Close the input and output files (note that this is handled for you; see Design Notes)
 

}







