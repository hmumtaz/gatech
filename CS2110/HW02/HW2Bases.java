/**
 * CS 2110 Summer 2016 HW2
 * Part 1 - Coding with bases
 * 
 * @author Hussain Mumtaz
 *
 * Global rules for this file:
 * - You may not use more than 2 conditionals per method. Conditionals are
 *   if-statements, if-else statements, or ternary expressions. The else block
 *   associated with an if-statement does not count toward this sum.
 * - You may not use more than 2 looping constructs per method. Looping
 *   constructs include for loops, while loops and do-while loops.
 * - You may not use nested loops.
 * - You may not declare any file-level variables.
 * - You may not declare any objects, other than String in select methods.
 * - You may not use switch statements.
 * - You may not use the unsigned right shift operator (>>>)
 * - You may not write any helper methods, or call any other method from this or
 *   another file to implement any method.
 * - The only Java API methods you are allowed to invoke are:
 *     String.length()
 *     String.charAt()
 *     String.equals()
 * - You may not invoke the above methods from string literals.
 *     Example: "12345".length()
 * - When concatenating numbers with Strings, you may only do so if the number
 *   is a single digit.
 *
 * Method-specific rules for this file:
 * - You may not use multiplication, division or modulus in any method, EXCEPT
 *   strdtoi.
 * - You may declare exactly one String variable each in itostrb, and itostrx.
 */
public class HW2Bases
{
	/**
	 * strdtoi - Decimal String to int
	 *
	 * Convert a string containing ASCII characters (in decimal) to an int.
	 * You do not need to handle negative numbers. The Strings we will pass in will be
	 * valid decimal numbers, and able to fit in a 32-bit signed integer.
	 * 
	 * Example: strdtoi("123"); // => 123
	 */
	public static int strdtoi(String decimal)
	{
		int stringLength = decimal.length();
        int ASCIIFactor = -48;
		int number = 0;
        for (int i = 0; i < stringLength; i++) {
            number *= 10;
            number += decimal.charAt(i) + ASCIIFactor;
        }
        return number;
	}

	/**
	 * strbtoi - Binary String to int
	 *
	 * Convert a string containing ASCII characters (in binary) to an int.
	 * You do not need to handle negative numbers. The Strings we will pass in will be
	 * valid binary numbers, and able to fit in a 32-bit signed integer.
	 * 
	 * Example: strbtoi("111"); // => 7
	 */
	public static int strbtoi(String binary)
	{
		int stringLength = binary.length();
        int ASCIIFactor = -48;
        int number = 0;
        int digit;
        for (int i = 0; i < stringLength; i++)
        {
            digit = binary.charAt(i) + ASCIIFactor;
            number += digit << (stringLength - i - 1);
        }
        return number;
	}

	/**
	 * strxtoi - Hexadecimal String to int
	 *
	 * Convert a string containing ASCII characters (in hex) to an int.
	 * The input string will only contain numbers and uppercase letters A-F.
	 * You do not need to handle negative numbers. The Strings we will pass in will be
	 * valid hexadecimal numbers, and able to fit in a 32-bit signed integer.
	 * 
	 * Example: strxtoi("A6"); // => 166
	 */
	public static int strxtoi(String hex)
	{
		int stringLength = hex.length();
        int number = 0;
        int ASCIIDecimalFactor = -48;
        int ASCIILetterFactor = -55;
        int ASCIIUppercaseStart = 65;
        int digit;
        for (int i = 0; i < stringLength; i++) {
            if (hex.charAt(i) >= ASCIIUppercaseStart) {
                digit = hex.charAt(i) + ASCIILetterFactor;
            }
            else {
                digit = hex.charAt(i) + ASCIIDecimalFactor;
            }
            number = (number << 4) + digit;
        }
        return  number;
	}

	/**
	 * itostrb - int to Binary String
	 *
	 * Convert a int into a String containing ASCII characters (in binary).
	 * You do not need to handle negative numbers.
	 * The String returned should contain the minimum number of characters necessary to
	 * represent the number that was passed in.
	 * 
	 * Example: itostrb(7); // => "111"
	 */
	public static String itostrb(int binary)
	{
        if (binary == 0)
        {
            return "0";
        }
        String binaryString = "";
        while (binary != 0)
        {
            if ((binary & 1) == 1)
            {
                binaryString = "1" + binaryString;
            }
            else
            {
                binaryString = "0" + binaryString;
            }
            binary = binary >> 1;
        }
        return binaryString;
	}

	/**
	 * itostrx - int to Hexadecimal String
	 *
	 * Convert a int into a String containing ASCII characters (in hexadecimal).
	 * The output string should only contain numbers and uppercase letters A-F.
	 * You do not need to handle negative numbers.
	 * The String returned should contain the minimum number of characters necessary to
	 * represent the number that was passed in.
	 * 
	 * Example: itostrx(166); // => "A6"
	 */
    public static String itostrx(int hex)
    {
        if (hex == 0)
        {
            return "0";
        }
        int ASCIILetterFactor = 55;
        int ASCIINumberFactor = 48;
        String hexString = "";
        int decimal;
        char representation;
        while (hex != 0)
        {
            decimal = hex & 0xF;
            if (decimal > 9) {
                representation = (char) (decimal + ASCIILetterFactor);
            } else {
                representation = (char) (decimal + ASCIINumberFactor);
            }
            hexString = representation + hexString;
            hex = hex >> 4;
        }
        return hexString;
    }
}
