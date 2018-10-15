


// //This program prompts reads a file (interview corpus) and counts the
//occurrences that are not included in the excluded words file.  It then displays the
//frequencies using a minimum number supplied by the user that limits the output
//to words of a minimum frequency

//source of word cloud code: https://github.com/kennycason/kumo
import java.util.*;
import java.util.Map.Entry;

import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.PixelBoundryBackground;
import com.kennycason.kumo.font.scale.LinearFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.palette.ColorPalette;
import com.kennycason.kumo.palette.LinearGradientColorPalette;
import com.kennycason.kumo.*;

import java.awt.Color;
import java.awt.Dimension;


import java.io.*;

public class WordCount {
	public static void main(String[] args) throws FileNotFoundException {
		
		// open the file
		Scanner console = new Scanner(System.in);
		File fileName = new File("interview-corpus.txt");
		Scanner input = new Scanner((fileName));
		
		List<String> excludeList = new ArrayList<>();
		buildExcluded(excludeList);
		
		// create map for word counts and word frequency list to build word cloud
		Map<String, Integer> wordCounts = new TreeMap<String, Integer>();
		final List<WordFrequency> wordFrequencies= new ArrayList<>(); 
		while (input.hasNext()) {
			String next = input.next().toLowerCase();
			//fix words with parentheses or quotes at the beginning or end by removing them
			char first_element = next.charAt(0);
			if (String.valueOf(first_element).equals('"') || String.valueOf(first_element).equals("(")) {
				next = next.substring(1, next.length() - 1);
			}
			if (next.length() != 0) {
				char element = next.charAt(next.length() - 1);
				if (String.valueOf(element).equals(".") || String.valueOf(element).equals('"')) {
					next = next.substring(0, next.length() - 1);

				}
				if (String.valueOf(element).equals(",")) {
					next = next.substring(0, next.length() - 1);
				}
			}

			if (!excludeList.contains(next)) {
				/*if(next=="friends"|| next=="friendship"|| next=="friendly"){
					if (!wordCounts.containsKey("friend")) {
						wordCounts.put("friend", 1);
					} else {
						wordCounts.put("friend", wordCounts.get(next) + 1);
					}
				}*/
				if (next.length()>1){ //to prevent index out of range for one letter words
				if(wordCounts.containsKey(next.substring(0,next.length()-1))){
					wordCounts.put(next.substring(0,next.length()-1), wordCounts.get(next.substring(0,next.length()-1)) + 1);
				}
				
				else if(next.equals("speak") || next.equals("speaker") || next.equals("speaking") || next.equals("speakers")|| next.equals("speaks")){
					if (!wordCounts.containsKey("speak")) {
						wordCounts.put("speak", 1);
					} else {
						wordCounts.put("speak", wordCounts.get("speak") + 1);
					}
				}
				else if(next.equals("bilingual") || next.equals("bilingualism")){
					if (!wordCounts.containsKey("bilingual")) {
						wordCounts.put("bilingual", 1);
					} else {
						wordCounts.put("bilingual", wordCounts.get("bilingual") + 1);
					}
				}
				else if(next.equals("culture") || next.equals("cultural") || next.equals("cultures")){
					if (!wordCounts.containsKey("culture")) {
						wordCounts.put("culture", 1);
					} else {
						wordCounts.put("culture", wordCounts.get("culture") + 1);
					}
				}
				else if(next.equals("learn") || next.equals("learned") || next.equals("learning")){
					if (!wordCounts.containsKey("learn")) {
						wordCounts.put("learn", 1);
					} else {
						wordCounts.put("learn", wordCounts.get("learn") + 1);
					}
				}
				else if(next.equals("multilingual") || next.equals("multilingualism")){
					if (!wordCounts.containsKey("multilingual")) {
						wordCounts.put("multilingual", 1);
					} else {
						wordCounts.put("multilingual", wordCounts.get("multilingual") + 1);
					}
				}
				else if(next.equals("person") || next.equals("people")){
					if (!wordCounts.containsKey("person")) {
						wordCounts.put("person", 1);
					} else {
						wordCounts.put("person", wordCounts.get("person") + 1);
					}
				}
				else if(next.equals("know") || next.equals("knowing") || next.equals("knew")){
					if (!wordCounts.containsKey("know")) {
						wordCounts.put("know", 1);
					} else {
						wordCounts.put("know", wordCounts.get("know") + 1);
					}
				}
				else if(next.equals("grow") || next.equals("growing") || next.equals("grew")){
					if (!wordCounts.containsKey("grow")) {
						wordCounts.put("grow", 1);
					} else {
						wordCounts.put("grow", wordCounts.get("grow") + 1);
					}
				}
				else if(next.equals("think") || next.equals("thinking") || next.equals("thought")){
					if (!wordCounts.containsKey("think")) {
						wordCounts.put("think", 1);
					} else {
						wordCounts.put("think", wordCounts.get("think") + 1);
					}
				}
				else if(next.equals("teach") || next.equals("taught") || next.equals("teacher")){
					if (!wordCounts.containsKey("teach")) {
						wordCounts.put("teach", 1);
					} else {
						wordCounts.put("teach", wordCounts.get("teach") + 1);
					}
				}
				else if(next.equals("study") || next.equals("studying") || next.equals("studied") || next.equals("studies")){
					if (!wordCounts.containsKey("study")) {
						wordCounts.put("study", 1);
					} else {
						wordCounts.put("study", wordCounts.get("study") + 1);
					}
				}
				else if(next.equals("read") || next.equals("reading")){
					if (!wordCounts.containsKey("read")) {
						wordCounts.put("read", 1);
					} else {
						wordCounts.put("read", wordCounts.get("read") + 1);
					}
				}
				else if(next.equals("parent") || next.equals("parents")){
					if (!wordCounts.containsKey("parent")) {
						wordCounts.put("parent", 1);
					} else {
						wordCounts.put("parent", wordCounts.get("parent") + 1);
					}
				}
				else if(next.equals("mix") || next.equals("mixes") || next.equals("mixing")){
					if (!wordCounts.containsKey("mix")) {
						wordCounts.put("mix", 1);
					} else {
						wordCounts.put("mix", wordCounts.get("mix") + 1);
					}
				}
				else if(next.equals("interest") || next.equals("interested") || next.equals("interesting")){
					if (!wordCounts.containsKey("interest")) {
						wordCounts.put("interest", 1);
					} else {
						wordCounts.put("interest", wordCounts.get("interest") + 1);
					}
				}
				else if(next.equals("friend") || next.equals("friends")){
					if (!wordCounts.containsKey("friend")) {
						wordCounts.put("friend", 1);
					} else {
						wordCounts.put("friend", wordCounts.get("friend") + 1);
					}
				}
				else if(next.equals("fluent") || next.equals("fluency") || next.equals("fluently")){
					if (!wordCounts.containsKey("fluent")) {
						wordCounts.put("fluent", 1);
					} else {
						wordCounts.put("fluent", wordCounts.get("fluent") + 1);
					}
				}
				else if(next.equals("feel") || next.equals("feeling") || next.equals("felt") || next.equals("feelings")){
					if (!wordCounts.containsKey("feel")) {
						wordCounts.put("feel", 1);
					} else {
						wordCounts.put("feel", wordCounts.get("feel") + 1);
					}
				}
				else if(next.equals("explain") || next.equals("explained")){
					if (!wordCounts.containsKey("explain")) {
						wordCounts.put("explain", 1);
					} else {
						wordCounts.put("explain", wordCounts.get("explain") + 1);
					}
				}
				else if(next.equals("dream") || next.equals("dreaming")){
					if (!wordCounts.containsKey("dream")) {
						wordCounts.put("dream", 1);
					} else {
						wordCounts.put("dream", wordCounts.get("dream") + 1);
					}
				}
				else if(next.equals("understand") || next.equals("understanding")){
					if (!wordCounts.containsKey("understand")) {
						wordCounts.put("understand", 1);
					} else {
						wordCounts.put("understand", wordCounts.get("understand") + 1);
					}
				}
				else if(next.equals("country") || next.equals("countries")){
					if (!wordCounts.containsKey("country")) {
						wordCounts.put("country", 1);
					} else {
						wordCounts.put("country", wordCounts.get("country") + 1);
					}
				}
				else if(next.equals("communicate") || next.equals("communication")){
					if (!wordCounts.containsKey("communicate")) {
						wordCounts.put("communicate", 1);
					} else {
						wordCounts.put("communicate", wordCounts.get("communicate") + 1);
					}
				}
				else if(next.equals("life") || next.equals("live") || next.equals("living") || next.equals("lived") || next.equals("lives")){
					if (!wordCounts.containsKey("life")) {
						wordCounts.put("life", 1);
					} else {
						wordCounts.put("life", wordCounts.get("life") + 1);
					}
				}
				else if(next.equals("write") || next.equals("writing")){
					if (!wordCounts.containsKey("write")) {
						wordCounts.put("write", 1);
					} else {
						wordCounts.put("write", wordCounts.get("write") + 1);
					}
				}
				else if(next.equals("young") || next.equals("younger")){
					if (!wordCounts.containsKey("young")) {
						wordCounts.put("young", 1);
					} else {
						wordCounts.put("young", wordCounts.get("young") + 1);
					}
				}
				else if (!wordCounts.containsKey(next)) {
					wordCounts.put(next, 1);
				} else {
					wordCounts.put(next, wordCounts.get(next) + 1);
				}
				}
			}
			
		}

		// get minimum and display frequencies
		System.out.println("Total individual words generated= " + wordCounts.size());
		System.out.print("Minimum number of occurrences for printing? ");
		int min = console.nextInt();
		for (String word : wordCounts.keySet()) {
			int count = wordCounts.get(word);
			if (count >= min)
				System.out.println(count + "\t" + word);
		}
		final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
		frequencyAnalyzer.setMinWordLength(3);
		frequencyAnalyzer.setStopWords(excludeList);
        for(String word:wordCounts.keySet()){
        	if(wordCounts.get(word)>=15){
        	wordFrequencies.add(new WordFrequency(word, wordCounts.get(word)));
        	}
        }
		final Dimension dimension = new Dimension(525, 525);
		final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
		wordCloud.setPadding(2);
		try {
			wordCloud.setBackground(new PixelBoundryBackground("test5.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wordCloud.setColorPalette(new LinearGradientColorPalette(Color.BLUE, Color.GREEN, Color.MAGENTA, 50, 50));
		//wordCloud.setColorPalette(new LinearGradientColorPalette(Color.PINK, Color.BLUE, Color.GREEN, 50, 50));
		wordCloud.setFontScalar(new LinearFontScalar(10, 55));
		wordCloud.build(wordFrequencies);
		//results for frequency words
		wordCloud.writeToFile("results.png");
	}

	public static void buildExcluded(List<String> excluded) {
		Scanner console = new Scanner(System.in);
		File fileName = new File("exclude-words.txt");
		try {
			Scanner input = new Scanner((fileName));
			while (input.hasNext()) {
				String next = input.next().toLowerCase();
				excluded.add(next);
			}
			//exclude versions of the question numbers
			for (int i = 0; i < 30; i++) {
				String num = Integer.toString(i);
				excluded.add("(" + num + ")");
				excluded.add(num + ")");
				excluded.add("(" + num);
				excluded.add(num);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
