import java.util.Scanner;
public class BlackJack{
    static Scanner scan = new Scanner(System.in);
    static int[] cardCount= new int[13]; // stores the count of each card left in the deck
    private static boolean win = false;
    
    //resets deck to have 4 of each card
    public static void initializeDeck(){
        for(int i=0; i<13; i++){
            cardCount[i]=4; 
        }
    }
    
    //method that deals the cards to the player
    public static int dealCard(){
        while(true){
            int index = (int)(Math.random()*13);
            if(cardCount[index]>0){
                cardCount[index]--;
                if (index < 9)
                return index + 2; 
                if (index < 12)
                    return 10;
                return 11;   
            }
        }    
    }

    //total score, also determines if Ace is 11 or 1
    public static int score(int[] hand, int count){
        int sum =0;
        int aceCount = 0;
        for(int i=0; i<count; i++){
            sum+=hand[i];
            if(hand[i]==11)
                aceCount++;
        }
        while (sum>21 && aceCount>0){
            sum-=10;  
            aceCount--;
        }
        return sum;
    }

    //prints what the player currently has in their hands
    public static void printHand(int [] hand, int count){
        for(int i=0; i<count; i++){
            System.out.print(hand[i]+ " ");
        }
        System.out.println("Total: " + score(hand, count));
    }

    //method allows the player to play the blackjack game
    public static void playBlackJack(){
        GameUI.clearConsole();
         System.out.println("Let's play BlackJack\n");
        initializeDeck();
        int[] player = new int[10];
        int[] dealer = new int[10];
        int pCount=0;
        int dCount=0;

        player[pCount++] = dealCard();
        player[pCount++] = dealCard();
        dealer[dCount++] = dealCard();
        dealer[dCount++] = dealCard();

        //check for imediate blackjack
        if(score(player, pCount) == 21){
            System.out.print("Player hand: ");
            printHand(player, pCount);
            System.out.println("Blackjack! \u001B[33m You win! \u001B[o");
            win=true;
            GameUI.nextAction("blackjack");
            return;
        }

        System.out.println("Dealer has: " + dealer[0]);
        System.out.print("Player hand: ");
        printHand(player, pCount);

        boolean playerTurn = true;
        while(playerTurn){
            System.out.println("Hit or Stand?");
            String choice = scan.nextLine().toLowerCase();
            if (choice.equals("hit")){
                player[pCount++] = dealCard();
                System.out.print("Player hand: ");
                printHand(player, pCount);
                if(score(player, pCount) > 21){
                    System.out.println("You busted. Dealer wins.");
                    GameUI.nextAction("blackjack");
                    return;
                }
            }
            else if(choice.equals("stand")){
                playerTurn=false;
            }
            else{
                System.out.println("Please Input a Valid Answer");
            }
        }

        System.out.print("Dealer hand: ");
        printHand(dealer, dCount);
        while(score(dealer, dCount) < 17){
            dealer[dCount++] = dealCard();
            System.out.print("Dealer hand: ");
            printHand(dealer, dCount);
        }

        int pScore = score(player, pCount);
        int dScore = score(dealer, dCount);







       if (dScore > 21){
        System.out.println("Dealer busts. \u001B[33m You win!\u001B[o");
        win=true;
       }
       else if (pScore > dScore){
        System.out.println("\u001B[33mYou win!\u001B[o");
        win =true; 
       }
        
       else if (pScore < dScore)
        System.out.println("Dealer wins. \u001B[31m You lose \u001B[o");
       else
        System.out.println("It's a draw.");
        

        if(win){
            System.out.println("You earned 1000 points!");
            GameUI.addPoints(1000);
            win=false;
        }


       GameUI.nextAction("blackjack"); 
            
    }


    






}
