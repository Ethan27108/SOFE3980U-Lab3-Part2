package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary {
	private String number = "0"; // string containing the binary value '0' or '1'

	/**
	 * A constructor that generates a binary object.
	 *
	 * @param number a String of the binary values. It should contain only zeros or
	 *               ones with any length and order. otherwise, the value of "0"
	 *               will be stored. Trailing zeros will be excluded and empty
	 *               string will be considered as zero.
	 */
	public Binary(String number) {
		if (number == null || number.isEmpty()) {
			this.number = "0"; // Default to "0" for null or empty input
			return;
		}

		// Validate the binary string (only '0' or '1' allowed)
		for (int i = 0; i < number.length(); i++) {
			char ch = number.charAt(i);
			if (ch != '0' && ch != '1') {
				this.number = "0"; // Default to "0" for invalid input
				return;
			}
		}

		// Remove leading zeros
		int beg;
		for (beg = 0; beg < number.length(); beg++) {
			if (number.charAt(beg) != '0') {
				break;
			}
		}

		// If all digits are '0', ensure number is "0"
		this.number = (beg == number.length()) ? "0" : number.substring(beg);

		// Ensure empty strings are replaced with "0"
		if (this.number.isEmpty()) {
			this.number = "0";
		}
	}

	/**
	 * Return the binary value of the variable
	 *
	 * @return the binary value in a string format.
	 */
	public String getValue() {
		return this.number;
	}

	/**
	 * Adding two binary variables. For more information, visit
	 * <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers
	 * </a>.
	 *
	 * @param num1 The first addend object
	 * @param num2 The second addend object
	 * @return A binary variable with a value of <i>num1+num2</i>.
	 */
	public static Binary add(Binary num1, Binary num2) {
		// the index of the first digit of each number
		int ind1 = num1.number.length() - 1;
		int ind2 = num2.number.length() - 1;
		// initial variable
		int carry = 0;
		String num3 = ""; // the binary value of the sum
		while (ind1 >= 0 || ind2 >= 0 || carry != 0) // loop until all digits are processed
		{
			int sum = carry; // previous carry
			if (ind1 >= 0) { // if num1 has a digit to add
				sum += (num1.number.charAt(ind1) == '1') ? 1 : 0; // convert the digit to int and add it to sum
				ind1--; // update ind1
			}
			if (ind2 >= 0) { // if num2 has a digit to add
				sum += (num2.number.charAt(ind2) == '1') ? 1 : 0; // convert the digit to int and add it to sum
				ind2--; // update ind2
			}
			carry = sum / 2; // the new carry
			sum = sum % 2; // the resultant digit
			num3 = ((sum == 0) ? "0" : "1") + num3; // convert sum to string and append it to num3
		}
		Binary result = new Binary(num3); // create a binary object with the calculated value.
		return result;

	}

	public static Binary OR(Binary num1, Binary num2) {
		int ind1 = num1.number.length() - 1;
		int ind2 = num2.number.length() - 1;

		String num3 = ""; // The result in binary
		while (ind1 >= 0 || ind2 >= 0) { // Process until both numbers are done
			char digit1 = (ind1 >= 0) ? num1.number.charAt(ind1) : '0';
			char digit2 = (ind2 >= 0) ? num2.number.charAt(ind2) : '0';

			if (digit1 == '1' || digit2 == '1') {
				num3 = "1" + num3;
			} else {
				num3 = "0" + num3;
			}
			ind1--;
			ind2--;
		}
		return new Binary(num3);
	}

	public static Binary AND(Binary num1, Binary num2) {
		int ind1 = num1.number.length() - 1;
		int ind2 = num2.number.length() - 1;

		String num3 = ""; // The result in binary
		while (ind1 >= 0 || ind2 >= 0) { // Process until both numbers are done
			char digit1 = (ind1 >= 0) ? num1.number.charAt(ind1) : '0';
			char digit2 = (ind2 >= 0) ? num2.number.charAt(ind2) : '0';

			if (digit1 == '1' && digit2 == '1') {
				num3 = "1" + num3;
			} else {
				num3 = "0" + num3;
			}
			ind1--;
			ind2--;
		}
		return new Binary(num3);
	}

	public static Binary Multiply(Binary num1, Binary num2) {
		// Initialize the result to "0" (binary)
		Binary result = new Binary("0");

		// Get the second binary number as a string and its length
		String multiplier = num2.number;
		int length = multiplier.length();

		// Iterate over each digit in the multiplier (from right to left)
		for (int i = length - 1; i >= 0; i--) {
			if (multiplier.charAt(i) == '1') {
				// If the current bit in multiplier is 1, add num1 shifted by the correct amount
				// of places
				String shiftedNum1 = num1.number + "0".repeat(length - 1 - i);
				result = add(result, new Binary(shiftedNum1));
			}
		}

		// After adding all shifts, trim leading zeros from the result
		String resultValue = result.getValue();
		if (resultValue.startsWith("0") && resultValue.length() > 1) {
			resultValue = resultValue.substring(1); // Remove leading zeros
		}

		return new Binary(resultValue); // Return the properly formatted result
	}

}
