public class Picture {
	
	private static	int lives;

	public static int getLives() {
		return lives;
	}

	public static void setLives(int x) {
		lives = x;
	}

	public static void printPicture() {
		switch (lives) {
			case 7:
				System.out.println("");
				break;
			case 6:
				System.out.println("________");
				System.out.println("|/  |"   );
				System.out.println("|"       );
				System.out.println("|"       );
				System.out.println("|"       );
				System.out.println("|_______");
				break;
			case 5:
				System.out.println("________");
				System.out.println("|/  |"   );
				System.out.println("|   O"   );
				System.out.println("|"       );
				System.out.println("|"       );
				System.out.println("|_______");
				break;
			case 4:
				System.out.println("________");
				System.out.println("|/  |"   );
				System.out.println("|   O"   );
				System.out.println("|   |"   );
				System.out.println("|"       );
				System.out.println("|_______");
				break;
			case 3:
				System.out.println("________");
				System.out.println("|/  |"   );
				System.out.println("|   O"   );
				System.out.println("|  /|"   );
				System.out.println("|"       );
				System.out.println("|_______");
				break;
			case 2:
				System.out.println("________");
				System.out.println("|/  |"   );
				System.out.println("|   O"   );
				System.out.println("|  /|\\" );
				System.out.println("|"       );
				System.out.println("|_______");
				break;
			case 1:
				System.out.println("________");
				System.out.println("|/  |"   );
				System.out.println("|   O"   );
				System.out.println("|  /|\\" );
				System.out.println("|  /"    );
				System.out.println("|_______");
				break;
			case 0:
				System.out.println("________");
				System.out.println("|/  |"   );
				System.out.println("|   O"   );
				System.out.println("|  /|\\" );
				System.out.println("|  / \\" );
				System.out.println("|_______");
				break;
			default:
				System.out.println("error - never reaches here");
		}
	}
}
