
import java.util.Scanner;
/**
 * Task 7
 * This program acts as an application for a warehouse of books. 
 * It can keep track of what the warehouse has, enter and delete records of books, update records and search the
 * database which stores and captures the titles, authors, quantities and id's of the warehouse's books.
 * @author Kerusha Govender, 2 July 2019
 *
 */

//This class is responsible for searching the database and returning the findings.
public class RecordLocator {
	
	static Scanner userInput = new Scanner(System.in);
	MainMenuApp goBackToMainMenu  = new MainMenuApp();
	DatabaseManager searchDatabase = new DatabaseManager();
	String sqlQueryForDatabase;
	String stringUserChoice;
	int intUserChoice;
//This is the first method initiated in this class, by the main menu class, update and delete classes. 
//It captures the user to search by a database column preference.
//It also safeguards against invalid input and integers.
	void userChoosesSearchPref() {
		System.out.println("1. Search by Author" + "\n2. Search by Title" + "\n3. Search by Id" + "\n4. Go back to Main Menu" + "\n5. Exit");
		try {
			do {
				stringUserChoice = userInput.nextLine();
				intUserChoice = Integer.parseInt(stringUserChoice);
				if(intUserChoice == 1) { 
					searchByAuthor();
				}
				else if(intUserChoice == 2) {
					searchByTitle();
				}
				else if(intUserChoice == 3) {
					searchByBookId();
				}
				else if(intUserChoice == 4) {
					goBackToMainMenu.userChoosesFromMainMenu();
				}
				else if(intUserChoice == 5) {
					System.out.println("The program will now be closed. GoodBye.");
					userInput.close();
					break;
				}
			} while(intUserChoice < 1 || intUserChoice > 6 );
		}
		catch(NumberFormatException ex) {
			System.out.println("You did not enter a number. Please enter a number.");
			userChoosesSearchPref();
		}
	}
//These three methods below, are called dependent on the user's choice.
//Each asks the user to enter what they are searching for and their input is constructed in the sql query specific string.
//At the end all three methods send this string to another method which will contact the DatabaseManager class and its relevant method to query the database.
	void searchByAuthor() {
		System.out.println("Enter the author of the book you are looking for: ");
		String userAuthor = userInput.nextLine(); 
		sqlQueryForDatabase = "select * from books where Author = '" + userAuthor + "'";
		SqlQueryToDatabase(sqlQueryForDatabase);
	}
	
	void searchByTitle() {
		System.out.println("Enter the title of the book you are looking for: ");
		String userTitle = userInput.nextLine();
		sqlQueryForDatabase = "select * from books where Title = '" + userTitle + "'";
		SqlQueryToDatabase(sqlQueryForDatabase);
	}
//This method also safeguards against input that is not an integer, before it is compiled into a sql query string and passed on to the database.
	void searchByBookId() {
		try {
			System.out.println("Enter the book Id: ");
			String stringUserId = userInput.nextLine();
			int intBookId = Integer.parseInt(stringUserId);
			sqlQueryForDatabase = "select * from books where id = " + intBookId;
			SqlQueryToDatabase(sqlQueryForDatabase);
		}
		catch(NumberFormatException ex) {
			System.out.println("You did not enter a number as the book Id. Enter a number.");
			searchByBookId();
		}
	}
//All this method does is receive the sql query string, passes it to the Database Manager class's relevant method and prints the returned output from the database.
	void SqlQueryToDatabase(String sqlQueryForDatabase) {
		String returnedDatabaseOutput = searchDatabase.searchDatabase(sqlQueryForDatabase);
		System.out.println("Result from database:\n" + returnedDatabaseOutput);
		userChoosesActAfterSearch(returnedDatabaseOutput);
	}
//Because delete and update services are first prompted to search what they want to delete or update, there is a method that is called in this class which asks the user
//what they would like to do with their search result or what to do next.	
	void userChoosesActAfterSearch(String returnedRecordFromDatabase) {
		System.out.println("1. Search Again" + "\n2. Update Record" + "\n3. Delete Record" + "\n4. Go Back To Main Menu" + "\n5. Exit");
		RecordUpdater updateRecordService = new RecordUpdater();
		RecordRemover deleteRecordService = new RecordRemover();
		try {
			do {
				stringUserChoice = userInput.nextLine();
				intUserChoice = Integer.parseInt(stringUserChoice);
				if(intUserChoice == 1) { 
					userChoosesSearchPref();
				}
				else if(intUserChoice == 2) {
					updateRecordService.extractPrimaryKeyRecord(returnedRecordFromDatabase);;
				}
				else if(intUserChoice == 3) {
					deleteRecordService.extractPrimaryKey(returnedRecordFromDatabase);
				}
				else if(intUserChoice == 4) {
					goBackToMainMenu.userChoosesFromMainMenu();
				}
				else if(intUserChoice == 5) {
					System.out.println("The program will now be closed. Goodbye.");
					userInput.close();
					break;
				}
			} while(intUserChoice < 1 || intUserChoice > 5 );
		}
		catch(NumberFormatException ex) {
			System.out.println("You did not enter a number. Please enter a number.");
			userChoosesActAfterSearch(returnedRecordFromDatabase);
		}
	}
}
	
