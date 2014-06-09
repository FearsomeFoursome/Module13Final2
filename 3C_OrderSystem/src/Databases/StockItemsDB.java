/*
 * 3's Company (Amy Roberts, Bella Belova, Scott Young)
 * "We pledge that we have complied with the AIC in this work."
 *
 * STOCK_ITEMS database class
 * Drop C_STOCK_ITEMS table, Create C_STOCK_ITEMS table, Insert data into C_STOCK_ITEMS table,
 * Queries for the C_STOCK_ITEMS database
 */

package Databases;

import Control.*;

/**
 * StockItemsDB class to drop table, create table, insert & query StockItems database.
 * @author Bella Belova
 */
public class StockItemsDB {
    
    static final String STOCK_ITEMS_TABLE_NAME = "C_STOCK_ITEMS";
    private static java.sql.Connection mysqlConn;
    public static class TableException extends Exception{
        TableException(String s){
            super(s);
        }
    }
    
    public StockItemsDB()
    {
    mysqlConn = CommonConnection.getMSQLConn();
    }
    
    /**
     * function to Drop the C_STOCK_ITEMS table.
     * @throws Databases.StockItemsDB.TableException
     * @author Bella Belova
     */
    public static void droptable()throws TableException{
        mysqlConn = CommonConnection.getMSQLConn();
        String createString;    
        java.sql.Statement stmt;
        
        try{      
            createString = "drop table " + STOCK_ITEMS_TABLE_NAME + ";";
            stmt = mysqlConn.createStatement();
            stmt.executeUpdate(createString);
        } catch (java.sql.SQLException e) {
             if (!(e.getMessage().contains("Unknown")))
                System.err.println(e); 
            }
	}
    
    /**
     * function to Create the C_STOCK_ITEMS table.
     * @throws Databases.StockItemsDB.TableException
     * @author Bella Belova
     */
    public static void createtable() throws TableException{		  
	String createString;    
        java.sql.Statement stmt;
        
        try{
            createString =
            "create table " + STOCK_ITEMS_TABLE_NAME + " " + 
            "(PROD_ID integer NOT NULL, " +
            "PROD_NAME varchar(40) NULL, " +
            "STOCK_QTY integer NOT NULL, " +
            "PRIMARY KEY (PROD_ID), " +
            "FOREIGN KEY (PROD_ID) REFERENCES C_PRODUCTS (PROD_ID))";
            stmt = mysqlConn.createStatement();
            stmt.executeUpdate(createString);
        } catch (java.sql.SQLException e) {
            throw new TableException("Unable to create " + STOCK_ITEMS_TABLE_NAME + "\nDetail: " + e);
            }
        }

    /***************************************************************************
     * DATABASE QUERY FUNCTIONS
    ***************************************************************************/     
   
    /**
     * function to Insert Items into the C_STOCK_ITEMS database.
     * @param Prod_ID identity code for the Product
     * @param Prod_Name name of the Product
     * @param Stock_QTY quantity in stock
     * @throws Databases.StockItemsDB.TableException
     * @author Bella Belova
     */
    public static void createItems(int Prod_ID, String Prod_Name, int Stock_QTY) 
            throws TableException{
        java.sql.Statement stmt;
        
        try{

          String createString = "insert into " + STOCK_ITEMS_TABLE_NAME + 
                  " (PROD_ID, PROD_NAME, STOCK_QTY ) VALUES(" + Prod_ID + ", '" + Prod_Name + "', " + Stock_QTY  + " );" ;
          stmt = mysqlConn.createStatement();
          stmt.executeUpdate(createString);  
        } catch (java.sql.SQLException e) {
            throw new TableException("Unable to create a new Order in the Database." + "\nDetail: " + e);
            }
        }
    
    /**
     * query for all Stock Items in the Product Inventory
     * @return an Array List of Products in stock
     * @throws Databases.StockItemsDB.TableException
     * @throws Databases.StockItemsDB.TableException 
     * @author Bella Belova
     */
    public java.util.ArrayList getAllStocks()
            throws StockItemsDB.TableException, TableException{
        java.sql.Statement stmt;
        java.util.ArrayList results = null;
        java.sql.ResultSet rs = null;
        
       /* try{
          String createString = "select * from " + STOCK_ITEMS_TABLE_NAME + ";" ;                
          stmt = mysqlConn.createStatement();
          rs = stmt.executeQuery(createString);  
          results = new java.util.ArrayList();
            while (rs.next() == true)
                results.add(new OrderSystem_Classes.Stock_Items (rs.getInt("PROD_ID"), rs.getString("PROD_NAME"), 
                        rs.getInt("STOCK_QTY")));  
        }catch (java.sql.SQLException e){
            throw new TableException("Unable to search Quantity in Stock_Items Table." + "\nDetail: " + e);
        } */
        return results;
    }       
        
        /**
         * Query to search Stock Items Database for current Stock Quantity by PROD_ID.
         * @param prodID The product identification number
         * @return Number of units in stock for item
         * @throws Create_Tables.StockItemsDB.TableException
         * @author Scott Young
         */
    public static int searchforStockQTY(int prodID)
            throws TableException{
        java.sql.Statement stmt;
        int results = 0;
        java.sql.ResultSet rs = null;
        
        
        try{
          String createString = "select * from " + Databases.StockItemsDB.STOCK_ITEMS_TABLE_NAME + " where PROD_ID " + prodID + ";" ;                
          stmt = Databases.StockItemsDB.mysqlConn.createStatement();
          rs = stmt.executeQuery(createString);  
          rs.next();
                results = rs.getInt("STOCK_QTY");  
        }catch (java.sql.SQLException e){
            throw new TableException("Unable to search Quantity in Stock_Items Table." + "\nDetail: " + e);
        } 
        return results;
    }
    
}
