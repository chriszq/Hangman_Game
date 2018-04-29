import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Menu {

	private static boolean exit;

	private static void printHeader() {
		System.out.println("\t+--------------------+");
		System.out.println("\t| Welcome to Hangman |");
		System.out.println("\t+--------------------+");
	}

	private static void printMenu() {

		System.out.println("\n  Select option:\n");
		System.out.println("\t(0) Cheats (" + GameLogic.showCheatStatus() + ")");
		System.out.println("\t(1) Easy"    );
		System.out.println("\t(2) Medium"  );
		System.out.println("\t(3) Hard"    );
		System.out.println("\t(4) PvP"     );
		System.out.println("\t(e) Exit"    );
		
	}

	private static String getInput() {
		Scanner kb = new Scanner(System.in);	
		System.out.print("Enter option: ");
		String str = kb.nextLine();

		while (!str.matches("[0-4e]{1}")) {
				System.out.print("Invalid option. Enter again : ");
				str = kb.nextLine();
		}	

		return str;
	}

	private static String getPvpWord() {
		Scanner kb = new Scanner(System.in);
		System.out.print("second player pick a word: ");
		String str = kb.nextLine();

		while (!str.matches("[a-z]+")) {
			System.out.print("Invalid word (must be alphabetical, no uppercase)");
			str = kb.nextLine();
		}

		return str;
	}

	private static void performOption(String str) throws IOException {

		GameLogic gameLogic = new GameLogic();
		
		switch (str) {
			case "0":
				System.out.println("\ntoggling cheats");
				GameLogic.setCheats();
				break;
			case "1":
				System.out.println("\nloading easy mode");
				GameLogic.setMode("easy.txt");
				GameLogic.runGameMode(GameLogic.pickWord());
				break;
			case "2":
				System.out.println("\nloading medium mode");
				GameLogic.setMode("medium.txt");
				GameLogic.runGameMode(GameLogic.pickWord());
				break;
			case "3":
				System.out.println("\nloading hard mode");
				GameLogic.setMode("hard.txt");
				GameLogic.runGameMode(GameLogic.pickWord());
				break;
			case "4":
				System.out.println("\nloading pvp mode");
				GameLogic.runGameMode(getPvpWord());
				break;
			case "e":
				exit = true;
				System.out.println("\nbye.");
				break;
			default:
				System.out.println("never reaches here");
		}
	}

	public static void runMenu() throws IOException {
		printHeader();

		while (!exit) {
			printMenu();
			String option = getInput();
			performOption(option);	
		}
	}

	
}
