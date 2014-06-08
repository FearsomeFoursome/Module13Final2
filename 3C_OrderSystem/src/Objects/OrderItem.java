/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

/**
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
    
	public OrderItem()
	{
		orderItemID = orderID = productID = itemQuantity = 0;
		unitPrice = 0;
		productName = "";
	} //end constructor

	public OrderItem(int prodID, int quant, float price, String prodname)
	{
		orderItemID = orderID = 0;
		productID = prodID;
		itemQuantity = quant;
		unitPrice = price;
		productName = prodname;
	} //end constructor
	 

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
	
	public float getProductPrice()
	{
		return unitPrice;
	} //end getProductPrice
	
	public void setProductPrice(float price)
	{
		unitPrice = price;
	} //end setProductPrice
	
	public String getProductName()
	{
		return productName;
	} //end getProductName
	
	public void setProductName(String name)
	{
		productName = name;
	} //setProductName
}
