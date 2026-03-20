package nonterminal;

import context.Context;
import expression.Expression;

public class AndExpression implements Expression {
    private Expression leftExpression;
    private Expression rightExpression;
    
    public AndExpression(Expression left, Expression right) {
        this.leftExpression = left;
        this.rightExpression = right;
    }
    
    @Override
    public boolean interpret(Context context) {
        System.out.println("  [AND] Evaluating both conditions...");
        boolean result = leftExpression.interpret(context) && rightExpression.interpret(context);
        System.out.println("  [AND] Result: " + result);
        return result;
    }
}