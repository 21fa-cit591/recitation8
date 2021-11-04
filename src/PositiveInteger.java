
public class PositiveInteger {

    // for storing the positive integer
    int num;

    /**
     * Constructs a new PositiveInteger object
     * 
     * @param number - positive integer
     */
    public PositiveInteger(int number) throws NotPositiveException {
        if (number < 1) {
            throw new NotPositiveException(number + " is not a positive integer!");
        }
        num = number;
    }

    /**
     * Checks if the positive integer is a perfect number: equal to the sum of all
     * its unique factors, not including itself
     * 
     * @return - true if perfect
     */
    public boolean isPerfect() {
        if (num == 1) { // 1 cannot be perfect
            return false;
        }
        int sum = 1; // 1 is always a factor, so start sum at 1
        for (int i = 2; i <= Math.sqrt(num); i++) { // iterate from 2 to sqrt(num), inclusive
            if (num % i == 0) {
                sum += i + (num / i); // if i is a factor, add i and num/i
            }
            if (i == Math.sqrt(num)) {
                sum -= i; // if i is the square root of num, subtract i to delete overcounting
            }
        }
        if (num == sum) {
            return true; // return true if sum = num
        }
        return false;
    }

    /**
     * Checks if positive integer is abundant: the sum of its unique factors is
     * greater than the number itself
     * 
     * @return - true if abundant
     */
    public boolean isAbundant() {
        if (num == 1) {
            return false;
        }
        int sum = 1; // 1 is always a factor, so start sum at 1 (keep out of loop to prevent adding
                     // the number itself)
        for (int i = 2; i < Math.sqrt(num); i++) { // iterate from 2 to sqrt(num), inclusive
            if (num % i == 0) {
                sum += i + (num / i); // if i is a factor, add i and num/i
            }
            if (i == Math.sqrt(num)) {
                sum -= i; // if i is the square root of num, subtract i to delete overcounting
            }
        }
        if (sum > num) {
            return true; // return true if sum = num
        }
        return false;
    }

    /**
     * Checks if the positive integer is narcissistic: equal to the sum of its own
     * digits raised to the power of n (the number of digits)
     * 
     * @return true if narcisstic
     */
    public boolean isNarcissistic() {
        String numString = Integer.toString(num); // Convert number to String
        int numDigits = numString.length(); // Length of the String = number of digits
        int currentDigit;
        int sum = 0;
        for (int i = 0; i < numDigits; i++) { // Iterate through digits
            currentDigit = Character.getNumericValue(numString.charAt(i)); // Get character at index of String, and
                                                                           // convert to number
            sum += Math.pow(currentDigit, numDigits); // add digit^(number of digits) to sum
        }
        if (num == sum) {
            return true; // return true if sum = num
        }
        return false;

    }

    /**
     * Checks if the positive integer is a happy: eventually reaches 1 when replaced
     * by the sum of the squares of each digit
     * 
     * @return true if happy, false if unhappy
     */
    public boolean isHappy() {
        // first holds original integer
        // will also hold intermediate sums of the squares of the digits
        int tempNum = num;
        // will hold the Stringification of number in tempNum
        String tempNumString;
        // will hold the number of digits in number in tempNum (number of characters in
        // tempNumString)
        int tempNumDigits;
        // will hold each digit in tempNum, one at a time
        int currentDigit;
        // will hold running sum of the squares of the digits in tempNum
        int sum;
        // repeat the following loop until one of the following conditions
        // 1) reaches 1 (happy)
        // 2) reaches 4 (unhappy)
        while (tempNum != 4) {
            // set running sum back to 0
            sum = 0;
            // Stringify integer in tempNum
            tempNumString = Integer.toString(tempNum);
            // get number of digits of tempNum (characters in tempNumString)
            tempNumDigits = tempNumString.length();
            // iterate over digits in tempNum (characters in tempNumString)
            for (int i = 0; i < tempNumDigits; i++) {
                // convert the current character in tempNumString to an integer
                currentDigit = Character.getNumericValue(tempNumString.charAt(i));
                // add the square of the current digit to the running sum
                sum += Math.pow(currentDigit, 2);
            }
            // if the sum of the squares of the digits of tempNum is 1...
            if (sum == 1) {
                // original integer is happy :)
                // return true (forces the while loop to end)
                return true;
                // if the sum is NOT 1...
            } else {
                // replace value in tempNum with the sum
                tempNum = sum;
                // go back to start of while loop
            }
        }
        // if the while loop was ended, original integer is unhappy :(
        return false;
    }
}
