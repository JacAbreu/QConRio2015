import java.util.HashMap;
import java.util.Map;


public class Evaluator extends QConBaseVisitor<Double> {
    private final Map<String, Double> ids = new HashMap<>();

    @Override
    public Double visitLiteral(QConParser.LiteralContext ctx) {
        return Double.parseDouble(ctx.getText());
    }

    @Override
    public Double visitAssign(QConParser.AssignContext ctx) {
        Double visit = visit(ctx.expr());
        ids.put(ctx.ID().getText(), visit);
        return visit;
    }

    @Override
    public Double visitOp(QConParser.OpContext ctx) {
        switch (ctx.op.getText()) {
            case "+":
                return visit(ctx.left) + visit(ctx.right);
            case "-":
                return visit(ctx.left) - visit(ctx.right);
            case "*":
                return visit(ctx.left) * visit(ctx.right);
            case "/":
                return visit(ctx.left) / visit(ctx.right);
        }
        return null;
    }

    @Override
    public Double visitGetValue(QConParser.GetValueContext ctx) {
        String id = ctx.getText();
        if (!ids.containsKey(id))
            throw new IllegalArgumentException("Unknown variable: " + id);
        return ids.get(id);
    }

    @Override
    public Double visitUnary(QConParser.UnaryContext ctx) {
        switch (ctx.op.getText()) {
            case "+":
                return Double.parseDouble(ctx.expr().getText());
            case "-":
                return -Double.parseDouble(ctx.expr().getText());
        }
        return null;
    }

    @Override
    public Double visitParens(QConParser.ParensContext ctx) {
        return visit(ctx.expr());
    }
}
