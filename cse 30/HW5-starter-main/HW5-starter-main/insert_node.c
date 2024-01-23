#include <stdlib.h>
#include <string.h>
#include "node.h"
node *insert_node(node *front, char* id, double x, double y) {
	
	node *new_node;
	new_node = (node*) malloc(sizeof(node));
	if (new_node == NULL) {
        return NULL; 
    }

	// TODO: create a node, copy in the parameter fields
	new_node->id = (char*) malloc(strlen(id) + 1);
    if (new_node->id == NULL) {
        free(new_node); 
        return NULL; 
    }

    strcpy(new_node->id, id);
	new_node->x = x;
    new_node->y = y;
	new_node->next = front;
	return new_node;
}


