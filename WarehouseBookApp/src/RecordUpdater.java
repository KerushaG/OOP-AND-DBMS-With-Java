
import java.util.Scanner;
/**
 * Task 7
 * This program acts as an application for a warehouse of books. 
 * It can keep track of what the warehouse has, enter and delete records of books, update records and search the
 * database which stores and captures the titles, authors, quantities and id's of the warehouse's books.
 * @author Kerusha Govender, 2 July 2019
 *
 */

//This record is responsible for collecting update information from the user, after they searched what they wanted to update, and then send it to the DatabaseManager objects
//method to modify the relevant record.
public class RecordUpdater {
	
	static Scanner userInput = new Scanner(System.in);
	MainMenuApp goBackToMainMenu  = new MainMenuApp();
	DatabaseManager searchDatabase = new DatabaseManager();
	String stringUserChoice;
	int intUserChoice;
	String sqlUpdate = null;
//When the user chooses update from the main menu, this method is called to ask them to search first what they want to update	
	void searchRecordToUpdate() {
		RecordLocator searchRecord = new RecordLocator();
		System.out.println("Search for the record you want to update.");
		searchRecord.userChoosesSearchPref();
	}
//The result of their search is returned, and split into an array so that the primary key can be used for the sql query compilation
	void extractPrimaryKeyRecord(String returnedRecordFromDatabase) {
		String [] recordArray = returnedRecordFromDatabase.split(",");
		int recordPrimeKey = Integer.parseInt(recordArray[0]);
		userPicksUpdateOption(recordPrimeKey);
	}
//The user is prompted by this method to choose what they want to update in that record, and the record from the search class is passed into the relevant method for what column
//they wish to update.
//Invalid numbers and inputs are safeguarded against.
	void userPicksUpdateOption(int recordPrimeKey) {
		System.out.println("What do you want to update in this record: " + "\n1. Id" + "\n2. Title" + "\n3. Author" + "\n4. Quantity" + "\n5. Nevermind, go back to Main Menu" + "\n6. Exit");
		try {
			do {
				stringUserChoice = userInput.nextLine();
				intUserChoice = Integer.parseInt(stringUserChoice);
				if(intUserChoice == 1) { 
					userUpdatesId(recordPrimeKey);
				}
				else if(intUserChoice == 2) {
					userUpdatesTitle(recordPrimeKey);
				}
				else if(intUserChoice == 3) {
					userUpdatesAuthor(recordPrimeKey);
				}
				else if(intUserChoice == 4) {
					userUpdatesQuantity(recordPrimeKey);
				}
				else if(intUserChoice == 5) {
					goBackToMainMenu.userChoosesFromMainMenu();
				}
				else if(intUserChoice == 6) {
					System.out.println("Bye bye come back again.");
					userInput.close();
					break;
				}
			} while(intUserChoice < 1 || intUserChoice > 6 );
		}
		catch(NumberFormatException ex) {
			System.out.println("You did not enter a number. Please enter a number.");
			userPicksUpdateOption(recordPrimeKey);
		}
	}
//Depending on what attribute the user wants to update, the following 4 methods, ask the user for the input and pass the primary key, column to update and user's update to
//be compiled into a sql string by another method
	void userUpdatesAuthor(int recordPrimeKey) {
		System.out.println("What do you want to update the author to");
		String userUpdate = userInput.nextLine();
		String columnToUpdate = "Author";
		compileSqlUpdate(columnToUpdate, userUpdate, recordPrimeKey);
	}
	
	void userUpdatesTitle(int recordPrimeKey) {
		System.out.println("What do you want to update the title to");
		String userUpdate = userInput.nextLine();
		String columnToUpdate = "Title";
		compileSqlUpdate(columnToUpdate, userUpdate, recordPrimeKey);
	}
	
	void userUpdatesQuantity(int recordPrimeKey) {
		System.out.println("What do you want to update the quantity to");
		String userUpdate = userInput.nextLine();
		String columnToUpdate = "Qty";
		compileSqlUpdate(columnToUpdate, userUpdate, recordPrimeKey);
	}
	
	void userUpdatesId(int recordPrimeKey) {
		System.out.println("What do you want to update the id to");
		String userUpdate = userInput.nextLine();
		String columnToUpdate = "id";
		compileSqlUpdate(columnToUpdate, userUpdate, recordPrimeKey);
	}	
//This method checks what datatype is contained in the user's input variable. We know quantity and primary key will ONLY contain numbers, but titles could contain numbers.
//So this method goes through each character, as soon as it encounters a letter, a sql string is compiled and returned with the new data as a string, else it it's a number 
//an empty variable is returned
	String checkUserUpdateDataType(String columnToUpdate, String userUpdate, int recordPrimeKey) {
		char[] userUpdateArray = userUpdate.toCharArray();
		for(int i=0; i < userUpdateArray.length; i++) {
			if(Character.isLetter(userUpdateArray[i])) {
				sqlUpdate = "update books set " + columnToUpdate + " = '" + userUpdate + "' where id = " + recordPrimeKey;
				return sqlUpdate;
			}
		}
		return sqlUpdate;
	}
//In this method, the main duty is to send the sql query to the DataBase Manager to modify the relevant database record. 	
	void compileSqlUpdate(String columnToUpdate, String userUpdate, int recordPrimeKey) {
		sqlUpdate = checkUserUpdateDataType(columnToUpdate, userUpdate, recordPrimeKey);
		if (sqlUpdate == null) { //if the variable from the above method is empty we know its an integer, and the sql query is compiled and sent accordingly.
			int intUserUpdate = Integer.parseInt(userUpdate);
			sqlUpdate = "update books set " + columnToUpdate + " = " + intUserUpdate + " where id = " + recordPrimeKey;
			searchDatabase.DatabaseModifier(sqlUpdate);
		}
		else {
			searchDatabase.DatabaseModifier(sqlUpdate); //if its not empty the above method already compiled the query so it is just sent to the database.
		}	
	}	
}


