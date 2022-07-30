import java.util.Scanner; 
import java.io.*;
import java.util.Random; 

/** 
 * This program simulates the game, "Chutes and Ladders." The board has 
 * squares which are numbered from 1 to 100 and players have counters which
 * start on the theoretical square 0; The player takes turns at spinning at
 * spinner with the numbers 1 to 6 on it, and each moves his or her counter
 * forward the number of squares corresponding to the number on the spinner;
 * The first person to reach square 100 is the winner.  
 *
 * @author Emily Lee
 * @version 1.0 
 **/ 

public class Chutes 
{
	/** 
 		This function prints the welcome/goodbye message, reads numbers from 
		file, gets number of players and names, plays the game until one player
		wine, and repeats the program if player wishes.  	  
		@param args Array containing values
		Includes throws IOException for I/O   
   */ 
	public static void main(String[] args) throws IOException 
	{
			// Declare variables
			final int BOARD_SIZE = 101;		// Array size of 101 
			int[] boardArr; 						// Array containing squares on board 
			String[] namesArr; 					// Array containing names of players 
			int[] playerLocArr; 					// Array containing player locations
			int numPlayers; 						// player, number of players
			String playerName; 					// Name of player 
			String filepath = "/home/fac/ohsh/submit/21rq5001/files/p3input.txt";
			int numSpinned;						// random number spinned  	
	 		int playerLoc; 						// tracks player location 
			char repeat; 							// To hold y or n 
			String input; 
						 								// To hold user input for repeat

			// Create scanner object 
			Scanner keyboard = new Scanner(System.in); 

			// Welcome message
			welcome();
			System.out.print("\n------------------------------\n"); 
	
			// Create a multidimensional array to hold numbers read from file 
			boardArr = new int[BOARD_SIZE]; 
		do
		{
			// Open and Read file until EOF or until array is filled
			fillArray(filepath, boardArr); 	

			// Ask how many players, between 2-6
			System.out.println(); 
			numPlayers = getNumPlayers(keyboard); 
		
			// Create an array containing names of players based on number of 
			// players
			namesArr = new String[numPlayers]; 

			// Obtain names of players and store in names array 
			getNames(namesArr, numPlayers, keyboard); 
			System.out.print("\n------------------------------\n\n"); 
	
			// Create an array to store player locations
			playerLocArr = new int[numPlayers]; 
			playerLoc = 0; 

			// Players take turns spinning and see who wins! 
			do
			{
				for (int i = 0; playerLoc != 100 && i < numPlayers; i++)
				{
					System.out.println();
					System.out.println(namesArr[i] + ", it's your turn. You are" +
										 " currently at space " + playerLocArr[i] + 
										 ".");
					
					// Spin and display number spinned 
					numSpinned = spin(); 	
					showSpin(numSpinned);
			
					// Get player location using getPlayerLoc function	     
					playerLoc = getPlayerLoc(playerLocArr[i], boardArr, numSpinned); 
					playerLocArr[i] = playerLoc; 

					if (playerLocArr[i] == 100)
					{
						System.out.println();
						System.out.print(namesArr[i] + ", you have won the game!\n"); 
					}
				} 
			}
			while (playerLoc < 100); 	
				
			// Ask player if they would like to play again. Repeat if input is yes.  
			System.out.print("\nDo you want to play again? "); 
			input = keyboard.next();
			repeat = input.charAt(0);
			System.out.print("\n------------------------------\n");  
		}
		while (repeat == 'y' | repeat == 'Y');

		// Goodbye message
		goodbye(); 

		// Close scanner
		keyboard.close(); 
	}

		/**
 	 		 This function keeps track of the location of players; determines 
		    whether player landes on chutes or ladders; if player
			 lands on ladders, they go up, if player lands on chutes, they go
			 back down  
		 	 @param playerLoc, boardArr, numSpinned 
			 @return playerLoc updated player location 
		*/
		public static int getPlayerLoc(int playerLocArr, int[] boardArr,
												 int numSpinned)   	
		{
			// If the player location is less than zero proceed to chutes, ladders, 
			// or going up by number spinned 
			if ((playerLocArr + numSpinned) < 100)
			{ 
				playerLocArr += numSpinned; 
			
				// If the number of moves is greater than zero, the player has 
				// landed on ladders and will go up 	
				if (boardArr[playerLocArr] > 0) 
			 	{
					System.out.print("Congrats, that is a ladder! You get to " + 
						    			    "climb " + boardArr[playerLocArr] + 
                                  " spaces.\n");

					// Player loccation is updated on the board 
					playerLocArr += boardArr[playerLocArr]; 
				}	

				// If the number of moves on board is less than zero, the player
				// has landed on chutes and will go back 
				else if (boardArr[playerLocArr] < 0) 
				{
					System.out.print("Sorry, that is a chute! You are sent " +
										  "back " + (-boardArr[playerLocArr]) +
										  " spaces.\n");
					playerLocArr += boardArr[playerLocArr];
				}
			
				// If the number of moves on the board is zero, then the player
				// moves up spinned number  
				else 
				{
					playerLocArr += boardArr[playerLocArr]; 
				}
			System.out.print("You are now at space " + playerLocArr + ".\n"); 
		
			}
			// If the number of moves on the board is greater than 100, display
			// message saying they cannot pass 100  
			else if ((playerLocArr + numSpinned) > 100) 
				System.out.println("Sorry, no player can go over 100."); 
		
			// If the number reaches 100, the player moves up spinned number   
			else 
				playerLocArr += boardArr[playerLocArr]; 
				 			
			// Return updated player location to main function 
			return playerLocArr; 			 
		}				
	
		/** 
			This function prints a welcome message
		*/ 
		public static void welcome() 
		{
			System.out.print("\nWelcome to the Chutes & Ladders! You must land" + 
								  " on 100 (without going past) to win!\n" + 
							     "You will play against the computer.\n"); 		
		}

		/** 
			This function opens a file, reads numbers from file, and fills an 
			array 
			@param arr Array of numbers 
			Includes throws IOException for I/O
		*/ 
		public static void fillArray(String filepath, int[] boardArr) 
		throws IOException 
		{
 			// Declare variables 
		 	String input;									// To hold input 
			int location;		 							// first number on line
			int move; 										// second number on line 
			int index = 0; 								// index 										  
			File file = new File(filepath); 			// Open file 			
			Scanner inputFile = new Scanner(file); 

			// Read file until EOF and fill arrary
			while (inputFile.hasNext())
			{
				// Read the next number from file  
			 	location = inputFile.nextInt();
				move = inputFile.nextInt();  
						
				// Add numbers to array  	
				index = location;  
				boardArr[index] = move;  
			}	
			
			// Close file
			inputFile.close(); 		
		}   

		/** 
			This function asks for number of players and will only take in 
			a number that is valid; 2-6 players 
			@param keyboard Input from user 
			@return numPlayer Number of players, between 2-6 
		*/ 
		public static int getNumPlayers(Scanner keyboard) 
		{
			// Declare variables
			final int MIN_PLAYERS = 2;			// Game's minimum number of players
			final int MAX_PLAYERS = 6;			// Game's maximum number of players 
			int numPlayers;  						// number of players 

			// Prompt for number of players and validate input (2-6)  
			do
			{
				System.out.print("How many players will play (between 2-6)? ");
				numPlayers = keyboard.nextInt();
			}
			while (numPlayers < MIN_PLAYERS || numPlayers > MAX_PLAYERS);	
					
			return numPlayers; 
		}

 		/**
			This function obtains names of players using a for loop 
			@param namesArr Array containing names, number of players, and 
			keyboard    
		*/ 
		public static void getNames(String[] namesArr, int numPlayers,
											 Scanner keyboard) 
		{
			// Declare variables 
			String name;					// To hold user input; name of player 

			// Prompt the user for names of players 
			for (int i = 0; i <= numPlayers - 1; i++) 	
			{
				System.out.print("Enter the player " + (i + 1) +"\'s name: "); 
				name = keyboard.next(); 
				namesArr[i] = name; 
			}
		}

		/**
 			This function gets a random number spinned
			@return randomNum Random number spinned   
		*/ 
		public static int spin() 
		{
			// Declare variables 
			final int MAX_SPIN = 6; 	// Spinner has up to 6 
			int numSpinned; 				// Random number from spin 
			
			Random randomNumbers = new Random();
			numSpinned = randomNumbers.nextInt(MAX_SPIN) + 1; 
			
			return numSpinned; 
		}	
		
		/** 
			This function shows the number spun
			@param randomNum Number between from spin 
		*/ 
		public static void showSpin(int numSpinned)  
		{
			System.out.print("The spin was: " + numSpinned);   		
			System.out.println(); 
		}

		/** 
			This function prints a goodbye message 
		*/ 
		public static void goodbye() 
		{
			System.out.print("\nGoodbye, and thanks for playing Chutes" +
							     " & Ladders!\n\n");  		
		}
}
