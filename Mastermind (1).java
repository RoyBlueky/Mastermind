import java.util.*;

public class Mastermind {
	
	//will randomly make the code
	static void makeCode(char[] ans){
		int num;
		char color;
	
		for(int i = 0; i < ans.length; i++){
			num = (int)(Math.random()*6);
			if(num == 0)
				color = 'R';
			else if(num == 1)
				color = 'G';
			else if(num == 2)
				color = 'B';
			else if(num == 3)
				color = 'Y';
			else if(num == 4)
				color = 'W';
			else 
				color = 'P';
			
			ans[i] = color;
		}
			
	}
	//used to print the board with all the previous guesses
	static void print(String[] a, int[][] b, int row){
		System.out.println("Guess   Code    Correct   Position");
		for(int i = 1; i <= row; i++){
			//prints nicer if last number
			if(i == 10){
				System.out.print("  " + i + "    " + a[i-1]);
			}
			else{
				System.out.print("  " + i + "     " + a[i-1]);
			}
			//System.out.print("  " + i + "     " + a[i-1]);
			for(int j = 0;j < b[i-1].length; j++){
				System.out.print("       " + b[i-1][j] + "  " );			
			}
				
			System.out.println();
		}
		System.out.println();		
	}
	
	static void print(char[] a){
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}
	static void reset(boolean[] a){
		for(int i = 0 ; i < a.length; i++)
			a[i] = false;
	}
	
	//checks to see if its a valid response.  Will look at length
	//and then look at each value to see if it matches the color
	static boolean valid(String a){
		a = a.toUpperCase();
		boolean ans = true;
		if(a.length() != 4){
			System.out.println("Sorry, the length of the code is wrong.");		
			ans = false;
		}
		for(int i = 0; i < a.length(); i++){
			if(a.charAt(i) != 'R' && a.charAt(i) != 'G' && a.charAt(i) != 'B' && a.charAt(i) != 'Y' && a.charAt(i) != 'W' && a.charAt(i) != 'P'){
				System.out.println("Sorry, there is no: " + a.charAt(i));
				ans = false;				
			}
		}		
		return ans;
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String code; //code that is given from the player
		
		String[] board = new String[10];
		int[][] results = new int[10][2];

		
		int row = 1; //keeps track of the row
		
		int count;	//keeps track of the number of guesses

		//Makes the random code
		char[] ans = new char[4];		
		makeCode(ans);
		print(ans);
		
		boolean[] lookedAns = new boolean[4];
		boolean[] lookedCode = new boolean[4];
		
		System.out.println("Welcome to Mastermind");
		
		System.out.println("Would you like instructions? (y/n)");
		String in = sc.next();
		if(in.equals("y")){
			System.out.println("You will have 10 Chances to guess the random code generated");
			System.out.println("by the computer!");
			System.out.println("R = red, G = green, B = blue, Y = yellow, W = white and P = pink");
			System.out.println();
		}
		while(true){
			count = 0;
			for(int i = 0; i < 10; i++){
				reset(lookedCode);
				reset(lookedAns);
				
				//Gets the code from the user and checks it
				while(true){
					System.out.println("Please enter your code: ");
					code = sc.next();
					code = code.toUpperCase();	//makes the code upper case
					if(valid(code))
						break;
				}
				
				board[row -1] = code;
				count++;
				
				
				int count1 = 0;	//keeps track of how many correct in correct position
				int count2 = 0; //keeps track of how many color correct in wrong position
				
				for(int j = 0; j < code.length(); j++){
					if(code.charAt(j) == ans[j]){
						count1++;
						lookedCode[j] = true;
						lookedAns[j] = true;					
					}					
				}
				
				for(int j = 0; j < code.length(); j++){
					for(int k = 0; k < code.length(); k++){
						if(ans[j] == code.charAt(k) && lookedAns[j] == false && lookedCode[k] == false){
							count2++;
							lookedAns[j] = true;
							lookedCode[k] = false;
						}
					}
				}
				results[row -1][0] = count1;
				results[row -1][1] = count2;
				
				if(count1 == 4){
					print(board, results, row );
					System.out.println("Congratulations! You got all 4 correct!");
					System.out.println("It took you " + count+ " guesses!");
					break;
				}
				else{
					print(board, results, row );
					row++;
				}
					
			}
			if(count == 10){
				System.out.println("Sorry you were not able to break the code");
			}
			System.out.println("Would you like to play again? (y/n)");
			String play = sc.next();
			if(play.equals("n")){
				System.out.println("Thank you for playing!  Goodbye.");
				break;
			}
			else{
				//resets to play again
				makeCode(ans);
				row =1;
			}
				
		
		}
		
	} //ends main

}
