import java.util.Scanner;

/*
 * David Warren 
 * CMSC 412 Operating Systems
 * UMGC Jul 3 2023
 */

public class FinalProject {
	static String option;
	static String userInput; 
	
	FinalProject(){
		
	}
	
	 public static void main(String[] args) {
		do {
		 menu(); 
		 selection(option);
		}while (option != "0"); 
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
		int userNum; 
		
		switch(input) {
		
		case "0":
			option = "0"; 
			break; 
		case "1":
			System.out.println("Enter a positive integer between 2 and 8 ");
			userNum = Integer.parseInt(scan.nextLine()); 
			if (userNum >=2 && userNum <9) {
				System.out.println("Okay");
			}else {
				System.out.println("Not Okay");
			}
			break; 
			
		case "2":
			
			break; 
			
		case "3":
			
			break;
			
		case "4":
			
			break; 
			
		default:
			System.out.println("That is not a valid entry, enter 0 - 4 ");
		}
		
	}
	
	
	
}
