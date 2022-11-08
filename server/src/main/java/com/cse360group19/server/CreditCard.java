package com.cse360group19.server;

public class CreditCard {
    
    // variables to hold card details
    public long cardNumber;
    public int cvv;
    public String expDate;

    // constructor
    public CreditCard(long cardNumber, int cvv, String expDate){
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expDate = expDate;
    }

    // method to get the number of digits in a given number
    public static int getNumDigits(long cardNum){
        String strNum = cardNum + "";
        return strNum.length();
    }

    // method to get the first n digits in a number
    // if n > # of digits in the given number, then return the number itself
    public static long getLeadingDigits(long cardNum, int n){
        if (getNumDigits(cardNum) > n){
            String strNum = cardNum + "";
            long prefix = Long.parseLong(strNum.substring(0, n));
            return prefix;
        }
        return cardNum;
    }

    // method to test if the the digit(s): "x" is the first digit(s) of a the card number
    public static boolean isFirstDigit(long cardNum, int x){
        // use getLeadingDigits to check if x equals the result
        if (getLeadingDigits(cardNum, getNumDigits(x)) == x){
            return true;
        }
        return false;
    }

    // This method will return the number if it is a single digit
    // if the number is not a single digit it will return the sum of the digits
    // max num of digits that can be accepted as a valid imput is 2.
    // i.e. if 16 was the imput, the method will return 1 + 6;
    public static int getSingleDigit(int num){
        // if the number is strictly less than 9 return it
        if (num < 9){
            return num;
        }
        // The reason I chose less than 9 is that the largest integer that can be doubled
        // and is still one digit is 4 (i.e. 4*2=8).
        // If the number is two digits then return the sum of the two digits
        return (num/10 + num%10);
    }

    // If a card number is valid then 2 * the sum of every even indexed number from right to left
    // in getSingleDigit form plus the sum of every odd indexed number will be divisble by 10.
    // i.e. ( 2*SumOfEvenIndexes(in getSingleDigit form) + SumofOddIndexes ) % 10 = 0
    public static int sumOfEvenIndex(long cardNum){
        int sum = 0;
        String strNum = cardNum + "";

        // loop through cardNum starting at second from right most position
        // and move two positions to the left for each loop iteration
        for (int i = getNumDigits(cardNum) - 2; i >= 0; i -= 2){
            // sum up 2* the even indexed digits in single digit form
            sum += getSingleDigit(Integer.parseInt(strNum.charAt(i) + "") *2);
        }
        
        return sum;
    }


    // this function returns the sum of odd indexed numbers
    // from right to left in a credit card number 
    public static int sumOfOddIndex(long cardNum){
        int sum = 0;
        String strNum = cardNum + "";

        // loop through car number starting at right-most position and 
        // move two positions to the left for each loop iteration
        for (int i = getNumDigits(cardNum) - 1; i >= 0; i -= 2){
            // sum up odd indexes
            sum += Integer.parseInt(strNum.charAt(i) + "");
        }

        return sum;
    }


    // this function tests if a card numbers odd and even indexed sums are divisible by 10.
    // if the total sum is divisble by 10 card is valid if not then card is invalid
    public static boolean isCardNumDivByTen(long cardNum){
        if ((sumOfEvenIndex(cardNum) + sumOfOddIndex(cardNum)) % 10 == 0){
            return true;
        }
        return false;
    }
    

    // Criteria for card number to be valid
    // 1) must start with 4 | 5 | 37 | 6
    // 2) must have between 13 and 16 digits
    // 3) the sum of the card's odd indexed digits + 
    //    2 * the sum of even indexed digits in single digit form is divisble by 10
    //
    // NOTE: single digit form implies that if we had the number 14 its single digit form 
    //        would be 1 + 4 or 5.
    //
    // This function returns true if criteria is met and false otherwise
    public static boolean isCardNumValid(long cardNum){
        if (getNumDigits(cardNum) >= 13 && getNumDigits(cardNum) <= 16)
        {
            if (isFirstDigit(cardNum, 4) == true && isCardNumDivByTen(cardNum) == true){
                return true;
            }
            else if (isFirstDigit(cardNum, 5) == true && isCardNumDivByTen(cardNum) == true){
                return true;
            }
            else if (isFirstDigit(cardNum, 6) == true && isCardNumDivByTen(cardNum) == true){
                return true;
            }
            else if (isFirstDigit(cardNum, 37) == true && isCardNumDivByTen(cardNum) == true){
                return true;
            }
        }
        return false;
    }
}
