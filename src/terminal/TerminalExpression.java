package terminal;

import context.Context;
import expression.Expression;

public class TerminalExpression implements Expression {
    private String keyword;
    
    public TerminalExpression(String keyword) {
        this.keyword = keyword;
    }
    
    @Override
    public boolean interpret(Context context) {
        boolean result = context.contains(keyword);
        System.out.println("  [TERMINAL] Checking '" + keyword + "': " + result);
        return result;
    }
}