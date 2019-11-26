/*
 *  MiniC to JVM Bytecode Compiler
 *  2019 Fall CNU
 *  Introduction to Compiler Assignment
 *
 *  201704150 Kangjun Heo
 */

import generated.MiniCLexer;
import generated.MiniCParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.stringtemplate.v4.compiler.Bytecode;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import listener.main.BytecodeGenListener;

public class Main {
    // Remove unnecessary System.out (.NET Style)
    static PrintStream Console = System.out;

    public static void main(String[] args) {
        Console.println(
                "MiniC to Java Bytecode Compiler\n" +
                "Copyright by 201704150 Kangjun Heo, All rights reserved.\n" +
                "=========");

        if (args.length < 1) {
            Console.println("java -jar mcjc.jar <filename>");
            System.exit(1);
        }

        try {
            CharStream cs = CharStreams.fromFileName(args[0]);
            MiniCLexer lexer = new MiniCLexer(cs);
            CommonTokenStream tokens = new CommonTokenStream( lexer );
            MiniCParser parser = new MiniCParser( tokens );
            ParseTree tree = parser.program();

            ParseTreeWalker walker = new ParseTreeWalker();
            ParseTreeListener listener = new BytecodeGenListener();
            walker.walk(listener, tree);


        } catch (IOException e) {
            Console.println("Error while opening designated file.");
        }
    }
}
