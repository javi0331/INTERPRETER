package nonterminal;

import context.Context;
import expression.Expression;

public class NotExpression implements Expression {
    private Expression expression;
    
    public NotExpression(Expression expression) {
        this.expression = expression;
    }
    
    @Override
    public boolean interpret(Context context) {
        System.out.println("  [NOT] Checking for prohibited content...");
        boolean result = !expression.interpret(context);
        System.out.println("  [NOT] Result (negated): " + result);
        return result;
    }
}