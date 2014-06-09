/*
 * 3's Company (Amy Roberts, Bella Belova, Scott Young)
 * "We pledge that we have complied with the AIC in this work."
 *
 * PRODUCT database class
 * Drop C_PRODUCTS table, Create C_PRODUCTS table, Insert data into C_PRODUCTS table,
 * Queries for the C_PRODUCTS database
 */

package Databases;

import Control.*;
import Objects.Product;

/**
 * ProductDB class to drop table, create table, insert & query Product database.
 * @author Bella Belova
 */
public class ProductDB {
    // variable declarations
    static final String PRODUCT_TABLE_NAME = "C_PRODUCTS";
    private static java.sql.Connection mysqlConn;
    public static class TableException extends Exception{
        TableException(String s){
            super(s);
        }
    }
    
    public ProductDB()
    {
        mysqlConn = CommonConnection.getMSQLConn();
    }
    
    /**
     * function to Drop C_PRODUCTS table.
     * @throws Databases.ProductDB.TableException
     * @author Bella Belova
     */
    public static void droptable()throws TableException{
	mysqlConn = CommonConnection.getMSQLConn();
        String createString;    
        java.sql.Statement stmt;
        
        try{      
            createString = "drop table " + PRODUCT_TABLE_NAME + ";";
            stmt = mysqlConn.createStatement();
            stmt.executeUpdate(createString);
         } catch (java.sql.SQLException e) {
             if (!(e.getMessage().contains("Unknown")))
                System.err.println(e); 
            }
	 }
    
    /**
     * function to Create the C_PRODUCTS table.
     * @throws Databases.ProductDB.TableException 
     * @author Bella Belova
     */
    public static void createtable() throws TableException{    
        String createString;    
        java.sql.Statement stmt;

        try{
            createString =
            "create table " + PRODUCT_TABLE_NAME + " " + 
            "(PROD_ID integer NOT NULL, " + 
            "CATEGORY_ID integer NOT NULL, " +
            "PROD_NAME varchar(40) NOT NULL, " +
            "PROD_DESC varchar(40) NOT NULL, " +
            "PROD_PRICE decimal(12,2) NOT NULL, " + 
            "PRIMARY KEY (PROD_ID))";
            stmt = mysqlConn.createStatement();
            stmt.executeUpdate(createString);
        } catch (java.sql.SQLException e) {
            throw new TableException("Unable to create " + PRODUCT_TABLE_NAME + "\nDetail: " + e);
            }        
        }

    /**
     * function to Insert Product row data into C_PRODUCTS table.
     * @param Prod_ID identity code of the Product
     * @param Categ_ID category identity code of the Product
     * @param Prod_Name name of the Product
     * @param Prod_Desc description of the Product
     * @param Prod_Price price in U.S. dollars of the Product
     * @throws Databases.ProductDB.TableException 
     */
    public static void createProduct(int Prod_ID, int Categ_ID, String Prod_Name, 
                           String Prod_Desc, float Prod_Price) throws TableException{
        java.sql.Statement stmt;
        
        try{
          String createString = "insert into " + PRODUCT_TABLE_NAME + 
                  " (PROD_ID, CATEGORY_ID, PROD_NAME, PROD_DESC, PROD_PRICE ) VALUES(" + Prod_ID + ", " + 
                  Categ_ID + ", '" + Prod_Name + "', '" + Prod_Desc  + "', " + Prod_Price + " );" ;
          stmt = mysqlConn.createStatement();
          stmt.executeUpdate(createString);  
        } catch (java.sql.SQLException e) {
            throw new TableException("Unable to create a new Order in the Database." + "\nDetail: " + e);
        }
    }

    /***************************************************************************
     * DATABASE QUERY FUNCTIONS
    ***************************************************************************/    
    
    /**
     * query for all Products in the C_PRODUCTS database.
     * @return
     * @throws Databases.ProductDB.TableException
     * @throws Databases.ProductDB.TableException
     * @author Bella Belova
     */
    public static java.util.ArrayList getAllProducts()
            throws ProductDB.TableException, TableException{
        java.sql.Statement stmt;
        java.util.ArrayList results;
        java.sql.ResultSet rs;
        
        try{
          String createString = "select * from " + PRODUCT_TABLE_NAME + ";";                
          stmt = mysqlConn.createStatement();
          rs = stmt.executeQuery(createString);  
          results = new java.util.ArrayList();
			    while (rs.next() == true){
                results.add(new Objects.Product (rs.getInt("PROD_ID"), rs.getInt("CATEGORY_ID"), 
                    rs.getString("PROD_NAME"), rs.getString("PROD_DESC"), rs.getFloat("PROD_PRICE")));  
				}
        }catch (java.sql.SQLException e){
            throw new TableException("Unable to search Product Table." + "\nDetail: " + e);
            }
        return results;        
        }
        
    /**
    * Query to search for and return a single product from PRODUCT table.
    * @param prodID Product identification code
    * @return A Product Name from the PRODUCT table
    * @throws Databases.ProductDB.TableException
    * @author Scott Young
    */
    public static String searchforProductbyID(int prodID)
            throws TableException{
        java.sql.Statement stmt;
        String results = " ";
        java.sql.ResultSet rs = null;
        
        try{
          String createString = "select * from " + Databases.ProductDB.PRODUCT_TABLE_NAME + " where PROD_ID " + prodID + ";" ;                
          stmt = mysqlConn.createStatement();
          rs = stmt.executeQuery(createString);  
          rs.next();
                results = rs.getString("PROD_NAME");  
        }catch (java.sql.SQLException e){
            throw new TableException("Unable to search Product in 3C_PRODUCTS Table." + "\nDetail: " + e);
        } 
        return results;
    }
     
    /**
     * search PRODUCT database by prodID, return Product object.
     * @param prodID Product identification number
     * @return A Product Object with all column/row data
     * @throws Databases.ProductDB.TableException 
     * @author Scott Young
     */
    public static Product getProductbyID(int prodID)
            throws TableException{
        java.sql.Statement stmt;
        Product results;
        java.sql.ResultSet rs = null;
                
        try{
          String createString = "select * from " + Databases.ProductDB.PRODUCT_TABLE_NAME + " where PROD_ID " + prodID + ";" ;                
          stmt = mysqlConn.createStatement();
          rs = stmt.executeQuery(createString);  
          rs.next();
          results = new Objects.Product (rs.getInt("PROD_ID"), rs.getInt("CATEGORY_ID"), 
                        rs.getString("PROD_NAME"), rs.getString("PROD_DESC"), rs.getFloat("PROD_PRICE"));  
        }catch (java.sql.SQLException e){
            throw new TableException("Unable to create requested Product object." + "\nDetail: " + e);
            } 
        return results;
        }    
    
    /**
     * query to search Products database by PROD_ID.
     * @param prodID identity code of the Product
     * @return an Array List of Products
     * @throws Databases.ProductDB.TableException
     * @author Bella Belova
     */
     public static java.util.ArrayList searchProductsbyProductID(String prodID)
            throws TableException{
        java.sql.Statement stmt;
        java.util.ArrayList results = null;
        java.sql.ResultSet rs = null;
        
        try{
          String createString = "select * from " + Databases.ProductDB.PRODUCT_TABLE_NAME + " where PROD_ID like " + prodID + ";" ;                
          stmt = Databases.ProductDB.mysqlConn.createStatement();
          rs = stmt.executeQuery(createString);  
          results = new java.util.ArrayList();
            while (rs.next() == true)
                results.add(new Objects.Product (rs.getInt("PROD_ID"), rs.getInt("CATEGORY_ID"), 
                        rs.getString("PROD_NAME"), rs.getString("PROD_DESC"), rs.getFloat("PROD_PRICE")));  
        }catch (java.sql.SQLException e){
            throw new TableException("Unable to search Product ID in Product Table." + "\nDetail: " + e);
            }
        return results;
        }  

     /**
      * query to search all Products in the C_PRODUCTS database.
      * @return an Array List of all Products & Product row data
      * @throws Databases.ProductDB.TableException 
      * @author Bella Belova
      */
    public static java.util.ArrayList searchALLProducts()
            throws TableException{
        java.sql.Statement stmt;
        java.util.ArrayList results = null;
        java.sql.ResultSet rs = null;
        
        try{
          String createString = "select * from " + Databases.ProductDB.PRODUCT_TABLE_NAME + " ;" ;                
          stmt = Databases.ProductDB.mysqlConn.createStatement();
          rs = stmt.executeQuery(createString);  
          results = new java.util.ArrayList();
            while (rs.next() == true)
                results.add(new Objects.Product (rs.getInt("PROD_ID"), rs.getInt("CATEGORY_ID"), 
                        rs.getString("PROD_NAME"), rs.getString("PROD_DESC"), rs.getFloat("PROD_PRICE")));  
        }catch (java.sql.SQLException e){
            throw new TableException("Unable to search Product Table." + "\nDetail: " + e);
            }
        return results;        
        }

}
