import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GameLogic {
	
	private static String mode;
	private static boolean cheats;

	public GameLogic() {
		this.mode = "words.txt";
	}

	public static String getMode() {
		return mode;
	}
	
	public static boolean getCheats() {
		return cheats;
	}

	public static void setMode(String str) {
		mode = str;
	}

	public static boolean setCheats() {
		if (cheats == false) {
			cheats = true;
		} else {
			cheats = false;
		}
		return cheats;
	}

	public static String showCheatStatus() {
		if (cheats == false) {
			return "off";
		} else {
			return "on";
		}
	}

	public static String pickWord() throws IOException {
		Scanner dataFile = new Scanner(new File(GameLogic.getMode()));
		Random r = new Random();
		int count = 0;
		int solutionIndex = 1 + r.nextInt(212);

		while (dataFile.hasNext() && count < solutionIndex - 1) {
			count ++;
			dataFile.nextLine();
		}

		String solution = dataFile.nextLine();
		return solution;
	}

	public static void runGameMode (String str) {
			
		Hangman test = new Hangman(str);
		Scanner kb = new Scanner (System.in);
		String userInput;

		if (GameLogic.getCheats() == true) {
			System.out.println("Cheats enabled. Solution is: " + str);
		}

		while (!Arrays.equals(test.getWord(), test.getUnderscore()) && test.chances() > 0) {
			System.out.println("\n-=-=-=-=-=-=-=-=-=-=-=-=-=-");
			System.out.println("\nchances remaining: " + test.chances());
			System.out.print("\nwrong guesses: ");
			Hangman.showCharArray(test.getMiss());
			System.out.print("\ncorrect guesses: ");
			Hangman.showCharArray(test.getUnderscore());

			System.out.println();
			Picture.setLives(test.chances());
			Picture.printPicture();

			System.out.print("\ninput guess: ");
			userInput = kb.nextLine();		
			Hangman.checkInput(userInput);
		}	

			System.out.println("\n##=##=##=##=##=##=##=##=##=##");
			System.out.print("solution: " + str);
			System.out.print("\nwrong guesses: ");
			Hangman.showCharArray(test.getMiss());
			System.out.print("\ncorrect guesses (" + test.correctGuess() + "): ");
			Hangman.showCharArray(test.getUnderscore());

			System.out.println();
			Picture.setLives(test.chances());
			Picture.printPicture();

			if (Arrays.equals(test.getWord(), test.getUnderscore())) {
				System.out.println("\nYOU GOT IT!");
			} else {
					System.out.println("\nYOU DIDN'T GET IT!");
			}
		
	}
}
