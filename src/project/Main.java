package project;

/**
 * Application for reading text files and inserting entries into a database. The
 * Orders are printed in the console window including who placed the order.
 * @author Adam-PC
 *
 */
public class Main {
	
	// The filenames to be loaded
	protected static final String[] FILENAMES = {"person", "order"};
	protected static final String FILE_SUFFIX = ".data";
	
	// Used for accessing the database
	protected static final String DATABASE = "database";
	protected static final String USER = "user";
	protected static final String PASSWORD = "password";
	
	/**
	 * The main method used for running the application.
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Creates a new FileHandle for reading files
		FileHandler file = new FileHandler();
		
		// Creates a new connection to the database for handling queries
		DatabaseHandler database = new DatabaseHandler(DATABASE, USER, PASSWORD);
		
		// Read the .data files and add them to the database
		for (String filename : FILENAMES)
			database.add(file.read(filename + FILE_SUFFIX), filename);
		
		// Persons with at least one Order
		for (Person person : database.getPersonByOrder()) {
			// Prints all the people who have placed an order
			//System.out.println(person.toString());
			
			// Prints all the placed orders and first name of the person
			for (int i = 0; i < person.getOrdersSize(); i++)
				System.out.println(person.orderToString(i));
		}
		
		
	}	
}
