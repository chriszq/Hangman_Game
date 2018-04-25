import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

  private static char[] wordArray;
  private static char[] miss;
  private static char[] underscore;
  private static int chances_remaining;

  public static void main(String[] args) throws IOException {
    Scanner keyboard = new Scanner(System.in);

    do {
        String word = pickWord();	//picks a random word from a list in a text file
        wordArray = word.toCharArray();	//store the word as an array of characters
        miss = new char[7];	//a string that stores all of the wrong guesses. This game assumes maximum of 7 wrong guesses
        underscore = initUnderscore(wordArray.length);	//array which consists of underscores to represent each character of the selected word. Elements of this array will be changed to correctly guessed letters later on
        chances_remaining = miss.length;

      while (chances_remaining > 0 && !Arrays.equals(wordArray, underscore)) {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-");	//separator
        System.out.print("Word: ");
        dispCharArray(underscore);

        System.out.print("\nMisses: ");
        dispCharArray(miss);
        System.out.println("\nchances remaining: " + chances_remaining);

        System.out.print("\nGuess: ");
        String temp = keyboard.nextLine();	//store the string input in this temporary variable
        char guess = temp.charAt(0);	//convert the first charater of the string to a char

        while (inputLength(temp) || dupGuess(guess) || !alphaChar(guess)) {
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
      if (Arrays.equals(wordArray, underscore)) {
        System.out.println("\nYOU GOT IT!");
      } else {
        System.out.println("\nYOU DIDN'T GET IT!");
      }
    } while (playAgain());
  }

  public static void dispCharArray(char[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
  }

  //initialises char array with underscores
  public static char[] initUnderscore(int n) {

    char[] arr = new char[n];

    for (int i = 0; i < n; i ++) {
      arr[i] = '_';
    }
    return arr;
  }

  //checks if a character has any matches in the wordArray
  public static void checkGuess(char c) {
    int match = 0;	//keeps track of the number of matches

    //if matches then replace corresponding underscore with the character
    for (int i = 0; i < wordArray.length; i++) {
      if (wordArray[i] == c) {
        underscore[i] = c;
        match ++;
      }
    }

    //if no matches then replace first available empty element in the miss array with wrongly guessed character
    if (match == 0) {
      chances_remaining--;	//reduces the number of chances by 1

      for (int i = 0; i < miss.length; i++) {
        if (miss[i] == 0) {
          miss[i] = c;
          //break here to ensure only the first available empty element is modified
          break;
        }
      }
    }
  }

  //check player input is not greater than 1
  public static boolean inputLength(String str) {
    if (str.length() > 1) {
    System.out.print("input is too long. Try again: ");
      return true;
    } else {
      return false;
    }
  }

  //verifiying the guess is alphabetic
  public static boolean alphaChar(char c) {
    if (Character.isLetter(c)) {
      return true;
    } else {
      System.out.print("character is not alphabetic. Try again: ");
      return false;
    }
  }

  //verifying that character has not already been guessed
  public static boolean dupGuess(char c) {
    for (int i = 0; i < miss.length; i++) {
      if (miss[i] == c) {
        System.out.print("character already guessed. Try again: ");
        return true;
      }
    }

    for (int i = 0; i < underscore.length; i++) {
      if (underscore[i] == c) {
        System.out.print("character already guessed. Try again: ");
        return true;
      }
    }
    return false;
  }

  //asks if the player wants to play again or quit
  public static boolean playAgain() {
    Scanner kb = new Scanner(System.in);
    System.out.println("Play \"again\" or \"quit\" ?");
    String str = kb.nextLine();

  do
    {
      if (str.equals("again")) {
        return true;
      } else if (str.equals("quit")) {
        return false;
      } else {
        System.out.print("invalid choice. Try again");
        str = kb.nextLine();
      }
    } while(true);
  }

  // method that picks a random word from a text file
  public static String pickWord() throws IOException {
    // this text file contains the list of possible words to pick from
    Scanner dataFile = new Scanner(new File("words.txt"));
    Random r = new Random();
    int count = 0;
    // the text file has 213 lines each corresponding to a word
    int solutionIndex = 1 + r.nextInt(212);
    // System.out.println("The solution index is " + solutionIndex);

    while (dataFile.hasNext() && count < solutionIndex - 1) {
      count ++;
      dataFile.nextLine();
    }

    String solution = dataFile.nextLine();

    return solution;
  }

}