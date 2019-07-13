import java.util.Scanner;
/**
 * Task 7
 * This program acts as an application for a warehouse of books. 
 * It can keep track of what the warehouse has, enter and delete records of books, update records and search the
 * database which stores and captures the titles, authors, quantities and id's of the warehouse's books.
 * @author Kerusha Govender, 2 July 2019
 *
 */

//This class is responsible for collecting input from the user for a new book record for the database.
public class NewBookRecord {
	
	Scanner userInput = new Scanner(System.in);
//I use nextLine to capture users input event for integers, because when I use nextInt and nextLine, it causes malfunctions.
//However, in this method that prompts the user for entries for the new book, I check that for quantity and primary key their responses are integers, and only then accept it
//and pass it into the constructor of the BookObject, which then is passed to another method which will send it to the DatabaseManager class's relevant method.
	void enterNewRecordForAbook() { 
		System.out.println("Enter the id of the book: "); //Need to either generate ids for the user, how would they know which ones are taken and which one's aren't
		String StringIdFromUser = userInput.nextLine();
		int bookId = checkUserEntersIntValue(StringIdFromUser);
		System.out.println("Enter the title of the book: ");
		String bookTitleFromUser = userInput.nextLine(); 
		System.out.println("Enter the author of the book: ");
		String bookAuthorFromUser = userInput.nextLine();
		System.out.println("Enter the quantity of this book: ");
		String stringQtyFromUser = userInput.nextLine();
		int quantityOfBooks = checkUserEntersIntValue(stringQtyFromUser);
		BookObject newBook = new BookObject(bookId, bookTitleFromUser, bookAuthorFromUser, quantityOfBooks);
		compileForSqlQuery(newBook);
	}
//For input that is supposed to be integers, this method only casts and returns input that is integers, else throws an exception and initiates the above method for the user again.
	int checkUserEntersIntValue(String userInput) {
		try {
			int castedToIntInput = Integer.parseInt(userInput);
			return castedToIntInput;
			}
			catch(NumberFormatException ex) {
				System.out.println("You did not enter a number, please enter a number: ");
				enterNewRecordForAbook();
			}
		return 0;
	}
//NEED TO FIND A WAY TO CATCH ERRORS FOR DUPLICATE PRIMARY KEYS
//This method that is used after collecting user input and creating the BookObject, compiles the objects returned toString into the right sql query and sends it to the relevant
//method in the DatabaseManager class to officially insert the new book in the database.
	void compileForSqlQuery(BookObject newBook) {
		String sqlQueryForDatabase = ("insert into books " + "value (" + newBook + ")");
		DatabaseManager accessDatabase = new DatabaseManager();
		accessDatabase.DatabaseModifier(sqlQueryForDatabase);
	}
}	


