import java.util.Scanner;

/*
 * David Warren 
 * CMSC 412 Operating Systems
 * UMGC Jul 3 2023
 */

public class FinalProject {
	static String option;
	static String userInput; 
	static boolean hasNBeenSelected = false; 
	static int userNum; 
	static int refNumLen;
	static boolean valid = false; 
	static int refString[]; 
	FinalProject(){
		do {
		 menu(); 
		 selection(option);
		}while (option != "0");
	}
	
	 public static void main(String[] args) {
		 new FinalProject(); 
	}
	
	static String menu() {
		Scanner scan = new Scanner(System.in); 
		System.out.println("0 - Exit");
		System.out.println("1 - Input N");
		System.out.println("2 - Input the reference string");
		System.out.println("3 - Simulate the OPT algorithm");
		System.out.println("4 - Simulate the NEW algorithm");
		System.out.println("Select option: ");
		
		//collect the selection 
		option = scan.nextLine(); 
		
		return option; 
	}
	
	static void selection (String input) {
		Scanner scan = new Scanner(System.in);
		Scanner scanInt = new Scanner(System.in); 
		
		switch(input) {
		
		case "0":
			option = "0"; 
			break; 
		
		case "1":
			System.out.println("Enter a positive integer between 2 and 8 ");
			try {
			userNum = Integer.parseInt(scan.nextLine()); 
			if (userNum >=2 && userNum <9) {
				hasNBeenSelected = true;
			} else {
				System.out.println("Wrong value entered");
			}
			}catch (NumberFormatException e){
				System.out.println("Please enter a valid number");
			}	
					
			
			break; 
			
		case "2":
			valid = false; 
			if(hasNBeenSelected == true) {
			System.out.println("The Reference String numbers must be between 0-9 and must be at least as long as " + userNum+ " but no longer than 20");
			System.out.println("How many numbers do you want to enter? ");
			try {
			while(!valid) {
				refNumLen = Integer.parseInt(scanInt.nextLine()); 
				if(refNumLen >= userNum && refNumLen < 21) {
					valid = true;
					inputString(); 
				}else {
					System.out.println("The Reference String numbers must be between 0-9 and must be at least as long as \" + userNum+ \" but no longer than 20");
					System.out.println("How many numbers do you want to enter? ");
					 
				}
			}
			
			}
			
			catch(NumberFormatException e){
				System.out.println("Please enter a valid number");
			}
			

			}else {
				System.out.println("User must enter an input N");
			
				
			}
			
			break; 
			
		case "3":
			
			break;
			
		case "4":
			
			break; 
			
		default:
			System.out.println("That is not a valid entry, enter 0 - 4 ");
		}
		
	}
	
	static void inputString() {
		Scanner scanner = new Scanner(System.in); 
		refString = new int[refNumLen]; 
		for(int i = 0; i < refString.length; i++) {
			System.out.println("Enter number " +(1+i));
			refString[i] = Integer.parseInt(scanner.nextLine()); 
		}
		for(int i = 0; i < refString.length; i++) {
			System.out.println(refString[i]);
		}
	}
	
	
}
