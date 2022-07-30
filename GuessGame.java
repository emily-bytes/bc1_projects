import java.util.Scanner;   // Needed to open scanner 
import java.util.Random;	 // Needed to create random number 

/**
 * The GuessGame Program plays a guessing game. The Program will choose a 
 * random number between 1-100 and gives the user a chance to guess the number.
 * User will be given an unlimited number of guesses and on each guess, they
 * will be told whether their guess is too high, too low, or correct. User
 * will have the chance to repeat the game as many times as they wish.
 * */ 

public class GuessGame
{
	/** 
 	 * Prompts the user to guess a number generated by random. The result 
 	 * is printed to standard output.  
 	 *
 	 * */ 
	public static void main(String[] args)
	{
		// Declare variables 
		int guessNumber; 		// To hold a number between 1-100
		int randomNumber; 		// To hold a random number   
		char repeat; 			// To hold 'y' or 'n'
		String input; 			// To hold input(y or n)  
	
		// Welcome message
		System.out.println("\nThe GuessGame program plays a guessing game. ");
		System.out.println("The program will choose a \"random\" number between");
		System.out.println("1-100, and gives you a chance to guess the number.");
		System.out.println("You will be given an unlimited number of guesses ");
		System.out.println("and on each guess, you will be told whether your ");
		System.out.println("guess is too high, too low, or correct. You will ");
		System.out.println("have the chance to repeat the game as many times ");
		System.out.println("as you wish."); 
		System.out.println();
		System.out.println("Let's begin.");
		System.out.println();

		// Create scanner object
		Scanner keyboard = new Scanner(System.in); 

		// Create a random class object 
		Random randomNumbers = new Random(); 

		// Get one random number 
		do 
		{
			randomNumber = randomNumbers.nextInt(100)+1;
					
			// Prompt user to guess a number between 1-100
		 	do 
			{
				System.out.print("Guess a number between 1-100: ");
				guessNumber = keyboard.nextInt();
				
				// Determine and display whether the player's guess is too high,
				// too low, or correct 
				if (guessNumber > randomNumber)
					System.out.print("Your guess is too high. Try again.\n");
				else if (guessNumber < randomNumber) 
					System.out.print("Your guess is too low. Try again.\n");
				else 
			 		System.out.print("Your guess is correct!\n"); 
			}
			while (guessNumber != randomNumber);  
		
		// If the player's number is correct, ask the player if they 
		// would like to guess another number 
		System.out.print("\nWould you like to guess another number (y/n)? "); 
		input = keyboard.next();			// Read a line 
		repeat = input.charAt(0);			// Get the first character 
		System.out.println();

		}
		while (repeat == 'Y'|| repeat == 'y');   

	 	// Goodbye message 
	 	if (repeat == 'N'|| repeat == 'n') 
			System.out.print("Thanks for playing the GuessGame!\n\n");
	}
}