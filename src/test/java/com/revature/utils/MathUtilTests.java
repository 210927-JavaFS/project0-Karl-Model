package com.revature.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MathUtilTests {
	
	public static MathUtil mathUtil;
	public static int i;
	public static int j;
	public static int k;
	public static int result; 
	public static Logger log = LoggerFactory.getLogger(MathUtilTests.class);
	
	@BeforeAll
	public static void setMathUtil() {
		log.info("In setMathUtil");
		mathUtil = new MathUtil();
	}
	
	@BeforeEach
	public void setInts() {
		i = 7;
		j = 5;
		k = 0;
		log.info("In setInts");
	}
	
	@Test
	public void testAdd() {
		log.info("In testAdd");
		result = mathUtil.add(i, j);
		assertEquals(12, result);
	}
	
	@Test
	public void testSubtract() {
		log.info("In testSubtract.");
		result = mathUtil.subtract(i, j);
		assertTrue(result==2);
	}
	
	@Test
	public void testDivideByZero() {
		log.info("In testDivideByZero");
		assertThrows(ArithmeticException.class, () -> mathUtil.divide(i,k));
	}
	
	//While so far we have been testing directly inputs and outputs it can often be more useful to test properties.
	//For example, addition has the commutative property. 
	@Test
	public void testCommutativeAdd() {
		log.info("in testCommutativeAdd");
		assertEquals(mathUtil.add(i, j), mathUtil.add(j, i));
	}
	
	
	@AfterEach
	public void clearResult() {
		result = 0;
		log.info("In clearResult");
	}
	
	@AfterAll 
	public static void clearMathUtil() {
		mathUtil = null;
		log.info("in clearMathUtil");
	}
	
	

}
