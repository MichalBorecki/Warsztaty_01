package project05;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class Word implements Comparable<Word> {

	private Integer counter;
	private String word;

	public Word(Integer counter, String word) {
		this.counter = counter;
		this.word = word;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Override
	public int compareTo(Word o) {
		return o.counter.compareTo(this.counter);
	}

	public String toString() {
		return this.word + " (occured: " + this.counter + " times)";
	}

}

public class MostPopularWordsOnInternetSite {

	public static void main(String[] args) {
		checkTitles();
	}

	static void checkTitles() {
		Connection connect = Jsoup.connect("https://www.onet.pl/");
		StringBuilder reading = new StringBuilder();
		String[] excludedWords = { "lub", "nie", "tak", "czy", "ale", "się", "jak", "dla", "może", "czyli" };
		try {
			Document document = connect.get();
			Elements links = document.select("span.title");

			PrintWriter pw = new PrintWriter("popular_words.txt");
			for (Element elem : links) {
				String line = elem.text();
				line = line.toLowerCase().replaceAll("[^a-żA-Ż ]", "");
				String[] arrayWords = line.split(" ");
				for (String i : arrayWords) {
					if (i.length() > 2) {
						if (!Arrays.asList(excludedWords).contains(i)) {
							pw.println(i);// tworzę jedną linię przedzileoną spacjami
						}
					}
				}
			}
			pw.close();
		} catch (IOException e) {
			System.out.println("Błąd zapisu pliku");
		}
		mostPopularWords();
	}

	static void mostPopularWords() {
		try {
			Path path = Paths.get("popular_words.txt");// tworzy link do pliku (potrzebne w readAllLines)
			Map<String, Integer> words = new HashMap<>();

			for (String line : Files.readAllLines(path)) {
				if (words.containsKey(line)) {// jeżeli zawiera dany element to
					words.put(line, words.get(line) + 1);// dodaje do mapy ale wartość podnosi o 1
				} else {
					words.put(line, 1);// dodaje do mapy kolejny element, wartość ustawia na 1
				}
			}

			List<Word> wordsArray = new ArrayList<>();
			Iterator it = words.entrySet().iterator();

			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();
				wordsArray.add(new Word((Integer) pair.getValue(), pair.getKey().toString()));
				it.remove(); // aby uniknąć ConcurrentModificationException
			}
			Collections.sort(wordsArray);// sortowanie listy

			PrintWriter pw = new PrintWriter("most_popular_words.txt");
			if (wordsArray.size() >= 10) {
				for (int i = 0; i < 10; i++) {
					pw.println((i + 1) + " " + wordsArray.get(i).toString());
				}
			} else {
				for (int i = 0; i < wordsArray.size(); i++) {
					System.out.println((i + 1) + " " + wordsArray.get(i).toString());
				}
			}
			pw.close();
		} catch (IOException e) {
			System.out.println("Brak pliku");
		}

	}

}
