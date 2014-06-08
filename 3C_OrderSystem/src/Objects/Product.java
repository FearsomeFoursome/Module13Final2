/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

/**
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
	 public Product()
	 {
		 productID = categoryID = 0;
		 productName = productDesc = "";
		 productPrice = 0;
	 } //end constructor
	 
    public Product (int ProdID, int CategID, String ProdName, String ProdDesc, float ProdPrice)
	 {
        productID = ProdID;
        categoryID = CategID;
        productName = ProdName;
        productDesc = ProdDesc;
        productPrice = ProdPrice;
    } //end constructor
	 
	 public int getProductID()
	 {
		 return productID;
	 } //end getProductID
	 
	 public void setProductID(int prodID)
	 {
		 productID = prodID;
	 } //end setProductID
	 
	 public int getCategoryID()
	 {
		 return categoryID;
	 } //end getCategoryID
	 
	 public void setCategoryID(int catID)
	 {
		 categoryID = catID;
	 } //end setCategoryID
	 
	 public String getProductName()
	 {
		 return productName;
	 } //end getProductName
	 
	 public void setProductName(String prodname)
	 {
		 productName = prodname;
	 } //end setProductName
	 
	 public String getProductDesc()
	 {
		 return productDesc;
	 } //end getProductDesc
	 
	 public void setProductDesc(String proddesc)
	 {
		 productDesc = proddesc;
	 } //end setProductDesc
	 
	 public float getProductPrice()
	 {
		 return productPrice;
	 } //end getProductPrice
	 
	 public void setProductPrice(float price)
	 {
		 productPrice = price;
	 } //end setProductPrice
	 
	 
} //end class
