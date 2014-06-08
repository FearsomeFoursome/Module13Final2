/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

/**
 *
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
	
	public Customer()
	{
		custID = 0;
		fname = lname = email = phone = "";
	} //end constructor
	
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
	public int getCustID()
	{
		return custID;
	} //end getCustID
	
	public void setCustID(int ID)
	{
		custID = ID;
	} //end setCustID
	
	public String getCustName()
	{
		String name = fname + " " + lname;
		return name;
	} //end getCustName
	
	public void setCustFName(String fName)
	{
		fname = fName;
	} //end setCustFName
	
	public void setCustLName(String lName)
	{
		lname = lName;
	} //end setCustLName
	
	public String getEmail()
	{
		return email;
	} //end getEmail
	
	public void setEmail(String Email)
	{
		email = Email;
	} //end setEmail
	
	public String getPhone()
	{
		return phone;
	} //end getPhone
	
	public void setPhone(String phoneno)
	{
		phone = phoneno;
	} //end setPhone
	
	public Address getBilling()
	{
		return billing;
	} //end getBilling
	
	public void setBilling(Address bill)
	{
		billing = bill;
	} //end setBilling
	
	public Address getShipping()
	{
		return shipping;
	} //end getShipping
	
	public void setShipping(Address ship)
	{
		shipping = ship;
	} //end setShipping
	 
} //end class