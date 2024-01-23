#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include "node.h"

#define MIN_TAB_SZ 3
#define DEFAULT_TAB_SZ 1873
#define QUERY_SUCCESS_FORMAT "found tree id=%s, x=%.5f, y=%.5f\n" 
#define QUERY_FAILURE_FORMAT "could not find tree with id=%s\n"

int main(int argc, char **argv) {

	int s_flag;
	unsigned long tabsz;
	tabsz=DEFAULT_TAB_SZ;
	s_flag=0;
	// TODO: parse opts with getopt and initialize these variables 
	// (see strtoul for parsing unsigned long)
	int opt;
	while ((opt = getopt(argc, argv, "st:")) != -1) {
    	if (opt =='t') {
			tabsz = strtoul(optarg, NULL, 10);
			if(MIN_TAB_SZ>tabsz){
				tabsz=DEFAULT_TAB_SZ;
			}

    	} else if (opt =='s') {
        	s_flag = 1;

    	} else {
        	fprintf(stderr, "Usage: %s [-s] [-t table_size] csv_file\n", argv[0]);
        	return EXIT_FAILURE;
    	}
	}
	
	char *filename = argv[optind];


	// using calloc so all buckets are initialized to NULL
	node **htable = calloc(tabsz, sizeof(node *));
	if (htable == NULL) {
		return EXIT_FAILURE; 
	}

	if (load_table(htable, tabsz, filename) == -1) {
		fprintf(stderr, "error in load_table\n");
		free(htable);
		return EXIT_FAILURE;
	}
	
	size_t bufsz = 0;
	char *buf = NULL; 
	ssize_t bytes;
	int success_q = 0;
	while ((bytes = getline(&buf, &bufsz, stdin)) > 0) {
		// replace the \n, if it exists (for hashing)
		if (buf[bytes - 1] == '\n') {
					buf[bytes - 1] = '\0'; 
				}

		node *result = node_lookup(htable[hash(buf) % tabsz], buf);

		if (result == NULL) {
			printf(QUERY_FAILURE_FORMAT, buf);
    	} else {
			printf(QUERY_SUCCESS_FORMAT, result->id, result->xcoord, result->ycoord);
        	success_q++;
    	}
	}

	printf("%d successful queries\n", success_q);
	if (s_flag==1) {
        print_info(htable, tabsz);
	}

	delete_table(htable, tabsz);
	free(buf);
	return EXIT_SUCCESS;
}



