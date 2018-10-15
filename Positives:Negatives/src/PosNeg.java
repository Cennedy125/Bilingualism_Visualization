
//This program prompts reads a file (interview corpus) and counts the
//occurrences that match positive or negative words from a text file.  It then displays the
//frequencies using a minimum number supplied by the user that limits the output
//to words of a minimum frequency

import java.util.*;
import java.io.*;

public class PosNeg {
	public static void main(String[] args) throws FileNotFoundException {

		//open the file
		//number of total word occurrences
		int count_words=0;
		Scanner console = new Scanner(System.in);
		File fileName = new File("interview-corpusc.txt");
		Scanner input = new Scanner((fileName));
		List<String> PosList = new ArrayList<>();
		buildList(PosList);
		//count occurrences
		Map<String, Integer> wordCounts = new TreeMap<String, Integer>();
		while (input.hasNext()) {
			String next = input.next().toLowerCase();
			if (PosList.contains(next)) {
				if (!wordCounts.containsKey(next)) {
					count_words++;
					wordCounts.put(next, 1);
				} else {
					wordCounts.put(next, wordCounts.get(next) + 1);
					count_words++;
				}
			}
		}

		//get minimum and display frequencies
		System.out.println("Total individual words = " + wordCounts.size());
		System.out.println("Total words = " + count_words);
		System.out.print("Minimum number of occurrences for printing? ");
		int min = console.nextInt();
		for (String word : wordCounts.keySet()) {
			int count = wordCounts.get(word);
			if (count >= min)
				System.out.println(count + "\t" + word);
		}
	}

	public static void buildList(List<String> pos_words) {
		Scanner console = new Scanner(System.in);
		File fileName = new File("negative-words.txt");
		try {
			Scanner input = new Scanner((fileName));
			while (input.hasNext()) {
				String next = input.next().toLowerCase();
				pos_words.add(next);
			}

		} catch (FileNotFoundException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
