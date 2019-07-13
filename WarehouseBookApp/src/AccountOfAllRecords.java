/**
 * Task 7
 * This program acts as an application for a warehouse of books. 
 * It can keep track of what the warehouse has, enter and delete records of books, update records and search the
 * database which stores and captures the titles, authors, quantities and id's of the warehouse's books.
 * @author Kerusha Govender, 2 July 2019
 *
 */

//This class is responsible for returning all records of the database.
public class AccountOfAllRecords {
	
	void seeAllRecords() {
		DatabaseManager fullAccountOfDatabase = new DatabaseManager();
		fullAccountOfDatabase.returnAllRecords();
	}
}
