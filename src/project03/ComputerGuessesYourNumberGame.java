package project03;

import java.util.Scanner;

public class ComputerGuessesYourNumberGame {

	public static void main(String[] args) {
		play();
	}

	static void play() {
		int min = 0;
		int max = 1000;
		System.out.println("Pomyśl liczbę od " + min + " do " + max
				+ ", a ja ją zgadnę w 10 próbach. Wpisz 's' jeśli możemy zaczynać.");

		Scanner scan = new Scanner(System.in);
		String start = scan.next();
		while (true) {
			if ("s".equals(start)) {
				guess(min, max, 1);
				break;
			} else {
				System.out.print("Wpisz 's' jeśli możemy zaczynać.");
			}
		}
	}

	static void guess(int min, int max, int attempt) {
		int computerNumber = ((max - min) / 2) + min;
		for (int attempts = attempt; attempts < 11; attempts++) {
			if (attempts == 10) {
				System.out.println("Pozostało tylko " + computerNumber + ". To musi być liczba o której pomyślałeś na początku! Ja wygrywam!");
				playAgain();
				break;
			}
			System.out.print("--------------------------------------------\nMoja " + attempts + " próba.");
			inputReply(min, max, computerNumber, attempts);
		}
		playAgain();
	}

	static void inputReply(int min, int max, int computerNumber, int attempt) {
		Scanner scan = new Scanner(System.in);
		attempt += 1;
		while (true) {
			System.out.println(" Czy Twoja liczba to " + computerNumber + " [t/n]?");
			String reply;
			reply = scan.next();
			if ("t".equals(reply)) {
				System.out.println("wygrałem!");
				playAgain();
				break;
			} else if ("n".equals(reply)) {
				while (true) {
					System.out.println("Czy to za dużo [t/n]?");
					reply = scan.next();
					if ("t".equals(reply)) {
						guess(min, computerNumber, attempt);
						break;
					} else if ("n".equals(reply)) {
						while (true) {
							System.out.println("Czy to za mało [t/n] ?");
							reply = scan.next();
							if ("t".equals(reply)) {
								guess(computerNumber, max, attempt);
								break;
							} else if ("n".equals(reply)) {
								System.out.println("Nie oszukuj!!");
								System.out.println("\nZgaduję: " + computerNumber);
								inputReply(min, max, computerNumber, (attempt - 1));
								break;
							} else {
								System.out.print("Odpowiedz. ");
							}
						}
					} else {
						System.out.print("Odpowiedz. ");
					}
				}
			} else {
				System.out.print("Odpowiedz. ");
			}
		}
	}

	static void playAgain() {
		Scanner play = new Scanner(System.in);
		System.out.println("\nCzy chcesz zagrać ze mną jeszcze raz? [t/n]");
		String playAgain;
		playAgain = play.nextLine().toLowerCase();
		if ("t".equals(playAgain)) {
			System.out.println("\n[NOWA GRA]");
			play();
		} else if ("n".equals(playAgain)) {
			System.exit(0);
		}
		playAgain();
	}

}
