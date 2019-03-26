/*
 * Money.java
 */
package edu.northeastern.cs_5004;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Locale;

/**
 * <p>This class assumes <em>decimal currency</em>, without unusual divisions 
 * like 1/5. <tt>Money</tt> objects are immutable. The class uses {@link BigDecimal}
 * for the amount, and {@link Currency} to indicate the currency of the amount.</p> 
 * 
 * <p>BigDecimal allows specifying amounts as doubles or longs, or in the form
 * of an unscaled value and an int scale that can be negative or positive (e.g.
 * 12000 or {12, -3}, 1234.56 or {123456, 2}). BigDecimal can also round to a
 * number of decimal places. Money specifies rounding to the number of decimal
 * places for the Currency using "banker's rounding."</p>
 * 
 * <p>This version of Money uses the currency for the default {@link Locale}. 
 * An enhanced version would enable creating Money for other currencies.</p>
 */

final public class Money implements Comparable<Money> {
	
	
	
	@SuppressWarnings("serial")
	static public class MismatchedCurrencyException extends RuntimeException{
		/**
		 * Construct instance with message
		 * @param msg the message
		 * @param cause the cause
		 */
		protected MismatchedCurrencyException(String msg) {
			super(msg);
		}
		/**
		 * Construct instance with message
		 * @param msg the message
		 * @param cause the cause
		 */
		protected MismatchedCurrencyException(String msg, Throwable cause) {
			super(msg, cause);
		}
	}
	
	
	/**
	 * 
	 * @param aCurrency
	 * @param exchangeRate
	 * @throw NumberFormatException
	 * @return 
	 */
	public Money asMoney(Currency aCurrency, double exchangeRate) {
		if(exchangeRate<0) {
			throw new MismatchedCurrencyException("exchange rate less than 0");
		}
		return new Money(amount.multiply(BigDecimal.valueOf(exchangeRate)),aCurrency);
	}
	 
	/** The amount */
	private BigDecimal amount;
	
	/** The currency */
	private Currency currency;
	
	/** Rounding mode for money. This style of rounding introduces the least bias. 
	 * It is also called bankers' rounding, or round-to-even.
	 */
	private static final RoundingMode rounding = RoundingMode.HALF_EVEN;
	
	/**
	 * Initialize money with an amount and currency.
	 * 
	 * @param amount the amount
	 * @param currency the currency
	 */
	Money(BigDecimal amount, Currency currency) {  // package to allow testing
		this.currency = currency;
		this.amount = amount.setScale(currency.getDefaultFractionDigits(), rounding);
	}
	
	/**
	 * Initialize money with an amount and currency.
	 * 
	 * @param amount the amount
	 * @param currency the currency
	 */
	Money(double amount, Currency currency) {  // package to allow testing
		this(BigDecimal.valueOf(amount), currency);
	}

	/**
	 * Initialize money with an amount and the currency of the default locale.
	 * 
	 * @param amount the amount
	 * @param currency the currency
	 */
	public Money(BigDecimal amount) {
		this(amount, Currency.getInstance(Locale.getDefault()));
	}
	
	/**
	 * Initialize money with an amount and the currency of the default locale.
	 * 
	 * @param amount the amount
	 * @throws NumberFormatException for +/- infinity and NaN
	 */
	public Money(double amount) {
		this(BigDecimal.valueOf(amount));
	}

	/**
	 * Gets the currency of this money.
	 * 
	 * @return the currency of this money.
	 */
	public Currency getCurrency() {
		return currency;
	}
	
	/**
	 * Get the amount of money.
	 * 
	 * @return the amount of money
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	
	/**
	 * Determines whether other money is in the same currency.
	 * 
	 * @param money the other money
	 * @return true if in the same currency
	 */
	public boolean isSameCurrencyAs(Money money) {
		boolean result = currency.equals(money.currency);
		return result; 		
	}
	
	  /**
	  * Tests for equality with other money.
	  * 
	  * @param money other money
	  */
	@Override
	public boolean equals(Object money) {
		if(!this.isSameCurrencyAs((Money)money)) {
			throw new MismatchedCurrencyException("Different Currency");
		}
	    if (this == money) return true;
	    if (! (money instanceof Money) ) return false;
	    
	    Money that = (Money)money;
	    boolean result =    amount.equals(that.amount)
	    				 && currency.equals(that.currency);
	    return result;
	}

	/**
	 * Compare this money with other money. First compares the amount
	 * and then the currency codes if the values are equal
	 * 
	 * @param money other money
	 * @return <0 if this money < other money, >0 if this money > other money,
	 *   and 0 if this money equals other money.
	 */
	@Override
	public int compareTo(Money money) {
		if(!this.isSameCurrencyAs((Money)money)) {
			throw new MismatchedCurrencyException("Different Currency");
		}
	    final int EQUAL = 0;
	    
	    if ( this == money ) return EQUAL;

	    int comparison = amount.compareTo(money.amount);
	    if ( comparison != EQUAL ) return comparison;

	    comparison = currency.getCurrencyCode().compareTo(money.currency.getCurrencyCode());
	    if ( comparison != EQUAL ) return comparison;    
	    
	    return EQUAL;
	}
	
	/**
	 * Add money to this money.
	 * 
	 * @param money other money 
	 * @return sum of this money and other money;
	 */
	public Money add(Money... money) {
		BigDecimal sum = this.amount;
		for (Money aMoney : money) {
			if(!this.isSameCurrencyAs(aMoney)) {
				throw new MismatchedCurrencyException("Different Currency");
			}
			sum = sum.add(aMoney.amount);
		}
		return new Money(sum, currency);
	}
	
	/**
	 * Subtract money from this money.
	 * 
	 * @param money other money
	 * @return difference of this money and other money
	 */
	public Money subtract(Money money) {
		if(!this.isSameCurrencyAs((Money)money)) {
			throw new MismatchedCurrencyException("Different Currency");
		}
		BigDecimal difference = amount.subtract(money.amount);
		return new Money(difference, currency);
	}
	
	/**
	 * Multiply this money by money.
	 * 
	 * @param money other money
	 * @return product of this money and other money
	 */
	public Money multiply(Money money) {
		if(!this.isSameCurrencyAs((Money)money)) {
			throw new MismatchedCurrencyException("Different Currency");
		}
		BigDecimal product = amount.multiply(money.amount);
		return new Money(product, currency);
	}
	
	/**
	 * Multiply this money by a factor.
	 * 
	 * @param factor amount to multiply by
	 * @return product of this money and a factor
	 */
	public Money multiply(long factor) {
		
		BigDecimal product = amount.multiply(BigDecimal.valueOf(factor));
		return new Money(product, currency);
	}

	/**
	 * Multiply this money by a factor.
	 * 
	 * @param factor amount to multiply by
	 * @return product of this money and a factor
	 * @throws NumberFormatException for +/- infinity and NaN factor
	 */
	public Money multiply(double factor) {
		BigDecimal product = amount.multiply(BigDecimal.valueOf(factor));
	    return  new Money(product, currency);
	}

	/**
	 * Divide this money by money.
	 * 
	 * @param money other money
	 * @return quotient of this money and other money
	 * @throws ArithmeticException for divide by 0
	 */
	public Money divide(Money money) {
		if(!this.isSameCurrencyAs((Money)money)) {
			throw new MismatchedCurrencyException("Different Currency");
		}
		return new Money(amount.divide(money.amount), currency);
	}
	
	/**
	 * Divide this money by a factor.
	 * 
	 * @param factor amount to multiply by
	 * @return quotient of this money and a factor
	 * @throws ArithmeticException for divide by 0
	 */
	public Money divide(long factor) {
		
		BigDecimal quotient = amount.divide(BigDecimal.valueOf(factor));
		return new Money(quotient, currency);
	}

	/**
	 * Divide this money by a factor.
	 * 
	 * @param factor amount to multiply by
	 * @return quotient of this money and a factor
	 * @throws ArithmeticException for divide by 0
	 * @throws NumberFormatException for +/- infinity and NaN factor
	 */
	public Money divide(double factor) {
		BigDecimal product = amount.divide(BigDecimal.valueOf(factor));
	    return new Money(product, currency);
	}
	
	/**
	 * Remainder of dividing this money by other money.
	 * 
	 * @param money other money 
	 * @return remainder of dividing this money by other money;
	 * @throws ArithmeticException for divide by 0
	 */
	public Money remainder(Money money) {
		if(!this.isSameCurrencyAs((Money)money)) {
			throw new MismatchedCurrencyException("Different Currency");
		}
		BigDecimal remainder = amount.remainder(money.amount);
		return new Money(remainder, currency);
	}
	
	/**
	 * Absolute value of this money.
	 * 
	 * @return absolute value of this money
	 */
	public Money abs() {
		return (amount.signum() < 0) ? new Money(amount.abs(), currency) : this; 
	}
	
	/**
	 * Negative value of this money.
	 * 
	 * @return negative value of this money
	 */
	public Money negate() {
		return new Money(amount.negate(), currency); 
	}
	
	/**
	 * Returns a string representation of the money. The return value uses the runtime's 
	 * <em>default locale</em>, and will not always be suitable for display to an end user.
	 * See {@link #toString(Locale)}.
	 */
	@Override
	public String toString() {
		return toString(Locale.getDefault());
	}
	
	/**
	 * <p>Returns a string representation of the money. The format
	 * is {@link #getCurrency()}.getSymbol(Locale) + " " + {@link #getAmount()}.getPlainString()
	 * using the specified locale.</p>
	 * 
	 * <p>Special process is applied for 1-character currency symbols
	 * The format is -{@link #getCurrency()}.getSymbol(Locale) + {@link #getAmount()}.getPlainString()
	 * for negative, {@link #getCurrency()}.getSymbol(Locale) + {@link #getAmount()}.getPlainString()
	 * for non-negative amount.</p>
	 * 
	 * @param locale the locale
	 * @return the locale specific money string
	 */
	public String toString(Locale locale) {
		String currencySymbol = currency.getSymbol(locale);
		if (currencySymbol.length() > 1) {
			// default format
			return currencySymbol +  " " + amount.toPlainString();			
		} else {
			// eliminate space between one-character currency symbols like '$' and value
			// and places minus sign to left of currency symbol
			return ((amount.doubleValue() < 0.0) ? "-" : "") + currencySymbol + amount.abs().toPlainString();
		}
	}	
}
