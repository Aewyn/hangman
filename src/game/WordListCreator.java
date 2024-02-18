package game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WordListCreator {
	public static List<String> getWords() {
		List<String> words = new ArrayList<>();
		try (var x = new BufferedReader(new FileReader("words.txt"))) {

			var line = x.readLine();
			while (line != null) {
				words.add(line);
				line = x.readLine();
			}

			return words;
		} catch (IOException e){
			System.out.println("UH OH!");
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
}
