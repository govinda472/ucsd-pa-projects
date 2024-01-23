#include <string.h>
#include "node.h"

node *node_lookup(node *front, char *id) {

	node *current = front;
    while (current != NULL) {
        if (strcmp(current->id, id) == 0) {  // Return pointer if found
            return current;
        }
        current = current->next;
    }
    return NULL;

}
