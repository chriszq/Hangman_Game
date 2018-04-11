// Hangman
// no ArrayList or Collections used
// randomly choose a word from a list of words.
// stop when all the letters are guessed.
// limited tries and stop after they run out.
// display letters already guessed.

//extra ideas: maybe add a visual hangman which changes based on the number of empty slots remaining on the miss array?

import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
import java.io.*;

public class Hangman
{
	//these 3 variables can now be accessed by all methods belonging to this class
	//the accessibility of these variables makes it very convenient
	private static char[] wordArray;
	private static char[] miss;
	private static char[] underscore;
	private static int chances_remaining;

	public static void main (String[] args) throws IOException
	{
		Scanner keyboard = new Scanner(System.in);

		do
		{
			String word = pickWord();	//picks a random word from a list in a text file

			wordArray = word.toCharArray();	//store the word as an array of characters
			miss = new char[7];	//a string that stores all of the wrong guesses. This game assumes maximum of 7 wrong guesses
			underscore = initUnderscore(wordArray.length);	//array which consists of underscores to represent each character of the selected word. Elements of this array will be changed to correctly guessed letters later on
			chances_remaining = miss.length;

			while (chances_remaining > 0 && !Arrays.equals(wordArray, underscore))
			{
				System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-");	//separator
				System.out.print("Word: ");
				dispCharArray(underscore);

				System.out.print("\nMisses: ");
				dispCharArray(miss);
				System.out.println("\nchances remaining: " + chances_remaining);

				System.out.print("\nGuess: ");

				String temp = keyboard.nextLine();	//store the string input in this temporary variable
				char guess = temp.charAt(0);	//convert the first charater of the string to a char

				while (inputLength(temp) || dupGuess(guess) || !alphaChar(guess))
				{
					temp = keyboard.nextLine();
					guess = temp.charAt(0);
				}

				checkGuess(guess);

				System.out.println();
			}

			System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-");	//separator
			System.out.print("Answer: ");
			dispCharArray(wordArray);
			System.out.print("\nYour guess: ");
			dispCharArray(underscore);
			System.out.print("\nMisses: ");
			dispCharArray(miss);
			if (Arrays.equals(wordArray, underscore))
			{
				System.out.println("\nYOU GOT IT!");
			}
			else
			{
				System.out.println("\nYOU DIDN'T GET IT!");
			}
		}
		while (playAgain());
	}

	public static void dispCharArray (char[] arr)	//prints char type arrays
	{
		for (int i = 0; i < arr.length; i++)
		{
			System.out.print(arr[i] + " ");
		}
	}

	public static char[] initUnderscore (int n)	//initialises char array with underscores
	{
		char[] arr = new char[n];

		for (int i = 0; i < n; i ++)
		{
			arr[i] = '_';
		}
		return arr;
	}

	public static void checkGuess (char c)	//checks if a character has any matches in the wordArray
	{
		int match = 0;	//keeps track of the number of matches

		for (int i = 0; i < wordArray.length; i++)	//if there are matches then it will replace the corresponding underscore with the character
		{
			if (wordArray[i] == c)
			{
				underscore[i] = c;
				match ++;
			}
		}

		if (match == 0)	//if there are no matches then it will replace the first available empty element in the miss array with the wrongly guessed character
		{
			chances_remaining--;	//reduces the number of chances by 1

			for (int i = 0; i < miss.length; i++)
			{
				if (miss[i] == 0)
				{
					miss[i] = c;
					break;	//break here to ensure only the first available empty element is modified
				}
			}
		}
	}

	public static boolean inputLength(String str)	//check player input is not greater than 1
	{
		if (str.length() > 1)
		{
			System.out.print("input is too long. Try again: ");
			return true;
		}
		else
		{
			return false;
		}
	}

	public static boolean alphaChar(char c)	//verifiying the guess is alphabetic
	{
		if (Character.isLetter(c))
		{
			return true;
		}
		else
		{
			System.out.print("character is not alphabetic. Try again: ");
			return false;
		}
	}

	public static boolean dupGuess(char c)	//verifying that character has not already been guessed
	{
		for (int i = 0; i < miss.length; i++)
		{
			if (miss[i] == c)
			{
				System.out.print("character already guessed. Try again: ");
				return true;
			}
		}

		for (int i = 0; i < underscore.length; i++)
		{
			if (underscore[i] == c)
			{
				System.out.print("character already guessed. Try again: ");
				return true;
			}
		}

		return false;
	}

	public static boolean playAgain()	//asks if the player wants to play again or quit
	{
		Scanner kb = new Scanner(System.in);

		System.out.println("Play \"again\" or \"quit\" ?");
		String str = kb.nextLine();

		do
		{
			if (str.equals("again"))
			{
				return true;
			}
			else if (str.equals("quit"))
			{
				return false;
			}
			else
			{
				System.out.print("invalid choice. Try again");
				str = kb.nextLine();
			}
		}

		while(true);
	}

	public static String pickWord() throws IOException	// method that picks a random word from a text file
	{
		Scanner dataFile = new Scanner(new File("words.txt"));	// this text file contains the list of possible words to pick from
		Random r = new Random();
		int count = 0;
		int solutionIndex = 1 + r.nextInt(212);	// the text file has 213 lines each corresponding to a word
		// System.out.println("The solution index is " + solutionIndex);

		while (dataFile.hasNext() && count < solutionIndex - 1)
		{
			count ++;
			dataFile.nextLine();
		}

		String solution = dataFile.nextLine();

		return solution;
	}

}