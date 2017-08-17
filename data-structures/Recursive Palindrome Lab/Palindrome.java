import java.util.*;

public class Palindrome {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        char playAgain = 'Y';
        
        do{
            System.out.println("Enter a word or phase to check for palindrome:");
            String word = sc.nextLine();
            
            String lcWord = word.toLowerCase();
            lcWord = lcWord.replaceAll("[^a-zA-Z]", "");
            
            char[] charWord = lcWord.toCharArray();
            
            if(checkPalindrome(charWord)){
                System.out.println("true : " + word);
            }else {
                System.out.println("false : " + word);
            }
            
            System.out.println("Do you want to play again? Y/N");
            
            playAgain = sc.nextLine().toUpperCase().charAt(0);
        }while(playAgain == 'Y' || playAgain != 'N');
        
        System.out.println("Goodbye");
    }
    
    
    public static boolean checkPalindrome(char[] charWord){
        if(charWord.length == 0 || charWord.length == 1)
            return true;
        if (charWord[0] == charWord[charWord.length - 1])
            return checkPalindrome(Arrays.copyOfRange(charWord, 1, charWord.length - 1));
        return false;
    }
}
