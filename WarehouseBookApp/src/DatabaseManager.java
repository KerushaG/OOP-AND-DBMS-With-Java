import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Task 7
 * This program acts as an application for a warehouse of books. 
 * It can keep track of what the warehouse has, enter and delete records of books, update records and search the
 * database which stores and captures the titles, authors, quantities and id's of the warehouse's books.
 * @author Kerusha Govender, 2 July 2019
 *NOTE: Need to try out cashing, maybe converting the database into an arraylist and using collection methods to help with searching.
 */

//This class is solely responsible for interacting with the database through modifying it or querying it and returning results.
public class DatabaseManager {
	
	MainMenuApp returnToMenu = new MainMenuApp();
	String searchResult;
//This method takes in a single sql query from the classes that enter a new book, update and delete books.	
	void DatabaseModifier(String SqlInstruction) {
		try (
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eBookstore?useSSL=false&allowPublicKeyRetrieval=true", "myuser", "xxxx");
			Statement stmt = conn.createStatement();
		)
		{
			String sqlModifier = SqlInstruction;
			int countUpdated = stmt.executeUpdate(sqlModifier);
			System.out.println(countUpdated + " records affected.");
		}
		catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("You tried to enter a primary key (book id) that already exists."); //trying to catch a specific error
			returnToMenu.userChoosesFromMainMenu();
		}
	}
//This method has specific formatting, because it returns an entire record from a search result OR lets the user know that the books was not found.	
	String searchDatabase(String databaseQuery) {
		try (
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eBookstore?useSSL=false&allowPublicKeyRetrieval=true", "myuser", "xxxx");
			Statement stmt = conn.createStatement();
		)
		{	
			String strSelect = databaseQuery;
			System.out.println("The SQL query is: " + strSelect);
			System.out.println("Searching...");
			System.out.println();
			ResultSet rset = stmt.executeQuery(strSelect);
			if(rset.next() == false) {
				searchResult = "Book not found.";
			}
			else { 
				do {
					searchResult = (rset.getInt("id") + ", " + rset.getString("Title") + ", " + rset.getString("Author") + ", " + rset.getInt("Qty"));
				} while(rset.next());
			} 
		}
		catch(SQLException ex) {
				ex.printStackTrace();
				returnToMenu.userChoosesFromMainMenu();
		}
		return searchResult;
	}
//This method is also specific in its syntax in that it requests and prints out all the info in the database.					
	void returnAllRecords() {
		try (
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eBookstore?useSSL=false&allowPublicKeyRetrieval=true", "myuser", "xxxx");
			Statement stmt = conn.createStatement();
		)
		{
			String strSelect = "select * from books"; 
			System.out.println("The SQL query is: " + strSelect);
			ResultSet rset = stmt.executeQuery(strSelect);
			while(rset.next()) { 
				System.out.println(rset.getInt("id") + ", " + rset.getString("Title") + ", " + rset.getString("Author") + ", " + rset.getInt("Qty"));
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
			returnToMenu.userChoosesFromMainMenu();
		}
	}
}	

