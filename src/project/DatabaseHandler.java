package project;

import java.util.ArrayList;

/**
 * This class contains the required methods to initialise a connection to the database
 * and handle information sent to and from the database.
 * @author Adam-PC
 *
 */
public class DatabaseHandler extends DatabaseConnection {

	/**
	 * Creates a connection to the database using the specified database connection path
	 * and credentials. This constructor initiates the database connection.
	 * @param connectionString  String - Connection String
	 * @param user  String - User name required to access the database
	 * @param password  String - Password required to access the database
	 */
	public DatabaseHandler(String database, String user, String password) {
		super(database, user, password);
	}

	/**
	 * Takes an {@link ArrayList} of items and inserts them to the database table
	 * corresponding to the type of item.
	 * @param items  ArrayList - Items to be added to the database
	 * @param type  String - Item type to be added to the database
	 */
	public void add(ArrayList<String[]> items, String type) {

		// Use item type to add items to the corresponding database table
		switch (type.trim().toLowerCase()) {
		case "person": {
			addPerson(items);
			break;
		}
		case "order": {
			addOrder(items);
			break;
		}
		default: {
			System.err.println("No records inserted."  + " Error with filename in: " + this.getClass().getSimpleName());
		}
		}
	}
	
	/**
	 * Inserts an {@link ArrayList} of {@link Person} to the person table in the 
	 * database.
	 * @param items  ArrayList - Array of Person
	 */
	private void addPerson(ArrayList<String[]> items) {
		for (int i = 0; i < items.size(); i++)
			insertNewPerson(new Person(items.get(i)[0], items.get(i)[1],
					items.get(i)[2], items.get(i)[3], items.get(i)[4]));
	}

	/**
	 * Inserts an {@link ArrayList} of {@link Order} to the orders table in the 
	 * database.
	 * @param items  ArrayList - Array of Order
	 */
	private void addOrder(ArrayList<String[]> items) {
		for (int i = 0; i < items.size(); i++)
			insertNewOrder(new Order(items.get(i)[0], items
					.get(i)[1], items.get(i)[2]));
	}
	
	/**
	 * Returns a {@link ArrayList} of {@link Person} who have placed an {@link Order}. 
	 * All orders are added to the corresponding person.
	 * @return  ArrayList - Array of Person
	 */
	public ArrayList<Person> getPersonByOrder() {
		
		ArrayList<Person> people = new ArrayList<Person>();
		Person person = null;
		
		// Get every order placed
		for (Order order : getAllOrders())
			
			// Get the person of the order
			if ((person = getPersonByID(order.getPersonId())) != null) {
				
				boolean isAdded = false;
				
				// Add person to people
				for (int i = 0; i < people.size(); i++)
					// If person exists in people, add order to person
					if (people.get(i).getPersonID().contentEquals(order.getPersonId())) {
						people.get(i).addOrder(order);
						isAdded = true;
						break;
					}
				
				// If order not added, add person and order to people
				if (!isAdded) {
					person.addOrder(order);
					people.add(person);
				}
			}
		
		return people;
	}
	
}
