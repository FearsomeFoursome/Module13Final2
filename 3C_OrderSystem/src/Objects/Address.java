/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

/**
 * This class contains physical address data.
 * <p>
 * It is formatted for American addresses.
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
	
	/**
	 * A constructor for empty Address objects.
	 * <p>
	 * Data can later be loaded using the provided methods.
	 */
	public Address()
	{
		addressID = 0;
		address1 = address2 = city = state = zip = "";
	} //end constructor
	
	/**
	 * A constructor usable when all data is known.
	 *
	 * @param ID The integer ID of this address in the database.
	 * @param add1 The first line of the address.
	 * @param add2 The second line of the address. Use an empty string if none.
	 * @param City The name of the city.
	 * @param State The name of the state.
	 * @param ZIP The ZIP code for this address.
	 */
	public Address(int ID, String add1, String add2, String City, String State, String ZIP)
	{
		addressID = ID;
		address1 = add1;
		address2 = add2;
		city = City;
		state = State;
		zip = ZIP;
	} //end constructor
	
	/**
	 * Retrieves the database ID of this address.
	 * @return The address ID in the database.
	 */
	public int getAddressID()
	{
		return addressID;
	} //end getAddressID
	
	/**
	 * Sets or changes the address ID.
	 * @param ID The address ID this address should have.
	 */
	public void setAddressID(int ID)
	{
		addressID = ID;
	} //end setAddressID
	
	/**
	 * Retrieves the first line of the address.
	 * @return A string containing the street address.
	 */
	public String getAddress1()
	{
		return address1;
	} //end getAddress1
	
	/**
	 * Sets or changes the first line of the address.
	 * @param add1 The street address.
	 */
	public void setAddress1(String add1)
	{
		address1 = add1;
	} //end setAddress1
	
	/**
	 * Retrieves the second line of the address.
	 * @return The second line of the address.
	 */
	public String getAddress2()
	{
		return address2;
	} //end getAddress2
	
	/**
	 * Sets or changes the second line of the address. Use an empty string if none exists.
	 * @param add2 The second line of the address.
	 */
	public void setAddress2(String add2)
	{
		address2 = add2;
	} //end setAddress2
	
	/**
	 * Retrieves the city of the address.
	 * @return The name of the city.
	 */
	public String getCity()
	{
		return city;
	} //end getCity
	
	/**
	 * Sets or changes the city.
	 * @param City The name of the city.
	 */
	public void setCity(String City)
	{
		city = City;
	} //end setCity
	
	/**
	 * Retrieves the name of the state.
	 * @return The name of the state.
	 */
	public String getState()
	{
		return state;
	} //end getState
	
	/**
	 * Sets or changes the state.
	 * @param State The name of the state.
	 */
	public void setState(String State)
	{
		state = State;
	} //end setState
	
	/**
	 * Retrieves the ZIP code.
	 * @return The ZIP code, as a string.
	 */
	public String getZIP()
	{
		return zip;
	} //end getZIP
	
	/**
	 * Sets or changes the ZIP code.
	 * @param ZIP The ZIP code, as a string.
	 */
	public void setZIP(String ZIP)
	{
		zip = ZIP;
	} //end setZIP
	
} //end class