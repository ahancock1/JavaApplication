package project;

import java.util.ArrayList;

/**
 * Class containing variables that represent a person and the orders placed by this
 * person.
 * @author Adam-PC
 *
 */
public class Person {

	private String personID;
	private String lastName;
	private String firstName;
	private String street;
	private String city;

	private ArrayList<Order> orders;
	
	/** 
	 * Creates a person and initialises an empty {@link ArrayList} of orders.
	 * @param personID  String - Unique identifier of the person
	 * @param lastName  String - Last name
	 * @param firstName  String - First name
	 * @param street  String - Street
	 * @param city  String - City
	 */
	public Person(String personID, String lastName, String firstName,
			String street, String city) {
		this.personID = personID;
		this.lastName = lastName;
		this.firstName = firstName;
		this.street = street;
		this.city = city;
		this.orders = new ArrayList<Order>();
	}

	/** 
	 * Creates a person and sets an {@link ArrayList} of orders.
	 * @param personID  String - Unique identifier of the person
	 * @param lastName  String - Last name
	 * @param firstName  String - First name
	 * @param street  String - Street
	 * @param city  String - City
	 * @param orders  ArrayList - Orders by this person
	 */
	public Person(String personID, String firstName, String lastName,
			String street, String city, ArrayList<Order> orders) {
		this.personID = personID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.city = city;
		this.orders = orders;
	}

	/**
	 * Adds a new {@link Order} to the {@link ArrayList} of orders by this person.
	 * @param order  Order - Order to be added to this persons orders
	 */
	public void addOrder(Order order) {
		this.orders.add(order);
	}

	/**
	 * Returns the identity of the person.
	 */
	public String getPersonID() {
		return this.personID;
	}

	/**
	 * Returns the persons last name.
	 * @return  String
	 */
	public String getLastName() {
		return this.lastName.toUpperCase();
	}

	/**
	 * Returns the persons first name.
	 * @return  String
	 */
	public String getFirstName() {
		return this.firstName.toUpperCase();
	}

	/**
	 * Returns the street belonging to this person.
	 * @return  String
	 */
	public String getStreet() {
		return this.street.toUpperCase();
	}

	/**
	 * Returns the city belonging to this person.
	 * @return  String
	 */
	public String getCity() {
		return this.city.toUpperCase();
	}

	/**
	 * Returns the {@link ArrayList} of orders placed by this person.
	 * @return  ArrayList
	 */
	public ArrayList<Order> getOrders() {
		return this.orders;
	}
	
	/**
	 * Returns the number of orders placed by this person.
	 * @return  int
	 */
	public int getOrdersSize() {
		return this.orders.size();
	}

	/**
	 * Returns a string representing the contents of the person.
	 */
	public String toString() {
		return "Person ID: " + personID + ", First Name: " + firstName + ", Last Name: " + lastName
				+ ", Street: " + street
				+ ", City: " + city + ", Orders: " + orders.size();
	}
	
	/**
	 * Returns a string representing the first name of the person and the order 
	 * specified by the position of the order in the {@link ArrayList}.
	 * @param position  int - Orders position in the {@link ArrayList}
	 * @return  String
	 */
	public String orderToString(int position) {
		return "First Name: " + firstName + ", " + orders.get(position).toString();
	}
}
