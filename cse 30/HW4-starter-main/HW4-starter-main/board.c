/**
 * Assignment: life
 * Name :govinda sahoo
 * PID: A16378764
 * Class: UCSD CSE30-SP21
 *
 */
#include "cse30life.h"
#include "board.h"


/**
 * create a new board
 *
 * - malloc a boards structure
 * - set the generation to 0
 * - open the file (if it doesn't exist, return a NULL pointer
 * - read the first line which is the number of rows
 * - read the second line which is the number of cols
 * - set the # of rows and # of cols in the boards structure
 * - malloc bufferA and bufferB 
 * - Set currentBuffer and nextBuffer
 * - clear both board buffers
 * - read the file until done.  each row contains a row and a columns separted by
 *   white space
 *     for each line, set the cell in the current buffer
 * - close the file
 * - return the boards pointer if successfull or NULL ptr otherwise
 */
boards_t * createBoard(char *initFileName){
    //this code

  FILE *file;
  file=fopen(initFileName, "r");
  
  if (!file) {
    return NULL;
  }

  //allocate space
  boards_t *board = malloc(sizeof(boards_t));
  if (!board) {
    fclose(file);
    return NULL;
  }

   //read from the file
    fscanf(file, "%zu", &(board->numRows));
    fscanf(file, "%zu", &(board->numCols));
    //allocate space
    board->bufferA = malloc(sizeof(belem) * board->numRows * board->numCols);
    board->bufferB = malloc(sizeof(belem) * board->numRows * board->numCols);
    board->currentBuffer = board->bufferA;
    board->nextBuffer = board->bufferB;
    clearBoards(board); // clears the board space
      
    size_t row, col;
    while (fscanf(file, "%zu %zu", &row, &col) == 2) {
      board->currentBuffer[getIndex(board->numCols, row, col)] = 1;
    }
    fclose(file);
    return board;

}



/**
 * delete a board
 */
void deleteBoard(boards_t **bptrPtr){
   //resets memory
  free((*bptrPtr)->bufferA);
  free((*bptrPtr)->bufferB);
  free(*bptrPtr);
  *bptrPtr = NULL;

}

/**
 * set all the belems in both buffers to 0
 */
void clearBoards(boards_t *self){
  //resets memory
  memset(self->bufferA, 0, sizeof(belem) * self->numRows * self->numCols);
  memset(self->bufferB, 0, sizeof(belem) * self->numRows * self->numCols);

}

/**
 * swap the current and next buffers
 */
void swapBuffers(boards_t *self){
  belem *temp;
  temp = self->currentBuffer;
  self->currentBuffer = self->nextBuffer;
  self->nextBuffer = temp;

}


/**
 * get a cell index
 */
size_t getIndex(size_t numCols, size_t row, size_t col){
  int index;
  index= row * numCols + col;
  return index;
}
  
