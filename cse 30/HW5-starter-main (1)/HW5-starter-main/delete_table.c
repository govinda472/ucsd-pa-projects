#include <stdlib.h>
#include "node.h"

void delete_table(node **htable, unsigned long tabsz) {
	unsigned long count=0;
	while (count < tabsz){
        node *current = htable[count];
        while (current != NULL) {
            node *next = current->next;
            free(current->id);
            free(current);
            current = next; 
        }
		count++;
    }
    free(htable);
}
