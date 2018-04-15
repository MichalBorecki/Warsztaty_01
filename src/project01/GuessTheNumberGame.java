package project01;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GuessTheNumberGame {

	public static void main(String[] args) {
		guesssAnumber(getArandomNumber());
	}

	static int getArandomNumber() {
		Random r = new Random();
		return r.nextInt(101);
	}

	static void guesssAnumber(int randomNumber) {
		System.out.println(
				"Guess the computer generated random number between 1 and 100.\n-------------------------------------------------------------");
		int i;
		int number = 0;
		for (i = 1; i < 11; i++) {
			if (i == 1) {
				System.out.println("Pick a number: ");
			} else {
				System.out.println("You have " + (11 - i) + " attempts left. Pick a number: ");
			}
			number = inputInt();
			while (number > 100 || number < 1) {
				System.out.println("\nYou can choose only numbers between 1 and 100!");
				number = inputInt();
			}
			if (randomNumber < number) {
				System.out.println("\nYour number is higher!\n-----------------------------------------");
			} else if (randomNumber > number) {
				System.out.println("\nYour number is lower!\n----------------------------------------");
			} else {
				System.out.println("\nYes! That's it!");
				playAgain();
			}
		}
		System.out.println("Game over!");
		playAgain();
	}

	static int inputInt() {
		int result = 0;
		Scanner scan = new Scanner(System.in);
		while (true) {
			try {
				result = scan.nextInt();
				break;
			} catch (InputMismatchException error1) {
				break;
			}
		}
		return result;
	}

	static void playAgain() {
		Scanner play = new Scanner(System.in);
		System.out.println("\nPlay again? [y/n]");
		String playAgain;
		playAgain = play.nextLine().toLowerCase();
		if ("y".equals(playAgain)) {
			guesssAnumber(getArandomNumber());
		} else if ("n".equals(playAgain)) {
			System.exit(0);
		}
		playAgain();
	}

}
