#include <iostream>
#include <fstream>
#include <unordered_map>
#include "Helper.hpp"
#include <queue>
#include "HCTree.hpp"
// Define your HuffmanNode structure here

// Define your HuffmanTree class here

// Define your uncompress function here

void deleteTree(HCNode* root) {
    if (root == nullptr) {
        return;
    }
    deleteTree(root->c0); 
    deleteTree(root->c1); 
    delete root; 
}


int main(int argc, char* argv[]) {
    char* outputFile = argv[2];
    char* inputFile = argv[1];
    //Open the input file for reading
    FancyInputStream open(inputFile);
     int s_i;
     s_i= open.filesize();
            if(s_i==0) {
                FancyOutputStream writeF(outputFile);
                return 0;
            }

    //Read the file header at the beginning of the input file, and use it to reconstruct the Huffman coding tree
    //first 4 bytes represents the header size
    int s=open.read_int();
    int run;
    run= (open.filesize()-(s*5+4))*8;
    priority_queue<HCNode*, vector<HCNode*>, HCNodePtrComp> pq;
    for(int i=0; i<s; i++) {
        //insert the byte and its frequency into the hash table
        unsigned char symbol = open.read_byte();
        int count = open.read_int();
        HCNode* curr = new HCNode(count, symbol); 
        pq.push(curr);
    }

        while(pq.size() > 1) {
                    HCNode* node= new HCNode(0, NULL);

                    HCNode* left_child;
                    if (!pq.empty()){
                    left_child = pq.top();
                    left_child->p = node;
                    pq.pop();
                    }

                    HCNode* right_child;
                     if (!pq.empty()){
                    right_child=pq.top();
                    right_child->p = node;
                    pq.pop();
                    }

                    node->c0 = left_child;
                    node->c1 = right_child;
                    node->count = node->c0->count + node->c1->count;
                    pq.push(node);

                }


    HCNode* start_point= pq.top();

    //Open the output file for writing
    FancyOutputStream writeF(outputFile);
    //Using the Huffman coding tree, decode the bits from the input file into the appropriate sequence of bytes, writing them to the output file
    for(int i=0; i<run; i++) {
        unsigned char c; 
        //c=start_point->decode(open);
        if(open.read_bit()==0) {
            start_point=start_point->c0;
        }
        else {
            start_point=start_point->c1;
        }
        if(start_point->c0==NULL && start_point->c1==NULL) {
            c=start_point->symbol;
            writeF.write_byte(c);
            start_point=pq.top();
        }
        
    }

        if (pq.top() != nullptr) {
        deleteTree(pq.top());
    }
    //Close the input and output files (note that this is handled for you; see Design Notes)
}
