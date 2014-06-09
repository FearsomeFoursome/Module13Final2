/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Databases;

import Control.*;
import Objects.Address;



/**
 *
 * @author Bella Belova
 */
public class AddressDB {
    
    static final String ADDRESS_TABLE_NAME = "C_ADDRESS";  
    private static java.sql.Connection sqlConn;
    public static class TableException extends Exception{
        TableException(String s){
            super(s);
        }
    }
    
    public AddressDB()
    {
        sqlConn = CommonConnection.getSQLConn();
                
    }
    
    // Drop Address Table 
    public static void droptable()throws TableException{
        sqlConn = CommonConnection.getSQLConn();
        String createString;    
        java.sql.Statement stmt;
        
        try{      
            createString = "drop table " + ADDRESS_TABLE_NAME + ";";
            stmt = sqlConn.createStatement();
            stmt.executeUpdate(createString);
         } catch (java.sql.SQLException e) {
             if (!(e.getMessage().contains("Unknown")))
                System.err.println(e); 
        }
    }
    
    //Create the Address Table    
    public static void createtable() throws TableException{
        String createString;    
        java.sql.Statement stmt;
        
        try{
            createString =
            "create table " + ADDRESS_TABLE_NAME + " " + 
            "(ADDRESS_ID integer identity (1,1) NOT NULL, " +
            "ADDRESS1 varchar(50) NOT NULL, " +
            "ADDRESS2 varchar(50) NULL, " +
            "CITY varchar(50) NOT NULL, " + 
            "STATE varchar(50) NOT NULL, " + 
            "ZIP varchar(10) NOT NULL, " +
            "PRIMARY KEY (ADDRESS_ID)) ";
            stmt = sqlConn.createStatement();
            stmt.executeUpdate(createString);
        } catch (java.sql.SQLException e) {
            throw new TableException("Unable to create " + ADDRESS_TABLE_NAME + "\nDetail: " + e);
        }        
    }

    //Insert AddressDB data
    public static void createAddress(String Addr1, String Addr2, 
                                        String Addr_City, String Addr_State, String Addr_Zip) 
        throws TableException{
    
    java.sql.Statement stmt;
        try{

          String createString = "INSERT " + ADDRESS_TABLE_NAME + 
                  " (ADDRESS1, ADDRESS2, "
                  + "CITY, STATE, ZIP) VALUES('" + Addr1 + "', '" + Addr2 + "', '" + 
                  Addr_City + "', '" + Addr_State + "', " + Addr_Zip + ");" ;
          stmt = sqlConn.createStatement();
          stmt.executeUpdate(createString);  
        } catch (java.sql.SQLException e) {
            throw new TableException("Unable to create a new Address in the Database." + "\nDetail: " + e);
        }
    }

    /**
     * query to retrieve an Address Object by AddressID.
     * @param addID Address identifier
     * @return a single Address Object
     * @throws Databases.AddressDB.TableException 
     */
    public static Address getAddressbyID(int addID)
            throws TableException{
        java.sql.Statement stmt;
        Address results;
        java.sql.ResultSet rs = null;
        
        try{
          String createString = "select * from " + Databases.AddressDB.ADDRESS_TABLE_NAME + " where ADDRESS_ID='" + addID + "';" ;                
          stmt = sqlConn.createStatement();
          rs = stmt.executeQuery(createString);  
          rs.next();
          results = new Objects.Address (rs.getInt("ADDRESS_ID"), rs.getString("ADDRESS1"), 
                        rs.getString("ADDRESS2"), rs.getString("CITY"), rs.getString("STATE"),
                        rs.getString("ZIP"));  
        }catch (java.sql.SQLException e){
            throw new TableException("Unable to retrieve requested Address object." + "\nDetail: " + e);
        } 
        return results;
    }      
    
    public static java.util.ArrayList getAllAddresses()
            throws TableException{
        int id; String fn; String ln;
        java.sql.Statement stmt;
        Object p = null;
        java.util.ArrayList results = null;
        java.sql.ResultSet rs = null;
        
        try{
          String createString = "select * from " + ADDRESS_TABLE_NAME + ";" ;
          stmt = sqlConn.createStatement();
          rs = stmt.executeQuery(createString);  
          results = new java.util.ArrayList();
            while (rs.next() == true)
                results.add(new Objects.Address (rs.getInt("ADDRESS_ID"), rs.getString("ADDRESS1"), 
                        rs.getString("ADDRESS2"), rs.getString("CITY"), rs.getString("STATE"), 
                        rs.getString("ZIP")));  
        }catch (java.sql.SQLException e){
            throw new TableException("Unable to search Address Table." + "\nDetail: " + e);
        }
        return results;
    }

     // Query to search for addresses by ADDRESS_ID
    public static java.util.ArrayList searchAddbyCustomerID(int addID)
            throws TableException{
        int id; String fn; String ln;
        java.sql.Statement stmt;
        Object p = null;
        java.util.ArrayList results = null;
        java.sql.ResultSet rs = null;
        
        try{
          String createString = "select * from " + Databases.AddressDB.ADDRESS_TABLE_NAME + " where ADDRESS_ID like " + addID + ";" ;                
          stmt = Databases.AddressDB.sqlConn.createStatement();
          rs = stmt.executeQuery(createString);  
          results = new java.util.ArrayList();
            while (rs.next() == true)
                results.add(new Objects.Address (rs.getInt("ADDRESS_ID"), rs.getString("ADDRESS1"), rs.getString("ADDRESS2"), 
                        rs.getString("CITY"), rs.getString("STATE"), rs.getString("ZIP")));  
        }catch (java.sql.SQLException e){
            throw new TableException("Unable to search Address Table." + "\nDetail: " + e);
        }
        return results;
    }

    
    
    
    
}
