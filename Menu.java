import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Menu {
// reserved

	public static void loadMenu() {

		Scanner kb = new Scanner(System.in);

		System.out.println("\n\t--Hangman Game--");
		System.out.println("\noptions:\n");
		System.out.println("\t(g)od mode");
		System.out.println("\t(e)asy"    );
		System.out.println("\t(m)edium"  );
		System.out.println("\t(h)ard"    );
		System.out.println("\t(p)vp"     );
		
		System.out.print("\nEnter option: ");
	}
}
