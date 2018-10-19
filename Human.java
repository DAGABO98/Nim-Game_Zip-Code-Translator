/**
 * This program contains the movement dynamics for the human player 
 * @author: Daniel Garces
 * @date: 10/12/2018
 * @UNI: dg3008
 */ 
import java.util.Scanner;


public class Human{
    //instance variables
    private int choice;
    private Scanner input;
    
    /**
    Initializes the instance variables
    @param none
    */
    public Human(){
        input=new Scanner(System.in);
        choice = -1;
    }
    
    /**
    this method prompts the user for an integer to update choice
    @param none
    Note: it assumes that the user will provide an integer
    */
    public void move(){
        System.out.println("How many marbles do you want to take? Please"+ 
                           " enter an integer number");
        choice = input.nextInt();
    }
    
    /**
    this method returns the value of choice as the movement decision
    @param none
    */
    public int getChoice(){
        return choice;
    }
}