import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import generated.*;

public class MiniCPrintListener extends MiniCBaseListener {
    // Code string builder (To Replace Stdout Print)
    private StringBuilder codeBuilder = new StringBuilder();

    // Context Parse Tree Properties
    private ParseTreeProperty<String> subContextStrings = new ParseTreeProperty<String>();

    // Ident Related States
    private int indentLevel = 0;

    // Constant: Default Amount of Indentation
    private final int indentSpaces = 4;

    // Utility Function: Builds complete code form
    private String build() {
        return (codeBuilder == null) ? null : codeBuilder.toString();
    }

    // Utility Function: Emit formatted code to codeBuilder
    // Replaces codeBuilder.append
    private void emit(String str) {
        codeBuilder.append(str + "\n");
    }

    // Utility Function: Indentation with dot(.)
    // repeat(int) method of String object can be used
    // for implementation of indentation, because such
    // feature requires repeated characters.
    private String createIndent() {
        return ".".repeat(indentLevel * indentSpaces);
    }

    // Utility Function: Space Concatenation
    // Each tokens should be concatenated with spaces
    // to achieve pretty print function,
    // thus it can be implemented with String.join
    private String concatWithSpaces(String...strs) {
        return String.join(" ", strs);
    }

    // Global Variable Declaration
    @Override public void exitVar_decl(MiniCParser.Var_declContext ctx) {
        String decl = "";

        // Global Variable Declaration has three rules that
        // are having 3, 5, 6 children
        // thus...
        switch (ctx.getChildCount()) {
            case 3:
                decl = concatWithSpaces(
                  ctx.type_spec().getText(),
                  ctx.IDENT().getText()
                );
                break;
            case 5:
                decl = concatWithSpaces(
                        ctx.type_spec().getText(),
                        ctx.IDENT().getText(),
                        "=",
                        ctx.LITERAL().getText()
                );
                break;
            case 6:
                decl = concatWithSpaces(
                        ctx.type_spec().getText(),
                        ctx.IDENT().getText().concat(
                                "[" + ctx.LITERAL().getText() + "]"
                        )
                );
                break;
            default:
                return;
        }

        decl += ";";
        emit(decl);
    }

    // Local Variable Declaration
    // WHY
    @Override public void exitLocal_decl(MiniCParser.Local_declContext ctx) {
        String decl = "";

        // Create Indent Dots
        // For more information,
        // Refer private method createIndent()
        // at MiniCPrintListener.java, Line 19
        decl += createIndent();

        // Local Variable Declaration has three rules that
        // are having 3, 5, 6 children
        // thus...
        switch (ctx.getChildCount()) {
            case 3:
                decl += concatWithSpaces(
                        ctx.type_spec().getText(),
                        ctx.IDENT().getText()
                );
                break;
            case 5:
                decl += concatWithSpaces(
                        ctx.type_spec().getText(),
                        ctx.IDENT().getText(),
                        "=",
                        ctx.LITERAL().getText()
                );
                break;
            case 6:
                decl += concatWithSpaces(
                        ctx.type_spec().getText(),
                        ctx.IDENT().getText().concat(
                                "[" + ctx.LITERAL().getText() + "]"
                        )
                );
                break;
            default:
                return;
        }

        decl += ";";
        emit(decl);
    }

    // Function Declation
    @Override public void exitFun_decl(MiniCParser.Fun_declContext ctx) {

        super.exitFun_decl(ctx);
    }
}
