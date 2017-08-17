import java.util.*;

public class PalindromeTester {
    
    private static Scanner sc;
    
    public static void main(String[] args) {
        
        boolean invalid = false;
        sc = new Scanner(System.in);
        
        
        PalindromeEvaluator pe;
        
        while(!invalid){
            System.out.print("Enter a string (Leave blank to end) : ");
            String text = sc.nextLine();
            // Check if user entered a valid input
            if(!text.equals(""))
                invalid = false;
            else
                invalid = true;
            if(!invalid){
                pe  = new PalindromeEvaluator(text);
                
                if(pe.isPal())
                    System.out.println("True : " + text);
                else
                    System.out.println("False : " + text);
            }
        }
    }
    
}


class PalindromeEvaluator {
    private String input;
    private static Stack<Character> stack;
    private static Queue<Character> queue;
    private boolean isPal;
    
    public PalindromeEvaluator(String string){
        PalindromeEvaluator.stack = new Stack<Character>();
        PalindromeEvaluator.queue = new LinkedList<Character>();
        this.setInput(string);
    }
    
    public String getInput() {
        return input;
    }
    
    public void setInput(String input) {
        this.input = input;
        String lcInput = input.toLowerCase();
        lcInput = lcInput.replaceAll("[^a-zA-Z]", "");
        
        Stack<Character> backupStack = new Stack<Character>();
        
        for(int i = 0;i < lcInput.length();i++){
            stack.push(lcInput.charAt(i));
        }
        
        while(!stack.isEmpty()){
            Character ch = stack.pop();
            backupStack.push(ch);
            queue.add(ch);
        }
        
        this.setPal(PalindromeEvaluator.isPalindrome(backupStack, PalindromeEvaluator.queue));
    }
    
    public Stack<Character> getStack() {
        return stack;
    }
    
    public void setStack(Stack<Character> stack) {
        PalindromeEvaluator.stack = stack;
    }
    
    public Queue<Character> getQueue() {
        return queue;
    }
    
    public void setQueue(Queue<Character> queue) {
        PalindromeEvaluator.queue = queue;
    }
    
    public boolean isPal() {
        return isPal;
    }
    
    public void setPal(boolean isPal) {
        this.isPal = isPal;
    }
    
    private static boolean isPalindrome(Stack<Character> stack,Queue<Character> queue){
        String string = "";
        String reverseString = "";
        while(!queue.isEmpty()){
            reverseString += queue.remove();
        }
        
        while(!stack.isEmpty()){
            string += stack.pop();
        }
        
        if(string.equals(reverseString))
            return true;
        else
            return false;
    }
}
