import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game {
	//reserved
	public static String pickWord() throws IOException {
		Scanner dataFile = new Scanner(new File("words.txt"));
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

	public static void main(String[] args) throws IOException {
		// Menu.loadMenu();
		// Menu menu = new Menu();
		
		Hangman test = new Hangman(pickWord());

		System.out.print("solution (this is godmode): ");
		Hangman.showCharArray(test.getWord());
		System.out.println("");

		Scanner kb = new Scanner(System.in);
		
		String userInput;

		while (!Arrays.equals(test.getWord(), test.getUnderscore()) && test.chances() > 0) {
			System.out.println("\n-=-=-=-=-=-=-=-=-=-=-=-=-=-");
			System.out.println("\nchances remaining: " + test.chances());
			System.out.print("\nwrong guesses: ");
			Hangman.showCharArray(test.getMiss());
			System.out.print("\ncorrect guesses: ");
			Hangman.showCharArray(test.getUnderscore());

			System.out.print("\ninput guess: ");
			userInput = kb.nextLine();		
			Hangman.checkInput(userInput);
		}

			System.out.println("\n##=##=##=##=##=##=##=##=##=##");
			System.out.print("solution: ");
			Hangman.showCharArray(test.getWord());
			System.out.print("\nwrong guesses: ");
			Hangman.showCharArray(test.getMiss());
			System.out.print("\ncorrect guesses: ");
			Hangman.showCharArray(test.getUnderscore());

			if (Arrays.equals(test.getWord(), test.getUnderscore())) {
				System.out.println("\nYOU GOT IT!");
			} else {
				System.out.println("\nYOU DIDN'T GET IT!");
			}
			
	}

}
