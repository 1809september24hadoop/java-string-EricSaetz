package com.revature.string;

import org.apache.log4j.Logger;

import com.revature.string.JavaStringSolutions;

public class JavaStringSolutions implements JavaString {
	
	private static final Logger LOGGER = Logger.getLogger(JavaStringSolutions.class);
	
	public static void main(String args[]) {
		JavaStringSolutions solutions = new JavaStringSolutions();
		
		LOGGER.info(solutions.parseInteger("4325"));
		LOGGER.info(solutions.reverse("Hello World"));
		solutions.time(new StringBuilder(), new StringBuffer());
		LOGGER.info(solutions.itContains("Hello World", "World"));
		LOGGER.info(solutions.sort("Hello World"));
		LOGGER.info(solutions.delete("Hello World", 'o'));
		LOGGER.info(solutions.upperLower("Hello World!"));
		
	}
	
	//Parses a string into a number, can begin with a +/- sign
	
	@Override
	public int parseInteger(String number) throws IllegalArgumentException {
		if (number==null) {
			throw new IllegalArgumentException("number cannot be null");
		}
		
		int output = 0;
		for (int i=number.length()-1;i>-1;i--) {
			if (!Character.isDigit(number.charAt(i)) && !(i==0 && (number.charAt(i)=='+' || number.charAt(i)=='-'))) {
				throw new IllegalArgumentException("string must contain only numbers or +/- at the beggining");
			}
			if (i==0 && number.charAt(i)=='-')
				output*=-1;
			else if (i!=0 || number.charAt(i)!='+') {
				output+=(Character.getNumericValue(number.charAt(i)) * (int)Math.pow(10, number.length()-i-1));
			}
		}
		
		return output;
	}

	@Override
	public String reverse(String string) throws IllegalArgumentException {
		if (string==null) {
			throw new IllegalArgumentException();
		}
		String output = "";
		for (char c:string.toCharArray())
			output = c + output;
		return output;
	}

	@Override
	public void time(StringBuilder builder, StringBuffer buffer) throws IllegalArgumentException {
		if (builder==null || buffer==null) {
			throw new IllegalArgumentException("builder and buffer cannot be null");
		}
		
		//BUILDER
		
		long start = System.currentTimeMillis();
		for (int i=0;i<1000000;i++) {
			builder.append("hi");
		}
		
		long difference = System.currentTimeMillis()-start;
		LOGGER.info("Builder Time: " + difference + " ms");
		
		//BUFFER
		
		start = System.currentTimeMillis();
		for (int i=0;i<1000000;i++) {
			buffer.append("hi");
		}
		difference = System.currentTimeMillis()-start;
		LOGGER.info("Buffer Time: " + difference + " ms");
		
		
	}

	@Override
	public boolean itContains(String string, String contains) throws IllegalArgumentException {
		if (string == null || contains == null) {
			throw new IllegalArgumentException("Neither string can be null");
		}
		
		return string.contains(contains);
	}

	@Override
	public String sort(String string) throws IllegalArgumentException {
		if (string==null) {
			throw new IllegalArgumentException("string cannot be null");
		}
		
		
		char[] array = string.toCharArray();
		int min;
		char temp;
		for (int currentNum=0;currentNum<array.length;currentNum++) {
			min = currentNum;
			for (int n=currentNum;n<array.length;n++) {
				if (array[n]<array[min])
					min=n;
			}
			temp=array[currentNum];
			array[currentNum] = array[min];
			array[min]=temp;
		}
		
		return new String(array);
	}

	@Override
	public String delete(String string, char c) throws IllegalArgumentException {
		if (string==null) {
			throw new IllegalArgumentException("string cannot be null");
		}
		return string.replaceAll(c+"","");
	}

	@Override
	public String upperLower(String string) throws IllegalArgumentException {
		if (string==null || string.length()%2!=0) {
			throw new IllegalArgumentException("string cannot be null, and must have an even amount of characters");
		}
		
		return string.substring(0,string.length()/2).toUpperCase()
				+string.substring(string.length()/2,string.length()).toLowerCase();
	}

}
