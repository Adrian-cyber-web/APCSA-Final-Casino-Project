import java.util.Scanner;
import java.util.ArrayList;
public class GameUI{
    //static variable for the users name 
    private static String userName;
    //static variable to hold the total number points the player has
    private static int userPoints;
    //ArrayList that hold the inventory or prizes the user has 
    private static ArrayList<String> inventory;

    //constructor for the class initializing the usename,points, and Inventory
    public GameUI (String name, int points){
        userName = name;
        userPoints = points;
        inventory = new ArrayList<>();
    }


    //startGame method that starts the game
    public static void startGame(String start){
        Scanner scanner = new Scanner(System.in);
        // if statement checks if use enters yes in any format
        // else checks if no is enter in anywy
        //if neither then it calls recursion and ask to enter a valid answer
        if(start.length() >= 2){
            if( (start.substring(0,1).equals("y") || start.substring(0,1).equals("Y") ) && (start.substring(1,2).equals("e") || start.substring(1,2).equals("E") ) && (start.substring(2).equals("s") || start.substring(2).equals("S") )){
                System.out.println("Please enter a username");
                String userID = scanner.nextLine();
                GameUI user =  new GameUI(userID,0);
                System.out.println("Hello " + userName + " where would you like to go");
                System.out.println("Your current account points are " + userPoints);
                //Calls the selectGame method 
                GameUI.selectGame();
            } else if( (start.substring(0,1).equals("n") || start.substring(0,1).equals("N") ) &&  (start.substring(1,2).equals("o") || start.substring(1,2).equals("O"))){
            System.out.println("OKAY BYEEEEEEEEEEEEEEEEEEEEEEEEE");
            }else {
                System.out.println("Please Input a Valid Answer"); 
                String word = scanner.nextLine();
                GameUI.startGame(word);  
            }
        } else {
            System.out.println("Please Input a Valid Answer"); 
            String word = scanner.nextLine();
            GameUI.startGame(word);
        }






    }

    //A public static method that prints a display of the games allowing to choose where to go next
    public static void selectGame(){  
         System.out.println("---------------------------");
         System.out.println("|      1. BlackJack        |");
         System.out.println("|      2. Slots            |");
         System.out.println("|      3. Prize Booth      |");
         System.out.println("|      4. Account          |");
         System.out.println("----------------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number");
        String input = scanner.nextLine();
         if(input.equals("1")){
               //if the user enters 1 then it calls the playBlackJack method starting the game
               BlackJack.playBlackJack();    
          } else if(input.equals("2")){
               //if user enters 2 it calls the slotGame method starting the gamee
               slotGame();
          } else if (input.equals("3")){
               //if user enters 3 it calls the prizeBooth method sending you to the booth
               prizeBooth();
            }else if (input.equals("4")){
              //if user enter 4 it takes them to the accountPage calling the accountPage method
              accountPage();
            } else {
                //if the user dosen't enter a proper number it uses recursion to recall the method allowing them to reEnter a new number
               System.out.println("Invalid option enter a number");
                GameUI.selectGame();
          }
    }

    //slotGame static method runs the slots game 
    public static void slotGame(){
        //clears the console
        clearConsole();
         Scanner scanner = new Scanner(System.in);
         Slots slotGame1 = new Slots();
         //calls printslots method from the slots class, prints the outcome of your current slots
         slotGame1.printSlots();
         //creates a array that stores the matches of the current slots 
         //dose this by call the checkslots method from the class slots
         ArrayList<String> matches = slotGame1.checkSlots();
         //creates a temp variable to store the points earned in your slots run 
         // calls the calculatePoints method with the matches ArrayList previously made
         int pointsEarned = slotGame1.calculatePoints(matches);
         //adds points earned to the total user points
         userPoints += pointsEarned;
         System.out.println("You earned " + pointsEarned + " this spin");
         System.out.println("You have a total of " + userPoints + " points");
         System.out.println("Enter 1 to respin or 2 to go back");
         String respin = scanner.nextLine();
         //calls valid input method on the input of the user
         validInput(respin, "slots");
    }

    //static method that checks if the parameter is yes or no else calls recurisio
    //until a valid answer is enter
    public static boolean YesorNo(String start){
        Scanner scanner = new Scanner(System.in);
        if( (start.substring(0,1).equals("y") || start.substring(0,1).equals("Y") ) && (start.substring(1,2).equals("e") || start.substring(1,2).equals("E") ) && (start.substring(2).equals("s") || start.substring(2).equals("S") )){
            return true;
        } else if( (start.substring(0,1).equals("n") || start.substring(0,1).equals("N") ) &&  (start.substring(1,2).equals("o") || start.substring(1,2).equals("O"))){ 
            return false;
        } else {
            System.out.println("Please Input a Valid Answer"); 
            String word = scanner.nextLine();
            YesorNo(word);
        }
        return false;
    }

    //public static method that takkes a user input and the current game its being used in
    public static void validInput(String word, String game){
        Scanner scanner = new Scanner(System.in);
        //if user enters 1 proceed else go back to the main menu/game seleection
        if(word.equals("1")){
            //checks what game its being used in and recalls the game inorder to be able
            //to replay the game
            if(game.equals("blackjack")){
                BlackJack.playBlackJack();
            }
            else if(game.equals("slots")){
                slotGame();
            } else if(game.equals("prizebooth")){
                prizeBooth();
            }
         } else if(word.equals("2")){
            clearConsole();
            selectGame();
         } else {
            //if the user dosen't enter  a 1 or 2 it calls recurrion and asks for a valid
            // answer to be entered
           System.out.println("Please Input a Valid Answer");
           String input = scanner.nextLine();
           validInput(input, game);
         }
    }

    //public statuc method called nextAction taking in a string used in blackjack class
    public static void nextAction(String game){
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("1. Play Again");
        System.out.println("2. Return to Main Menu");
        String choice = scanner.nextLine();
        //calls validInput on what the user inputs
        validInput(choice, game);

    }

    //prizebooth method called on when you enter the prizebooth
    public static void prizeBooth(){
        //clears console
        clearConsole();
        Scanner scanner = new Scanner(System.in);
        PrizeBooth booth = new PrizeBooth();
        //calls printBooth from the prizeBooth class
        booth.printBooth();
        System.out.println("Enter the number for the prize you wish too claim");
        String word = scanner.nextLine();
        //calls the redeemPrize method from the prizebooth class based on user input
        booth.redeemPrize(word);
    }
    
    //accessor method to access userpoints from another class
    public static int getPoints(){
        return userPoints;
    }
    
    //accessor method to access inventory from another class
    public static ArrayList<String> getInventory(){
        return inventory;
    }

    //helper method that adds points earned to user points
    public static void addPoints(int x){
        userPoints+= x;
    }

    //static void method responsible for the accountPage
    public static void accountPage(){
        //clears console
        int count = 1;
        clearConsole();
        System.out.println("\n-------------------");
        System.out.println("ACCOUNT INFORMATION");
        System.out.println("-------------------");
        System.out.println("Username: " + userName);
        System.out.println("Points: " + userPoints);
        System.out.println("Inventory:");
        //if nothing in the inventory then print empty
        if (inventory.size() == 0){
            System.out.println("(empty)");
        }
        else{
            //for loop throught inventory printing each iteam you have
            for (int i = 0; i < inventory.size(); i++){
                if(i%3 == 0){
                    System.out.println("");
                }
                System.out.print("- " + count + inventory.get(i).substring(2) + " ");
                count++;
            }
        }
        System.out.println("\nEnter 1 to return to main menu");
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();
        //if user enters 1 it returns them back to the main menu/game selection
        if (word.equals("1")){
            clearConsole();
            selectGame();
        }
        else{
            System.out.println("Please Input a Valid Answer");
            //if they dont enter 1 then call recursion untiil valid answer is inputed
            accountPage();
        }
    }

    //helper method to help set the userpoints
    public static void setPoints(int points){
        userPoints= points;
    }

    //helper method that clears the console
    public static void clearConsole(){
        System.out.println("\033c");
        System.out.flush();
    }


}
