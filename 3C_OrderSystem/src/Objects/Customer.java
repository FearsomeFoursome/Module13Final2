/*
 * 3's Company (Amy Roberts, Bella Belova, Scott Young)
 * "We pledge that we have complied with the AIC in this work."
 *
 * Customer object class to contain customer data.
 */
package Objects;

/**
 * Stores customer data.
 * @author Amy Roberts
 */
public class Customer 
{
	//variables
	private int custID;
	private String fname;
	private String lname;
	private Address billing;
	private Address shipping;
	private String email;
	private String phone;
	
	/**
	 * A blank constructor which sets all values at defaults.
	 */
	public Customer()
	{
		custID = 0;
		fname = lname = email = phone = "";
	} //end constructor
	
	/**
	 * A constructor to be used when reading customer data from the database.
	 * @param ID The unique integer identifier of the customer.
	 * @param fName The customer's first name.
	 * @param lName The customer's last name.
	 * @param eMail The customer's email address.
	 * @param phoneno The customer's phone number, as a string.
	 * @param bill The customer's billing address, as an Address object.
	 * @param ship The customer's shipping address, as an Address object.
	 */
	public Customer(int ID, String fName, String lName, String eMail, String phoneno, Address bill, Address ship)
	{
		custID = ID;
		fname = fName;
		lname = lName;
		email = eMail;
		phone = phoneno;
		billing = bill;
		shipping = ship;
	} //end constructor
	
	//methods

	/**
	 * Retrieves the customer ID.
	 * @return The unique integer identifier of the customer.
	 */
		public int getCustID()
	{
		return custID;
	} //end getCustID
	
	/**
	 * Sets or changes the customer ID.
	 * @param ID The unique integer identifier of the customer.
	 */
	public void setCustID(int ID)
	{
		custID = ID;
	} //end setCustID
	
	/**
	 * Retrieves the customer's full name.
	 * @return The customer's first and last name, as a single string.
	 */
	public String getCustName()
	{
		String name = fname + " " + lname;
		return name;
	} //end getCustName
	
	/**
	 * Sets or changes the customer's first name.
	 * @param fName The customer's first name.
	 */
	public void setCustFName(String fName)
	{
		fname = fName;
	} //end setCustFName
	
	/**
	 * Sets or changes the customer's last name.
	 * @param lName The customer's last name.
	 */
	public void setCustLName(String lName)
	{
		lname = lName;
	} //end setCustLName
	
	/**
	 * Retrieves the customer's email address.
	 * @return The customer's email address.
	 */
	public String getEmail()
	{
		return email;
	} //end getEmail
	
	/**
	 * Sets or changes the customer's email address.
	 * @param Email The customer's email address.
	 */
	public void setEmail(String Email)
	{
		email = Email;
	} //end setEmail
	
	/**
	 * Retrieves the customer's phone number.
	 * @return The customer's phone number, as a string.
	 */
	public String getPhone()
	{
		return phone;
	} //end getPhone
	
	/**
	 * Sets or changes the phone number.
	 * @param phoneno The customer's phone number, as a string.
	 */
	public void setPhone(String phoneno)
	{
		phone = phoneno;
	} //end setPhone
	
	/**
	 * Retrieves the customer's billing address.
	 * @return An Address object containing the customer's billing address.
	 */
	public Address getBilling()
	{
		return billing;
	} //end getBilling
	
	/**
	 * Sets or changes the customer's billing address.
	 * @param bill An Address object containing the customer's billing address.
	 */
	public void setBilling(Address bill)
	{
		billing = bill;
	} //end setBilling
	
	/**
	 * Retrieves the customer's shipping address.
	 * @return An Address object containing the customer's shipping address.
	 */
	public Address getShipping()
	{
		return shipping;
	} //end getShipping
	
	/**
	 * Sets or changes the customer's shipping address.
	 * @param ship An Address object containing the customer's shipping address.
	 */
	public void setShipping(Address ship)
	{
		shipping = ship;
	} //end setShipping
	 
} //end class