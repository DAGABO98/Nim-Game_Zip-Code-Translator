/**
 * This program converts a zip code into its graphical representation and 
 * viceversa. 
 * @author: Daniel Garces
 * @date: 10/12/2018
 */
import java.util.Arrays;
import java.util.Scanner;

public class Zipcode{
    
    //instance variables
    private String barCode;
    private int zipCode;
    Scanner input;
    private final String[] BARCODES = {"||:::",":::||","::|:|","::||:",":|::|",
                                      ":|:|:",":||::","|:::|","|::|:","|:|::"};
    
    //This constructor initiliazes the instance variables based on the parameter
    //@param numericCode the integer value of the zipcode
    public Zipcode(int numericCode){
        zipCode = numericCode;
        input = new Scanner(System.in);
    }
    
    //This constructor initiliazes the instance variables based on the parameter
    //@param graphicalCode the string representation of the barcode
    public Zipcode(String graphicalCode){
        barCode = graphicalCode;
        input = new Scanner(System.in);
    }
    
    //This method returns a numerical zipcode based on a string of bars and
    //colons
    //@param none
    //@return actualZipCode the integer value for the barcode
    public int getZIPcode(){
        String stringCode = ""; //empty string
        int sumOfNumDigits = 0;
        
        while(barCode.length()!=30){ //checks if input is right format
            System.out.println("Wrong input length, please enter a bar code with 30 symbols");
            barCode = input.nextLine();
        }
        for (int i = 0; i<=(barCode.length()/6); i++){
            // creates integer from string representation of each digit in the 
            // barcode, using the array indexes references
            int numDigit = numericDigitFromBarcode(i*5,(i+1)*5, barCode);
            sumOfNumDigits = sumOfNumDigits + numDigit;
            String stringDigit = stringNumericDigit(numDigit);
            stringCode = stringCode+stringDigit;           
        }
        int actualZipCode = checkMismatchOfDigits(sumOfNumDigits, stringCode);
        return actualZipCode;       
    }
    
    //This method returns a numerical zipcode based on a string of bars and
    //colons
    //@param none
    //@return actualZipCode the integer value for the barcode
    public String getBarcode(){
        String barCodeResult = "";
        String stringZipCode = Integer.toString(zipCode);
        int totalSum = sumOfDigits(0,stringZipCode.length(),stringZipCode);
        System.out.println(totalSum);
        int checkDigit = checkDigitCreator(totalSum);
        
        for(int i = 0; i<stringZipCode.length(); i++){
            //creates barcode from integer representation using array indexes
            int index = createDigit(i, stringZipCode);
            String barCodeSection = BARCODES[index];
            barCodeResult=barCodeResult+barCodeSection;
        }
        String barCodeCheckDigit = BARCODES[checkDigit];
        barCodeResult=barCodeResult+barCodeCheckDigit;
        return barCodeResult;
    }
    
    //This method turns a character in a given location of a stgring into a
    //numerical digit
    //@param indexLocation the index in which the character is located
    //@param stringZipCode the string of the ZipCode to be considered
    private int createDigit(int indexLocation, String stringZipCode){
        char characterForDigit = stringZipCode.charAt(indexLocation);
        int digitAtIndex = Character.getNumericValue(characterForDigit);
        
        return digitAtIndex;
    }
    
    //This method finds the sum of a section of a string, based on the 
    //starting point and end point
    //@param startPoint index of first digit to be considered for the sum
    //@param endPoint index+1 of the last digit to be considered for the sum
    //@param stringZipCode the string of the ZipCode to be considered
    private int sumOfDigits(int startPoint, int endPoint, String stringZipCode){
        int sumDigits = 0;
        
        for(int i=startPoint; i<endPoint; i++){//gets the sum of all digits
            char numericalCharacter = stringZipCode.charAt(i);
            if ((numericalCharacter!='-')&&(numericalCharacter!=' ')){
                int digit = Character.getNumericValue(numericalCharacter);
                sumDigits = sumDigits + digit;
            }
        }
        
        return sumDigits;
    }
    
    //This method creates the check digit based on the total sum of the digits
    //@param sumOfCode the sum of the zipcode digits
    private int checkDigitCreator(int sumOfCode){
        int potentialDigit = (sumOfCode%10);
        int digit = 10-potentialDigit;
        if (digit == 10){
            digit = 0;
        }
        return digit;
            
    }
    
    //This method translates a symbol from the barcode into an integer digit
    //for the zipcode
    //@param startPoint index of first digit to be considered for the sum
    //@param endPoint index+1 of the last digit to be considered for the sum
    //@param stringZipCode the string of the barcode to be considered
    private int numericDigitFromBarcode(int startPoint, int endPoint, String stringZipCode){
        String barcodeDigit = stringZipCode.substring(startPoint, endPoint);
        int numericDigit = Arrays.binarySearch(BARCODES, barcodeDigit);
        if (numericDigit == -11){
            numericDigit = 0;
        }
        return numericDigit;
    }
    
    //This method turns an integer into its string representation
    //@param numericDigit the integer valuje of the digit
    private String stringNumericDigit(int numericDigit){
        String stringNumDigit = Integer.toString(numericDigit);
        return stringNumDigit;
    }
    
    //This method checks whether or not the barcode contains the right check
    //digit verifying if the data is corrupted
    //@param sumOfNumDigits the sum of the integer digits in the zipcode
    //@param stringCode the string representation of the numbers for the
    //zipcode
    private int checkMismatchOfDigits(int sumOfNumDigits , String stringCode){
        if(checkDigitCreator(sumOfNumDigits)==0){
            String stringActualZipCode = 
                stringCode.substring(0,stringCode.length()-1);
            int actualZipCode = Integer.parseInt(stringActualZipCode);
            return actualZipCode;
        }
        else{
            System.out.println("Corrupted barcode, there is mismatch in the " 
                               +"digits. The sum of all digits, including the"
                               + " check digit, is " + sumOfNumDigits);
            return 0;          
       }
        
    }
}
