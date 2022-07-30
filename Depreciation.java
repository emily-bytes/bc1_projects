import java.util.Scanner; 

/**
 * This program displays the depreciated value of office equipment at
 * the accounting firm of Slime, Weasel, and Swindle
 *
 * @author Emily Lee
 * @version 1.0  
 */ 
public class Depreciation 
{
	/** 
    	This main function welcomes the user, reads in the description of the
		item, the original value, and annual depreciation of the item; 
		displays the depreciation report, and displays a goodbye message 
		@param args   
	*/ 
	public static void main(String[] args)
	{
	
	// Declare variables
	String equipment; 			// To hold equipment purchased 
	boolean answer;  			// To hold user answer: y or n 						
	double originalValue;			// To hold original value of equipment 
	double annualDepreciation; 		// To hold annual depciation cost 
 	int year;		 		// To hold year 		 
	char yn; 				// To hold (y)es or (n)o 

	// Create object scanner
	Scanner keyboard = new Scanner(System.in);

	// Prints the welcome message from welcome function
	welcome(); 

	do
	{
		// Get equipment from getEquipment function 
		equipment = getEquipment(keyboard);                       
	
		// Get original value from getOriginalValue function
		originalValue = getOriginalValue(keyboard);  
	
		// Get annual depreciation from getDepreciation function
		annualDepreciation = getAnnualDepreciation(keyboard); 
		System.out.println();

		// Displays the equipment, original value, and annual depreciation 
		System.out.println("Equipment description: " + equipment);	
		System.out.println("Beginning value: " + originalValue);
		System.out.println("Depreciation: " + annualDepreciation); 
		System.out.println(); 
      
		// Displays the heading for depreciation report; including year, annual
		// depreciation, end-of-year value, and accumulated depreciation
		System.out.println("Year" + "\t" + "Depreciation" + "\t" + 
						 	 "End-of-Year Value\t" + "Accumulated Depreciation");     	 
	
		// Display depreciation report
		for (year = 1; year * annualDepreciation < originalValue; year++)
		{
			System.out.printf(" " + (year) + "\t$" + "%,6.2f" + "\t\t$" + 
				          "%,6.2f" + "\t\t$" + "%,6.2f", annualDepreciation,
					 (originalValue - (year * annualDepreciation)),
					 (year * annualDepreciation)); 	
			System.out.println(); 
		}	

		if (year * annualDepreciation > originalValue) 
		{
			System.out.printf(" " + (year) + "\t$" + "%,6.2f" + "\t\t$" + 
					  "  0.00" + "\t\t$" + "%,6.2f",
					 (annualDepreciation - ((year * annualDepreciation)
					  - originalValue)), originalValue); 
			System.out.println(); 
	 	}
	
		// Does the user want to depreciate another item? Get user input: y or n
		// Get input from the getYN function   
		yn = getYN(keyboard); 

	// Get boolean variable from getAnswer method  	 
	}
	while (getAnswer(yn)); 
		
	// Prints the goodbye message from goodbye function 
	goodbye();
	
	// Close scanner
	keyboard.close(); 

}
	/** 
 		This function prints a welcome message
	*/
	public static void welcome()
	{
		System.out.print("\nWelcome to the asset depreciation reporting system, "
			         + "\nbrought to you by Slime, Weasel, and Swindle. This "
			         + "\nprogram tracks the annual depreciation of your " 
				 + "\nequipment until it is fully depreciated.");
		System.out.println();  
	}	

   /**
 		This function prompts the user to enter a description of the item
		@param Scanner keyboard
		@return equipment Type of equipment purchased  
	*/ 
	public static String getEquipment(Scanner keyboard)
	{
		// Declare variables 
		String equipment; 			// To hold description of property
		
		// Get description of equipment 
		System.out.print("\nEnter a description of the property: ");
		equipment = keyboard.next();
	
		// return equipment type to main function
		return equipment;

	}
	/**
 		This function prompts the user to enter original price of item 
 		@param Scanner keyboard
		@return originalValue Price of item at time of purchase 
	*/ 
	public static double getOriginalValue(Scanner keyboard)
	{
		// Declare variables 
		double originalValue; 			// To hold description of property
		
		// Get original purchase price of equipment 
		System.out.print("What is the original value of the equipment? $" );
		originalValue = keyboard.nextDouble();
			
		// return values to main function
		return originalValue; 

	}
	/**
 		This function prompts the user for annual depreciation 
		@param Scanner keyboard
		@return annualDepreciation Yearly depreciation cost 
	*/ 
	public static double getAnnualDepreciation(Scanner keyboard) 
	{
		// Declare variables 
		double annualDepreciation; 		// To hold annual depreciation amount
		
		// Get original yearly depreciation cost 
		System.out.print("What is the annual depreciation? $");
		annualDepreciation = keyboard.nextDouble();

		// return annual depreciation to main function
		return annualDepreciation; 
	}
	
	/** 
 		This function prompts user enter yes or no for whether they would
		like to depreciate another item 
		@param keyboard 
		@return yn yes or no  
	*/
	public static char getYN(Scanner keyboard)
	{
		// Declare variables 
		String input; 	// To hold answer
		char yn; 	// To hold y or n   
	
		// Prompts user for 'y' or 'n' for repeating program 
		System.out.print("\nDo you want to depreciate another item (y/n)? ");
		input = keyboard.next();		// Read line
		yn = input.charAt(0);			// Get the first character  
	
		// Return (y)es or (n)o  
		return yn; 
	}

	/**
 		This function tests a boolean argument; if y then true and if n then 
		false 
		@param yn yes or no 
		@ return answer true or false  
	*/  
	public static boolean getAnswer(char yn) 
	{
		boolean answer;			// To hold true or false  

		if (yn == 'y' || yn == 'Y') 
			answer = true; 
		else
			answer = false;
		
		// Return answer  
		return answer; 
	}

	/** 
 		This function prints a goodbye message
	*/ 
	public static void goodbye()
	{
		System.out.print("\nGoodbye and thanks for using Depreciation!!\n\n"); 
	}
}
