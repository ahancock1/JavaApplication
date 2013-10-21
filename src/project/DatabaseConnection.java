package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class contains the required methods to access the database through SQL 
 * statements. This class establishes a connection to the database and creates
 * SQL statements.
 * @author Adam-PC
 *
 */
public class DatabaseConnection {

	protected Connection connection;
	private ResultSet result;
	private final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private String connectionString;
	private String user;
	private String password;
	
	/**
	 * Creates a connection to the database using the specified database connection path
	 * and credentials.
	 * @param connectionString  String - Connection String
	 * @param user  String - User name required to access the database
	 * @param password  String - Password required to access the database
	 */
	public DatabaseConnection(String database, String user,
			String password) {
		
		this.connectionString = "jdbc:mysql://localhost:3306/" + database;
		this.user = user;
		this.password = password;
	}
	
	/**
	 * Opens a connection to the database
	 */
	protected void openConnection() {
		try {
			// Used for accessing the driver from the referenced JAR file
			Class.forName(DB_DRIVER);

			// Create a connection to the database using JDBC
			connection = DriverManager.getConnection(connectionString, user,
					password);

		} catch (ClassNotFoundException e) {
			System.err.println("Could not locate database driver: " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Could not establish connection to database: " + e.getMessage());
		}
	}
	
	/**
	 * Closes the connection to the database
	 */
	protected void closeConnection() {
		
			try {
				// Check connection exists
				if (connection != null)
					connection.close();
				if (result != null)
					result.close();
			} catch (SQLException e) {
				System.err.println("Could not close connection to database: " + e.getMessage());
			}
	}

	/**
	 * Opens connection to database constructs and executes query statement.
	 * Returns an {@link ArrayList} of all {@link Person} that exist within the 
	 * database, then closes database connection.
	 * @return  ArrayList - All person records within database
	 */
	public ArrayList<Person> getAllPeople() {

		// Open database connection, construct and execute statement
		openConnection();
		ArrayList<Person> people = new ArrayList<Person>();

		String queryPersonSQL = "SELECT * FROM PERSON;";
		executeQuery(queryPersonSQL);

		try {
			while (result.next())
				people.add(new Person(
						result.getString("PERSON_ID"),
						result.getString("LAST_NAME"),
						result.getString("FIRST_NAME"),
						result.getString("STREET"),
						result.getString("CITY")));
			
			result.close();
			
		} catch (SQLException e) {
			System.err.println("Could not search database: " + e.getMessage());
		} 

		// Close database connection
		closeConnection();
		return people;
	}
	
	
	/**
	 * Opens connection to database constructs and executes query statement.
	 * Returns an {@link ArrayList} of all {@link Order} that exist within the 
	 * database, then closes database connection.
	 * @return  ArrayList - All order records within database
	 */
	public ArrayList<Order> getAllOrders() {

		// Open database connection, construct and execute statement
		openConnection();
		ArrayList<Order> orders = new ArrayList<Order>();

		String queryOrdersSQL = "SELECT * FROM ORDERS;";
		executeQuery(queryOrdersSQL);

		try {
			while (result.next())
				orders.add(new Order(
						result.getString("ORDER_ID"),
						result.getString("ORDER_NO"),
						result.getString("PERSON_ID")));
			
			
		} catch (SQLException e) {
			System.err.println("Could not search database: " + e.getMessage());
		}

		// Close database connection
		closeConnection();
		return orders;
	}

	/**
	 * Opens connection to database constructs and executes query statement.
	 * Returns {@link Person} specified by the identity of the person, then
	 * closes the database connection. If person does not exist the returned 
	 * value is null.
	 * @param personID  String - Identity of the person
	 * @return Person
	 */
	public Person getPersonByID(String personID) {

		// Open database connection, construct and execute statement
		openConnection();
		String queryPersonSQL = "SELECT *, COUNT(*) AS ROW_COUNT FROM PERSON WHERE PERSON_ID = '"
				+ personID + "';";
		executeQuery(queryPersonSQL);
		
		Person person = null;

		try {
			result.next();
			if (result.getInt("ROW_COUNT") > 0) {
				person = new Person(
						result.getString("PERSON_ID"),
						result.getString("LAST_NAME"),
						result.getString("FIRST_NAME"),
						result.getString("STREET"),
						result.getString("CITY"));
			} else {
				System.out.println("No records found with Person ID: " + personID);				
			}
			
		} catch (SQLException e) {
			System.err.println("Could not search database: " + e.getMessage());
		}
		
		// Close database connection
		closeConnection();
		return person;
	}

	/**
	 * Creates and executes an SQL statement to insert a new {@link Person} to the database.
	 * @param person  Person - Person to be inserted
	 */
	public void insertNewPerson(Person person) {

		String insertPersonSQL = "INSERT INTO PERSON (PERSON_ID, LAST_NAME, FIRST_NAME, STREET, CITY) VALUES('"
				+ person.getPersonID() + "', '"
				+ person.getLastName() + "', '"
				+ person.getFirstName()	+ "', '"
				+ person.getStreet() + "', '"
				+ person.getCity() + "');";

		execute(insertPersonSQL);
	}

	/**
	 * Creates and executes an SQL statement to insert a new {@link Order} to the database.
	 * @param order  Order - Order to be inserted
	 */
	public void insertNewOrder(Order order) {

		String insertOrderSQL = "INSERT INTO ORDERS (ORDER_ID, ORDER_NO, PERSON_ID) VALUES('"
				+ order.getOrderID() + "', '"
				+ order.getOrderNo() + "', '"
				+ order.getPersonId() + "');";
		
		execute(insertOrderSQL);
	}
	
	/**
	 * Opens connection to the database and executes the statement, then closes the 
	 * database connection.
	 * @param statement  String - SQL statement
	 */
	protected void execute(String statement) {
		
		try {
			// Open database connection, execute statement, then close connection
			openConnection();
			connection.createStatement().executeUpdate(statement);
			closeConnection();

		} catch (SQLException e) {
			System.out.println("Statement failed: " + e.getMessage());
		}
	}

	/**
	 * Executes a query to the database. Connection to the database must be 
	 * open prior to executing and closed after.
	 * @param query  String - SQL query statement
	 */
	protected void executeQuery(String query) {
		
		try {
			result = connection.createStatement().executeQuery(
					query);
			
		} catch (SQLException e) {
			System.out.println("Query failed: " + e.getMessage());
		}
	}

}
