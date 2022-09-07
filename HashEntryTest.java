package com.p3.kashika;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class HashEntryTest {

	@Test
	public void testGetKey() {
		HashEntry test1 = new HashEntry("hello", 89);
		Assert.assertEquals("hello", test1.getKey());
        Assert.assertNotEquals(89, test1.getKey());
	}

	@Test
	public void testGetValue() {
		HashEntry test1 = new HashEntry("hello", 89);
		Assert.assertEquals(89, test1.getValue());
        Assert.assertNotEquals("hello", test1.getValue());
	}

	@Test
	public void testSetValue() {
		HashEntry test1 = new HashEntry("hello", 89);
		test1.setValue(98);
		Assert.assertEquals(98, test1.getValue());
        Assert.assertNotEquals(89, test1.getValue());
	}

}
