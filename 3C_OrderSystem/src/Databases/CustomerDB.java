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
public class CustomerDB {
    
    public static final String CUSTOMER_TABLE_NAME = "C_CUSTOMERS";
    public static java.sql.Connection sqlConn;
    CommonConnection sql_access;
    public static class TableException extends Exception{
        TableException(String s){
            super(s);
        }
    }
    
    public CustomerDB()
    {
        sql_access = new CommonConnection();
        sqlConn = Control.CommonConnection.getSQLConn();
    }
    // Drop Table
    
    public static void reset()throws TableException{
        String createString;    
        java.sql.Statement stmt;
        
        try{      
            createString = "drop table " + CUSTOMER_TABLE_NAME + ";";
            stmt = sqlConn.createStatement();
            stmt.executeUpdate(createString);
         } catch (java.sql.SQLException e) {
             if (!(e.getMessage().contains("Unknown")))
                System.err.println(e); 
        }
       
        try{
            //Create the CUSTOMER Table
            createString =
            "create table " + CUSTOMER_TABLE_NAME + " " + 
            "(CUSTOMER_ID integer identity (1,1) NOT NULL, " +
            "FIRST_NAME varchar(50) NOT NULL, " +
            "LAST_NAME varchar(50) NOT NULL, " +
            "BILL_ADDRESS integer NOT NULL, " + 
            "SHIP_ADDRESS integer NOT NULL, " + 
            "EMAIL varchar(50) NOT NULL, " + 
            "PHONE varchar(13) NULL, " + 
            "PRIMARY KEY (CUSTOMER_ID), " +
            "FOREIGN KEY (BILL_ADDRESS) REFERENCES C_ADDRESS (ADDRESS_ID), " + 
            "FOREIGN KEY (SHIP_ADDRESS) REFERENCES C_ADDRESS (ADDRESS_ID))";

            stmt = sqlConn.createStatement();
            stmt.executeUpdate(createString);
        } catch (java.sql.SQLException e) {
            throw new TableException("Unable to create " + CUSTOMER_TABLE_NAME + "\nDetail: " + e);
        }        
    }
    
/**
 * @author Bella Belova
 * @param Cust_ID A unique CustomerDB ID
 * @param FName CustomerDB First Name
 * @param LName CustomerDB Last Name
 * @param BillAddr An integer that except "0" or "1" for checked or unchecked Billing Address
 * @param ShipAddr An integer that except "0" or "1" for checked or unchecked Shipping Address
 * @param EMail CustomerDB EMail Address
 * @param PhNbr String field that will except parenthesis and numbers
 * @throws TableException This exception represents a problem with the access and updating of the DB table.
 */
    
    //Insert CustomerDB data
    public static void createCustomer(int Cust_ID, String FName, String LName, int BillAddr, 
                                        int ShipAddr, String EMail, String PhNbr) 
        throws TableException{
    
    java.sql.Statement stmt;
        try{

          String createString = "SET IDENTITY_INSERT " + CUSTOMER_TABLE_NAME + " on insert into " + CUSTOMER_TABLE_NAME + 
                  " (CUSTOMER_ID, FIRST_NAME, LAST_NAME, BILL_ADDRESS, SHIP_ADDRESS, "
                  + "EMAIL, PHONE) VALUES(" +
                    Cust_ID + ", '" + FName + "', '" + LName + "', " + BillAddr + ", " + 
                    ShipAddr + ", '" + EMail + "', '" + PhNbr + "');" ;
          stmt = sqlConn.createStatement();
          stmt.executeUpdate(createString);  
        } catch (java.sql.SQLException e) {
            throw new TableException("Unable to create a new Customer in the Database." + "\nDetail: " + e);
        }
    }

    // Query to return a customer object w/ address objects within by custID
    
    
    
    
    // Query to retrieve all customers from the Customer database
    /************************************************************************
    * update the code rs.get
    *************************************************************************/
    public static java.util.ArrayList getAllCustomers()
            throws AddressDB.TableException, TableException{
        int id; String fn; String ln;
        java.sql.Statement stmt;
        Object p = null;
        java.util.ArrayList results = null;
        java.sql.ResultSet rs = null;
        
        /*
        try{
          String createString = "select * from " + CUSTOMER_TABLE_NAME + ";" ;
          stmt = sqlConn.createStatement();
          rs = stmt.executeQuery(createString);  
          results = new java.util.ArrayList();
            while (rs.next() == true)
                results.add(new OrderSystem_Classes.Customer (rs.getInt("CUSTOMER_ID"), rs.getString("FIRST_NAME"), 
                        rs.getString("LAST_NAME"),rs.getString("EMAIL"), rs.getString("PHONE") rs.getAddress("BILL_ADDRESS"), 
                        rs.getAddress("SHIP_ADDRESS")));  
        }catch (java.sql.SQLException e){
            throw new TableException("Unable to search Customer Table." + "\nDetail: " + e);
        } */
        return results;
    }
    
    // Query to search for a Customer by their LAST_NAME
    public static java.util.ArrayList searchCustbyLastName(String lName)
            throws TableException{
        int id; String fn; String ln;
        java.sql.Statement stmt;
        Object p = null;
        java.util.ArrayList results = null;
        java.sql.ResultSet rs = null;
        /*
        try{
          String createString = "select * from " + Create_Tables.CustomerDB.CUSTOMER_TABLE_NAME + " where LAST_NAME like '%" + lName + "%';" ;                
          stmt = Create_Tables.CustomerDB.sqlConn.createStatement();
          rs = stmt.executeQuery(createString);  
          results = new java.util.ArrayList();
            while (rs.next() == true)
                results.add(new OrderSystem_Classes.Customer (rs.getInt("CUSTOMER_ID"), rs.getString("FIRST_NAME"), 
                        rs.getString("LAST_NAME"), rs.getString("EMAIL"), rs.getString("PHONE"), 
                        rs.getAddress("BILL_ADDRESS"), rs.getAddress("SHIP_ADDRESS")));  
        }catch (java.sql.SQLException e){
            throw new TableException("Unable to search Customer Table." + "\nDetail: " + e);
        } */
        return results;
    }
    
    
}
