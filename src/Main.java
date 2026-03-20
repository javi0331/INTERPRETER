import context.Context;
import expression.Expression;
import nonterminal.AndExpression;
import nonterminal.NotExpression;
import nonterminal.OrExpression;
import terminal.TerminalExpression;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║       INTERPRETER PATTERN - LEASE CLAUSE VALIDATOR        ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        Expression validator = buildClauseValidator();
        
        System.out.println("=".repeat(60));
        System.out.println("TEST CASE 1: Valid Clause");
        System.out.println("=".repeat(60));
        String clause1 = "El arrendatario pagará la renta mensual";
        validateClause(validator, clause1);
        
        pause(1000);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 2: Valid Clause (Synonyms)");
        System.out.println("=".repeat(60));
        String clause2 = "El inquilino abonará el canon anticipado";
        validateClause(validator, clause2);
        
        pause(1000);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 3: Invalid Clause (Missing Action)");
        System.out.println("=".repeat(60));
        String clause3 = "El arrendatario la renta mensual";
        validateClause(validator, clause3);
        
        pause(1000);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 4: Invalid Clause (Prohibited Word)");
        System.out.println("=".repeat(60));
        String clause4 = "El arrendatario pagará la renta mensual y podrá subarrendar";
        validateClause(validator, clause4);
        
        pause(1000);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST CASE 5: Invalid Clause (Missing Complements)");
        System.out.println("=".repeat(60));
        String clause5 = "El arrendatario pagará";
        validateClause(validator, clause5);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("VALIDATION COMPLETE");
        System.out.println("=".repeat(60));
    }
    
    private static Expression buildClauseValidator() {
        System.out.println("Building Abstract Syntax Tree (AST)...\n");
        
        Expression subject = new OrExpression(
            new TerminalExpression("arrendatario"),
            new TerminalExpression("inquilino")
        );
        
        Expression action = new OrExpression(
            new TerminalExpression("pagará"),
            new TerminalExpression("abonará")
        );
        
        Expression complement1 = new OrExpression(
            new TerminalExpression("renta"),
            new TerminalExpression("canon")
        );
        
        Expression complement2 = new OrExpression(
            new TerminalExpression("mensual"),
            new TerminalExpression("anticipado")
        );
        
        Expression prohibitedWord = new TerminalExpression("subarrendar");
        Expression noProhibited = new NotExpression(prohibitedWord);
        
        Expression structure = new AndExpression(
            new AndExpression(subject, action),
            new AndExpression(complement1, complement2)
        );
        
        Expression finalValidator = new AndExpression(structure, noProhibited);
        
        System.out.println("AST Built Successfully!\n");
        
        return finalValidator;
    }
    
    private static void validateClause(Expression validator, String clauseText) {
        System.out.println("CLAUSE: \"" + clauseText + "\"\n");
        
        Context context = new Context(clauseText);
        boolean isValid = validator.interpret(context);
        
        System.out.println("\n" + "─".repeat(60));
        if (isValid) {
            System.out.println("✓ RESULT: VALID CLAUSE");
        } else {
            System.out.println("✗ RESULT: INVALID CLAUSE");
        }
        System.out.println("─".repeat(60));
    }
    
    private static void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}