/*
 * 3's Company (Amy Roberts, Bella Belova, Scott Young)
 * "We pledge that we have complied with the AIC in this work."
 *
 * CUSTOMER database class
 * Drop C_CUSTOMER table, Create C_CUSTOMER table, Insert data into C_CUSTOMER table,
 * Queries for the C_CUSTOMER database
 */

package Databases;

import Control.CommonConnection;
import static Databases.AddressDB.getAddressbyID;
import Objects.Address;
import Objects.Customer;

/**
 * CustomerDB class to drop table, create table, insert & query Customer database.
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

    /**
     * function to Drop the C_CUSTOMERS table.
     * @throws Databases.CustomerDB.TableException
     * @author Bella Belova
     */
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
    
    /**
     * function to Create the C_CUSTOMERS table.
     * @throws Databases.CustomerDB.TableException
     * @author Bella Belova
     */
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
    * function to insert Customer row data into the C_CUSTOMER database.
    * @param FName First Name of the customer
    * @param LName Last Name of the customer
    * @param BillAddr An integer that references the ADDRESS_ID of the address
    * @param ShipAddr An integer that references the ADDRESS_ID of the address
    * @param EMail EMail Address of the customer
    * @param PhNbr Phone Number of the customer
    * @throws TableException This exception represents a problem with the access and updating of the DB table.
    * @author Bella Belova
     */
    public static void createCustomer(String FName, String LName, int BillAddr, 
                             int ShipAddr, String EMail, String PhNbr) throws TableException{    
        java.sql.Statement stmt;
            
        try{
            String createString = "INSERT INTO " + CUSTOMER_TABLE_NAME + 
                  " (FIRST_NAME, LAST_NAME, BILL_ADDRESS, SHIP_ADDRESS, "
                  + "EMAIL, PHONE) VALUES('" + FName + "', '" + LName + "', " 
                  + BillAddr + ", " + ShipAddr + ", '" + EMail + "', '" + PhNbr + "');" ;
            stmt = sqlConn.createStatement();
            stmt.executeUpdate(createString);  
        } catch (java.sql.SQLException e) {
            throw new TableException("Unable to create a new Customer in the Database." + "\nDetail: " + e);
            }
        } 

    /***************************************************************************
     * DATABASE QUERY FUNCTIONS
    ***************************************************************************/    
    
    /**
     * Query to retrieve one customer object with the 2 address objects by custID.
     * @param custID Customer identification number
     * @return a Customer object
     * @throws Databases.CustomerDB.TableException
     * @author Scott Young
     */
    public static Customer getCustomerbyID(int custID)
            throws TableException{
        java.sql.Statement stmt;
        Customer results;
        java.sql.ResultSet rs = null;
             
        try{
          String createString = "select * from " + Databases.CustomerDB.CUSTOMER_TABLE_NAME + " where CUSTOMER_ID " + custID + ";" ;                
          stmt = sqlConn.createStatement();
          rs = stmt.executeQuery(createString);  
          rs.next();
          int cID = rs.getInt("CUSTOMER_ID");
          String fName = rs.getString("FIRST_NAME");
          String lName = rs.getString("LAST_NAME");
          String eMail = rs.getString("EMAIL");
          String phone = rs.getString("PHONE");
          Address bill = getAddressbyID(rs.getInt("BILL_ADDRESS"));
          Address ship = getAddressbyID(rs.getInt("SHIP_ADDRESS"));
          results = new Objects.Customer (cID, fName, lName, eMail, phone, bill, ship); 
        }catch (Exception e){
            throw new TableException("Unable to retrieve Customer object." + "\nDetail: " + e);
            } 
        return results;
        } 
}
    
    /************************************************************************
    * update the code rs.get
    *************************************************************************/
  /*  public static java.util.ArrayList getAllCustomers()
            throws AddressDB.TableException, TableException{
        java.sql.Statement stmt;
        java.util.ArrayList results = null;
        java.sql.ResultSet rs = null;
           
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
            } 
        return results;
        }
    
    // Query to search for a Customer by their LAST_NAME
    public static java.util.ArrayList searchCustbyLastName(String lName)
            throws TableException{
        java.sql.Statement stmt;
        java.util.ArrayList results = null;
        java.sql.ResultSet rs = null;
        
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
            } 
        return results;
        }
   */ 
    
