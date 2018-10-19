/**
 * This program contains the game dynamics for Nim, including the pile size 
 * and the player references. 
 * @author: Daniel Garces
 * @date: 10/12/2018
 * @UNI: dg3008
 */
import java.util.Random;
import java.util.Scanner;

public class Game{
    
    //instance variables
    private int marbles; //corresponds to the size of the pile of marbles
    private Human humanPlayer; //the human player object
    private Computer computerPlayer; //the computer player object
    private Random generator; //a generator of random values
    private int playStyle; // the mode for the computer (dumb or smart)
    private int firstTurn; // contains a random number between 0 and 1
    private int legalMove; // contains the limit for a move
    private boolean done; // control variable
    private Scanner input; // receives user input if wrong initial input
    

    /**
    Initializes the instance variables using the given parameters
    @param difficulty refers to the mode in which the computer will play
    NOTE: this constructor assumes that difficulty is an integer value
    between 1 and 2.
    */
    public Game(int difficulty){
        playStyle = difficulty;
        generator =  new Random(); 
        marbles = 11+generator.nextInt(90); // random integer between 10 and 100
        computerPlayer = new Computer(playStyle);
        humanPlayer = new Human();
        firstTurn = generator.nextInt(2); // random integer between 0 and 1
        legalMove = marbles/2;
        done = false;
        input=new Scanner(System.in);
    }
    
    /**
    This method contains the game sequence for Nim, determining whether the 
    computer or the human starts 
    @param none
    */     
    public void play(){
        System.out.println("The pile has "+marbles+" marbles");
        if (firstTurn==0){
            humanStartsGame();
        }
        else{
            computerStartsGame();
        }
    }
    
    /**
    This method contains the game steps when the human starts the game. It
    checks whether or not the pile size reaches zero to end the game.
    @param none
    */ 
    private void humanStartsGame(){
        while(marbles>0){
            humanTurn();
            System.out.println("The pile has "+marbles+" marbles");
            if (marbles>0){
                computerTurn(marbles);
                System.out.println("The pile has "+marbles+" marbles");
                if(marbles<=0){
                    System.out.println("You win!");
                }
            }
            else{
                System.out.println("You Lose!");
            }
        }
    }
    
    /**
    This method contains the game steps when the computer starts the game. It
    checks whether or not the pile size reaches zero to end the game.
    @param none
    */ 
    private void computerStartsGame(){
        while(marbles>0){
            computerTurn(marbles);
            System.out.println("The pile has "+marbles+" marbles");
            if (marbles>0){
                humanTurn();
                System.out.println("The pile has "+marbles+" marbles");
                if(marbles<=0){
                    System.out.println("You Lose!");
                }
            }
            else{
                System.out.println("You Win!");
            }
        }
    }
    
    /**
    This method contains the movement command for the human using two methods
    of Game and one method of Human
    @param none
    */ 
    private void humanTurn(){
        humanPlayer.move();
        checkLegalMove();
        checkInput(humanPlayer.getChoice());
    }
    
    /**
    This method contains the movement command for the computer and updates the
    value for the number of marbles left in the pile.
    @param marblesLeft corresponds to the number of marbles left in the pile
    */ 
    private void computerTurn(int marblesLeft){
        computerPlayer.move(marblesLeft);
        marbles -= computerPlayer.getChoice();
    }
    
    /**
    This method checks whether or not the user input is within the established
    range (from 1 to number of marbles in the pile divided by two)
    @param userChoice refers to the initial user input
    */ 
    private void checkInput(int userChoice){
        int potentialMove = userChoice;
        done = false;
        while(!done){
            if (potentialMove<=legalMove && potentialMove>0){
                marbles -= potentialMove;
                done = true;
            }
            else{
                System.out.println("Wrong Number of marbles entered");
                System.out.println("Please enter a number between 1 and "+legalMove);
                potentialMove = input.nextInt();
            } 
        } 
    }
    
    /**
    This method checks whether or not the size of a legal move would be zero
    so that it can be adjusted to one, allowing the user to pick a number
    @param none
    */ 
    private void checkLegalMove(){
        legalMove = marbles/2;
        if(legalMove<1){
            legalMove=1;
        }
    }
}