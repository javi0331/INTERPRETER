package context;

public class Context {
    private String text;
    
    public Context(String text) {
        this.text = text.toLowerCase();
    }
    
    public String getText() {
        return text;
    }
    
    public boolean contains(String keyword) {
        return text.contains(keyword.toLowerCase());
    }
}