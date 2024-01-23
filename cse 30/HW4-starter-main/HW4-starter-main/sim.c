/**
 * Assignment: life
 * Name: govinda sahoo
 * PID: A16378764
 * Class: UCSD CSE30-SP21
 *
 */
#include "sim.h"

#define CIMP
extern void asm_doRow(belem *, belem *, size_t, size_t, size_t);

/**
 * gets x mod N (works for negative numbers as well! Use this instead of %)
 */
size_t getModVal(int x, size_t N){
     size_t adj = x/N;

     return((x+adj*N)%N);
}

/**
 * process one row of the board
 */
static void doRow(belem *dest, belem *src, size_t row, size_t rows, size_t cols){
  // goes through the row
  for (size_t col = 0; col < cols; ++col) {
        //finds out how many alive nieghbors there are
        size_t count = 0;
        int i, j; 
        for (i = -1; i <= 1; ++i) {
            for (j = -1; j <= 1; ++j) {
                if (i != 0 || j != 0) {
                    size_t neighbor_row = getModVal(row + i, rows);
                    size_t neighbor_col = getModVal(col + j, cols);
                    count += src[getIndex(cols, neighbor_row, neighbor_col)];
                }
            }
        }
        //detemine for a cell to stay alive or die based upon the rules
        size_t index = getIndex(cols, row, col);
        if (src[index] == 1) {
            if (count >= 2 && count <= 3 ) {
                dest[index] = 1;
            }  else {
                dest[index] = 0;
            }
        } else {
            if (count == 3) {
                dest[index] = 1;
            } else {
                dest[index] = 0;
            }
        }
    }
}


/**
 * perform a simulation for "steps" generations
 *
 * for steps
 *   calculate the next board
 *   swap current and next
 */
void simLoop(boards_t *self, unsigned int steps){
  // this code
  unsigned int step;
   for (step = 0; step < steps; ++step) {
        size_t row;
        for (row = 0; row < self->numRows; ++row) {
            doRow(self->nextBuffer, self->currentBuffer, row, self->numRows, self->numCols);
        }

        swapBuffers(self);
    }
}