/*
 * 3's Company (Amy Roberts, Bella Belova, Scott Young)
 * "We pledge that we have complied with the AIC in this work."
 *
 * ADDRESS database class
 * Drop C_ADDRESS table, Create C_ADDRESS table, Insert data into C_ADDRESS table,
 * Queries for the C_ADDRESS database
 */

package Databases;

import Control.*;
import Objects.Address;

/**
 * AddressDB class to drop table, create table, insert & query Address database.
 * @author Bella Belova
 */
public class AddressDB { 
    // declare AddressDB variables
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
    
    /**
     * function to Drop Address Table.
     * @throws Databases.AddressDB.TableException
     * @author Bella Belova
     */
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
    
    /**
     * function to Create the Address Table.
     * @throws Databases.AddressDB.TableException
     * @author Bella Belova
     */
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

    /**
     * function to Insert AddressDB data.
     * @param Addr1 address line 1 data
     * @param Addr2 address line 2 data
     * @param Addr_City city for the address provided
     * @param Addr_State state for the address provided
     * @param Addr_Zip zip code associated with the address provided
     * @throws Databases.AddressDB.TableException 
     * @author Bella Belova
     */
    public static void createAddress(String Addr1, String Addr2, 
                       String Addr_City, String Addr_State, String Addr_Zip) throws TableException{
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
     * query to retrieve an Address Object by ADDRESS_ID.
     * @param addID Address identifier
     * @return a single Address Object
     * @throws Databases.AddressDB.TableException
     * @author Scott Young
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
    
    /**
     * query to return all Address records from the Address database in an Array List.
     * @return Array List of Address records
     * @throws Databases.AddressDB.TableException
     * @author Bella Belova
     */
    public static java.util.ArrayList getAllAddresses()
            throws TableException{
        java.sql.Statement stmt;
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

    /**
     * Query to retrieve a single Address record from Address database by ADDRESS_ID.
     * @param addID Address identifier
     * @return An Address record with all row data from associated columns
     * @throws Databases.AddressDB.TableException
     * @author Bella Belova
     */
    public static java.util.ArrayList searchAddbyCustomerID(int addID)
            throws TableException{
        java.sql.Statement stmt;
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
