/**
 * Task 7
 * This program acts as an application for a warehouse of books. 
 * It can keep track of what the warehouse has, enter and delete records of books, update records and search the
 * database which stores and captures the titles, authors, quantities and id's of the warehouse's books.
 * @author Kerusha Govender, 2 July 2019
 *
 */

//This class is responsible for sending a sql query to the Database Manager class to delete a record, which is first gets from the search class, then extracts the primary key
//and compiles the relevant sql query to contact the database.
public class RecordRemover {
	
	DatabaseManager deleteRecord = new DatabaseManager();
//search class is called first, and the returned result is then passed on to be dissected and compiled into an sql query.	
	void searchRecordToDelete() {
		RecordLocator searchRecord = new RecordLocator();
		System.out.println("Search for the record you want to delete.");
		searchRecord.userChoosesSearchPref();
	}
//This method just gets the primary key of the returned record and by splitting it into an array allocates the primary key and uses it for an sql query to send to the relevant
//DatabaseManager class method.
	void extractPrimaryKey(String returnedRecordFromDatabase) {
		String [] recordArray = returnedRecordFromDatabase.split(",");
		String primaryKeyId = recordArray[0];
		int intId = Integer.parseInt(primaryKeyId);
		String sqlDeleteQuery = "delete from books where id = " + intId; 
		System.out.println("This record will now be deleted:\n" + returnedRecordFromDatabase);
		deleteRecord.DatabaseModifier(sqlDeleteQuery);
	}		
}
