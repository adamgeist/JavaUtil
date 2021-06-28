package com.adamk.stringtoint;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringToIntConverter {

    /**
     * Converts a string to an integer, closely resembles Integer.parseInt(String)
     *
     * @param inputString String to parse
     * @return Integer value of the input string
     */
    public int convertStringToInteger(String inputString) {
        //Check the format of our input
        validate(inputString);
        //Remove the leading dash if there is one and mark this number as negative
        boolean isNegative = false;
        if (inputString.startsWith("-")) {
            isNegative = true;
            inputString = inputString.substring(1);
        }

        int numberResult = 0;
        int factor = 1;
        //Iterate from least to most significant digits
        for (int i = inputString.length() - 1; i >= 0; i--) {
            //Convert current char to int
            int charIntVal = inputString.charAt(i) - '0';
            //Add trailing zeros before adding this digit
            int significantInt = charIntVal * factor;
            numberResult = sumIntegers(numberResult, significantInt, isNegative);
            //Increase the factor by an order of magnitude
            factor *= 10;
        }
        if (isNegative) {
            numberResult *= -1;
        }
        return numberResult;
    }

    /**
     * Sums two integer values and throws IllegalArgumentException if they cause an overflow
     *
     * @param firstInt        The first integer to sum
     * @param secondInt       The second integer to sum
     * @param parsingNegative Add one to the overflow threshold if we are parsing negative
     * @return Sum of the two integers
     */
    private int sumIntegers(int firstInt, int secondInt, boolean parsingNegative) {
        //Check for overflows
        //We know that num1 and num2 are always positive here
        long sum = (long) firstInt + (long) secondInt;
        //Make sure our overflow threshold adjusts for positive and negative numbers (negative stores 1 extra value)
        long overflowThreshold = (parsingNegative ? (long) Integer.MIN_VALUE * (long) -1 : Integer.MAX_VALUE);
        if (sum > overflowThreshold) {
            //We're not going to allow overflows
            throw new IllegalArgumentException("The input string is too large of a number for java int");
        }
        return (int) sum;
    }

    /**
     * Validate the input string, throws IllegalArgumentException if it cannot be parsed
     *
     * @param str String to validate
     */
    private void validate(String str) {
        //Don't allow null input
        if (str == null) {
            throw new IllegalArgumentException("Input string was null");
        }
        //Regular expression for validating the string format
        Pattern p = Pattern.compile("([-+]?+\\d+)");
        Matcher m = p.matcher(str);

        if (!m.matches()) {
            throw new IllegalArgumentException("Not a valid integer, check the input format");
        }
    }
}
