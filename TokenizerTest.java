package com.p3.kashika;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Assert;
import org.junit.Test;

public class TokenizerTest {
	Tokenizer test;
	
	@Test
	public void wordListTestFile() {
		try {
			test = new Tokenizer("C:\\Users\\kashi\\Desktop\\hello_world.txt.txt");
		}catch(Exception ex) {
			
		}
		ArrayList<String> first = test.wordList();
		Assert.assertEquals("[hello, world, how, are, you, today, i, hope, you, are, doing, well]", first.toString());
        Assert.assertNotEquals("[hello, world,, how, are, you, today,, i, hope, you, are, doing, well.]", first.toString());
	}

}
