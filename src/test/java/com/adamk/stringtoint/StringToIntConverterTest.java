package com.adamk.stringtoint;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StringToIntConverterTest {

    private static final String dataproviderName = "StringToIntConverterTestData";

    @DataProvider(name = dataproviderName)
    public Object[][] getTestData() {
        return new Object[][]{
                //Positive cases
                {"12345", "Positive number", false},
                {"-12345", "Negative Number", false},
                {String.valueOf(Integer.MAX_VALUE), "Largest positive number", false},
                {String.valueOf(Integer.MIN_VALUE), "Smallest negative number", false},
                {"0", "Zero", false},
                {"00000000", "Zero", false},
                {"-00000000", "Negative Zero", false},
                {"234000034", "Mid Zeros", false},
                {"000034", "Leading Zeros", false},
                {"34000", "Trailing Zeros", false},
                //Negative cases
                {null, "Null Value", true},
                {")*&^#@$)*&^)#@KSFSD(*Y)**JA_DUA(_H#_PE@#", "Garble", true},
                {"0.0", "Decimals not supported", true},
                {"123,456,678", "Commas not supported", true},
                {"123,45,6678", "Commas not supported", true},
                {"123458973409573498508934750347089580347534098573498", "Too large number", true},
                {"-123458973409573498508934750347089580347534098573498", "Too large negative number", true},
        };
    }

    /**
     * Make sure our implementation of String to Int behaves similarly to the Java implementation Integer.parseInt(String)
     *
     * @param testData        A string to parse for this test
     * @param description     Description of this test case
     * @param expectException Whether this test case expects an exception
     */
    @Test(dataProvider = dataproviderName)
    public void testStringToIntConverter(String testData, String description, boolean expectException) {
        StringToIntConverter stringToIntConverter = new StringToIntConverter();

        Integer actualResult;
        try {
            actualResult = stringToIntConverter.convertStringToInteger(testData);
        } catch (Exception e) {
            if (!expectException) {
                Assert.fail(String.format("Didn't expect an exception but got %s, message %s", e.getClass(), e.getMessage()));
            }
            if (e.getClass() != IllegalArgumentException.class) {
                Assert.fail(String.format("Did expect an exception but got %s instead of %s", e.getClass(), IllegalArgumentException.class.getName()));
            }
            //If were here, we got an expected exception and it must be the expected IllegalArgumentException so we're happy!
            return;
        }

        Integer expectedResult = Integer.parseInt(testData);
        Assert.assertEquals(actualResult, expectedResult, description);
    }

}
