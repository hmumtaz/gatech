;;===============================
;;Name:
;;===============================

.orig x3000
MAIN
LD R6, STACK ;initialize stack
;;==================================================
;;Call HANOI subroutine with correct arguments here
;;==================================================
	LD R0, N 		;	\ STORE INIT VARS ON STACK
	ADD R6, R6, -1	;	|
	STR R0, R6, 0	;	|
	LEA R0, B		;	| AUX
	ADD R6, R6, -1	;	|
	STR R0, R6, 0	;	|
	LEA R0, C		;	| END
	ADD R6, R6, -1	;	|
	STR R0, R6, 0	;	|
	LEA R0, A		;	| START
	ADD R6, R6, -1	;	|
	STR R0, R6, 0	;	/
	JSR HANOI		;	| CALL HANOI

	LDR R0, R6, 0 	;	| POP VALUE
	ST R0, ANSWER 	;	| STORE ANSWER
	ADD R6, R6, 4	;	| RESTORE SP
	HALT 



ARROW 	.stringz "->"
A 		.stringz "A"
B 		.stringz "B"
C 		.stringz "C"
NL 		.stringz "\n"
N 		.fill 2 	; number of disks
ANSWER 	.fill 0 	; save your answer here
STACK 	.fill xF000



HANOI
;;=====================
;;Your subroutine here
;;=====================
	ADD R6, R6, -9	;	\ STORE RV, RA, OLD FP, NEW FP, TEMPS, AND REGISTERS
	STR R7, R6, 7	;	|
	STR R5, R6, 6	;	|
	ADD R5, R6, 5	;	| ALLLOCATE SPACE FOR TEMP HERE
	STR R4, R6, 4	;	|
	STR R3, R6, 3	;	|
	STR R2, R6, 2	;	|
	STR R1, R6, 1	;	|
	STR R0, R6, 0	;	/

	AND R1, R1, 0	;	\ R1 = MOVES, STORE IN TEMP
	STR R1, R5, 0	;	/

	LDR R0, R5, 7	;	| R0 = N
	ADD R0, R0, -1	;	\ CHECK IF N IS < OR <= 1
	BRN BASE0		;	|
	BRZ BASE1		;	/

	ADD R6, R6, -4	;	\ ADD RECURSIVE VALUES TO STACK
	STR R0, R6, 3	;	|
	LDR R0, R5, 5	;	| END
	STR R0, R6, 2	;	|
	LDR R0, R5, 6	;	| AUX
	STR R0, R6, 1	;	|
	LDR R0, R5, 4	;	| START
	STR R0, R6, 0	;	/
	JSR HANOI		;	| LAUNCH RECURSIVE SUBROUTINE

	LDR R2, R6, 0	;	| STORE R2 = SUBROUTINE RV
	LDR R1, R5, 0	;	| RESTORE R1 = MOVES
	ADD R1, R1, R2	;	| MOVES = MOVES + HANOI(START,AUX, END, N-1)
	STR R1, R5, 0	;	| STORE MOVES


	LDR R2, R5, 4	;	\ CONSTRUCT PRINTF STATEMENT (START)
	LDR R2, R2, 0	;	|
	STR R2, R0, 0	;	|
	LEA R3, ARROW	;	| 2 SPACES FOR ARROW
	LDR R2, R3, 0	;	|
	STR R2, R0, 1	;	|
	LDR R2, R3, 1	;	|
	STR R2, R0, 2	;	|
	LDR R2, R5, 5 	;	| END 
	LDR R2, R2, 0	;	|
	STR R2, R0, 3	;	|
	LEA R3, NL		;	| 2 SPACES FOR NL
	LDR R2, R3, 0	;	|
	STR R2, R0, 4	;	|
	LDR R2, R3, 1	;	|
	STR R2, R0, 5	;	|
	PUTS			;	PRINTF("%C->%C\N",START,END)

	LDR R1, R5, 0	;	\RESTORE, INCREMENT, AND STORE MOVES
	ADD R1, R1, 1	;	|
	STR R1, R5, 0	;	/

	ADD R6, R6, 1	;	\ ADD RECURSIVE VALUES TO STACK
	LDR R0, R5, 3	;	|
	ADD R0, R0, -1	;	|
	STR R0, R6, 0	;	|
	LDR R0, R5, 4	;	| START
	STR R0, R6, 2	;	|
	LDR R0, R5, 5	;	| END
	STR R0, R6, 1	;	|
	LDR R0, R5, 6	;	| AUX
	STR R0, R6, 0	;	/
	JSR HANOI		;	| LAUNCH RECURSIVE SUBROUTINE

	LDR R2, R6, 0	;	| STORE R2 = SUBROUTINE RV
	LDR R1, R5, 0	;	| RESTORE R1 = MOVES
	ADD R1, R1, R2	;	| MOVES = MOVES + HANOI(AUX, END, START,N-1)
	STR R1, R5, 0	;	| STORE MOVES

	LDR R2, R5, 4	;	\ CONSTRUCT PRINTF STATEMENT (START)
	LDR R2, R2, 0	;	|
	STR R2, R0, 0	;	|
	LEA R3, ARROW	;	| 2 SPACES FOR ARROW
	LDR R2, R3, 0	;	|
	STR R2, R0, 1	;	|
	LDR R2, R3, 1	;	|
	STR R2, R0, 2	;	|
	LDR R2, R5, 5 	;	| END
	LDR R2, R2, 0	;	|
	STR R2, R0, 3	;	|
	LEA R3, NL		;	| 2 SPACES FOR NL
	LDR R2, R3, 0	;	|
	STR R2, R0, 4	;	|
	LDR R2, R3, 1	;	|
	STR R2, R0, 5	;	|
	PUTS			;	PRINTF("%C->%C\N",START,END)

	LDR R1, R5, 0	;	\ STORE MOVES IN RV
	STR R1, R5, 3	;	/

	ADD R6, R6, 5	;	| MOVE R6 TO TOP OF STACK
	LDR R0, R6, 0	;	\ RESTORE REGISTERS
	LDR R1, R6, 1	;	|
	LDR R2, R6, 2	;	|
	LDR R3, R6, 3	;	|
	LDR R4, R6, 4	;	/

	LDR R7, R5, 2	;	| RESTORE RA

	ADD R6, R5, 1	;	| SP -> OLD FP
	LDR R5, R6, 0	;	| RESTORE OLD FP
	ADD R6, R6, 2	;	| SP -> RV
	RET

BASE0
	AND R0, R0, 0	;	| SET RV TO 0
	STR R0, R5, 3	;	| STORE RV
	LDR R0, R6, 0	;	\ RESTORE REGISTERS
	LDR R1, R6, 1	;	|
	LDR R2, R6, 2	;	|
	LDR R3, R6, 3	;	|
	LDR R4, R6, 4	;	/
	LDR R7, R5, 2	;	| RESTORE RA
	ADD R6, R5, 1	;	| SP -> OLD FP
	LDR R5, R6, 0	;	| RESTORE OLD FP
	ADD R6, R6, 2	;	| SP -> RV
	RET

BASE1
	LDR R2, R5, 4	;	\ CONSTRUCT PRINTF STATEMENT (START)
	LDR R2, R2, 0	;	|
	STR R2, R0, 0	;	|
	LEA R3, ARROW	;	| 2 SPACES FOR ARROW
	LDR R2, R3, 0	;	|
	STR R2, R0, 1	;	|
	LDR R2, R3, 1	;	|
	STR R2, R0, 2	;	|
	LDR R2, R5, 5 	;	| END
	LDR R2, R2, 0	;	|
	STR R2, R0, 3	;	|
	LEA R3, NL		;	| 2 SPACES FOR NL
	LDR R2, R3, 0	;	|
	STR R2, R0, 4	;	|
	LDR R2, R3, 1	;	|
	STR R2, R0, 5	;	|
	PUTS			;	PRINTF("%C->%C\N",START,END)

	AND R0, R0, 0	;	\ SET RV TO 1
	ADD R0, R0, 1	;	/
	STR R0, R5, 3	;	| STORE RV
	LDR R0, R6, 0	;	\ RESTORE REGISTERS
	LDR R1, R6, 1	;	|
	LDR R2, R6, 2	;	|
	LDR R3, R6, 3	;	|
	LDR R4, R6, 4	;	/

	LDR R7, R5, 2	;	| RESTORE RA

	ADD R6, R5, 1	;	| SP -> OLD FP
	LDR R5, R6, 0	;	| RESTORE OLD FP
	ADD R6, R6, 2	;	| SP -> RV
	RET
.end
