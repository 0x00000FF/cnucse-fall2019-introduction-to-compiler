; Introduction to Compiler
; Assignment 5-1
; ------------------------
; 201704150 Kangjun Heo
; 
; This Java Assembly is written in manual, for Jasmin Assembler.
; The program calls a function sum(int) and prints its return value.

.class public Sum
.super java/lang/Object

; 
; class object initializer 
;
.method public <init>()V
    aload_0
    invokespecial java/lang/Object/<init>()V
    return
.end method

;
; method sum(int)
;
; returns int
; paramter n : int
;
; this method takes a integer typed value, 
; returns a integer typed sum of 1 to n.
;
.method public static sum(I)I
    .limit stack 8
    .limit locals 16

    ; int sum = 0
    ldc 0
    istore 1

    ; int i = 1
    ldc 1
    istore 2

    ; Loop start 
Sum_Loop:
    ; sum = sum + i;    
    iload 1
    iload 2
    iadd
    istore 1
    
    ; i - arg0
    iload 2
    iload 0
    isub

    ; ++i
    iinc 2 1

    ; test i == arg0 (from ln 51~53, check if not zero)
    ifne Sum_Loop

    ; Return 
    iload 1
    ireturn
.end method

;
; method main(String[])
;
; returns nothing 
; parameter args : String[]
;
; this method is an entry point of Sum Java Program.
;
.method public static main([Ljava/lang/String;)V
    ; limit maximum count of stack items and local variables
    .limit stack 8
    .limit locals 16

    ; get System.out static PrintStream object
    getstatic java/lang/System/out Ljava/io/PrintStream;

    ; call sum(100)
    ldc 100
    invokestatic Sum/sum(I)I

    ; call System.out.println(/* return value of sum(100) */)
    invokevirtual java/io/PrintStream/println(I)V

    return
.end method