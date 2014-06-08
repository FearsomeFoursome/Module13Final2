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
public class OrdersDB {
    
    public static final String ORDERS_TABLE_NAME = "3C_ORDERS";   
    public static java.sql.Connection sqlConn;
    CommonConnection sql_access;
    public static class TableException extends Exception{
        TableException(String s){
            super(s);
        }
    }
    
    public OrdersDB()
    {
        sql_access = new CommonConnection();
        sqlConn = Control.CommonConnection.getSQLConn();
    }
    
    // Drop Table
    
    public static void reset()throws TableException{
        String createString;    
        java.sql.Statement stmt;
        
        try{      
            createString = "drop table " + ORDERS_TABLE_NAME + ";";
            stmt = sqlConn.createStatement();
            stmt.executeUpdate(createString);
         } catch (java.sql.SQLException e) {
             if (!(e.getMessage().contains("Unknown")))
                System.err.println(e); 
        }
        
        try{
     //Create the 3C_ORDERS Table
            createString =
            "create table " + ORDERS_TABLE_NAME + " " + 
            "(ORDER_ID integer identity (1,1) NOT NULL, " +
            "CUSTOMER_ID integer NOT NULL, " +
            "FINANCIAL varchar(50) NULL, " +
            "ORDER_DATE varchar(10) NULL, " +
            "ORDER_TOTAL decimal(12,2) NOT NULL, " +
            "PRIMARY KEY (ORDER_ID))";
            stmt = sqlConn.createStatement();
            stmt.executeUpdate(createString);
        } catch (java.sql.SQLException e) {
            throw new TableException("Unable to create " + ORDERS_TABLE_NAME + "\nDetail: " + e);
        }        
    }

            //Insert Order data
    public static void createOrder(int Ord_ID, int Cust_ID, String Fin, String Ord_Date, float Ord_Total) 
        throws TableException{
    
    java.sql.Statement stmt;
        try{

          String createString = "SET IDENTITY_INSERT " + ORDERS_TABLE_NAME + " on insert into " + ORDERS_TABLE_NAME + 
                  " (ORDER_ID, CUSTOMER_ID, FINANCIAL, ORDER_DATE, ORDER_TOTAL ) VALUES(" + Ord_ID + ", "
                   + Cust_ID + ", '" + Fin + "', '" + Ord_Date  + "', " + Ord_Total +  " );" ;
          stmt = sqlConn.createStatement();
          stmt.executeUpdate(createString);  
        } catch (java.sql.SQLException e) {
            throw new TableException("Unable to create a new Order in the Database." + "\nDetail: " + e);
        }
    }
    
        public static java.util.ArrayList getAllOrders()
            throws OrdersDB.TableException, TableException{
        int id; String fn; String ln;
        java.sql.Statement stmt;
        Object p = null;
        java.util.ArrayList results = null;
        java.sql.ResultSet rs = null;
        /*
        try{
          String createString = "select * from " + ORDERS_TABLE_NAME + ";" ;                
          stmt = sqlConn.createStatement();
          rs = stmt.executeQuery(createString);  
          results = new java.util.ArrayList();
            while (rs.next() == true)
                results.add(new OrderSystem_Classes.Order (rs.getInt("ORDER_ID"), rs.getInt("CUSTOMER_ID"), 
                        rs.getString("FINANCIAL"), rs.getString("ORDER_DATE"), rs.getFloat("ORDER_TOTAL")));  
        }catch (java.sql.SQLException e){
            throw new TableException("Unable to search Order Database." + "\nDetail: " + e);
        }*/
        return results;
    }  
        
        // Query to search orders by the ORDER_ID
    public static java.util.ArrayList searchOrdersbyOrderID(int orderID)
            throws TableException{
        int id; String fn; String ln;
        java.sql.Statement stmt;
        Object p = null;
        java.util.ArrayList results = null;
        java.sql.ResultSet rs = null;
        /*
        try{
          String createString = "select * from " + Create_Tables.OrdersDB.ORDERS_TABLE_NAME + " where ORDER_ID like " + orderID + ";" ;                
          stmt = Create_Tables.OrdersDB.sqlConn.createStatement();
          rs = stmt.executeQuery(createString);  
          results = new java.util.ArrayList();
            while (rs.next() == true)
                results.add(new OrderSystem_Classes.Order (rs.getInt("ORDER_ID"), rs.getInt("CUSTOMER_ID"), 
                        rs.getString("FINANCIAL"), rs.getString("ORDER_DATE"), rs.getFloat("ORDER_TOTAL")));  
        }catch (java.sql.SQLException e){
            throw new TableException("Unable to search Order Database." + "\nDetail: " + e);
        } */
        return results;
    }

        
}
