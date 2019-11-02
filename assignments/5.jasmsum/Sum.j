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
.method public sum(I)I

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

.end method

