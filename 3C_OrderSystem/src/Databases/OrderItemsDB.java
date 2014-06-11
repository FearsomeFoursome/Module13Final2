/*
 * 3's Company (Amy Roberts, Bella Belova, Scott Young)
 * "We pledge that we have complied with the AIC in this work."
 *
 * ORDER_ITEMS database class
 * Drop C_ITEMS table, Create C_ITEMS table, Insert data into C_ITEMS table,
 * Queries for the C_ITEMS database
 */

package Databases;

import Control.*;

/**
 * OrderItemsDB class to drop table, create table, insert & query OrderItems database.
 * @author Bella Belova
 */
public class OrderItemsDB {

	static final String ITEMS_TABLE_NAME = "C_ITEMS";   
	private static java.sql.Connection sqlConn;
	public static class TableException extends Exception{
		TableException(String s){
			super(s);
		}
	}

	public OrderItemsDB()
	{
		sqlConn = CommonConnection.getSQLConn();
	}

	/**
	 * function to Drop the C_ITEMS table.
	 * @throws Databases.OrderItemsDB.TableException
	 * @author Bella Belova
	 */
	public static void droptable()throws TableException{
		sqlConn = CommonConnection.getSQLConn();
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
	}

	/**
	 * function to Create the C_ITEMS table.
	 * @throws Databases.OrderItemsDB.TableException 
	 * @author Bella Belova
	 */
	public static void createtable() throws TableException{
		String createString;    
		java.sql.Statement stmt;

		try{
			createString =
						"create table " + ITEMS_TABLE_NAME + " " + 
						"(ORDER_ITEM_ID integer identity (1,1) NOT NULL, " +
						"ORDER_ID integer NOT NULL, " +
						"PRODUCT_ID integer NOT NULL, " +
						"QUANTITY integer NOT NULL, " +
						"PROD_PRICE decimal(12,2) NOT NULL, " +
						"PRIMARY KEY (ORDER_ITEM_ID), " +
						"FOREIGN KEY (ORDER_ID) REFERENCES C_ORDERS (ORDER_ID)) ";
			stmt = sqlConn.createStatement();
			stmt.executeUpdate(createString);
		} catch (java.sql.SQLException e) {
			throw new TableException("Unable to create " + ITEMS_TABLE_NAME + "\nDetail: " + e);
		}        
	}

	/**
	 * function to Insert Order Item row data into the C_ITEMS table.
	 * @param Ord_ID identification code of the order
	 * @param Prod_ID identification code of the product
	 * @param QTY quantity of the item in the order
	 * @param Prod_Price price of the product
	 * @throws Databases.OrderItemsDB.TableException
	 * @author Bella Belova
	 */
	public static void createItems(int Ord_ID, int Prod_ID, 
				int QTY, float Prod_Price) throws TableException{
		java.sql.Statement stmt;

		try{
			String createString = "insert into " + ITEMS_TABLE_NAME 
					  + " (ORDER_ID, PRODUCT_ID, QUANTITY, PROD_PRICE ) VALUES(" 
					  + Ord_ID + ", " + Prod_ID + ", " + QTY  + "," + Prod_Price + " );" ;
			stmt = sqlConn.createStatement();
			stmt.executeUpdate(createString);  
		} catch (java.sql.SQLException e) {
			throw new TableException("Unable to create a new OrderItem in the Database." + "\nDetail: " + e);
		}
	}

	/***************************************************************************
	 * DATABASE QUERY FUNCTIONS
	 ***************************************************************************/    

	/**
	 * query all items in the C_ITEMS database.
	 * @return an Array List of items
	 * @throws Databases.OrderItemsDB.TableException
	 * @throws Databases.OrderItemsDB.TableException 
	 * @author Bella Belova
	 */
	public static java.util.ArrayList getAllItems()
				throws OrderItemsDB.TableException, TableException{
		java.sql.Statement stmt;
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

	/**
	 * query an item in the C_ITEMS database by ITEM_ID.
	 * @param itemID the itemID to search by
	 * @return an Array List of items to match the ITEM_ID
	 * @throws Databases.OrderItemsDB.TableException
	 * @author Bella Belova
	 */
	public static java.util.ArrayList searchItemsbyItemID(int itemID)
				throws TableException{
		java.sql.Statement stmt;
		java.util.ArrayList results = null;
		java.sql.ResultSet rs = null;

		try{
			String createString = "select * from " + Databases.OrderItemsDB.ITEMS_TABLE_NAME 
					  + " where ORDER_ITEM_ID like " + itemID + ";" ;                
			stmt = Databases.OrderItemsDB.sqlConn.createStatement();
			rs = stmt.executeQuery(createString);  
			results = new java.util.ArrayList();
			while (rs.next() == true)
			results.add(new Objects.OrderItem (rs.getInt("ORDER_ITEM_ID"), 
					  rs.getInt("ORDER_ID"), rs.getInt("PRODUCT_ID"), rs.getInt("QUANTITY"), 
					  rs.getFloat("PROD_PRICE")));  
		}catch (java.sql.SQLException e){
			throw new TableException("Unable to search Item Database." + "\nDetail: " + e);
		}
		return results;
	}

}





