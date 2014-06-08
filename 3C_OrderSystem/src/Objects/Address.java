/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

/**
 *
 * @author Amy Roberts
 */
public class Address
{
	private int addressID;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zip;
	
	public Address()
	{
		addressID = 0;
		address1 = address2 = city = state = zip = "";
	} //end constructor
	
	public Address(int ID, String add1, String add2, String City, String State, String ZIP)
	{
		addressID = ID;
		address1 = add1;
		address2 = add2;
		city = City;
		state = State;
		zip = ZIP;
	} //end constructor
	
	public int getAddressID()
	{
		return addressID;
	} //end getAddressID
	
	public void setAddressID(int ID)
	{
		addressID = ID;
	} //end setAddressID
	
	public String getAddress1()
	{
		return address1;
	} //end getAddress1
	
	public void setAddress1(String add1)
	{
		address1 = add1;
	} //end setAddress1
	
	public String getAddress2()
	{
		return address2;
	} //end getAddress2
	
	public void setAddress2(String add2)
	{
		address2 = add2;
	} //end setAddress2
	
	public String getCity()
	{
		return city;
	} //end getCity
	
	public void setCity(String City)
	{
		city = City;
	} //end setCity
	
	public String getState()
	{
		return state;
	} //end getState
	
	public void setState(String State)
	{
		state = State;
	} //end setState
	
	public String getZIP()
	{
		return zip;
	} //end getZIP
	
	public void setZIP(String ZIP)
	{
		zip = ZIP;
	} //end setZIP
	
} //end class