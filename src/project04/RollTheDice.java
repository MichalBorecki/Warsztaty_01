package project04;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class RollTheDice {

	static String type;

	public static void main(String[] args) {
		diceRoll(userTypeOfDiceRoll());
		playAgain();
	}

	static String userTypeOfDiceRoll() {
		System.out
				.println("----------------------------------------\nWitaj w symulatorze rzutu kośćmi do gry. \n\n"
						+ "I|    Wprowadź do konsoli typ rzutu [xDy+z] gdzie: \n"
						+ "N|    y – rodzaj kostek, których należy użyć (D3, D4, D6, D8, D10, D12, D20, D100),\n"
						+ "F|    x – liczba rzutów kośćmi; jeśli rzucamy raz, ten parametr jest pomijalny,\n"
						+ "O|    z – liczba, którą należy dodać (lub odjąć) do wyniku rzutów (opcjonalnie).\n\n"
						+ "Wprowadź typ rzutu:");
		Scanner scan = new Scanner(System.in);
		while (!scan.hasNextLine()) {
			scan.nextLine();
			System.out.print("Nieprawidłowe dane. Podaj jeszcze raz: ");
		}
		type = scan.nextLine();
		return type;
	}

	static void diceRoll(String throwType) {
		int numberOfThrows = 0;
		int diceType = 0;
		int minusOrPlus = 0;
		int result = 0;
		String[] infoArray = new String[3];
		String[] infoArrayCopy;
		infoArrayCopy = throwType.split("D|\\+|\\-");
		infoArray = Arrays.copyOf(infoArrayCopy, 3);
		char[] array = throwType.toCharArray();

		if (infoArray[2] == null && (array[0] == 'D')) {
			infoArray[0] = "1";
			infoArray[2] = "0";
		} else if (infoArray[2] == null) {
			infoArray[2] = "0";
		} else if (array[0] == 'D') {
			infoArray[0] = "1";
		}

		boolean isGoodType = isGoodType(infoArray);

		if (isGoodType) {
			try {
				numberOfThrows = Integer.parseInt(infoArray[0]);
				diceType = Integer.parseInt(infoArray[1]);
				minusOrPlus = Integer.parseInt(infoArray[2]);
			} catch (NumberFormatException e) {
				System.out.println("Niepoprawny format kodu obliczenia wartości rzutu!");
			}
			for (int i = 0; i < numberOfThrows; i++) {
				result += roll(diceType);
			}
			result += minusOrPlus;
			System.out.println("----------------------------------------\n" + "Kod rzutu: " + throwType
					+ ". Wynik rzutu: " + result + "\n----------------------------------------");
		} else {
			System.out.println("Niepoprawny typ kostki do gry");
		}
	}

	public static boolean isGoodType(String[] infoArray) {
		boolean isGoodType = false;
		int[] diceTypesInGames = { 3, 4, 6, 8, 10, 12, 20, 100 };
		for (int i : diceTypesInGames) {
			try {
				if (i == Integer.parseInt(infoArray[1])) {
					isGoodType = true;
				}
			} catch (NumberFormatException e) {
				System.out.println("Niepoprawny format kodu obliczenia wartości rzutu!");
				diceRoll(userTypeOfDiceRoll());
			}
		}
		return isGoodType;
	}

	static int roll(int diceType) {
		Random r = new Random();
		return r.nextInt(diceType + 1) + 1;
	}

	static void playAgain() {
		Scanner play = new Scanner(System.in);
		System.out.println("Jeśli chcesz rzucić jeszcze raz wpisz 'r'\n"
				+ "jeśli chcesz wybrać nowy typ rzutu wpisz 't'\n" + "jeśli chcesz zakończyć program wpisz 'k'");
		String playAgain;
		playAgain = play.nextLine().toLowerCase();
		if ("r".equals(playAgain)) {
			System.out.println("\n[Kolejny rzut]");
			diceRoll(type);
		} else if ("k".equals(playAgain)) {
			System.exit(0);
		} else if ("t".equals(playAgain)) {
			diceRoll(userTypeOfDiceRoll());
		}
		playAgain();
	}
}
