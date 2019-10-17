/*
 *  Filename      : Test.java
 *  Author        : 201704150 Kangjun Heo (knowledge@o.cnu.ac.kr)
 *  Written at    : 2019-10-17
 *  Modified at   : 
 *  Organization  : Undergraduated Student,
 *                  Department of Computer Science and Engineering,
 *                  Chungnam National University
 *  Package       : none
 *  Class         : Test
 *  Extended From : nothing
 *  Implements    : nothing
 *  Purpose       :
 *      This java file is manually decompiled from JAVA Intermediate Language.
 *      referred Java Virtual Machine Instruction Architecture Manual
 *      https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html
 *  Addendum      :
 *      Some of ILs cannot be directly decompiled due to modifier problem
 *      If you want an optimized version of this code, please see Test.o.java
 *      Also there's a lot of comments like here, enjoy! :D
 */

 // .class public Test
 // .super java/lang/Object
 // ---------------------------
 // Actually, every classes of Java are extended from
 // java.lang.Object which represents object.
public class Test extends java.lang.Object {
    
    // .method public static add(II)I
    // ------------------------
    // Method : add [Static]
    // Purpose: This method adds two int values and returns.
    // Paramters:
    //     _a          int
    //     _b          int
    // Returns  : int ( _a + _b )
    // Side-Effects :
    //      This method has no side effects. 
    public static int add(int l_0x26, int l_0x27) {
        int l_0x28 = l_0x26 + l_0x27;
        return l_0x28;
    }

    // .method public static main([Ljava/lang/String;)V
    // ---------------------------
    // Method : main [Static]
    // Purpose: Entry point of Java program
    // Paramters:
    //     _Vmain_LString_0   String[]
    // Returns  : nothing
    // Side-Effects :
    //      This method has no side effects. 
    public static void main(String[] _Vmain_LString_0) {
        // ldc 33
        // lstore_2
        final int rc_0 = 33;
        int l_0x28 = rc_0;

        // getstatic java/lang/System/out Ljava/io/PrintStream;
        java.io.PrintStream _ls_Ljava_io_PrintStream_0 = java.lang.System.out;

        // ldc 1
        // iload_2
        // invokestatic Test/add(II)I
        // ------------------------
        // this invokes static method Test.add
        // with one constant and one variable l_0x28
        // also, it passes its return to next virutal method invocation
        final int rc_1 = 1;

        // invokevirtual java/io/PrintStream/println(I)V
        // ------------------------
        // virtual method println invocation of System.out
        // System.out is stored in _ls_Ljava_io_PrintStream_0
        // prints return of Test.add that just invoked
        _ls_Ljava_io_PrintStream_0.println( Test.add(rc_1, l_0x28) );
    }
}
