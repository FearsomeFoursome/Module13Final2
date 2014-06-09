/*
 * 3's Company (Amy Roberts, Bella Belova, Scott Young)
 * "We pledge that we have complied with the AIC in this work."
 *
 * Class to load data into the tables
 * Initialize DB connections 
 * Drop all tables, Create all tables, Insert data into tables
 */
package Control;

import Databases.CustomerDB;
import Databases.OrderItemsDB;
import Databases.StockItemsDB;
import Databases.AddressDB;
import Databases.ProductDB;
import Databases.OrdersDB;

public class DataLoad {
    /**
     * function to get and initialize the connections.
     * @author Scott Young
     */
    public static void connect(){
        CommonConnection.getMSQLConn();
        CommonConnection.initialize_Connection_MYSQL();
        CommonConnection.getSQLConn();
        CommonConnection.initialize_Connection_SQL();
    }
    /**
     * function to drop all tables.
     * @author Scott Young
     */
    public static void dropAllTables() {
        try{
        AddressDB.droptable();
        CustomerDB.droptable();
        ProductDB.droptable();
        OrdersDB.droptable();        
        OrderItemsDB.droptable();
        StockItemsDB.droptable();
        }catch(Exception e){          
            System.err.println(e);            
        }
    }
    /**
     * function to create all tables.
     * @author Scott Young
     */
    public static void createAllTables() {
        try{
        AddressDB.createtable();
        CustomerDB.createtable();
        ProductDB.createtable();
        OrdersDB.createtable();        
        OrderItemsDB.createtable();
        StockItemsDB.createtable();
        }catch(Exception e){          
            System.err.println(e);            
        }        
    }
    /**
     * function to load row data into each table
     * @author Scott Young
     */
    public static void loadTableData() {
        // load Address row data
        try{
            AddressDB.createAddress("1455 Mother of Dragons Ln", "", "Dothraki Valley", "Westeros", "23543");
            AddressDB.createAddress("997 W. King Slayer Ave.", "", "Kings Landing", "Westeros", "23543");
            AddressDB.createAddress("45 N. Walker Way", "", "New America", "GA", "23543");
            AddressDB.createAddress("576 E. PDS St.", "", "Roarton", "Lancashire", "23543");
            AddressDB.createAddress("1810 S. BubbaGump Pl.", "", "Montgomery", "AL", "23543");
            AddressDB.createAddress("178 Island Circle", "", "The Island", "UK", "23543");
        }catch (Exception Address){              
            System.err.println(Address);
        }
        // load Customer row data
        try{
            CustomerDB.createCustomer("Daenarys", "Targaryen", 1, 1, "stormborn@dragon.com", "555-444-3333");
            CustomerDB.createCustomer("Tyrion", "Lanister", 2, 2, "imp@kingslanding.com", "555-666-7777");
            CustomerDB.createCustomer("Rick", "Grimes", 3, 3, "apocalypse@zombie.com", "555-888-9999");
            CustomerDB.createCustomer("Kieren", "Walker", 4, 4, "rotten@pds.com", "555-111-2222");
            CustomerDB.createCustomer("Forrest", "Gump", 5, 5, "run@alabama.edu", "555-222-5555");
            CustomerDB.createCustomer("Bear", "Grylls", 6, 6, "fdiscovery@g4.com", "555-555-0000");
        }catch (Exception Customer){              
            System.err.println(Customer);
        }
        // load Product row data
        try{
            ProductDB.createProduct(1001, 200, "Hat", "Team USA red, white, and blue hat", 12.99f);
            ProductDB.createProduct(1002, 300, "T-shirt", "Team USA red, white, and blue t-shirt", 12.99f);
            ProductDB.createProduct(1003, 300, "T-shirt, black", "Team USA black t-shirt", 12.99f);
            ProductDB.createProduct(1004, 400, "Key chain", "Team USA Key Chain", 2.95f);
            ProductDB.createProduct(1005, 500, "Jacket", "Team USA red, white, and blue jacket", 29.99f);
            ProductDB.createProduct(2001, 601, "Mug", "Team USA red, white, and blue mug", 9.99f);
        }catch (Exception Product){              
            System.err.println(Product);
        }
        // load Orders row data
        try{
            OrdersDB.createOrder(5001, 2001, "Amex", "06/02/2014", 9.99f);
            OrdersDB.createOrder(5002, 2002, "Visa", "06/04/2014", 14.99f);
            OrdersDB.createOrder(5003, 2003, "Master Card", "06/05/2014", 19.99f);
            OrdersDB.createOrder(5004, 2004, "Visa", "06/06/2014", 24.99f);
            OrdersDB.createOrder(5005, 2005, "Access Card", "06/07/2014", 29.99f);
            OrdersDB.createOrder(5006, 2006, "Discover", "06/08/2014", 34.99f);
        }catch (Exception Orders){              
            System.err.println(Orders);
        }
        // load Stock_Items row data
        try{
           StockItemsDB.createItems(1001, "Hat", 9971);
           StockItemsDB.createItems(1002, "T-shirt", 4805);
           StockItemsDB.createItems(1003, "T-shirt, black", 10000);
           StockItemsDB.createItems(1004, "Key chain", 0);
           StockItemsDB.createItems(1005, "Jacket", 10000);
           StockItemsDB.createItems(2001, "Mug", 10000);
        }catch (Exception Stock){              
            System.err.println(Stock);
        }
        // load Order_Items row data
        try{
            OrderItemsDB.createItems(1001, 5001, 1001, 2, 12.99f);
            OrderItemsDB.createItems(1002, 5002, 1002, 1, 12.99f);
            OrderItemsDB.createItems(1003, 5003, 1003, 2, 12.99f);
            OrderItemsDB.createItems(1003, 5004, 1003, 3, 12.99f);
            OrderItemsDB.createItems(1005, 5005, 1005, 5, 29.99f);
            OrderItemsDB.createItems(2001, 5006, 2001, 10, 9.99f);
        }catch (Exception OrdItems){              
            System.err.println(OrdItems);
        }
    
    }
}



/**
 *
 * @author Bella Belova
 */

/*
public class DataLoad {
    AddressDB address_data;
    CustomerDB customer_data;
    OrderItemsDB item_order_data;
    OrdersDB orders_data;
    ProductDB product_data;
    StockItemsDB stock_item_data;
    int max_index;
    int index;

    public DataLoad()
    {
        address_data = new AddressDB();
        customer_data = new CustomerDB();
        item_order_data = new OrderItemsDB();
        orders_data = new OrdersDB();
        product_data = new ProductDB();
        stock_item_data = new StockItemsDB();
    }
    
    public void load_data() throws AddressDB.TableException, OrderItemsDB.TableException, OrdersDB.TableException
    {
        /*
    Tables create:
    
    Address;
    Customer;
    Item;
    order;
    Product;
    Stock_Items;
            

// Customer Table
        try {
            customer_data.droptable();
            customer_data.createtable();
            customer_data.createCustomer("John", "Smith", 1, 2, "bb248141@yahoo.com", "(718)698-9852" );
            customer_data.createCustomer("Adam", "Sandler", 1, 2, "sy896325@hotmail.com", "(212)569-7896" );
            java.util.ArrayList results  = customer_data.getAllCustomers();
            max_index = results.size();
            System.out.println("Customer Table:\n");
            for(index = 0; index < max_index; index++)
            {
                System.out.println(results.get(index));
            }
        }catch (Exception Customer){                    // Renamed "e" to "Customer" for clearly define where the error is
            System.err.println(Customer);
        }
        
        
        try {
        // Address Table
            address_data.droptable();
            address_data.createtable();
            address_data.createAddress("45 Jewett St", "Address2", "Brooklyn", "NY", "10314");
            address_data.createAddress("7A Rockland Ave", "Address2", "New York", "NY", "10003");
            java.util.ArrayList results  = address_data.getAllAddresses();
            max_index = results.size();
            System.out.println("Address Table:\n");
            for(index = 0; index < max_index; index++)
            {
                System.out.println(results.get(index));
            }
        }catch (Exception Address){                    // Renamed "e" to "Address" for clearly define where the error is
            System.err.println(Address);
        }

                // Order Table
        try {
            orders_data.droptable();
            orders_data.createtable();
            orders_data.createOrder(77777, 10005, "1234567812348965", "12/27/2005", 59.99f);
            orders_data.createOrder(77712, 10012, "7896123645691025", "5/14/1998", 69.99f);
            java.util.ArrayList results  = orders_data.getAllOrders();
            max_index = results.size();
            System.out.println("Order Table:\n");
            for(index = 0; index < max_index; index++)
            {
                System.out.println(results.get(index));
            }
        }catch (Exception Orders){                    // Renamed "e" to "Order" for clearly define where the error is
            System.err.println(Orders);
        }

        // Item Table
        try {
            item_order_data.droptable();
            item_order_data.createtable();
            item_order_data.createItems(12, 77777, 7896, 2, 12.99f);
            item_order_data.createItems(78, 77712, 7589, 6, 10.99f);
            java.util.ArrayList results  = item_order_data.getAllItems();
            max_index = results.size();
            System.out.println("Items Table:\n");
            for(index = 0; index < max_index; index++)
            {
                System.out.println(results.get(index));
            }
        }catch (Exception Items){                    // Renamed "e" to "Order Items" for clearly define where the error is
            System.err.println(Items);
        }

        // Stock Items
        try {
            stock_item_data.droptable();
            stock_item_data.createtable();
            stock_item_data.createItems(1001, "Hat", 9971);
            stock_item_data.createItems(1002, "T-shirt", 9808);
            stock_item_data.createItems(1003, "T-shirt, black", 10000);
            stock_item_data.createItems(1005, "Jacket", 10000);
            stock_item_data.createItems(2001, "Mug", 10000);
            java.util.ArrayList results  = stock_item_data.getAllStocks();
            max_index = results.size();
            System.out.println("Stock Items Table:\n");
            for(index = 0; index < max_index; index++)
            {
                System.out.println(results.get(index));
            }
        }catch (Exception Stocks){                    // Renamed "e" to "Stock Items" for clearly define where the error is
            System.err.println(Stocks);
        }
 
        
        
        // Product
        try {
            product_data.droptable();
            product_data.createtable();
            product_data.createProduct(7896, 12, "hat", "red had", 1.25f);
            product_data.createProduct(7589, 78, "gloves", "white gloves", 5.99f);
            java.util.ArrayList results  = product_data.getAllProducts();
            max_index = results.size();
            System.out.println("Product Table:\n");
            for(index = 0; index < max_index; index++)
            {
                System.out.println(results.get(index));
            }
        }catch (Exception Products){                    // Renamed "e" to "Customer" for clearly define where the error is
            System.err.println(Products);
        }
}
}*/