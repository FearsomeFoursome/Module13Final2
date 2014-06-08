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
public class StockItemsDB {
    
    public static final String STOCK_ITEMS_TABLE_NAME = "3C_STOCK_ITEMS";
    public static java.sql.Connection mysqlConn;
    CommonConnection mysql_access;
    public static class TableException extends Exception{
        TableException(String s){
            super(s);
        }
    }
    
    public StockItemsDB()
    {
        mysql_access = new CommonConnection();
        mysqlConn = Control.CommonConnection.getMSQLConn();
    }
    
    
    // Drop 3C_STOCK_ITEMS Table
    public static void reset()throws TableException{
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
    //Create the 3C_STOCK_ITEMS Table        
        try{
            createString =
            "create table " + STOCK_ITEMS_TABLE_NAME + " " + 
            "(PROD_ID integer NOT NULL, " +
            "PROD_NAME varchar(40) NULL, " +
            "STOCK_QTY integer NOT NULL, " +
            "PRIMARY KEY (PROD_ID))";
            stmt = mysqlConn.createStatement();
            stmt.executeUpdate(createString);
        } catch (java.sql.SQLException e) {
            throw new TableException("Unable to create " + STOCK_ITEMS_TABLE_NAME + "\nDetail: " + e);
        }
    }
        
                  //Insert Items data
    /*************************************************************************
    * needs work! discuss with Amy
    **************************************************************************/
    
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
        public java.util.ArrayList getAllStocks()
            throws StockItemsDB.TableException, TableException{
        int id; String fn; String ln;
        java.sql.Statement stmt;
        Object p = null;
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
         */
    public static int searchforStockQTY(int prodID)
            throws TableException{
        int id; String fn; String ln;
        java.sql.Statement stmt;
        Object p = null;
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
