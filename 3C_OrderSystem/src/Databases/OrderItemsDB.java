/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Databases;

import Control.CommonConnection;

/**
 *
 * @author Bella Belova
 */
public class OrderItemsDB {
    
    public static final String ITEMS_TABLE_NAME = "3C_ITEMS";   
    public static java.sql.Connection sqlConn;
    CommonConnection sql_access;
    public static class TableException extends Exception{
        TableException(String s){
            super(s);
        }
    }
    
    public OrderItemsDB()
    {
        sql_access = new CommonConnection();
        sqlConn = Control.CommonConnection.getSQLConn();
    }
    
    // Drop Table
    
    public static void reset()throws TableException{
        String createString;    
        java.sql.Statement stmt;
        
        try{      
            createString = "drop table " + ITEMS_TABLE_NAME + ";";
            stmt = sqlConn.createStatement();
            stmt.executeUpdate(createString);
         } catch (java.sql.SQLException e) {
             if (!(e.getMessage().contains("Unknown")))
                System.err.println(e); 
        }
        
        try{
            //Create the OrderItemsDB Table
            createString =
            "create table " + ITEMS_TABLE_NAME + " " + 
            "(ORDER_ITEM_ID integer identity (1,1) NOT NULL, " +
            "ORDER_ID integer NOT NULL, " +
            "PRODUCT_ID integer NOT NULL, " +
            "QUANTITY integer NOT NULL, " +
            "PROD_PRICE decimal(12,2) NOT NULL, " +
            "PRIMARY KEY (ORDER_ITEM_ID), " +
            "FOREIGN KEY (ORDER_ID) REFERENCES 3C_ORDERS (ORDER_ID)) ";
            stmt = sqlConn.createStatement();
            stmt.executeUpdate(createString);
        } catch (java.sql.SQLException e) {
            throw new TableException("Unable to create " + ITEMS_TABLE_NAME + "\nDetail: " + e);
        }        
    }

    
        //Insert OrderItemsDB data
    public static void createItems(int Ord_Item_ID, int Ord_ID, int Prod_ID, int QTY, float Prod_Price) 
        throws TableException{
    
    java.sql.Statement stmt;
        try{

          String createString = "SET IDENTITY_INSERT " + ITEMS_TABLE_NAME + " on insert into " + ITEMS_TABLE_NAME + 
                  " (ORDER_ITEM_ID, ORDER_ID, PRODUCT_ID, QUANTITY, PROD_PRICE ) VALUES(" + Ord_Item_ID + ", "
                   + Ord_ID + ", " + Prod_ID + ", " + QTY  + "," + Prod_Price + " );" ;
          stmt = sqlConn.createStatement();
          stmt.executeUpdate(createString);  
        } catch (java.sql.SQLException e) {
            throw new TableException("Unable to create a new Address in the Database." + "\nDetail: " + e);
        }
    }
    
        public static java.util.ArrayList getAllItems()
            throws OrderItemsDB.TableException, TableException{
        int id; String fn; String ln;
        java.sql.Statement stmt;
        Object p = null;
        java.util.ArrayList results = null;
        java.sql.ResultSet rs = null;
        
        try{
          String createString = "select * from " + ITEMS_TABLE_NAME + ";" ;                
          stmt = sqlConn.createStatement();
          rs = stmt.executeQuery(createString);  
          results = new java.util.ArrayList();
            while (rs.next() == true)
                results.add(new Objects.OrderItem (rs.getInt("ORDER_ITEM_ID"), rs.getInt("ORDER_ID"), 
                        rs.getInt("PRODUCT_ID"), rs.getInt("QUANTITY"), rs.getFloat("PROD_PRICE")));  
        }catch (java.sql.SQLException e){
            throw new TableException("Unable to search Item Database." + "\nDetail: " + e);
        }
        return results;
    
        }

    // Query to search for Items by ITEM_ID
    public static java.util.ArrayList searchItemsbyItemID(int itemID)
            throws TableException{
        int id; String fn; String ln;
        java.sql.Statement stmt;
        Object p = null;
        java.util.ArrayList results = null;
        java.sql.ResultSet rs = null;
        
        try{
          String createString = "select * from " + Databases.OrderItemsDB.ITEMS_TABLE_NAME + " where ORDER_ITEM_ID like " + itemID + ";" ;                
          stmt = Databases.OrderItemsDB.sqlConn.createStatement();
          rs = stmt.executeQuery(createString);  
          results = new java.util.ArrayList();
            while (rs.next() == true)
                results.add(new Objects.OrderItem (rs.getInt("ORDER_ITEM_ID"), rs.getInt("ORDER_ID"), 
                        rs.getInt("PRODUCT_ID"), rs.getInt("QUANTITY"), rs.getFloat("PROD_PRICE")));  
        }catch (java.sql.SQLException e){
            throw new TableException("Unable to search Item Database." + "\nDetail: " + e);
        }
        return results;
    
        }
        
        
}


    
    
    
