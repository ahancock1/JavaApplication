package project;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class OrderTest {

	private Order order;
	
	private String orderID = "11";
	private String orderNo = "2001";
	private String personID = "2";
	
	@Before
	public void setUp() throws Exception {
		order = new Order(orderID, orderNo, personID);
	}

	@Test
	public void testGetOrderID() {
		assertEquals(orderID, order.getOrderID());
	}

	@Test
	public void testGetOrderNo() {
		assertEquals(orderNo, order.getOrderNo());
	}

	@Test
	public void testGetPersonId() {
		assertEquals(personID, order.getPersonId());
	}

}
