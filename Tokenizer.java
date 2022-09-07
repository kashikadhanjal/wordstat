package com.p3.kashika;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Tokenizer {
	private ArrayList<String> wordlist; //creates intital arraylist of words
	private ArrayList<String> normalized; // arraylist of words that are normalized
	public Tokenizer(String file) throws FileNotFoundException {
		Scanner scan = new Scanner(new FileInputStream(file)); //takes file name input and assigns scanner to it
		wordlist = new ArrayList<String>(); //instantiates wordlist and normalized
		normalized = new ArrayList<String>();
		while(scan.hasNextLine()) { //begins reading in file
			String s = scan.nextLine(); //assigns next line to a string
			if(s.equals("") == false) { //if line is empty don't do anything
				s = s.trim(); //take off white space
				String[] line = s.split(" "); //split to get every word around whitespace
				for(int n = 0; n < line.length; n++) {
					wordlist.add(line[n]); //add every word to wordlist
				}
			}
		}
		scan.close(); //stop reading
	}
	public Tokenizer(String[] array1) {
		wordlist = new ArrayList<String>(); //instantiate both arraylists
		normalized = new ArrayList<String>();
		for(int n = 0; n < array1.length; n++) {
			wordlist.add(array1[n]); //read in from array
		}
	}
	
	public ArrayList<String> wordList(){
		int n = 0;
		while(n < wordlist.size()) //iterate through word list
		{
			String word = wordlist.get(n); //get word
			word = word.toLowerCase(); //take to lowercase
			word = word.trim(); //get rid of surrounding whitespace
			word = word.replaceAll("\\p{Punct}", ""); //get rid of all punctuation
			normalized.add(word); //add to normalized list
			n++;
		}
		return normalized; //return normalized list 
	}
}
