package rockPaperScissors;

import java.util.Random;
import java.util.Scanner;
/**Task 2.1.
 * @author Kerusha Govender, 4 June 2019
 * This class takes in one number from the user from the range (0-2) and generates a random number from the same range. It then lists a number of conditions based on the
 * comparison of the user's number and the random generated number. The numbers are represented by rock, paper, scissors and follows the logic of the game. 
 */
public class RockPaperScissors {
	private static short rock = 0;
	private static short paper = 1;
	private static short scissors = 2;
/**
* This method takes in the user's inputed number, by making an object from the Scanner class, 
* and stores it in the variable userNum, it also displays the values that rock, paper and scissors represent. 	
* @return userNum
*/	
	private static int input() {
		Scanner num = new Scanner(System.in);
		System.out.printf("%n Rock represents 0 %n Paper represents 1 %n Scissors represents 2%n %nEnter 0, 1 or 2: ");
		int userNum = num.nextInt();
		num.close();
		System.out.println("The entered number is " + userNum);
		return userNum;
	}
/**
* This method generates a random number by importing the Random class, making an object and using nextInt method, the parameter is set for 3 which excludes 3. 
* This allows only 3 options to be generated randomly (0, 1, 2). 
* This random number is stored in the variable called randomInt. 
* The method also displays what the random number is. 	
* @return randomInt
*/	
	private static int generatesNum() {
//		usingRandomClass();
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(3);
		System.out.println("Random number generated is: " + randomInt );
		return randomInt;
	}
/**
* This method has a number of if and else if statements, with print statements, based on the comparison of the user's inputed number and the random number generated. 	
* @param userNum
* @param randomInt
*/	
	private static void checkWinner(int userNum, int randomInt) {
		if (userNum == randomInt)
		{
			System.out.println("It's a draw");
		}
		else if (userNum == rock && randomInt == scissors)
		{
			System.out.println("Rock beats Scissors. You win!");
		}
		else if (userNum == paper && randomInt == rock)
		{
			System.out.println("Paper beats Rock. You win!");
		}
		else if (userNum == scissors && randomInt == paper)
		{
			System.out.println("Scissors beats Paper. You win!");
		}
		else if (randomInt == rock && userNum == scissors) {
			System.out.println("Rock beats Scissors. You lose!");
		}
		else if (randomInt == paper && userNum == rock)
		{
			System.out.println("Paper beats Rock. You lose!");
		}
		else if (randomInt == scissors && userNum == paper)
		{
			System.out.println("Scissors beats Paper. You lose!");	
		}
	}
//In the main method I just call methods, store their results in variables and pass them into the last method which determines the winner of the game	
	public static void main(String[]args) {	
		int userNum = input();
		int randomInt = generatesNum();
		checkWinner(userNum, randomInt);
	}	
}


  
