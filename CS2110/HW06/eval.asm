;;===============================
;;Name: HUSSAIN MUMTAZ
;;===============================


.orig x3000
;;===============================
;;Do not change anything in MAIN
;;===============================
MAIN
	LD R6, STACK	; Initialize the stack

	LEA R0, STRING	; R0 = &str[0]
	ADD R1, R0, 0

SL_LOOP	
	LDR R2, R1, 0	; \ R1 = strlen(str)
	BRz SL_END		; |
	ADD R1, R1, 1	; |
	BR SL_LOOP		; |
SL_END	
	NOT R2, R0		; |
	ADD R2, R2, 1	; |
	ADD R1, R1, R2	; /

	ADD R6, R6, -2	; \ R0 = eval(str, len)
	STR R0, R6, 0	; |
	STR R1, R6, 1	; |
	LD R2, EVALPTR	; |
	JSRR R2		; |
	LDR R0, R6, 0	; |
	ADD R6, R6, 3	; /

	ST R0, ANS
	HALT

STACK	.fill xf000
ANS		.fill -1
EVALPTR	.fill EVAL
STRING	.stringz "1+2*3+4*5"
		.blkw 200

EVAL
;;==========================
;;Your code subroutine here
;;==========================
	ADD R6, R6, -11	;	\ STORE RV, RA, OLD FP, NEW FP, TEMPS, AND REGISTERS
	STR R7, R6, 9	;	|
	STR R5, R6, 8	;	|
	ADD R5, R6, 7	;	| ALLLOCATE SPACE FOR I, LEFT, RIGHT (TEMP VARS) HERE
	STR R4, R6, 4	;	|
	STR R3, R6, 3	;	|
	STR R2, R6, 2	;	|
	STR R1, R6, 1	;	|
	STR R0, R6, 0	;	/

	LDR R0, R5, 4	;	| R0 = STR
	LDR R1, R5, 5	;	| R1 = LEN
	AND R2, R2, 0	;	| I = 0
	STR R2, R5, -2	;	\ CLEAR TEMPS
	STR R2, R5, -1  ;	|
	STR R2, R5, 0	;	/
ADDLOOP
	NOT R3, R1		;	\ R3 = -LEN
	ADD R3, R3, 1	;	/
	ADD R3, R2, R3	;	| R3 = I - LEN
	BRZ ENDADD
	ADD R3, R0, R2	;	| R3 = STR + I
	LDR R3, R3, 0	;	| R3 = *(STR + I)
	ADD R3, R3, -15	;	\ ASCII VALUE OF '+' == 43, R3 = *(STR + I) - 43
	ADD R3, R3, -15	;	|
	ADD R3, R3, -13	;	/
	BRNP NOTADD

	ADD R6, R6, -2	;	\ LEFT = EVAL(STR, I)
	STR R2, R6, 1	;	|
	STR R0, R6, 0	;	|
	JSR EVAL 		;	/

	LDR R3, R6, 0	;   \ STORE LEFT IN TEMP
	STR R3, R5, -1	;	/
	
	ADD R6, R6, 1	;	\ RIGHT = EVAL(STR + I + 1, LEN - I - 1) AND STORE ON STACK
	ADD R0, R0, R2	;	|
	ADD R0, R0, 1	;	|
	NOT R2, R2		;	|
	ADD R1, R1, R2	;	|
	STR R1, R6, 1	;	|
	STR R0, R6, 0	;	|
	JSR EVAL  		;	/
	
	LDR R3, R6, 0	;   \ STORE RIGHT IN TEMP
	STR R3, R5, 0	;	/

	LDR R0, R5, -1	;	\ LEFT + RIGHT AND STORE TO RV
	LDR R1, R5, 0	;	|
	ADD R0, R0, R1	;	|
	STR R0, R5, 3	;	/

	ADD R6, R6, 3	;	| Move SP TO TOP OF STACK
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


NOTADD
	ADD R2, R2, 1	;	| I++
	BR ADDLOOP

ENDADD
	AND R2, R2, 0	;	| I = 0
	STR R2, R5, -2	;	\ CLEAR TEMPS
	STR R2, R5, -1  ;	|
	STR R2, R5, 0	;	/
MULTLOOP
	NOT R3, R1		;	\ R3 = -LEN
	ADD R3, R3, 1	;	/
	ADD R3, R2, R3	;	| R3 = I - LEN
	BRZ ENDMULT
	ADD R3, R0, R2	;	| R3 = STR + I
	LDR R3, R3, 0	;	| R3 = *(STR + I)
	ADD R3, R3, -15	;	\ ASCII VALUE OF '*' == 42, R3 = *(STR + I) - 42
	ADD R3, R3, -15	;	|
	ADD R3, R3, -12	;	/
	BRNP NOTMULT


	ADD R6, R6, -2	;	\ LEFT = EVAL(STR, I)
	STR R2, R6, 1	;	|
	STR R0, R6, 0	;	|
	JSR EVAL 		;	/

	LDR R3, R6, 0	;   \ STORE LEFT IN TEMP
	STR R3, R5, -1	;	/
	
	ADD R6, R6, 1	;	\ RIGHT = EVAL(STR + I + 1, LEN - I - 1) AND STORE ON STACK
	ADD R0, R0, R2	;	|
	ADD R0, R0, 1	;	|
	NOT R2, R2		;	|
	ADD R1, R1, R2	;	|
	STR R1, R6, 1	;	|
	STR R0, R6, 0	;	|
	JSR EVAL  		;	/
	
	LDR R3, R6, 0	;   \ STORE RIGHT IN TEMP
	STR R3, R5, 0	;	/

	LDR R0, R5, -1	;	\ LEFT * RIGHT AND STORE TO RV
	LDR R1, R5, 0	;	|
	AND R2, R2, 0	;	|
INMULT
	ADD R2, R2, R0	;	| MULTIPLY
	ADD R1, R1, -1	;	|
	BRP INMULT		;	|
	ADD R0, R2, 0	;	|
	STR R0, R5, 3	;	/

	ADD R6, R6, 3	;	| Move SP TO TOP OF STACK
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

NOTMULT
	ADD R2, R2, 1	;	| I++
	BR MULTLOOP

ENDMULT
	LDR R0, R0, 0	;	\ DEREFERENCE VALUE AND SUBTRACT '0'
	ADD R0, R0, -15	;	|
	ADD R0, R0, -15	;	|
	ADD R0, R0, -15	;	|
	ADD R0, R0, -3	;	/

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
