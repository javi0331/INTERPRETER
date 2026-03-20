package expression;

import context.Context;

public interface Expression {
    boolean interpret(Context context);
}