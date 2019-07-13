/**
 * Task 7
 * This program acts as an application for a warehouse of books. 
 * It can keep track of what the warehouse has, enter and delete records of books, update records and search the
 * database which stores and captures the titles, authors, quantities and id's of the warehouse's books.
 * @author Kerusha Govender, 2 July 2019
 *
 */

//This particular class has only one main method and that is to start the program by prompting the user to interact with a menu.
public class RunApplication {
	
	public static void main(String[]args) {
		System.out.println("For the rest of the program, enter only the number for the action you want to perform.");
		MainMenuApp startProgram = new MainMenuApp();
		startProgram.userChoosesFromMainMenu();
	}
}
