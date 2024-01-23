#include <stdlib.h>
#include <string.h>
#include "node.h"

node *insert_node(node *front, char* id, double x, double y) {
	node *new_node = (node *) malloc(sizeof(node));
	if (new_node == NULL) {
        return NULL; 
    }
	new_node->id = (char *) malloc(strlen(id) + 1);

	if (new_node->id == NULL) {
        free(new_node); 
        return NULL; 
    }
	strcpy(new_node->id, id); // deep copy
	//link the new node into the chain 
	new_node->xcoord = x;
    new_node->ycoord = y;
	new_node->next = front;
	return new_node; // return the new head of the chain if the function succeeded
}


