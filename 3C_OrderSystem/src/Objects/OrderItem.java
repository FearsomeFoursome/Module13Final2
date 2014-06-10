/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

/**
 * This class contains data about order items.
 * <p>
 * The data contained includes the number of products ordered, and the unit
 * price at the time of the order. OrderItems are contained inside Orders.
 *
 * @author Amy Roberts
 */
public class OrderItem {
    
    private int orderItemID;
    private int orderID;
    private int productID;
    private int itemQuantity;
    private float unitPrice;
	 private String productName;
    
	/**
	 * A constructor to create empty objects.
	 * <p>
	 * This constructor sets all values to defaults. They can be changed later
	 * with the provided methods.
	 */
	public OrderItem()
	{
		orderItemID = orderID = productID = itemQuantity = 0;
		unitPrice = 0;
		productName = "";
	} //end constructor

	/**
	 * A constructor to create objects which are not yet in the database.
	 * @param prodID
	 * @param quant
	 * @param price
	 * @param prodname
	 */
	public OrderItem(int prodID, int quant, float price, String prodname)
	{
		orderItemID = orderID = 0;
		productID = prodID;
		itemQuantity = quant;
		unitPrice = price;
		productName = prodname;
	} //end constructor
	 
	/**
	 * A constructor to create objects with data from the database.
	 * @param orditemID The unique integer identifier of this item in the database.
	 * @param ordID The unique integer identifier of the order this item belongs to.
	 * @param prodID The unique integer identifier of the product contained in this item.
	 * @param quant The quantity of products in this order.
	 * @param price The unit price of the product in this order.
	 */
	public OrderItem(int orditemID, int ordID, int prodID, int quant, float price)

	 {
		 orderItemID = orditemID;
		 orderID = ordID;
		 productID = prodID;
		 itemQuantity = quant;
		 unitPrice = price;

		 //get product name from product ID
	 } //end constructor
	 
	 /**
	 * Gets the OrderItemID.
	 *
	 * @return The numerical <code>OrderItemID</code> of this object.
	 */
	public int getOrderItemID() 
	{
		return orderItemID;
	} //end getOrderItemID

	/**
	 * Sets the OrderItemID in case it needs to be changed or read from the
	 * database.
	 *
	 * @param ordItemID The <code>OrderItemID</code> this object should have.
	 */
	public void setOrderItemID(int ordItemID) 
	{
		orderItemID = ordItemID;
	} //end setOrderItemID

	/**
	 * Gets the OrderID.
	 *
	 * @return The <code>OrderID</code> of this object.
	 */
	public int getOrderID() 
	{
		return orderID;
	} //end getOrderID

	/**
	 * Sets the OrderID in case it needs to be changed.
	 *
	 * @param ordID The <code>OrderID</code> this object should have.
	 */
	public void setOrderID(int ordID) 
	{
		orderID = ordID;
	} //end setOrderID

	/**
	 * Gets the ProductID.
	 *
	 * @return The <code>ProductID</code> saved in this object.
	 */
	public int getProductID() 
	{
		return productID;
	} //end getProductID

	/**
	 * Sets or changes the ProductID.
	 *
	 * @param prodID The <code>ProductID</code> this object should have.
	 */
	public void setProductID(int prodID) 
	{
		productID = prodID;
	} //end setProductID

	/**
	 * Gets the ProductQuantity.
	 *
	 * @return The <code>ProductQuantity</code> saved in this object.
	 */
	public int getProductQuant() 
	{
		return itemQuantity;
	} //end getProductQuant
	
	/**
	 * Sets or changes the ProductQuantity.
	 *
	 * @param quant The <code>ProductQuantity</code> this object should have.
	 */
	public void setProductQuant(int quant)
	{
		itemQuantity = quant;
	} //end setProductQuant
	
	/**
	 * Retrieves the unit price of the product in this item.
	 * @return The floating-point price of the product.
	 */
	public float getProductPrice()
	{
		return unitPrice;
	} //end getProductPrice
	
	/**
	 * Sets or changes the unit price of the product in this object.
	 * @param price The floating-point price of this product.
	 */
	public void setProductPrice(float price)
	{
		unitPrice = price;
	} //end setProductPrice
	
	/**
	 * Retrieves the name of the product in this object.
	 * @return The product's name.
	 */
	public String getProductName()
	{
		return productName;
	} //end getProductName
	
	/**
	 * Sets or changes the product's name.
	 * @param name The name of the product in this object.
	 */
	public void setProductName(String name)
	{
		productName = name;
	} //setProductName
}
