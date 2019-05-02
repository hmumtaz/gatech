;;===============================
;;Name: Hussain Mumtaz
;;===============================

.orig x3000

;; CODE GOES HERE! :D
		AND	R0,	R0,	0	; CLEAR REGISTERS
		AND	R1,	R1,	0
		AND R2, R2, 0
		LD	R0,	A 		; LOAD VARS
		LD	R1,	B
		NOT R0, R0		; NEGATE VARS
		NOT R1, R1
		AND R2, R0, R1	; A' & B' == ~(A | B)
		NOT R2, R2		; ~(~(A | B)) = A | B
		ST R2, ANSWER
		HALT
A       .fill 6
B       .fill 13
ANSWER  .fill 0		; This answer should contain A | B when finished.
.end
