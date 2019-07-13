import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Task 7
 * This program acts as an application for a warehouse of books. 
 * It can keep track of what the warehouse has, enter and delete records of books, update records and search the
 * database which stores and captures the titles, authors, quantities and id's of the warehouse's books.
 * @author Kerusha Govender, 2 July 2019
 *
 */

//This class is mainly responsible for being the centre point of the program. It provides the user with a menu of options and brings them back to the menu after each
//activity is comleted. 
public class MainMenuApp {
	
	void displayMainMenu() {
		String mainMenu = "1. Enter book" + "\n2. Update book" + "\n3. Delete book" + "\n4. Search books" + "\n5. See all books" + "\n0. Exit"; //see all records
		System.out.println(mainMenu);
	}
//This method takes in the user's number response, but safeguards against invalid numbers or invalid inputs, by notifying them or their error and/or repeating the prompt
//for them to respond with a number again.
	void userChoosesFromMainMenu() {
		Scanner userInput = new Scanner(System.in);
		int userMenuChoice;
		try { 
			do {
			displayMainMenu();
			userMenuChoice = userInput.nextInt();
			if(userMenuChoice > 0 || userMenuChoice <= 5) {
				checkUserChoice(userMenuChoice);
			}
			else if(userMenuChoice == 0) {
				System.out.println("The program will now be closed. Good Bye."); 
				userInput.close();
				break;
			}
			} while(userMenuChoice < 0 || userMenuChoice > 5);
		}
		catch (InputMismatchException ex) {
			System.out.println("You did not enter a number. Please enter a number.");
			userChoosesFromMainMenu();
		}
	}
//This method receives a valid user's number, and then directs them to the relevant service they picked, through objects of those services and their starting point methods. 
//It also brings users back to this class after each service is complete.
		void checkUserChoice(int userChoice) {
		if (userChoice == 1) {
			NewBookRecord goToEnterABook = new NewBookRecord();
			goToEnterABook.enterNewRecordForAbook();
			userChoosesFromMainMenu();
		}
		else if(userChoice == 2) {
			RecordUpdater updateBook = new RecordUpdater();
			updateBook.searchRecordToUpdate();
			userChoosesFromMainMenu();
		}
		else if(userChoice == 3) {
			RecordRemover deleteAbook = new RecordRemover();
			deleteAbook.searchRecordToDelete();
			userChoosesFromMainMenu();
		}
		else if(userChoice == 4) {
			RecordLocator bookSearch = new RecordLocator();
			bookSearch.userChoosesSearchPref();
		}
		else if(userChoice == 5 ) {
			AccountOfAllRecords seeAllBooks = new AccountOfAllRecords();
			seeAllBooks.seeAllRecords();
			userChoosesFromMainMenu();
		}
	} 
}
