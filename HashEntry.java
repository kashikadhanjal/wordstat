package com.p3.kashika;

public class HashEntry {
	private String yourKey = null; //instantiate yourkey
	private int yourValue = -1; //instantiate your value
	public HashEntry(String key, int value) {
		yourKey = key; //assign key and value
		yourValue = value;
	}
	
	public String getKey() {
		return yourKey; //returns key
	}
	
	public int getValue() {
		return yourValue; //returns value
	}
	
	public void setValue(int value) {
		yourValue = value; //set value
	}
}
