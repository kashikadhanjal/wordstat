package com.p3.kashika;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class HashTableTest {

	@Test
	public void putnohashtest() {
		HashTable h = new HashTable(5);
		h.put("kate", 1);
		h.put("hannah", 1);
		h.put("zoe", 3);
		h.put("zoe", 5);
        Assert.assertEquals(5, h.get("zoe"));
        Assert.assertNotEquals(3, h.get("zoe"));
	}
	public void puthashtest() {
		HashTable h = new HashTable(5);
		h.put("kate", 1, 0);
		h.put("hannah", 1, 1);
		h.put("zoe", 3, 1);
        Assert.assertEquals(1, h.get("hannah", 1));
        Assert.assertNotEquals(0, h.get("hannah", 1));
	}

}
