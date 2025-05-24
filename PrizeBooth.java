import java.util.ArrayList;
import java.util.Scanner;
public class PrizeBooth{
     //creates a ArrayList for the prizes
     static ArrayList<String> prizes;
     //creates a ArrayList for the price of each prize
     static ArrayList<Integer> prices;

    //PrizeBooth constructor initalizes both arrays and adds the prices and prizes
    public PrizeBooth(){
        prizes = new ArrayList<>();
        prizes.add("1. Teddy Bear $5000");
        prizes.add("2. Giant Giraffe $10000");
        prizes.add("3. Toy Car $1500");
        prizes.add("4. Yo-yo $1000");
        prizes.add("5. Rubik's Cube $1000");
        prizes.add("6. Nerf Blaster $2500");
        prizes.add("7. Toy Drone $5000");
        prizes.add("8. Lava Lamp $1500");
        prizes.add("9. Action Figure $1000");
        prices = new ArrayList<>();
        prices.add(5000);
        prices.add(10000);
        prices.add(1500);
        prices.add(1000);
        prices.add(1000);
        prices.add(2500);
        prices.add(5000);
        prices.add(1500);
        prices.add(1000);
    }

    //prints the prizeBooth 
    public void printBooth(){
        System.out.println("-----------------------------------------------------------------");
        for(int x = 0; x <  prizes.size(); x++){
            if(x % 3 == 0){
                System.out.println("");
            }
            System.out.print(prizes.get(x)+ "   ");
        }
         System.out.println("");
        System.out.println("-----------------------------------------------------------------");
    }

    //method that redeemprize enter in the parameter
    public void redeemPrize(String num){
        Scanner scanner = new Scanner(System.in);
        boolean found = false;
        //loops throught the prizes array
        for(int x = 0; x < prizes.size(); x++){
            //if parameter is equals to the first character then continue
            if(num.equals(prizes.get(x).substring(0,1))){
                
                found=true;
                //if user points is greater or equals to the prize price then continue
                if(GameUI.getPoints() >=  prices.get(x) ){
                    //adds prize to the users Inventory
                    GameUI.getInventory().add(prizes.get(x));
                    //subtracts the users points by the price of the prize
                    GameUI.setPoints(GameUI.getPoints() - prices.get(x));

                    System.out.println("You claimed: " + prizes.get(x));


                    System.out.println("Enter 1 to get another reward");
                    System.out.println("Enter 2 to go back");
                    String word = scanner.nextLine();
                    //calls recursion giving user option to claim new reward or go to menu
                    GameUI.validInput(word, "prizebooth");
                    return;

                } else {
                    System.out.println("You dont have enought points");
                    System.out.println("Enter 1 to get new reward");
                    System.out.println("Enter 2 to go back");
                    String word = scanner.nextLine();
                    //if user has insufficent funds then call recursion to claim new prize
                    // or go back to menu
                    GameUI.validInput(word,"prizebooth");
                    return;
                }
            } 
        }
        if(!found){
            //if the prize enter isnt found then call recursion to reenter valid answer
            System.out.println("Invalid option. Enter a number from the list.");
            String word2 = scanner.nextLine();
            redeemPrize(word2);
        }
    }
    
}
