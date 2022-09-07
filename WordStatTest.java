package com.p3.kashika;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class WordStatTest {
	WordStat raf;
	@Test
	public void testMostCommonWords() {
		try {
			raf = new WordStat("C:\\Users\\kashi\\Desktop\\raf_lyrics.txt");
		}
		catch(Exception ex) {
			
		}
		String[] common = raf.mostCommonWords(6);
		Assert.assertEquals("[raf, what, rick, homies, this, share]", Arrays.toString(common));
        Assert.assertNotEquals("[raf, what, i, rick, homies, share]", Arrays.toString(common));
	}

	@Test
	public void testMostCommonWordPairs() {
		try {
			raf = new WordStat("C:\\Users\\kashi\\Desktop\\raf_lyrics.txt");
		}
		catch(Exception ex) {
			
		}
		String[] commonpairs = raf.mostCommonWordPairs(3);
		Assert.assertEquals("[my raf, raf what, share raf]", Arrays.toString(commonpairs));
        Assert.assertNotEquals("[raf what, my raf, share raf]", Arrays.toString(commonpairs));
	}

	@Test
	public void testMostCommonCollocs() {
		try {
			raf = new WordStat("C:\\Users\\kashi\\Desktop\\raf_lyrics.txt");
		}
		catch(Exception ex) {
			
		}
		String[] mostCommonCollocs = raf.mostCommonCollocs(2, "raf", -1);
		Assert.assertEquals("[my, share]", Arrays.toString(mostCommonCollocs));
        Assert.assertNotEquals("[share, my]", Arrays.toString(mostCommonCollocs));
	}

}
