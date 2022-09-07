package com.p3.kashika;

import java.io.FileNotFoundException;
import java.util.*;

public class WordStat {
	private ArrayList<String> words; //arraylist of words from tokenizer class
	private HashTable individual; //hashtable of the individual words
	private HashTable pairs; //hashtable of pairs
	private ArrayList<String> orderedWords; //array list of the words ordered with most common at index 0
	private ArrayList<String> orderedPairWords; //array list of the pairs orderd with most common  at index o
	private String[] mostCommon; //string array of words in decreasing order of commonality
	private String[] leastCommon; //string array of words in increasing order of commonality
	private String[] mostCommonPairs; //string array of words in decreasing order of commmonality
	public WordStat(String file) throws FileNotFoundException {
		Tokenizer read = new Tokenizer(file);
		words = read.wordList();
		individual = new HashTable(words.size()*2);
		pairs = new HashTable(words.size()*3);
		int n = 0;
		while(n < words.size()) { //iterate through array 
			String put = words.get(n);
			int value = individual.get(put);
			if(value == -1) { //if word is not in the hashtable
				individual.put(put, 1); //put it in
			}
			else {
				value+=1;
				individual.update(put, value); //if it is in hashtable j update the value
			}
			n++; 
		}
		int pair = 0;
		while(pair+1 < words.size()) { //iterate through the array 
			String combined = words.get(pair)+" " +words.get(pair+1);
			int value = pairs.get(combined);
			if(value == -1) { //if pair is not in the hashtable
				pairs.put(combined, 1); //put it in
			}
			else {
				value+=1;
				pairs.update(combined, value); //if it is in the hashtable j update the value
			}
			pair++;
		}
		int max = 0;
		int j = 0;
		while(j < words.size()) { //finds the max count 
			String word = words.get(j);
			int check = wordCount(word);
			if(check > max)
				max = check;
			j++;
		}
		String[] ranked = new String[max+1];
		int i = 0;
		while(i < words.size()) { //assigns every word to the index of its count (sorts)
			String check = words.get(i);
			int index = wordCount(check);
			ranked[index]= check;
			i++;
		}
		ArrayList<String> better = new ArrayList<String>();
		int count = 0;
		while(count < ranked.length) { //pushes ranked array to better arraylist where you get rid of empty indices and you are not going in decreasing order
			if(ranked[count] != null)
			{
				better.add(0, ranked[count]);
			}
			count++;
		}
		orderedWords = better; //assigns better to orderedwords
		int max1 = 0;
		int n1 = 0;
		while(n1 +1 < words.size()) { //does basically the same thing but with the pair hashtable (lines 75-101)
			String word1 = words.get(n1);
			String word2 = words.get(n1+1);
			int potmax = wordPairCount(word1, word2);
			if(potmax > max1)
				max1 = potmax;
			n1++;
		}
		String[] ranked1 = new String[max1+1];
		int i1 = 0;
		while(i1 +1 < words.size()) {
			String word1 = words.get(i1);
			String word2 = words.get(i1+1);
			int index = wordPairCount(word1, word2);
			ranked1[index]= word1 + " " + word2;
			i1++;
		}
		ArrayList<String> better1 = new ArrayList<String>();
		int count1 = 0;
		while(count1 < ranked1.length) {
			if(ranked1[count1] != null)
			{
				better1.add(0, ranked1[count1]);
			}
			count1++;
		}
		orderedPairWords = better1;
		String[] ordered = orderedWords.toArray(new String[orderedWords.size()]); //turns orderdwords array list to ordered array 
		mostCommon = ordered; //assigns most common to ordered
		String[] right = new String[ordered.length]; //makes reverse array where sorted order is opposite
		for(int n2 = 0; n2 < right.length; n2++) {
			right[n2] = ordered[ordered.length-n2-1]; //loads up reverse array
		}
		leastCommon = right; //assigns least common to reversed array
		String[] orderedPairs = orderedPairWords.toArray(new String[orderedPairWords.size()]); //same but with the pairs
		mostCommonPairs = orderedPairs;
	}
	public WordStat(String[] array){ //this is literally all the same code from the first constructor except we pass through an array for the scanner and not a filenmae
		Tokenizer read = new Tokenizer(array);
		words = read.wordList();
		individual = new HashTable(words.size()*2);
		pairs = new HashTable(words.size()*3);
		int n = 0;
		while(n < words.size()) {
			String put = words.get(n);
			int value = individual.get(put);
			if(value == -1) {
				individual.put(put, 1);
			}
			else {
				value+=1;
				individual.update(put, value);
			}
			n++;
		}
		int pair = 0;
		while(pair+1 < words.size()) {
			String combined = words.get(pair)+" " +words.get(pair+1);
			int value = pairs.get(combined);
			if(value == -1) {
				pairs.put(combined, 1);
			}
			else {
				value+=1;
				pairs.update(combined, value);
			}
			pair++;
		}
		int max = 0;
		int j = 0;
		while(j < words.size()) {
			String word = words.get(j);
			int check = wordCount(word);
			if(check > max)
				max = check;
			j++;
		}
		String[] ranked = new String[max+1];
		int i = 0;
		while(i < words.size()) {
			String check = words.get(i);
			int index = wordCount(check);
			ranked[index]= check;
			i++;
		}
		ArrayList<String> better = new ArrayList<String>();
		int count = 0;
		while(count < ranked.length) {
			if(ranked[count] != null)
			{
				better.add(0, ranked[count]);
			}
			count++;
		}
		orderedWords = better;
		int max1 = 0;
		int n1 = 0;
		while(n1 +1 < words.size()) {
			String word1 = words.get(n1);
			String word2 = words.get(n1+1);
			int potmax = wordPairCount(word1, word2);
			if(potmax > max1)
				max1 = potmax;
			n1++;
		}
		String[] ranked1 = new String[max1+1];
		int i1 = 0;
		while(i1 +1 < words.size()) {
			String word1 = words.get(i1);
			String word2 = words.get(i1+1);
			int index = wordPairCount(word1, word2);
			ranked1[index]= word1 + " " + word2;
			i1++;
		}
		ArrayList<String> better1 = new ArrayList<String>();
		int count1 = 0;
		while(count1 < ranked1.length) {
			if(ranked1[count1] != null)
			{
				better1.add(0, ranked1[count1]);
			}
			count1++;
		}
		orderedPairWords = better1;
		String[] ordered = orderedWords.toArray(new String[orderedWords.size()]);
		mostCommon = ordered;
		String[] reverse = ordered;
		String[] right = new String[reverse.length];
		for(int n2 = 0; n2 < right.length; n2++) {
			right[n2] = reverse[reverse.length-n2-1];
		}
		leastCommon = right;
		String[] orderedPairs = orderedPairWords.toArray(new String[orderedPairWords.size()]);
		mostCommonPairs = orderedPairs;
	}
	public int wordCount(String word) { 
		int value = individual.get(word);
		if(value == -1) { //if the value is -1, not in hashtable return 0
			return 0;
		}
		else {
			return value; //returns value
		}
	}
	public int wordPairCount(String w1, String w2) { //basically same as wordcount but with pair hashtable and have to concatenate the words to produce the one in the hashtable if it is there
		String together = w1+" " +w2;
		int value = pairs.get(together);
		if(value == -1) {
			return 0;
		}
		else {
			return value;
		}
	}
	public int wordRank(String word) { //its the index of the arraylist plus 1 so swag
		word = word.toLowerCase();
		word = word.trim();
		word = word.replaceAll("\\p{Punct}", "");
		return orderedWords.indexOf(word)+1;	
		} 
	
	public int wordPairRank(String w1, String w2){ //lit the same thing as wordRank love except clean the words up
		w1 = w1.toLowerCase();
		w1 = w1.trim();
		w1 = w1.replaceAll("\\p{Punct}", "");
		w2 = w2.toLowerCase();
		w2 = w2.trim();
		w2 = w2.replaceAll("\\p{Punct}", "");
		
		return orderedPairWords.indexOf(w1 + " " + w2)+1;	
	}
	public String[] mostCommonWords(int k) {
		return Arrays.copyOf(mostCommon, k, String[].class); //returns slice of most common array till k
	}
	public String[] leastCommonWords(int k) {
		return Arrays.copyOf(leastCommon, k, String[].class); //returns slice of least common array till k
	}
	public String[] mostCommonWordPairs(int k) {
		return Arrays.copyOf(mostCommonPairs, k, String[].class); //returns slice of most common pair array till k
	}
	public String[] mostCommonCollocs(int k, String baseWord, int i) {
		String[] collocs = new String[k]; //new string array of length k
		if(i == -1) { //if preceding check
			int count = 0;
			int iterator = 0;
			while(count < k && iterator < mostCommonPairs.length) { //while you still have to find the most common collocations
				String[] pair = mostCommonPairs[iterator].split(" "); //split the pair since stored with a space
				if(pair[1].equals(baseWord)) { //if the second word is the base word
					collocs[count] = pair[0]; //add in the first word 
					count++; //iterate count to make sure not greater than k
				}
				iterator++; //iterate iterator to make sure not out of bounds
			}
		}
		else { //basically the same thing but instead you check if index 0 is the base word and add the second word to the collocs array 
			int count = 0;
			int iterator = 0;
			while(count < k && iterator < mostCommonPairs.length) {
				String[] pair = mostCommonPairs[iterator].split(" ");
				if(pair[0].equals(baseWord)) {
					collocs[count] = pair[1];
					count++;
				}
				iterator++;
			}
		}
		return collocs; //return the collocation array 
	}
	public static void main(String[] args) throws FileNotFoundException
    {
		WordStat raf = new WordStat("C:\\Users\\kashi\\Desktop\\raf_lyrics.txt");
		int rafcount = raf.wordCount("raf");
		String[] common = raf.mostCommonWords(6);
		String[] commonpairs = raf.mostCommonWordPairs(3);
		String[] mostCommonCollocs = raf.mostCommonCollocs(2, "raf", -1);
		System.out.println(rafcount);
		System.out.println(Arrays.toString(common));
		System.out.println(Arrays.toString(commonpairs));
		System.out.println(Arrays.toString(mostCommonCollocs));
		
    }
}

