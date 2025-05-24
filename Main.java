import java.util.Scanner;
public class Main {
    
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to play a game ;)");
        String word = scanner.nextLine();
        //calls GameUI based on user input starting the whole game
        GameUI.startGame(word);
        
    }
}
