/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Databases;

import Control.CommonConnection;
import Objects.Address;
import Objects.Customer;
import java.util.Map;


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
        sqlConn = CommonConnection.getSQLConn();
    }
    // Drop Customer Table
    
    public static void droptable()throws TableException{
        sqlConn = CommonConnection.getSQLConn();
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
    }
        //Create the CUSTOMER Table
    public static void createtable()throws TableException{
        sqlConn = CommonConnection.getSQLConn();
        String createString;    
        java.sql.Statement stmt;        
      
        try{
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
 * @param FName CustomerDB First Name
 * @param LName CustomerDB Last Name
 * @param BillAddr An integer that except "0" or "1" for checked or unchecked Billing Address
 * @param ShipAddr An integer that except "0" or "1" for checked or unchecked Shipping Address
 * @param EMail CustomerDB EMail Address
 * @param PhNbr String field that will except parenthesis and numbers
 * @throws TableException This exception represents a problem with the access and updating of the DB table.
 */
    
    //Insert CustomerDB data
    public static void createCustomer(String FName, String LName, int BillAddr, 
                                        int ShipAddr, String EMail, String PhNbr) 
        throws TableException{
    
    java.sql.Statement stmt;
        try{

          String createString = "INSERT INTO " + CUSTOMER_TABLE_NAME + 
                  " (FIRST_NAME, LAST_NAME, BILL_ADDRESS, SHIP_ADDRESS, "
                  + "EMAIL, PHONE) VALUES('" +
                    FName + "', '" + LName + "', " + BillAddr + ", " + 
                    ShipAddr + ", '" + EMail + "', '" + PhNbr + "');" ;
          stmt = sqlConn.createStatement();
          stmt.executeUpdate(createString);  
        } catch (java.sql.SQLException e) {
            throw new TableException("Unable to create a new Customer in the Database." + "\nDetail: " + e);
        }
    }

    // Query to return a customer object w/ address objects within by custID
    
    
    
    
    // Query to retrieve one customer object with the 2 address objects by custID
    public static Customer getCustomerbyID(int custID)
            throws TableException{
        int id; String fn; String ln;
        java.sql.Statement stmt;
        Object p = null;
        Customer results;
        java.sql.ResultSet rs = null;
             
        try{
          String createString = "select * from " + Databases.CustomerDB.CUSTOMER_TABLE_NAME + " where CUSTOMER_ID " + custID + ";" ;                
          stmt = sqlConn.createStatement();
          rs = stmt.executeQuery(createString);  
          rs.next();
          results = new Objects.Customer (rs.getInt("CUSTOMER_ID"), rs.getString("FIRST_NAME"), 
                        rs.getString("LAST_NAME"), rs.getString("EMAIL"), rs.getString("PHONE"),
                        rs.getString("BILL_ADDRESS"), rs.getString("SHIP_ADDRESS"));  
        }catch (java.sql.SQLException e){
            throw new TableException("Unable to retrieve Customer object." + "\nDetail: " + e);
        } 
        return results;
    }      
    
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
