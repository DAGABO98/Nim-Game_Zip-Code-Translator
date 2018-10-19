Name: Daniel Garces
UNI: dg3008
Assignment: PP3

===============================================================================
Nim Game
===============================================================================

To tackle this programming task I used top-down design, starting with the Game
class, where I implemented different helper methods to simplify the play() 
method. In the play method I included a check to verify the value of the random
integer that determines whether the human player or the computer player starts 
the game. Based on that If statement, one of two helper methods gets executed, 
humanStartsGame() or computerStartsGame(). Inside each of these two methods, I
included calls to the methods humanTurn() and computerTurn(), which include the
commands that are associated with the actual move of the computer or the prompts
for the human user. Inside humanTurn() I included two checks, checkInput() and
checkLegalMove(). checkInput() is responsible for determining whether the number
that the user provided as an input is a valid number of marbles to take;
if the user inputted a negative integer or an integer that is greater than half
of the pile size, the progream enters a while loop that prompts the user to 
enter the right value. This loop will run until the user enters a valid input. 
checkLegalMove() determines the upper limit for the number of marbles a player 
can take. I decided to divide my code into multiple helper methods to be able
to reuse the code in other parts of the project.

The Human class is relatively short because it just includes the promps for the
user to input an integer. All the checks to determine whether or not that input
is valid are included in the Game class, as the current value for the pile size 
is only contained within the Game class.

For the Computer class, I divided the code into two main methods stupidMove()
and smartMove() with their respective helper methods. For stupidMove() I used
the Class Random() to generate a random integer between 1 and half of the
pile size. I used this class because I saw it being used in the book. For 
SmartMove() I used a while loop to check which move will be legal and result 
in a number of marbles left that is equal to a power of two inus one. I included
to a helper method that produces a power of two based on  multiplication and a 
for loop. In the smartMove() method I included a do-while loop because it was 
more efficient to execute the code in the do block before checking the condition.

===============================================================================
Zipcode Translator
===============================================================================

To efficiently tackle this programming task I decided to use the Array class
in order to be able to easily manipulate arrays, in which I hardcoded the 
symbols for each of the digits in the barcodes.I learned how to use this class
using oracle tutorials and information in the java API. I decided to overload
the constructor for the Zipcode class, so that it could handle different types
of parameters when a new instance is created and either a barcode or numerical
zipcode is provided. For all the other methods, I used string manipulation and
references to the BARCODES array and its indexes to translate barcodes into 
numerical values for the zipcode.

The method getZIPcode() uses a while loop to determine if a barcode has the 
right length. It also contains a for loop that uses two helper methods
numericDigitFromBarcode(), which returns the integer value of a barcode digits,
and stringNumericDigit(), which converts the integer value into its string 
representation.

the method getBarcode() uses a for loop to geenrate each of the symbols for the
barcode using the integer value of the zipcode's digits as indexes for the 
BARCODES array. This method calls to three helper methods, sumOfDigits(),
which returns the sum of the digits in the zipcode; createDigit(), which
creates an integer based on a string representation of a number; and 
checkDigitCreator(), which generates the check digit based on the sum of the 
zipcode's digits.

I divided all my code into smaller helper methods so that it could be easily
read and understood. I reused two methods I used in the programming project
2, as these two methods helped me handle strings and turn them into integers,
and then obtain the sum of those integers.