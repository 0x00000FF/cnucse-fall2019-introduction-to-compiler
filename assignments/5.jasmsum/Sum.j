; Introduction to Compiler
; Assignment 5-1
; ------------------------
; 201704150 Kangjun Heo
; 
; This Java Assembly is written in manual, for Jasmin Assembler.
; The program calls a function sum(int) and prints its return value.

.class public Sum
.super java/lang/Object ; Every class inherits java.lang.Object

; 
; class object initializer 
;
.method public <init>()V
    aload_0
    invokevirtual java/lang/Object/<init>()V
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
    ; int a = 0
    ldc 0
    istore_0
    
    ; int sum = 0
    istore_1

    ; int i = 1
    ldc 1
    istore_2

    ; Loop start 
    
.end method

;
; method main(String[])
;
; returns nothing 
; paramter args : String[]
;
; this method is an entry point of Sum Java Program.
;
.method public static main([Ljava/lang/String;)V
    ; limit maximum count of stack items and local variables
    .limit stack 2
    .limit locals 8

    ; get System.out static PrintStream object
    getstatic java/lang/System/out Ljava/io/PrintStream

    ; call sum(100)
    ldc 100
    invokestatic Sum/sum(I)I

    ; call System.out.println(/* return value of sum(100) */)
    invokevirtual java/io/PrintStream/println(I)V

    return
.end method