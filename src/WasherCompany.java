import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;

public class WasherCompany implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final int WASHER_NOT_FOUND = 1;
	public static final int OPERATION_FAILED = 2;
	public static final int OPERATION_COMPLETED = 3;
	private Inventory inventory;
	private CustomerList customerList;
	private WasherList washerList;
	private static WasherCompany washerCompany;

	private WasherCompany() {
		inventory = Inventory.instance();
		customerList = CustomerList.instance();
		washerList = WasherList.instance();
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static WasherCompany instance() {
		if (washerCompany == null) {
			MemberIdServer.instance();
			return (washerCompany = new WasherCompany());
		} else {
			return washerCompany;
		}
	}

	/**
	 * Organizes the operations for adding a customer
	 * 
	 * @param name
	 *            customer name
	 * @param phoneNumber
	 *            customer phone number
	 * @return the Customer object created
	 * 
	 */
	public Customer addCustomer(String name, String phoneNumber) {
		Customer customer = new Customer(name, phoneNumber);
		if (customerList.insertCustomer(customer)) {
			return (customer);
		}
		return null;
	}

	/**
	 * 
	 * @param brand
	 *            washer brand
	 * @param model
	 *            washer model
	 * @param quantity
	 *            quantity of washers
	 * @return the result of the operation
	 */
	public int addInventory(String brand, String model, int quantity) {
		Washer washer = inventory.search(brand, model);
		if (washer == null) {
			return (WASHER_NOT_FOUND);
		} else {
			Washer.addQuantity(quantity);
			return (OPERATION_COMPLETED);
		}

	}

	/**
	 * 
	 * @param brand
	 * @param model
	 * @param price
	 * @return
	 */
	public Washer addWasher(String brand, String model, double price) {
		Washer washer = new Washer(brand, model, price);
		if (inventory.insertWasher(washer)) {
			return (washer);
		}
		return null;
	}

	public void displayTotal() {

	}

	/**
	 * 
	 * @return
	 */
	public Iterator listCustomers() {
		if (customerList.getCustomerList() == null) {
			return (null);
		} else {
			return customerList.getCustomerList();
		}
	}

	/**
	 * 
	 * @return
	 */
	public Iterator listWashers() {
		if (washerList.getWasherList() == null) {
			return (null);
		} else {
			return washerList.getWasherList();
		}

	}

	/**
	 * 
	 * @param customerId
	 * @param brand
	 * @param model
	 * @param quantity
	 * @return
	 */
	public Washer purchaseWasher(String customerId, String brand, String model, int quantity) {

		Washer washer = inventory.search(brand, model);
		if (washer == null) {
			return (null);
		}
		Customer customer = customerList.search(customerId);
		if (customer == null) {
			return (null);
		}
		if (!(customer.purchase(washer))) {
			return null;
		}
		return (washer);

	}

	/**
	 * 
	 * @param customerId
	 * @return
	 */
	public Customer searchCustomer(String customerId) {
		return customerList.search(customerId);
	}

	/**
	 * 
	 * @return
	 */
	public static WasherCompany retrieve() {

		try {
			FileInputStream file = new FileInputStream("WasherCompanyData");
			ObjectInputStream input = new ObjectInputStream(file);
			washerCompany = (WasherCompany) input.readObject();
			MemberIdServer.retrieve(input);
			return washerCompany;

		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @return
	 */
	public static boolean save() {

		try {
			FileOutputStream file = new FileOutputStream("WasherCompanyData");
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(washerCompany);
			output.writeObject(MemberIdServer.instance());
			file.close();
			return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return inventory + "\n" + customerList + "\n" + washerList;

	}

}
