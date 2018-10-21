import java.io.Serializable;

/**
 * Object class for an individual customer of the Washer Company.
 * 
 * @author Stephen Thomas
 *
 */
public class Customer implements Serializable {
	private static final String MEMBER_STRING = "C";
	private String id;
	private String phoneNumber;
	private String name;

	/**
	 * Constructor for an individual customer.
	 * 
	 * @param name        name of the customer
	 * @param phoneNumber phone number of the customer
	 */
	public Customer(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		setId(MEMBER_STRING + (MemberIdServer.instance()).getId());
	}

	/**
	 * Getter for id
	 * 
	 * @return customer id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Getter for phone number
	 * 
	 * @return customer phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Getter for name
	 * 
	 * @return customer name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for id
	 * 
	 * @param id customer's new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Setter for phone number
	 * 
	 * @param phoneNumber customer's new phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Setter for name
	 * 
	 * @param name customer's new name
	 */
	public void setName(String name) {
		this.name = name;
	}

//	public boolean purchase(Washer washer) {
//		// TODO Auto-generated method stub
//		return false;
//	}

}
