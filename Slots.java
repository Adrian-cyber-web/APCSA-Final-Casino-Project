import java.util.Scanner;
import java.util.ArrayList;
public class Slots{
    //creates a 2 array to store the slots
    String[][] slots;

    public Slots() {
        //calls rollSlots which sets up the 2d array
        rollSlots();
    }

    //Traverses the 2d array and depending on what is store in it, It prints something
    //diffrent
    public void printSlots(){
        System.out.println("-------------------------------------");
         for(int row = 0; row < slots.length; row++){
            for(int col = 0; col < slots[row].length; col++){
                String raw = slots[row][col];
                String word = raw;
                if (raw.equals("bomb"))
                    word = "\u001B[5m\u001B[31m" + raw + "\u001B[0m"; // blink + red
                else if (raw.equals("cherry"))
                    word = "\u001B[91m" + raw + "\u001B[0m"; // purple
                else if (raw.equals("watermelon"))
                    word = "\u001B[32m" + raw + "\u001B[0m"; // green
                else if (raw.equals("grape"))
                    word = "\u001B[35m" + raw + "\u001B[0m"; // blue
                else if (raw.equals("lemon"))
                    word = "\u001B[33m" + raw + "\u001B[0m"; // yellow
                else if (raw.equals("diamond"))
                    word = "\u001B[36m" + raw + "\u001B[0m"; //sky blue
                while (raw.length() < 10){
                    word += " ";
                    raw += " ";
                } 
                System.out.print("| " + word); 
                
            }
            System.out.println("|");
         }
        System.out.println("-------------------------------------");
             
    }

    //method that returns a 2d array of the matches 
    public ArrayList<String> checkSlots(){
        //creates an ArrayList that stores the matches
        ArrayList<String> matches = new ArrayList<String>();

        //if first row all matches then stores row1 indicating that row one has a line match
        if(slots[0][0].equals(slots[0][1])  && slots[0][0].equals(slots[0][2])){
            matches.add("row1");
            System.out.println("You got three across in the first row");
        } 
        //if second row all matches then stores row2 indicating that row two has a line match
        if(slots[1][0].equals(slots[1][1])  && slots[1][0].equals(slots[1][2])){
            matches.add("row2");
            System.out.println("You got three across in the second row");
        }
        //if third row all matches then stores row3 indicating that row three has a line match
        if(slots[2][0].equals(slots[2][1])  && slots[2][0].equals(slots[2][2])){
            matches.add("row3");
            System.out.println("You got three across in the third row");
        }
        
        //if column one all matches then stores col1 indicating that column one has a line match
        if(slots[0][0].equals(slots[1][0])  && slots[0][0].equals(slots[2][0])){
            matches.add("col1");
            System.out.println("You got three down in the first column");
        }
        //if column two all matches then stores col2 indicating that column two has a line match
        if(slots[0][1].equals(slots[1][1])  && slots[0][1].equals(slots[2][1])){
            matches.add("col2");
            System.out.println("You got three down in the second column");
        }
        //if column three all matches then stores col3 indicating that column three has a line match
        if(slots[0][2].equals(slots[1][2])  && slots[0][2].equals(slots[2][2])){
            matches.add("col3");
            System.out.println("You got three down in the third column");
        }
        //if diagonal from top right matches then stores dia1 indication that diagonal has a line match
        if(slots[0][0].equals(slots[1][1]) && slots[0][0].equals(slots[2][2])){
            matches.add("dia1");
            System.out.println("You got three diagonal from top right");
        }
        //if diagonal from top left matches then stores dia2 indication that diagonal has a line match
        if(slots[0][2].equals(slots[1][1]) && slots[0][2].equals(slots[2][0])){
            matches.add("dia2");
            System.out.println("You got three diagonal from top left");
        }
        //return the ArrayList containing all the matches
        return matches;
    }

    //public method rollSlots that sets the entire 2d array up
    //calls math.random to select which iteam is stored 
    //30% bomb and 14% everything else
    public void rollSlots(){
        slots = new String[3][3];
        for(int row = 0; row < slots.length; row++){
            for(int col = 0; col < slots[row].length; col++){
                int random = (int) (Math.random()*100)+1;
                if(random <= 30){
                    slots[row][col] =  "bomb";
                } else if ( random <= 44){
                    slots[row][col] = "cherry";
                } else if ( random <= 58){
                    slots[row][col] = "watermelon";
                } else if( random <= 72){
                    slots[row][col] = "grape";
                } else if ( random <= 86){
                    slots[row][col] = "lemon";
                } else{
                    slots[row][col] = "diamond";
                }
            }
        }
    }

    //method that return int and takes a arraylist parameter
    public int calculatePoints(ArrayList<String> matches){
        int pointsEarned = 0;
        boolean bomb = false;
        //if matches arraylist is empty then return 0
        if(matches.size() < 1){
            return 0;
        }

        //cycles through matches arraylist 
        for(int x = 0; x < matches.size(); x++){
            //if the first three letter are row then continue
            if(matches.get(x).substring(0,3).equals("row")){
                //if last character is 1 then row 1 is a match
                if(matches.get(x).substring(3).equals("1")){
                    //if the row is a bomb match bomb variable is true
                    if(slots[0][0].equals("bomb")){
                        bomb = true;
                        //if match is diamond earn 2000 points
                    } else if(slots[0][0].equals("diamond")){
                        pointsEarned += 2000;
                        //if anything else earn 1000 points
                    } else {
                        pointsEarned += 1000;
                    }
                    //if last character is 2 then row 2 is a match
                }  else if(matches.get(x).substring(3).equals("2")){
                     //if the row is a bomb match bomb variable is true
                    if(slots[1][0].equals("bomb")){
                        bomb = true;
                        //if match is diamond earn 2000 points
                    } else if(slots[1][0].equals("diamond")){
                        pointsEarned += 2000;
                       //if anything else earn 1000 points 
                    } else {
                        pointsEarned += 1000;
                    }
                    //else it must be a match for row 3
                } else{
                  //if the row is a bomb match bomb variable is true
                  if(slots[2][0].equals("bomb")){
                        bomb = true;
                        //if match is diamond earn 2000 points
                    } else if(slots[2][0].equals("diamond")){
                        pointsEarned += 2000;
                        //if anything else earn 1000 points 
                    } else {
                        pointsEarned += 1000;
                    }  
                }
                //same operation happens if first three charcters are col
                //same logic as row
            } else if (matches.get(x).substring(0,3).equals("col")){
                 if(matches.get(x).substring(3).equals("1")){
                    if(slots[0][0].equals("bomb")){
                        bomb = true;
                    } else if(slots[0][0].equals("diamond")){
                        pointsEarned += 2000;
                    } else {
                        pointsEarned += 1000;
                    }
                } else if(matches.get(x).substring(3).equals("2")){
                    if(slots[0][1].equals("bomb")){
                        bomb = true;
                    } else if(slots[0][1].equals("diamond")){
                        pointsEarned += 2000;
                    } else {
                        pointsEarned += 1000;
                    }
                } else{
                  if(slots[0][2].equals("bomb")){
                        bomb = true;
                    } else if(slots[0][2].equals("diamond")){
                        pointsEarned += 2000;
                    } else {
                        pointsEarned += 1000;
                    }  
                }
                //else the first three letters must be dia 
                //used the same logic as row 
            } else {
               if(matches.get(x).substring(3).equals("1")){
                    if(slots[0][0].equals("bomb")){
                        bomb = true;
                    } else if(slots[0][0].equals("diamond")){
                        pointsEarned += 2000;
                    } else {
                        pointsEarned += 1000;
                    }
                } else if(matches.get(x).substring(3).equals("2")){
                    if(slots[0][2].equals("bomb")){
                        bomb = true;
                    } else if(slots[0][2].equals("diamond")){
                        pointsEarned += 2000;
                    } else {
                        pointsEarned += 1000;
                    }
                }     
            }
        }

        //if the bomb variable is true which only happens when you get a match of 3 bombs
        //you earn no points for the  entire slots run
        if(bomb){
            pointsEarned = 0;
        }
        //returns the points player earned in this slot run
        return pointsEarned;
    }
        
    
}
