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
	static boolean referenceString = false ; 
	static String table[][]; 
	static int columnTracker=0; 
	static String flush; 
	
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
					referenceString = true; 
					
					table = new String [userNum+3][refNumLen]; 
					for(int i = 0 ; i < userNum+3; i++) 
						for (int j = 0; j < refNumLen; j++){
						table[i][j] = " "; 
					}
					
					inputString(); 
				}else {
					System.out.println("The Reference String numbers must be between 0-9 and must be at least as long as " + userNum+ " but no longer than 20");
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
			if(referenceString == true) {
				optAlgorithm(); 
			}else {
				System.out.println("Please input a correct N value");
			}
			
			break;
			
		case "4":
			if(referenceString == true) {
				newAlgorithm(); 
			}else {
				System.out.println("Please input a correct N value");
			}
			
			break; 
			
		default:
			System.out.println("That is not a valid entry, enter 0 - 4 ");
		}
		
	}
	
	static void inputString() {
		Scanner scanner = new Scanner(System.in); 
		refString = new int[refNumLen]; 
		for(int i = 0; i < refString.length; i++) {
			do {
			System.out.println("Enter number " +(1+i));
			
			refString[i] = Integer.parseInt(scanner.nextLine()); 
			}while(refString[i] < 0 || refString[i] > 9);
		}
			 
		
			
		for(int i = 0; i < refString.length; i++) {
			table[0][i] = String.valueOf(refString[i]) ;
			}
		
	}
	
	static void optAlgorithm() {
		Scanner scan = new Scanner(System.in); 
		tableReset(); columnTracker = 0; 
		while(columnTracker< refNumLen) {
		optTable(); 
		add();
		optTable(); 
		
		nextCol();
		
		if(columnTracker < refNumLen) {
		columnTracker++;
		}
		optTable();
		String nothing=scan.nextLine(); 
		}
	}
	static void optTable() {
		
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.print("Reference string |");
		for(int i = 0; i < refNumLen; i++) {
			System.out.print(table[0][i] + " |");
		}
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------");
		for(int i = 0; i < userNum; i++) {
			System.out.print("Physical frame "+ i +" |");
		
			for(int j = 0; j < refNumLen; j++) {
				System.out.print(table[i+1][j]+" |");
			}System.out.println();
		}	
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.print("Page Faults      |");
		for(int i = 0; i < refNumLen; i++) {
			System.out.print(table[userNum+1][i]+" |");
		}
		System.out.println();
		System.out.print("Victim Pages     |");
		for(int i = 0; i < refNumLen; i++) {
			System.out.print(table[userNum+2][i]+" |");
		}
		System.out.println("\n\n");
	}
	
	static void add() {
		int i = 0; 
		if (alreadyPres()) {
			return; 
		}
		if (!isFull()) {
		for (;i < userNum; i++) {
			if (columnTracker > userNum) {break;}
			if (table[i+1][columnTracker] == " ") {
				table[i+1][columnTracker] = table[0][columnTracker];
				table[userNum+1][columnTracker] = "F"; 
				break; 
			}
			
		}
		}else {
				flush = optFind();
				table[userNum+2][columnTracker]= flush; 
				table[userNum+1][columnTracker] = "F"; 
				for (int i1 =0; i1 < userNum+1; i1++) {
					if (flush.equals(table[i1+1][columnTracker])) {
						table[i1+1][columnTracker] = table[0][columnTracker]; 
					}
				}
			}
		}	
	

	public static void nextCol() {
		for (int k = 0; k < userNum; k++ ) {//add next column to table data
			if (columnTracker+1 == refNumLen) {
				return; 
			}
			if (columnTracker < refNumLen) {
			table[k+1][columnTracker+1] = table[k+1][columnTracker]; 
			}
			}
			
	}
	
	static String optFind() {//storing string in array 
		int next = 0; 
		boolean foundTrue[] = new boolean[userNum];  
		String list[] = new String [userNum]; 
//		for (int i = 0; i < refNumLen-columnTracker; i++) {//changed to 0
//			foundTrue[i] = false; 
//		}
		
		for (int i = columnTracker; i< refNumLen; i++) {//column tracker so to know where to start
			for(int j = 1; j < userNum+1; j++) {//set j to 1 to offset the ref string row
				if (foundTrue[j-1] != false) {
					continue;
				}else if(foundTrue[j-1]== false && table[j][columnTracker].equals(table[0][i])   ) {
					list[next] = table[0][i]; 
					next++; 
					foundTrue[j-1] = true; 
				
				}
			}
			
		}
		for (int i = 0; i < userNum; i++) { //idea is if the string is not in the array it is not used and should be discarded next
			if(foundTrue[i] == false) {
				return table[i+1][columnTracker];
			
			} 
		
		}
		return list[userNum-1];//idea is return the last value added to the list as farthest out
			
		
		
	}
	static boolean isFull() {
		for(int i = 0; i < userNum; i++) 
			if (table[i+1][columnTracker] == " ")
				return false;
			
		return true; 
		
	}
	static boolean alreadyPres() {
		for(int i = 0; i < userNum +1; i++) {
			if (table[1+i][columnTracker].contentEquals(table[0][columnTracker]) ) {
				return true;
			}
		}return false; 
		
	}
	static void tableReset() {
		for(int i = 1 ; i < userNum+3; i++) 
			for (int j = 0; j < refNumLen; j++){
			table[i][j] = " "; 
		}
	}
	
	static void newAlgorithm() {
		Scanner scan = new Scanner(System.in); 
		tableReset(); columnTracker = 0; 
		while(columnTracker< refNumLen) {
		optTable(); 
		addNew();
		optTable(); 
		
		nextCol();
		
		if(columnTracker < refNumLen) {
		columnTracker++;
		}
		optTable();
		String nothing=scan.nextLine(); 
		
		
		}
	}
	static void addNew() {
		int i = 0; 
		if (alreadyPres()) {
			return; 
		}
		if (!isFull()) {
		for (;i < userNum; i++) {
			if (columnTracker > userNum) {break;}
			if (table[i+1][columnTracker] == " ") {
				table[i+1][columnTracker] = table[0][columnTracker];
				table[userNum+1][columnTracker] = "F"; 
				break; 
			}
			
		}
		}else {
				flush = newFind();
				table[userNum+2][columnTracker]= flush; 
				table[userNum+1][columnTracker] = "F"; 
				for (int i1 =0; i1 < userNum+1; i1++) {
					if (flush.equals(table[i1+1][columnTracker])) {
						table[i1+1][columnTracker] = table[0][columnTracker]; 
					}
				}
			}
		}	
	static String newFind() {//storing string in array 
		int next = 0; 
		boolean foundTrue[] = new boolean[userNum];  
		String list[] = new String [userNum]; 

		
		for (int i = columnTracker; i< refNumLen; i++) {//column tracker so to know where to start
			for(int j = 1; j < userNum+1; j++) {//set j to 1 to offset the ref string row
				if (foundTrue[j-1] != false) {
					continue;
				}else if(foundTrue[j-1]== false && table[j][columnTracker].equals(table[0][i])   ) {
					list[next] = table[0][i]; 
					next++; 
					foundTrue[j-1] = true; 
				
				}
			}
			
		}
		for (int i = 0; i < userNum; i++) { //idea is if the string is not in the array it is not used and should be discarded next
			if(foundTrue[i] == false) {
				return table[i+1][columnTracker];
			
			} 
		
		}
		return list[userNum-2];//idea is return the last value added to the list as farthest out
			
		
		
	}
}
