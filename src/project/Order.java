package project;

/**
 * Class containing variables that represent an order.
 * @author Adam-PC
 *
 */
public class Order {

	private String orderID;
	private String orderNo;
	private String personID;
	
	/**
	 * Creates an order.
	 * @param orderID  String - Unique identifier of the order
	 * @param orderNo  String - Order number
	 * @param personID  String - Identity of the person the order belongs to
	 */
	public Order(String orderID, String orderNo, String personID) {
		this.orderID = orderID;
		this.orderNo = orderNo;
		this.personID = personID;
	}
	
	/**
	 * Returns the identity of the order.
	 * @return  String
	 */
	public String getOrderID() {
		return this.orderID;
	}
	
	/**
	 * Returns the order number.
	 * @return  String
	 */
	public String getOrderNo() {
		return this.orderNo;
	}
	
	/**
	 * Returns the identity of the person the order belongs to.
	 * @return  String
	 */
	public String getPersonId() {
		return this.personID;
	}
	
	/**
	 * Returns a string representing the contents of the order.
	 */
	public String toString() {
		return "Order ID: " + orderID + ", Order No: " + orderNo + ", Person ID: " + personID;
	}
}
