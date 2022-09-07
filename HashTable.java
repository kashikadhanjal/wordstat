package com.p3.kashika;

public class HashTable {
	private int tableSize; //the size of the table
	private int insertions; //keep track of number of insertions to compute loadfactor
	private HashEntry[] entries; //array of hashentries 
	public HashTable() {
		entries = new HashEntry[100]; //instantiates array with 100 size
		tableSize = 100; //set tablesize and insertion
		insertions = 0;
	}
	public HashTable(int size) {
		entries = new HashEntry[size]; //instantiate array with size given
		tableSize = size; //set tablesize and insertions
		insertions = 0;
	}
	
	private int LinearProbe(String word1) { //private method for linear probing 
		int hashed = Math.abs(word1.hashCode())%tableSize; //find appropriate hashcode for word
		while(entries[hashed] != null && hashed < tableSize && entries[hashed].getKey().equals(word1)!=true) { //if index not empty and not greater than table size and does not already exist
			hashed++; //iterate
			if(hashed == tableSize) { //if equal reset to 0
				hashed = 0;
			}
		}
		return hashed; //return index
	}
	public void put(String key, int value) {
		int index = LinearProbe(key); //find the index
		if(entries[index] != null) { //already exists
			update(entries[index].getKey(), value); //if its already there
		}
		else {
			entries[index] = new HashEntry(key, value); //enter it in bae
			insertions++; //keep track
		}
		double load = ((double)insertions)/((double)tableSize);
		if( load > 0.5) { //rehashing code (if load factor over 0.5, more than half full)
			HashTable newTable = new HashTable(tableSize*2); //create new table with double size
			for(int i = 0; i < tableSize; i++) {
				if(entries[i] != null) { //move all non null entries into new table
					newTable.put(entries[i].getKey(), entries[i].getValue());
				}
			}
			entries = newTable.entries; //set new tables entries to this entry 
			tableSize = tableSize*2; //then double the table size
		}
	}
	
	public void put(String key, int value, int hashCode) {
		int index = hashCode % tableSize; //get the index from the given hashcode
		if(entries[index] == null) { //if empty put it in
			entries[index] = new HashEntry(key, value);
			insertions++; //keep track of number of things
		}
		else if(entries[index] != null && entries[index].getKey().equals(key)==true) {
			update(entries[index].getKey(), value);
		}
		else {
			while(entries[index] != null && index < tableSize) { //if not empty go through and find when empty (basically linear probe)
				index++;
				if(index == tableSize) { //reset if over
					index = 0;
				}
			}
			entries[index] = new HashEntry(key, value); //put it in at empty index
			insertions++; //keep track of number of things
		}
		double load = ((double)insertions)/((double)tableSize);
		if( load > 0.5) { //its the same rehashing method from the non-given hashcode put
			HashTable newTable = new HashTable(tableSize*2);
			for(int i = 0; i < tableSize; i++) {
				if(entries[i] != null) {
					newTable.put(entries[i].getKey(), entries[i].getValue());
				}
			}
			entries = newTable.entries;
			tableSize = tableSize*2;
		}
	}
	public void update(String key, int value) {
		int expected = Math.abs(key.hashCode())%tableSize; //find expected location
		while(entries[expected] != null && entries[expected].getKey().equals(key) != true) { //iterate through while its not null (means it might still exist) and key is not equal to the hashentry key
			expected++;
			if(expected == tableSize) { 
				expected = 0;
			}
		}
		if(entries[expected] == null) { //if you hit a null its not there so insert with the value
			put(key, value);
		}
		else {
			entries[expected].setValue(value); //update the value if you found it
		}
	}
	public int get(String key) {
		int expected = Math.abs(key.hashCode())%tableSize; //where its supposed to be
		while(entries[expected] != null && entries[expected].getKey().equals(key) != true) { //linear probe
			expected++;
			if(expected == tableSize) {
				expected = 0;
			}
		}
		if(entries[expected] == null) { //if you hit null its not there return -1
			return -1;
		}
		else {
			return entries[expected].getValue(); //return the value if found
		}
	}
	public int get(String key, int hashCode) {
		int index = hashCode % tableSize; //find expected index
		while(entries[index] != null && entries[index].getKey().equals(key) != true) { //linear probe till you get the expected key or you hit a null
			index++;
			if(index == tableSize) {
				index = 0;
			}
		}
		if(entries[index] == null) { //if you get a null return -1 its not there
			return -1;
		}
		else {
			return entries[index].getValue(); //return value if you get it 
		}
	}
	
}
