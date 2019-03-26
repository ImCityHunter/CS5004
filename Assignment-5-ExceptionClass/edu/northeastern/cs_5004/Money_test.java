/**
 *  Money_test.java
 */
package edu.northeastern.cs_5004;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import org.junit.FixMethodOrder;  
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runners.MethodSorters;

import edu.northeastern.cs_5004.Money.MismatchedCurrencyException;

import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)




/**
 * This class performs unit tests for the Money class.
 */
public class Money_test {
	
	/**
	 * Test asMoney
	 */
	@Test
	public void test_asMoney() {
		Locale USA = Locale.US;
		Locale Taiwan = Locale.TAIWAN;
		Currency USD = Currency.getInstance(USA);
		Currency TWD = Currency.getInstance(Taiwan);
		
		Locale.setDefault(USA);
		Money money1 = new Money(100);
		assertEquals(USD,money1.getCurrency());
		double TWtoUSA = 30.00;
		Money money2 = money1.asMoney(TWD, TWtoUSA);
		assertEquals("TWD 3000.00", money2.toString());
		
		try {
			money2 = money1.asMoney(TWD, Double.NaN);
			System.out.println("here");
			fail();
		}
		catch(NumberFormatException e) {
		}
	}
	/**
	 * Test MismatchedCurrencyException
	 */
	@Test
	public void test_diffCurrencies_Money() {
		Locale USA = Locale.US;
		Locale Taiwan = Locale.TAIWAN;
		Currency USD = Currency.getInstance(USA);
		Currency TWD = Currency.getInstance(Taiwan);
		
		Locale.setDefault(USA);
		Money money1 = new Money(100);
		assertEquals(USD,money1.getCurrency());
		double TWtoUSA = 30.00;
		Money money2 = money1.asMoney(TWD, TWtoUSA);
		
		try {
			money1.equals(money2);
			fail(); //why write fail here?
		}
		catch(MismatchedCurrencyException e){
			
		}
		
		try {
			money1.compareTo(money2);
			fail();
		}
		catch(MismatchedCurrencyException e){
			
		}
		
		try {
			money1.add(money2);
			fail();
		}
		catch(MismatchedCurrencyException e){
			
		}
		
		try {
			money1.subtract(money2);
			fail();
		}
		catch(MismatchedCurrencyException e){
			
		}
		
		try {
			money1.multiply(money2);
			fail();
		}
		catch(MismatchedCurrencyException e){
			
		}
		
		try {
			money1.divide(money2);
			fail();
		}
		catch(MismatchedCurrencyException e){
			
		}
		
		try {
			money1.remainder(money2);
			fail();
		}
		catch(MismatchedCurrencyException e){
			
		}
		
		
		
		
	}
	
	
	/**
	 * Test construction Money.
	 */
	@Test
	public void test_0010_Money() {
		// set locale and currency for testing purposes
		final Locale locale = Locale.US;
		Locale.setDefault(locale);
		final Currency currency = Currency.getInstance(locale);
		
		// construct instance using double with more decimal places than currency
		Money money1 = new Money(10.1051);
		assertEquals(10.11, money1.getAmount().doubleValue(), 0.0);
		assertEquals(currency, money1.getCurrency());

		// check string conversion with default and UK locale
		// special handling for 1-char currency symbol
		assertEquals("$10.11", money1.toString());
		assertEquals("USD 10.11", money1.toString(Locale.UK));

		// construct instance using BigDecimal with unscaled long value and int scale,
		// with more decimal places than currency
		Money money2 = new Money(BigDecimal.valueOf(-101051, 4));  // -10.1051
		assertEquals(-10.11, money2.getAmount().doubleValue(), 0.0);
		assertEquals(currency, money2.getCurrency());
		
		// check string conversion with default and UK locale
		// special handling for 1-char currency symbol and negative number
		assertEquals("-$10.11", money2.toString());
		assertEquals("USD -10.11", money2.toString(Locale.UK));

		// test handling of invalid doubles 
		try {
			new Money(Double.NaN);
			fail();
		} catch (NumberFormatException ex) {
		}
		
		try {
			new Money(Double.POSITIVE_INFINITY);
			fail();
		} catch (NumberFormatException ex) {
		}
	}
	
	/**
	 * Test compareTo and equals operations.
	 */
	@Test
	public void test_0020_compareTo() {
		// set locale and currency for testing purposes
		final Locale locale = Locale.US;
		Locale.setDefault(locale);
		
		Money money1 = new Money(10.1051);
		Money money2 = new Money(-10.1051);

		// test comparison operators 
		assertTrue(money1.compareTo(money2) > 0);
		assertTrue(money2.compareTo(money1) < 0);
		assertTrue(money2.compareTo(money2) == 0);
		
		// test equals given that values are scaled to currency
		Money money3 = new Money(BigDecimal.valueOf(1, -2));
		Money money4 = new Money(BigDecimal.valueOf(100, 0));
		assertEquals(money3,money4);
		assertTrue(money3.compareTo(money4) == 0);

	}

	/**
	 * Test add operations.
	 */
	@Test
	public void test_0030_add() {
		// set locale and currency for testing purposes
		final Locale locale = Locale.US;
		Locale.setDefault(locale);
		
		Money money1 = new Money(10.1051);
		Money money2 = new Money(-10.1051);

		// test add operations on Money
		Money money3;
		
		// test addition of number with its negative
		money3 = money1.add(money2);
		assertEquals(0.0, money3.getAmount().doubleValue(), 0.0);
		assertEquals(money1.getCurrency(), money3.getCurrency());
		
		// test addition of number with twice its negative
		money3 = money1.add(money2, money2);
		assertEquals(-10.11, money2.getAmount().doubleValue(), 0.0);
		assertEquals(money1.getCurrency(), money3.getCurrency());
		
		// test addition of 0.1 nine times itself
		money3 = new Money(0.1);
		money3 = money3.add(money3,money3,money3,money3,money3,money3,money3,money3,money3);
		assertEquals(1.0, money3.getAmount().doubleValue(), 0.0);		
	}

	/**
	 * Test subtract operations.
	 */
	@Test
	public void test_0040_subtract() {
		// set locale and currency for testing purposes
		final Locale locale = Locale.US;
		Locale.setDefault(locale);
		//final Currency currency = Currency.getInstance(locale);
		
		Money money1 = new Money(10.1051);
		Money money2 = new Money(-10.1051);

		// test subtract operations on Money
		Money money3;
		
		// test subtraction
		money3 = money1.subtract(money2);
		assertEquals(20.22, money3.getAmount().doubleValue(), 0.0);
		assertEquals(money1.getCurrency(), money3.getCurrency());
		
	}

	/**
	 * Test multiply operations.
	 */
	@Test
	public void test_0050_multiply() {
		// set locale and currency for testing purposes
		final Locale locale = Locale.US;
		Locale.setDefault(locale);
		
		Money money1 = new Money(10.1051);
		Money money2 = new Money(-10.1051);

		// test multiply operations on Money
		Money money3;
		
		// test multiplication of number and its negative
		money3 = money1.multiply(money2);
		assertEquals(-102.21, money3.getAmount().doubleValue(), 0.0);
		assertEquals(money1.getCurrency(), money3.getCurrency());
		
		// test multiplication by long
		money3 = money1.multiply(-1);
		assertEquals(money2, money3);
		
		money3 = money2.multiply(-1);
		assertEquals(money1, money3);

		// test multiplication by double
		money3 = money1.multiply(-1.00);
		assertEquals(money2, money3);
		
		money3 = money2.multiply(-1.0);
		assertEquals(money1, money3);

		// test multiplication by double that has more decimal places that currency
		// -- rounding only happens after multiplication
		money3 = money1.multiply(1.005);  
		assertEquals(10.16, money3.getAmount().doubleValue(), 0.0);
		assertEquals(money1.getCurrency(), money3.getCurrency());

		// test multiplication by invalid double
		try {
			money2.multiply(Double.NaN);
			fail();
		} catch (NumberFormatException e) {
		}
	}

	/**
	 * Test divide operations.
	 */
	@Test
	public void test_0070_divide() {		
		// set locale and currency for testing purposes
		final Locale locale = Locale.US;
		Locale.setDefault(locale);
		
		Money money1 = new Money(10.1051);
		Money money2 = new Money(-10.1051);

		// test divide operations on Money
		Money money3;
		
		// test division of number and its negative
		money3 = money1.divide(money2);
		assertEquals(-1.0, money3.getAmount().doubleValue(), 0.0);
		assertEquals(money1.getCurrency(), money3.getCurrency());

		// test division by long
		money3 = money1.divide(-1);
		assertEquals(money2, money3);
		
		money3 = money2.divide(-1);
		assertEquals(money1, money3);

		// test division by double
		money3 = money1.divide(-1.0);
		assertEquals(money2, money3);
		
		money3 = money2.divide(-1.0);
		assertEquals(money1, money3);

		// test division by invalid zero
		try {
			money1.divide(new Money(0.0));
			fail();
		} catch (ArithmeticException e) {
		}
		
		// test division by invalid double
		try {
			money2.divide(Double.NaN);
			fail();
		} catch (NumberFormatException e) {
		}

	}

	/**
	 * Test remainder operations.
	 */
	@Test
	public void test_0080_remainder() {
		
		// set locale and currency for testing purposes
		final Locale locale = Locale.US;
		Locale.setDefault(locale);
		
		Money money1 = new Money(10.1051);
		Money money2 = new Money(-10.1051);

		// test remainder operations on Money
		Money money3;
		
		// test remainder of number with its negative
		money3 = money1.remainder(money2);
		assertEquals(0.0, money3.getAmount().doubleValue(), 0.0);
		assertEquals(money1.getCurrency(), money3.getCurrency());
		
		// test remainder of number with 1
		money3 = money1.remainder(new Money(BigDecimal.ONE));
		assertEquals(0.11, money3.getAmount().doubleValue(), 0.0);
		assertEquals(money1.getCurrency(), money3.getCurrency());
	}

	/**
	 * Test negate and abs operations.
	 */
	@Test
	public void test_0090_abs() {
		
		// set locale and currency for testing purposes
		final Locale locale = Locale.US;
		Locale.setDefault(locale);
		
		Money money1 = new Money(10.1051);
		Money money2 = new Money(-10.1051);

		// test negate and abs operations on Money
		Money money3;
		
		// test negation
		money3 = money1.negate();
		assertEquals(money2, money3);
		
		money3 = money2.negate();
		assertEquals(money1, money3);

		// test abs
		money3 = money1.abs();
		assertEquals(money1, money3);
		
		money3 = money2.abs();
		assertEquals(money1, money3);

	}

	/**
	 * Run the tests in this class.
	 * 
	 * @param args the program arguments
	 */
	public static void main(String[] args) {
	    Result result = JUnitCore.runClasses(Money_test.class);
	    
	    System.out.println("[Unit Test Results]");
	    System.out.println();
	    
	    if (result.getFailureCount() > 0) {
	    	System.out.println("Test failure details:");
		    for (Failure failure : result.getFailures()) {
		       System.out.println(failure.toString());
		    }
		    System.out.println();
	    }
	    
	    int passCount = result.getRunCount()-result.getFailureCount()-result.getIgnoreCount(); 
	    System.out.println("Test summary:");
	    System.out.println("* Total tests = " + result.getRunCount());
	    System.out.println("* Passed tests: " + passCount);
	    System.out.println("* Failed tests = " + result.getFailureCount());
	    System.out.println("* Inactive tests = " + result.getIgnoreCount());
	}
}
