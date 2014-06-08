/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import java.util.ArrayList;

/**
 *
 * @author Amy Roberts
 */
public class Order {
	
	private int orderID;
	private int customerID;
	private float orderTotal;
	private ArrayList<OrderItem> orderItemList;
	
	public Order(int custID)
	{
		customerID = custID;
		orderItemList = new ArrayList();
		orderID = 0;
		orderTotal = 0;
	} //end constructor
	
	public Order(int ordID, int custID)
	{
		customerID = custID;
		orderItemList = new ArrayList();
		orderID = ordID;
		orderTotal = 0;
	} //end constructor
	
	/**
	 * Gets the order ID.
	 *
	 * @return The order ID of this object.
	 */
	public int getOrderID() 
	{
		return orderID;
	} //end getOrderID

	/**
	 * Gets the customer ID.
	 *
	 * @return The customer ID of this object.
	 */
	public int getCustomerID()
	{
		return customerID;
	} //end getCustomerID

	/**
	 * Gets the list of order items.
	 *
	 * @return ArrayList of OrderItems.
	 */
	public ArrayList getOrderItems() 
	{
		return orderItemList;
	} //end getOrderItem

	/**
	 * Adds an OrderItem to this order.
	 *
	 * @param item <code>OrderItem</code> to be added to the list of items.
	 */
	public void addOrderItem(OrderItem item)
	{
		orderItemList.add(item);
	} //end addOrderItem
	
	/**
	 * Removes an item from the list of items in this order.
	 *
	 * @param prodID The product ID of the item to be removed from the list of
	 * order items.
	 */
	public void removeOrderItem(int prodID)
	{
		//create temporary variables
		OrderItem temp;
		int test = 0;

		//iterate through list and compare product ID of each item against the 
		//parameter, then remove any items that match
		for (int x = 0; x < orderItemList.size(); x++) 
		{
			temp = orderItemList.get(x);
			test = temp.getProductID();
			if (test == prodID) 
			{
				orderItemList.remove(x);
				System.out.println("Item removed from order."); //to make debugging easier
			} //end if
		} //end for		
	} //end removeOrderItem
	
	public void changeQuantity(int prodID, int quant)
	{
		OrderItem temp;
		int test = 0;
		
		//iterate through list and compare product ID of each item against the parameter,
		//then update the quantity of the one that matches
		for (int x = 0; x < orderItemList.size(); x++)
		{
			temp = orderItemList.get(x);
			test = temp.getProductID();
			if (test == prodID)
			{
				temp.setProductQuant(quant);
			} //end if
		} //end for
			
	} //end changeQuantity
	
	public float calcOrderTotal()
	{
		OrderItem temp;
		float total = 0;
		
		//iterate through list and get quantity and unit price of each item
		//then multiply together and add to running total
		for (int x = 0; x < orderItemList.size(); x++)
		{
			temp = orderItemList.get(x);
			total = total + (temp.getProductQuant() * temp.getProductPrice());
		}
		
		return total;
	} //end calcOrderTotal
	
} //end class
