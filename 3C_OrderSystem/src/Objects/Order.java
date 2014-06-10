/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import java.util.ArrayList;

/**
 * A class which contains data about orders.
 * @author Amy Roberts
 */
public class Order {
	
	private int orderID;
	private int customerID;
	private float orderTotal;
	private ArrayList<OrderItem> orderItemList;
	
	/**
	 * A constructor for a new, empty order when only the customer ID is known.
	 * <p>
	 * The order ID is temporarily set to 0.
	 * @param custID The integer identifier of the customer this order is for.
	 */
	public Order(int custID)
	{
		customerID = custID;
		orderItemList = new ArrayList();
		orderID = 0;
		orderTotal = 0;
	} //end constructor
	
	/**
	 * A constructor for an order being read from the database.
	 * <p>
	 * This constructor assumes that the order ID is known from the database.
	 * @param ordID The unique integer identifier of this order.
	 * @param custID The unique integer identifier of the customer this order belongs to.
	 */
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
	
	/**
	 * Changes the quantity of an item being ordered.
	 * @param prodID The product ID of the item to be updated,
	 * @param quant The new quantity being ordered.
	 */
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
	
	/**
	 * Calculates the total cost of this order.
	 * <p>
	 * This method uses the unit price and quantity data stored in the OrderItems
	 * in the array to calculate the total cost of the order.
	 * @return The floating-point total cost of this order.
	 */
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
