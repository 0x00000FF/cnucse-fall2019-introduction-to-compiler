import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;


public class MiniCPrintListener extends MiniCBaseListener {
    ParseTreeProperty<?> paramContexts = new ParseTreeProperty<MiniCParser.ParamContext>();

    private int indentLevel = 0;
    private final int indentSpaces = 4;

    private String createIndent() {
        return new String(
                new char[] { ' ' },
                0,
                indentLevel * indentSpaces
        );
    }

    @Override
    public void enterProgram(MiniCParser.ProgramContext ctx) {
    }

    @Override
    public void enterVar_decl(MiniCParser.Var_declContext ctx) {
    }

    @Override
    public void exitExpr(MiniCParser.ExprContext ctx) {
    }

    @Override
    public void enterDecl(MiniCParser.DeclContext ctx) {

        super.enterDecl(ctx);
    }

    @Override
    public void exitFun_decl(MiniCParser.Fun_declContext ctx) {
        String typeSpec = ctx.children.get(0).getText();
        String funcName = ctx.children.get(1).getText();
        String ptLeft = ctx.children.get(2).getText();

        System.out.printf("%s %s %s", typeSpec, funcName, ptLeft);

        paramContexts.
        // TODO: Parameters should be expressed

        System.out.println(")");

        // TODO: Compound Statements should be expressed
    }

    @Override
    public void enterParams(MiniCParser.ParamsContext ctx) {

    }

    @Override
    public void exitParams(MiniCParser.ParamsContext ctx) {
        for (var ctxChild : ctx.children) {
            if (ctxChild instanceof TerminalNodeImpl) {
                TerminalNodeImpl tnParam = (TerminalNodeImpl)ctxChild;
                String tnParamText = tnParam.getText();

                if (tnParamText == null) continue;

                System.out.print(tnParamText);
                if (tnParamText.equals(",")) {
                    System.out.print(" ");
                }
            } else {
                MiniCParser.ParamContext param = (MiniCParser.ParamContext) ctxChild;
                String paramTypeSpec = param.getChild(0).getText();
                String paramName = param.getChild(1).getText();

                System.out.printf("%s %s", paramTypeSpec, paramName);
            }
        }

        System.out.println(")");
    }

    @Override
    public void enterCompound_stmt(MiniCParser.Compound_stmtContext ctx) {
        System.out.println("{");

        indentLevel++;
    }

    @Override
    public void exitCompound_stmt(MiniCParser.Compound_stmtContext ctx) {
        System.out.println("}\n");

        indentLevel--;
    }

}
