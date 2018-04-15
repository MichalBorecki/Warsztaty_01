package project02;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class LottoGame {

	public static void main(String[] args) {
		game();
	}

	static void game() {
		System.out.println("------------------------------------\n| Symulator LOTTO - wpisz 6 liczb. |\n------------------------------------");
		int userNumber = 0;
		int[] userArrays = new int[6];
		for (int i = 0; i < userArrays.length; i++) {
			userNumber = nextInt("  Twoja liczba nr " + (i + 1) + " z zakresu: 1 - 49 to: ");
			if ((userNumber == -1) || (userNumber == 0)) {
				System.out.println("  Wprowadź poprawną liczbę. ");
				i--;
			} else if (checkIfContains(userArrays, userNumber)) {
				System.out.println("  Już wprowadziłeś wcześniej tę liczbę, wprowadź inną.");
				i--;
			} else {
				userArrays[i] = userNumber;
			}
		}
		// sortujemy tablicę liczb użytkownika
		Arrays.sort(userArrays);
		System.out.print("\nTwoje liczby to: \n");
		for (int i : userArrays) {
			System.out.print("[" + i + "] ");
		}
		System.out.print("\n\nLiczby LOTTO to: \n");
		int[] lotto = lottoNumbers(); // losujemy liczby LOTTO

		String result = checkResult(userArrays, lotto);
		System.out.println(result);
		playAgain();
	}

	static int[] lottoNumbers() {
		// tworzymy tablicę z 49 elementami, mieszamy i drukujemy tylko 6 elementów
		Integer[] arr = new Integer[49];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i + 1;
		}
		int[] lotto = new int[6];
		Collections.shuffle(Arrays.asList(arr));
		for (int i = 0; i < 6; i++) {
			System.out.print("[" + arr[i] + "] ");
			lotto[i] = arr[i];
		}
		return lotto;
	}

	static String checkResult(int[] userArrays, int[] lotto) {
		int count = 0;
		String resultMessage;
		for (int i = 0; i < 6; i++) {
			if (checkIfContains(lotto, userArrays[i])) {
				count++;
			}
		}
		if(count ==6) {
			resultMessage = "\n\nZostałeś milionerem!! Trafiłeś " + count + "!!! ";
		} else {
			resultMessage = "\n\nTrafiłeś " + count + "!";
		}
		return resultMessage;
	}

	static boolean checkIfContains(int[] arr, int targetValue) {
		for (int s : arr) {
			if (s == targetValue)
				return true;
		}
		return false;
	}

	static int nextInt(String message) {
		Scanner scan = new Scanner(System.in);
		int number = 0;
		if (message != null && !"".equals(message)) {
			System.out.println(message);
		}
		while (scan.hasNextInt() /*&& scan.hasNext()*/) {
			try {
				number = scan.nextInt();
				if (number < 50 && number > 0) {
					return number;
				} else {
					number = -1;
				}
				break;
			} catch (NumberFormatException e) {
				System.out.print("Nieprawidłowe dane. Podaj jeszcze raz:");
			}
		}
		return number;
	}

	static void playAgain() {
		Scanner play = new Scanner(System.in);
		System.out.println("\nCzy chcesz zagrać jeszcze raz? [y/n]");
		String playAgain;
		playAgain = play.nextLine().toLowerCase();
		if ("y".equals(playAgain)) {
			System.out.println("\n[NOWA GRA]");
			game();
		} else if ("n".equals(playAgain)) {
			System.exit(0);
		}
		playAgain();
	}

}
