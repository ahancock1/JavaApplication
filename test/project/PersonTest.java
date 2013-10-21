package project;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PersonTest {

	private Person person;
	
	private String personID = "1";
	private String lastName = "oLa";
	private String firstName = "HanSEn";
	private String street = "TImOtEiVN";
	private String city = "sANdnEs";

	@Before
	public void setUp() throws Exception {
		person = new Person(personID, lastName, firstName, street, city);
	}

	@Test
	public void testAddOrder() {
		String orderID = "2";
		String orderNo = "1340";
		String personID = "15";
		
		Order order = new Order(orderID, orderNo, personID);
		
		person.addOrder(order);
		
		assertEquals(1, person.getOrders().size());
		
		assertEquals(orderID, person.getOrders().get(0).getOrderID());
		assertEquals(orderNo, person.getOrders().get(0).getOrderNo());
		assertEquals(personID, person.getOrders().get(0).getPersonId());
		
	}
	
	@Test
	public void testGetPersonID() {
		assertEquals("1", person.getPersonID());
	}

	@Test
	public void testGetLastName() {
		assertEquals("OLA", person.getLastName());
	}
	
	@Test
	public void testGetFirstName() {
		assertEquals("HANSEN", person.getFirstName());
	}
	
	@Test
	public void testGetStreet() {
		assertEquals("TIMOTEIVN", person.getStreet());
	}
	
	@Test
	public void testGetCity() {
		assertEquals("SANDNES", person.getCity());
	}
	
	@Test
	public void testGetOrders() {
				
		String orderID = "2";
		String orderNo = "1340";
		String personID = "15";
		
		Order order = new Order(orderID, orderNo, personID);
		
		person.addOrder(order);
		person.addOrder(order);
		person.addOrder(order);
		
		assertEquals(3, person.getOrders().size());
		
		assertEquals(orderID, person.getOrders().get(0).getOrderID());
		assertEquals(orderNo, person.getOrders().get(0).getOrderNo());
		assertEquals(personID, person.getOrders().get(0).getPersonId());
	}

	@Test
	public void testGetOrdersSize() {
		String orderID = "2";
		String orderNo = "1340";
		String personID = "15";
		
		Order order = new Order(orderID, orderNo, personID);
		
		person.addOrder(order);
		
		assertEquals(1, person.getOrdersSize());
		
	}

}
