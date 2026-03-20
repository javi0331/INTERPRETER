package nonterminal;

import context.Context;
import expression.Expression;

public class OrExpression implements Expression {
    private Expression leftExpression;
    private Expression rightExpression;
    
    public OrExpression(Expression left, Expression right) {
        this.leftExpression = left;
        this.rightExpression = right;
    }
    
    @Override
    public boolean interpret(Context context) {
        System.out.println("  [OR] Evaluating at least one condition...");
        boolean result = leftExpression.interpret(context) || rightExpression.interpret(context);
        System.out.println("  [OR] Result: " + result);
        return result;
    }
}