#include <string.h>
#include "node.h"
//add code
node *node_lookup(node *front, char *id) {
	node *current;
	current = front;
	 while (current != NULL) {
        if (strcmp(current->id, id) == 0) {
            return current;
        }
		else{
			current= current->next;
		}
	 }
	return NULL; 
}
