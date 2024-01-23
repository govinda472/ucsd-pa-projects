        .arch armv7
        .cpu cortex-a53
        .equ 	NUL, 0

        .global asm_doRow
	.syntax unified	

	// Set offsets for incoming parameters 5 and 6
	.equ	IARG6, 8	// new life parameter
	.equ    IARG5, 4	// number columns parameter	

        // !!! SET FP_OFFSET TO THE NUMBER OF (SAVED REGISTERS -1 * 4)	
        .equ 	FP_OFFSET, 32	// (# of saved regs - 1) * 4

	// Add any local variables used
	.equ	IARG3, -36	// size_t nRows,   // r3
	.equ    IARG4, -40   // size_t curRow,  // r2
	.equ	IARG2, -44	// belem *src,     // r1
	.equ    IARG1, -48    // belem *dest,    // r0




	// asm_doRow(belem *dest,    // r0
	//           belem *src,     // r1
	//           size_t curRow,  // r2
	//           size_t nRows,   // r3
	//           size_t nCols,   // fp + 4 r4
	//	     int *newLife)   // fp + 8 r5


asm_doRow:	
	push {r4-r10, fp, lr} 	//TODO: Push any preserved registers
	add fp, sp, #FP_OFFSET
	sub sp, sp, #8//TODO
	// Write body of asm_doRow here

	ldr r4,[fp, IARG5]
	ldr r5,[fp, IARG6]

	str r0,[fp, IARG1]
	str r1,[fp, IARG2]

	mov r0, r3
	mov r1, r4
	BL setNRowsNCols

	ldr r0,[fp, IARG1]
	ldr r1,[fp, IARG2]

	




//for (size_t col = 0; col < cols; ++col)

//r7 stores first loop
mov r7, #0 // col = 0
.LLoop_1:
    cmp r7, r4     //col< cols;
    bge .Lfunc_end
	
	//loop1
	mov r6, #0  // size_t count = 0; count be r6
    mov r8, #0//   i be r8 
	// j be r9 
	//loop2
	.LLoop_2:
		cmp r8, #3   /// for (i = -1; i <= 1; ++i)
		bge .Ldetermine
		//loop 3
		mov r9, #0

		.Lloop_3:
		cmp r9, #3   /// for (j = -1; i <= 1; ++i)
		bge .LLoop2_end

		// inside loop 3
	    	// if (i != 0 || j != 0) {
				cmp r8, #1
				bne .Lnot_core
				cmp r9, #1
				bne .Lnot_core
				B .LLoop3_end

				.Lnot_core:	 
				// row-1 col-1
				
				// r2+r8-1 
				add r0, r2, r8
				sub r0, r0, #1  

				// r9+r7-1
				add r1, r7, r9   
				sub r1, r1, #1

				BL nGetIndexRC
				mov r10, r0

				
				ldr r1,[fp, IARG2]
				Ldr r0, [r1, r10]

				cmp r0, #1
				beq .Lincrement
				ldr r0,[fp, IARG1]
				B .Ldone

				.Lincrement:
				ldr r0,[fp, IARG1]
				add r6, r6, #1
				.Ldone:

			


		// inside loop 3

		.LLoop3_end:
		ADD r9, r9, #1
		B .Lloop_3
		//loop3
	.LLoop2_end:
	ADD r8, r8, #1
	B .LLoop_2
	//loop2
	//loop1
.LLoop1_end:
ADD r7, r7, #1
B .LLoop_1
// logical error exits fix it





.Ldetermine:

//r7, current col
//r2 row 
mov r0, r2
mov r1, r7
BL nGetIndexRC
Mov r10, r0

ldr r0,[fp, IARG1]
ldr r1,[fp, IARG2]






ldr r9, [r1, r10]

// check condition for determining life
    //register 1= src[index]  //r9
	//register 2= count // r6

	cmp r9, #1
	bne .Linitial_dead
	cmp r6, #2    
    	blt .Lstay_dead 
    	cmp r6, #3    
	bgt .Lstay_dead
	B .Lalive	

.Linitial_dead:
     cmp r6,#3
	  bne .Lstay_dead
	  add r5, r5, #1
	  B .Lalive

.Lstay_dead:
		// add code 
		//dest[index] = 0;
		mov r9, #0
		str r9, [r10,r0]
		
B .Lfinish
.Lalive:
	//dest[index] = 1;
	mov r9, #1
	str r9, [r10,r0]

.Lfinish:
B .LLoop1_end
	

.Lfunc_end:

	sub sp, fp, #FP_OFFSET
	pop {r4-r10, fp, lr} //TODO: Pop preserved registers
	bx lr
	






