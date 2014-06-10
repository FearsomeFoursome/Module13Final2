/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

/**
 * This class contains data about products.
 *
 * @author Amy Roberts
 */
public class Product
{   
    private int productID;
    private int categoryID;
    private String productName;
    private String productDesc;
    private float productPrice;

	 // Constructors

	/**
	 * A constructor which creates an empty object with default values.
	 * <p>
	 * Values can later be set using provided methods.
	 */
		 public Product()
	 {
		 productID = categoryID = 0;
		 productName = productDesc = "";
		 productPrice = 0;
	 } //end constructor
	 
	/**
	 * A constructor to create an object when all variables are known.
	 *
	 * @param ProdID The unique integer identifying this product.
	 * @param CategID An integer identifying the general category or type of this product.
	 * @param ProdName The name of this product.
	 * @param ProdDesc A description of this product.
	 * @param ProdPrice The price per unit of this product.
	 */
	public Product (int ProdID, int CategID, String ProdName, String ProdDesc, float ProdPrice)
	 {
        productID = ProdID;
        categoryID = CategID;
        productName = ProdName;
        productDesc = ProdDesc;
        productPrice = ProdPrice;
    } //end constructor
	 
	/**
	 * Retrieves the product ID.
	 * @return The unique integer identifying this product.
	 */
	public int getProductID()
	 {
		 return productID;
	 } //end getProductID
	 
	/**
	 * Sets or changes the product ID.
	 * @param prodID The unique integer identifying this product.
	 */
	public void setProductID(int prodID)
	 {
		 productID = prodID;
	 } //end setProductID
	 
	/**
	 * Retrieves the category ID.
	 * @return An integer identifying the general category or type of this product.
	 */
	public int getCategoryID()
	 {
		 return categoryID;
	 } //end getCategoryID
	 
	/**
	 * Sets or changes the category ID.
	 * @param catID An integer identifying the general category or type of this product.
	 */
	public void setCategoryID(int catID)
	 {
		 categoryID = catID;
	 } //end setCategoryID
	 
	/**
	 * Retrieves the product name.
	 * @return The name of the product.
	 */
	public String getProductName()
	 {
		 return productName;
	 } //end getProductName
	 
	/**
	 * Sets or changes the product name.
	 * @param prodname The product's name.
	 */
	public void setProductName(String prodname)
	 {
		 productName = prodname;
	 } //end setProductName
	 
	/**
	 * Retrieves the description of the product.
	 * @return The product's description.
	 */
	public String getProductDesc()
	 {
		 return productDesc;
	 } //end getProductDesc
	 
	/**
	 * Sets or changes the product's description.
	 * @param proddesc The description of the product.
	 */
	public void setProductDesc(String proddesc)
	 {
		 productDesc = proddesc;
	 } //end setProductDesc
	 
	/**
	 * Retrieves the unit price of the product.
	 * @return The floating-point price of this product per unit.
	 */
	public float getProductPrice()
	 {
		 return productPrice;
	 } //end getProductPrice
	 
	/**
	 * Sets or changes the unit price of the product.
	 * @param price The floating-point price of this product per unit.
	 */
	public void setProductPrice(float price)
	 {
		 productPrice = price;
	 } //end setProductPrice
	 
	 
} //end class
