/*
 * 3's Company (Amy Roberts, Bella Belova, Scott Young)
 * "We pledge that we have complied with the AIC in this work."
 *
 * ORDERS database class
 * Drop C_ORDERS table, Create C_ORDERS table, Insert data into C_ORDERS table,
 * Queries for the C_ORDERS database
 */

package Databases;

import Control.*;
import Objects.Order;
import Objects.OrderItem;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * OrdersDB class to drop table, create table, insert & query Orders database.
 * @author Bella Belova
 */
public class OrdersDB {
    
    static final String ORDERS_TABLE_NAME = "C_ORDERS";   
    private static java.sql.Connection sqlConn;
    public static class TableException extends Exception{
        TableException(String s){
            super(s);
        }
    }
    
    public OrdersDB()
    {
    sqlConn = CommonConnection.getSQLConn();
    }
    
    /**
     * function to Drop the C_ORDERS table.
     * @throws Databases.OrdersDB.TableException
     * @author Bella Belova
     */
    public static void droptable()throws TableException{
        sqlConn = CommonConnection.getSQLConn();
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
	}
    
    /**
     * function to Create the C_ORDERS table
     * @throws Databases.OrdersDB.TableException 
     * @author Bella Belova
     */
    public static void createtable() throws TableException{
        String createString;    
        java.sql.Statement stmt;
        try{
            createString =
            "create table " + ORDERS_TABLE_NAME + " " + 
            "(ORDER_ID integer identity (1,1) NOT NULL, " +
            "CUSTOMER_ID integer NOT NULL, " +
            "ORDER_DATE varchar(10) NULL, " +
            "ORDER_TOTAL decimal(12,2) NOT NULL, " +
            "PRIMARY KEY (ORDER_ID), " +
            "FOREIGN KEY (CUSTOMER_ID) REFERENCES C_CUSTOMERS (CUSTOMER_ID))";
            stmt = sqlConn.createStatement();
            stmt.executeUpdate(createString);
        } catch (java.sql.SQLException e) {
            throw new TableException("Unable to create " + ORDERS_TABLE_NAME + "\nDetail: " + e);
            }        
        }

    /**
     * function to Insert ORDER row data into the C_ORDERS database.
     * @param Cust_ID identity code of the Customer associated to the order
     * @param Ord_Date date when the order was placed
     * @param Ord_Total total in U.S. dollars of the Order
     * @throws Databases.OrdersDB.TableException 
     * @author Bella Belova
     */
    public static void createOrder(int Cust_ID, String Ord_Date, float Ord_Total) 
            throws TableException{
        java.sql.Statement stmt;
        
        try{
          String createString = "insert into " + ORDERS_TABLE_NAME + 
                  " (CUSTOMER_ID, ORDER_DATE, ORDER_TOTAL ) VALUES("
                   + Cust_ID + ", '"  + Ord_Date  + "', " + Ord_Total +  " );" ;
          stmt = sqlConn.createStatement();
          stmt.executeUpdate(createString);  
        } catch (java.sql.SQLException e) {
            throw new TableException("Unable to create a new Order in the Database." + "\nDetail: " + e);
            }
        }

	/**
	* Method for saving orders into the database.
	* <p>
	* This method writes both order and order item data, to both the Orders
	* table and the OrderItems table.
	* @param incord The Order object containing all data about the order to be saved.
	* @return The order ID, as an integer.
	* @throws Databases.OrdersDB.TableException 
	* @author Amy Roberts
	*/
	public static int placeOrder(Order incord) 
	throws TableException{
		java.sql.Statement stmt;
		java.sql.ResultSet rs;
		int orderid;
		
		//extract data from the order object
		Order ord = incord;
		ArrayList<OrderItem> itemlist = ord.getOrderItems();
		OrderItem item;

		try
		{
			String createString = "insert into " + ORDERS_TABLE_NAME + 
					" (CUSTOMER_ID, ORDER_DATE, ORDER_TOTAL ) VALUES("	+ ord.getCustomerID() + 
					", '"  + getDateTime() + "', " + ord.calcOrderTotal() +  " );";
			stmt = sqlConn.createStatement();
			
			//generated keys in this case is the order ID, which is an identity in the table
			stmt.executeUpdate(createString, stmt.RETURN_GENERATED_KEYS);
			rs = stmt.getGeneratedKeys();
			rs.next();
			orderid = rs.getInt(1);

			try
			{
				for(int x = 0; x < itemlist.size(); x++)
				{
					item = itemlist.get(x);
					OrderItemsDB.createItems(orderid, item.getProductID(), item.getProductQuant(), item.getProductPrice());
				} //end for
			} //end try
			catch (OrderItemsDB.TableException e)
			{
				System.err.println("Error writing order items: " + e);
			} //end catch			
			
			return orderid;

		} //end try
		catch (java.sql.SQLException e) 
		{
			throw new TableException("Unable to create a new Order in the Database." + "\nDetail: " + e);
		} //end catch
	} //end placeOrder
    
    
    /***************************************************************************
     * DATABASE QUERY FUNCTIONS
    ***************************************************************************/
    
    /**
     * query for all Orders in the C_ORDERS database.
     * @return an Array List of Orders
     * @throws Databases.OrdersDB.TableException
     * @throws Databases.OrdersDB.TableException 
     * @author Bella Belova
     */
    public static java.util.ArrayList getAllOrders()
            throws OrdersDB.TableException, TableException{
        java.sql.Statement stmt;
        java.util.ArrayList results = null;
        java.sql.ResultSet rs = null;
        
        try{
          String createString = "select * from " + ORDERS_TABLE_NAME + ";" ;                
          stmt = sqlConn.createStatement();
          rs = stmt.executeQuery(createString);  
          results = new java.util.ArrayList();
            while (rs.next() == true)
                results.add(new Objects.Order (rs.getInt("ORDER_ID"), rs.getInt("CUSTOMER_ID")));  
        }catch (java.sql.SQLException e){
            throw new TableException("Unable to search Order Database." + "\nDetail: " + e);
            }
        return results;
        }  
        
    /**
     * query for all Orders in the C_ORDERS database by ORDER_ID
     * @param orderID identity code of the order
     * @return an Array List of Orders
     * @throws Databases.OrdersDB.TableException
     * @author Bella Belova
     */
    public static java.util.ArrayList searchOrdersbyOrderID(int orderID)
            throws TableException{
        java.sql.Statement stmt;
        java.util.ArrayList results = null;
        java.sql.ResultSet rs = null;
        
        try{
          String createString = "select * from " + Databases.OrdersDB.ORDERS_TABLE_NAME + " where ORDER_ID like " + orderID + ";" ;                
          stmt = Databases.OrdersDB.sqlConn.createStatement();
          rs = stmt.executeQuery(createString);  
          results = new java.util.ArrayList();
            while (rs.next() == true)
                results.add(new Objects.Order (rs.getInt("ORDER_ID"), rs.getInt("CUSTOMER_ID")));  
        }catch (java.sql.SQLException e){
            throw new TableException("Unable to search Order Database." + "\nDetail: " + e);
            } 
        return results;
        }
    
	 private static String getDateTime()
	 {
		 DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		 Date date = new Date();
		 return dateFormat.format(date);
	 }
}
