/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Databases;

import Control.CommonConnection;
import Objects.Product;


/**
 *
 * @author Bella Belova
 */
public class ProductDB {
    
    public static final String PRODUCT_TABLE_NAME = "C_PRODUCTS";
    public static java.sql.Connection mysqlConn;
    CommonConnection mysql_access;    
    public static class TableException extends Exception{
        TableException(String s){
            super(s);
        }
    }
    
    public ProductDB()
    {
        mysql_access = new CommonConnection();
        mysqlConn = Control.CommonConnection.getMSQLConn();
    }
    
   
    // Drop any existing 3C_PRODUCTS Table
    
    public static void reset()throws TableException{
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
        
        //Create the 3C_PRODUCTS Table
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

     //Insert new product into C_PRODUCTS database
    public static void createProduct(int Prod_ID, int Categ_ID, String Prod_Name, String Prod_Desc, float Prod_Price) 
        throws TableException{
    
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
    
        public static java.util.ArrayList getAllProducts()
            throws ProductDB.TableException, TableException{
        int id; String fn; String ln;
        java.sql.Statement stmt;
        Object p = null;
        java.util.ArrayList results = null;
        java.sql.ResultSet rs = null;
        
        try{
          String createString = "select * from " + PRODUCT_TABLE_NAME + " ;" ;                
          stmt = mysqlConn.createStatement();
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
        
        /**
         * Query to search for and return a single product from PRODUCT table.
         * @param prodID Product identification code
         * @return A Product Name from the PRODUCT table
         * @throws Databases.ProductDB.TableException 
         */
    public static String searchforProductbyID(int prodID)
            throws TableException{
        int id; String fn; String ln;
        java.sql.Statement stmt;
        Object p = null;
        String results = " ";
        java.sql.ResultSet rs = null;
        
        
        try{
          String createString = "select * from " + Databases.ProductDB.PRODUCT_TABLE_NAME + " where PROD_ID " + prodID + ";" ;                
          stmt = Databases.StockItemsDB.mysqlConn.createStatement();
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
     */
    public static Product getProductbyID(int prodID)
            throws TableException{
        int id; String fn; String ln;
        java.sql.Statement stmt;
        Object p = null;
        Product results;
        java.sql.ResultSet rs = null;
        
        
        try{
          String createString = "select * from " + Databases.ProductDB.PRODUCT_TABLE_NAME + " where PROD_ID " + prodID + ";" ;                
          stmt = Databases.StockItemsDB.mysqlConn.createStatement();
          rs = stmt.executeQuery(createString);  
          rs.next();
          results = new Objects.Product (rs.getInt("PROD_ID"), rs.getInt("CATEGORY_ID"), 
                        rs.getString("PROD_NAME"), rs.getString("PROD_DESC"), rs.getFloat("PROD_PRICE"));  
        }catch (java.sql.SQLException e){
            throw new TableException("Unable to create requested Product object." + "\nDetail: " + e);
        } 
        return results;
    }    
    
    

        // Query to search Products database by PROD_ID & return Array List of Products
     public static java.util.ArrayList searchProductsbyProductID(String prodID)
            throws TableException{
        int id; String fn; String ln;
        java.sql.Statement stmt;
        Object p = null;
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
    // Query for all Products in the C_PRODUCTS database    
    public static java.util.ArrayList searchALLProducts()
            throws TableException{
        int id; String fn; String ln;
        java.sql.Statement stmt;
        Object p = null;
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
