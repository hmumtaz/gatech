;;===============================
;;Name: HUSSAIN MUMTAZ
;;===============================

.orig x3000



;; YOUR CODE HERE! :D

		AND R0, R0, 0	;PRINTING REGISTER

		AND R1, R1, 0	;N REGISTER
		LD 	R1, N
		ADD R1, R1, -1	;INDEX FROM 0

		AND R2, R2, 0	;DUMMY N
		LD R2, N
		ADD R2, R2, -1

		AND R3, R3, 0	;EVENT NUMBER
		AND R4, R4, 0	;EVENT NUMBER DUMMY
		AND R5, R5, 0	;NULL TERMINATOR CHECKER
		
		AND R6, R6, 0	;UPPER EVENT LIMIT
		ADD R6, R6, 10
		
		ADD R1, R1, 0	; BRANCHES IF N = 0 TO NO BEFORE STATEMENT
		BRz NOBEF


		LEA R0, BEFORE_MSG_1 ; PRINTS STANDARD BEFORE MESSAGE
		PUTS

YEAR	LD R0, YEAR_ARR	; LOADS FIRST YEAR

YRCHK	ADD R2, R2, -1	; CHECKS IF CURRENT YEAR
		BRn YRPRNT

YRLP	ADD R0, R0, 6	; GETS YEAR RELATIVE TO N
		BR YRCHK		; CHECK FOR CURRENT YEAR

NOBEF	LEA R0, BEFORE_MSG_2	;PRINTS NOTHING BEFORE MESSAGE
		PUTS
		BR YEAR 	; EVALUATES YEAR

YRPRNT	PUTS
		AND R2, R2, 0	;RESTORE DUMMY N VALUE
		LD 	R2, N
		ADD R2, R2, -1

CHECK	ADD R1, R1, -1	; DECREMENTS N TO CHECK IF ALL EVENTS BEFORE HAVE BEEN PRINTED
		BRn AFTER 		; IF BEFORE EVENTS HAVE BEEN PRINTED GOES TO AFTER STATEMENT

EVENT 	LD R0, EVENT_ARR	;LOADS FIRST EVENT
		ADD R0, R0, -1

EVCHK	ADD R0, R0,  1
		ADD R4, R4, -1		;CHECK IF EVENT IS PRINTED
		BRn EVPRNT

EVLP	LDR R5, R0, 0		;ITERATES THROUGH EVENT
		BRz EVCHK	;IF EVENT STRING IS ITERATED CHECKS IF THIS EVENT NEEDS PRINTING
		ADD R0, R0, 1
		BR EVLP

EVPRNT	PUTS
		ADD R3, R3, 1	;INCREMENTS PRINTED EVENTS COUNTER
		ADD R4, R3, 0	;SETS VALUE OF CHECKER TO EVENT COUNTER
		ADD R6, R6, -1	;DECREMENTS TOTAL NEEDING PRINTING
		BRz DONE		;IF TOTAL NEEDING PRINTING IS 0 WE ARE DONE
		BR CHECK 		;GOES BACK TO CHECK FOR AFTER MESSAGE

AFTER	ADD R1, R1, 1 	;CHECK IF AFTER MESSAGE HAS ALREADY BEEN PRINTED
		BRn EVENT 		;IF IT HAS GO TO EVENT LOOP
		ADD R1, R1, -1	
		ADD R3, R3, -9 	;CHECK IF NO AFTER MESSAGE IS NEEDED
		BRz NOAFT 		
		ADD R3, R3, 9
		LEA R0, AFTER_MSG_1 ; PRINTS STANDARD AFTER MESSAGE
		PUTS
		ADD R3, R3, 1		;INCREMENT EVENTS COUNTER
		ADD R4, R3, 0 		;INCREMENT EVENTS CHECKER
		BR YEAR 			;ADD YEAR

NOAFT	LEA R0, AFTER_MSG_2	
		PUTS

DONE	HALT

N	.fill 5

BEFORE_MSG_1
	.stringz "In the years before "

BEFORE_MSG_2
	.stringz "Nothing happened before "

AFTER_MSG_1
	.stringz "And in the years after "

AFTER_MSG_2
	.stringz "And nothing happened after "

YEAR_ARR
	.fill YEAR01
	.fill YEAR02
	.fill YEAR03
	.fill YEAR04
	.fill YEAR05
	.fill YEAR06
	.fill YEAR07
	.fill YEAR08
	.fill YEAR09
	.fill YEAR10

EVENT_ARR
	.fill EVENT01
	.fill EVENT02
	.fill EVENT03
	.fill EVENT04
	.fill EVENT05
	.fill EVENT06
	.fill EVENT07
	.fill EVENT08
	.fill EVENT09
	.fill EVENT10
.end

.orig x5000
YEAR01
	.stringz "1607\n"
YEAR02
	.stringz "1776\n"
YEAR03
	.stringz "1788\n"
YEAR04
	.stringz "1861\n"
YEAR05
	.stringz "1879\n"
YEAR06
	.stringz "1917\n"
YEAR07
	.stringz "1941\n"
YEAR08
	.stringz "1961\n"
YEAR09
	.stringz "1969\n"
YEAR10
	.stringz "1985\n"
EVENT01
	.stringz "John Smith founded Jamestown in 1607\n"
EVENT02
	.stringz "The Declaration of Independence was signed in 1776\n"
EVENT03
	.stringz "The Constitution was ratified in 1788\n"
EVENT04
	.stringz "The Civil War Broke out in 1861\n"
EVENT05
	.stringz "Thomas Edison invented the light bulb in 1879\n"
EVENT06
	.stringz "The US entered WWI in 1917\n"
EVENT07
	.stringz "Japan attacked Pearl Harbor in 1941\n"
EVENT08
	.stringz "The Vietnam War began in 1961\n"
EVENT09
	.stringz "Apollo 11 landed on the moon in 1969\n"
EVENT10
	.stringz "Super Mario Bros. debuted in 1985\n"
.end

