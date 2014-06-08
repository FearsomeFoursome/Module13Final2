/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Databases.CustomerDB;
import Databases.OrderItemsDB;
import Databases.StockItemsDB;
import Databases.AddressDB;
import Databases.ProductDB;
import Databases.OrdersDB;
/**
 *
 * @author Bella Belova
 */
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
            
  */
// Customer Table
        try {
            customer_data.reset();
            customer_data.createCustomer(10005, "John", "Smith", 1, 2, "bb248141@yahoo.com", "(718)698-9852" );
            customer_data.createCustomer(10012, "Adam", "Sandler", 1, 2, "sy896325@hotmail.com", "(212)569-7896" );
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
            address_data.reset();
            address_data.createAddress(1, 10005, "home", "45 Jewett St", "Address2", "Brooklyn", "NY", 10314);
            address_data.createAddress(2, 10012, "home","7A Rockland Ave", "Address2", "New York", "NY", 10003);
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
            orders_data.reset();
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
            item_order_data.reset();
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
            stock_item_data.reset();
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
            product_data.reset();
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
}