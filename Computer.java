/**
 * This program contains the movement dynamics for the computer player 
 * @author: Daniel Garces
 * @date: 10/12/2018
 * @UNI: dg3008
 */ 
import java.util.Random;

public class Computer{
    
    //instance variables
    private int mode;
    private int choice;
    private Random generator;
    private final int  MAXIMUMEXPONENT=6;
    
    /**
    This constructor initializes the instance variables using the given 
    parameters
    @param m refers to the difficulty level chosen by the user
    */
    public Computer(int m){
        mode = m;
        choice = -1;
        generator = new Random();
    }
    
    /**
    This method updates choice based on the mode
    @param marblesLeft the size of the pile of marbles
    */
    public void move(int marblesLeft){
        if(marblesLeft>1){
            if (mode==1){
                System.out.println("The computer is playing in smart mode.");
                choice = smartMove(marblesLeft);
            }
            else{
                System.out.println("The computer is playing in stupid mode.");
                choice = stupidMove(marblesLeft);
            }
        }
        else{
            choice =1;
        }
    }
    
    /**
    this method returns the value of choice as the movement decision
    @param none
    */
    public int getChoice(){
        return choice;
    }
    
    /**
    This method checks which move would result in a pile size of a power of two
    minus one, and then returns that potential move. If the pile is already a 
    power of two minus one, then it chooses a random number between 1 andthe 
    size of the pile divided by two.
    @param pileSize the size of the pile of marbles
    */
    private int smartMove(int pileSize){
        int potentialMove;
        int legalMove = pileSize/2;
        int tracker = MAXIMUMEXPONENT;
        int marblesRemaining = powerOfTwoMinusOne(tracker);
        do{
            potentialMove = pileSize-marblesRemaining;
            tracker-=1;
            marblesRemaining = powerOfTwoMinusOne(tracker);
        }
        while(pileSize-marblesRemaining <= legalMove 
              && pileSize-marblesRemaining!=0);
        if(pileSize-marblesRemaining==0){
            potentialMove = stupidMove(pileSize);
        }
        return potentialMove;
    }
    
    /**
    This method uses a for loop to create a power of two
    @param exponent it is the exponent used for the power of two
    */
    private int powerOfTwoMinusOne(int exponent){
        int powerOfTwo = 1;
        for(int i=1;i<=exponent;i++){
            powerOfTwo = powerOfTwo*2;
        }
        return powerOfTwo-1;
    }
     
    /**
    This method chooses a random number between 1 and half of the pile size
    @param pileSize the size of the pile of marbles
    */
    private int stupidMove(int pileSize){
        int legalMove = pileSize/2;
        int actualMove = generator.nextInt(legalMove)+1;
        return actualMove;
    }
    
}
