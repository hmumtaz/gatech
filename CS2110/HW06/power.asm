;;===============================
;;Name: HUSSAIN MUMTAZ
;;===============================

.orig x3000
;;===============================
;;Do not change anything in MAIN
;;===============================
MAIN
LD 	R6, STACK 	; initialize stack
LD 	R0, N 		; load N
ADD R6, R6, -1 	; move the stack pointer
STR R0, R6, 0 	; push N into the stack
LD 	R0, X 		; load x
ADD R6, R6, -1 	; move the stack pointer
STR R0, R6, 0 	; push X into the stack
JSR POWER
LDR R0, R6, 0 	; pop from the stack
ADD R6, R6, 3 	; move the stack pointer
ST  R0, ANS	  	; store answer at ANS
HALT
X .fill 2
N .fill 10
ANS .fill 0
STACK .fill xF000

POWER
;;==========================
;;Your code subroutine here
;;==========================
	ADD R6, R6, -9	;	\ STORE RV, RA, OLD FP, NEW FP, TEMPS, AND REGISTERS
	STR R7, R6, 7	;	|
	STR R5, R6, 6	;	|
	ADD R5, R6, 5	;	| ALLLOCATE SPACE FOR TEMP HERE
	STR R4, R6, 4	;	|
	STR R3, R6, 3	;	|
	STR R2, R6, 2	;	|
	STR R1, R6, 1	;	|
	STR R0, R6, 0	;	/

	LDR R0, R5, 4	;	| R0 = X
	LDR R1, R5, 5	;	| R1 = N
	BRZ BASE

	ADD R6, R6, -2	;	\ STORE X AND N-1 TO RECURSE
	STR R0, R6, 0	;	|
	ADD R1, R1, -1	;	|
	STR R1, R6, 1	;	/
	JSR POWER

	LDR R0, R6, 0
	STR R0, R5, 0	;	| STORE TEMP


	ADD R6, R6, 1	;	\ SET PRECONDITIONS FOR MULTIPLY
	LDR R0, R5, 4	;	|
	STR R0, R6, 0	;	|
	LDR R0, R5, 0	;	|
	STR R0, R6, 1	;	/
	JSR MULTIPLY

	LDR R0, R6, 0	;	\ STORE RV
	STR R0, R5, 3	;	/
	ADD R6, R6, 3	;	|MOVE R6 TO TOP OF STACK
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


BASE
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

;;========================================================================
;;Precondition: A and B are integer values.
;;              R6 is pointing to value A and R6+1 is pointing to value B
;;Postcondition: R6 is pointing to A*B
;;========================================================================
MULTIPLY
.fill x001a
.fill x1dbd
.fill x7b80
.fill x1bbf
.fill x7f42
.fill x5020
.fill x6344
.fill x6545
.fill x0406
.fill x0807
.fill x1001
.fill x14bf
.fill x03fd
.fill x7143
.fill x0e08
.fill x7143
.fill x0e06
.fill x1001
.fill x14a1
.fill x09fd
.fill x903f
.fill x1021
.fill x7143
.fill x6f42
.fill x1d63
.fill x6b41
.fill xc1c0
.end
