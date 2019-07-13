/**
 * Task 7
 * This program acts as an application for a warehouse of books. 
 * It can keep track of what the warehouse has, enter and delete records of books, update records and search the
 * database which stores and captures the titles, authors, quantities and id's of the warehouse's books.
 * @author Kerusha Govender, 2 July 2019
 *
 */

//This class's responsibility is to create a book record and return a toString that is in the format of a database book record.
public class BookObject {
	
	private int bookId;
	private String bookTitle;
	private String bookAuthor;
	private int bookqty;
	
	BookObject(int bookId, String bookTitle, String bookAuthor, int bookqty) {
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.bookqty = bookqty;
	}
	
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public int getBookqty() {
		return bookqty;
	}

	public void setBookqty(int bookqty) {
		this.bookqty = bookqty;
	}
	
	public String toString() {
		return bookId + ", " + "'" + bookTitle + "'" + ", " + "'" + bookAuthor + "'" + ", " + bookqty;
	}
}
