public class Hangman {
	
	private static char[] word;
	private static char[] miss;
	private static char[] underscore;

	// constructor with argument, i.e. the solution is the argument
	public Hangman(String str) {
		
		this.word = str.toCharArray();

		this.miss = new char[7];
		
		this.underscore = new char[str.length()];

		for (int i = 0; i < str.length(); i ++) {
			this.underscore[i] = '_';
		} 
	}

	// start of get methods
	public static char[] getWord() {
		return word;
	}
					
	public static char[] getMiss() {
		return miss;
	}

	public static char[] getUnderscore() {
		return underscore;
	}

	// end of get methods
	
	// start of data validation methods
	// check if a char has any matches with word[]
	public static void checkSolution(char c) {
		boolean match = false;

		for (int i = 0; i < word.length; i ++) {
			if (word[i] == c) {
				underscore[i] = c;
				match = true;
			}
		} 

		if (!match) {
			for (int i = 0; i < miss.length; i ++) {
				if (miss[i] == 0) {
					miss[i] = c;
					System.out.println(">> you guessed wrong ):");
					// only first non-empty elt is modified
					break;
				}
			}
		}
	}
	
	// check if char has not already been guessed
	public static boolean checkDuplicate(char c) {
		for (char x : miss) {
			if (c == x) {
				System.out.println(">> character already guessed (not part of solution)");
				return true;
			} 
		}
		
		for (char x : underscore) {
			if (c == x) {
				System.out.println(">> character already guessed (part of solution)");
				return true;
			}
		}
		return false;
	}

	// checks user input string is a valid character. then calls for further check methods
	public static void checkInput(String str) {
		if (!str.matches("[a-z]{1}")) {
			System.out.println(">> invalid input, must be a lowercase alphabetical character");
		} else {

				if (!checkDuplicate(str.charAt(0))) {
					checkSolution(str.charAt(0));
				}
		}
	}
	//end of data validation methods

	// start of general methods 
	public static void showCharArray(char[] arr) {
		for (int i = 0; i < arr.length; i ++) {
			System.out.print(arr[i] + " ");
		}
	}

	public static int chances() {
		int total = miss.length;

		for (char x : miss) {
			if (x != 0) {
				total --;
			}
		}
		return total;
	}

	public static int correctGuess() {
		int total = 0;

		for (char x : underscore) {
			if (x != '_') {
				total ++;
			}
		}
		return total;
	}

		//end of general methods

}
